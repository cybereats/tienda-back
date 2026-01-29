package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.repository.CartRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CartDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.CartJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.CartItemJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.ProductJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.UserJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CartJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CartItemJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ProductJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.repository.mapper.CartMapper;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

public class CartRepositoryImpl implements CartRepository {
  private final CartJpaDao cartJpaDao;
  private final CartItemJpaDao cartItemJpaDao;
  private final ProductJpaDao productJpaDao;
  private final UserJpaDao userJpaDao;

  public CartRepositoryImpl(CartJpaDao cartJpaDao, CartItemJpaDao cartItemJpaDao,
      ProductJpaDao productJpaDao, UserJpaDao userJpaDao) {
    this.cartJpaDao = cartJpaDao;
    this.cartItemJpaDao = cartItemJpaDao;
    this.productJpaDao = productJpaDao;
    this.userJpaDao = userJpaDao;
  }

  @Override
  public Optional<CartDto> findByUserId(Long userId) {
    return cartJpaDao.findByUserIdWithItems(userId)
        .map(CartMapper.getInstance()::fromCartJpaEntityToCartDto);
  }

  @Override
  @Transactional
  public CartDto save(Long userId, Long productId, int quantity) {
    CartJpaEntity cart = cartJpaDao.findByUserId(userId)
        .orElseGet(() -> {
          UserJpaEntity user = userJpaDao.findById(userId)
              .orElseThrow(() -> new ResourceNotFoundException("User not found"));
          CartJpaEntity newCart = new CartJpaEntity();
          newCart.setUser(user);
          newCart.setItems(new ArrayList<>());
          return cartJpaDao.insert(newCart);
        });

    ProductJpaEntity product = productJpaDao.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

    Optional<CartItemJpaEntity> existingItem = cartItemJpaDao.findByCartIdAndProductId(cart.getId(), productId);

    if (existingItem.isPresent()) {
      CartItemJpaEntity item = existingItem.get();
      item.setQuantity(item.getQuantity() + quantity);
      cartItemJpaDao.update(item);
    } else {
      CartItemJpaEntity newItem = new CartItemJpaEntity();
      newItem.setCart(cart);
      newItem.setProduct(product);
      newItem.setQuantity(quantity);
      newItem.setPrice(product.getPrice());
      cartItemJpaDao.insert(newItem);
    }

    return findByUserId(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
  }

  @Override
  @Transactional
  public CartDto updateItemQuantity(Long userId, Long productId, int quantity) {
    CartJpaEntity cart = cartJpaDao.findByUserId(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

    CartItemJpaEntity item = cartItemJpaDao.findByCartIdAndProductId(cart.getId(), productId)
        .orElseThrow(() -> new ResourceNotFoundException("Item not found in cart"));

    if (quantity <= 0) {
      cartItemJpaDao.deleteById(item.getId());
    } else {
      item.setQuantity(quantity);
      cartItemJpaDao.update(item);
    }

    return findByUserId(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
  }

  @Override
  @Transactional
  public CartDto removeItem(Long userId, Long productId) {
    CartJpaEntity cart = cartJpaDao.findByUserId(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

    CartItemJpaEntity item = cartItemJpaDao.findByCartIdAndProductId(cart.getId(), productId)
        .orElseThrow(() -> new ResourceNotFoundException("Item not found in cart"));

    cartItemJpaDao.deleteById(item.getId());

    return findByUserId(userId)
        .orElse(new CartDto(cart.getId(), userId, new ArrayList<>(), 0, BigDecimal.ZERO));
  }

  @Override
  @Transactional
  public void clearCart(Long userId) {
    CartJpaEntity cart = cartJpaDao.findByUserId(userId).orElse(null);
    if (cart != null) {
      cart.clearItems();
      cartJpaDao.update(cart);
    }
  }
}
