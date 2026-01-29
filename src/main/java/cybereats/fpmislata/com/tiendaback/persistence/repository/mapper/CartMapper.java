package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CartDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CartItemDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CartJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CartItemJpaEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CartMapper {
  private static final CartMapper instance = new CartMapper();

  private CartMapper() {
  }

  public static CartMapper getInstance() {
    return instance;
  }

  public CartDto fromCartJpaEntityToCartDto(CartJpaEntity entity) {
    if (entity == null) {
      return null;
    }

    List<CartItemDto> items = entity.getItems().stream()
        .map(this::fromCartItemJpaEntityToCartItemDto)
        .collect(Collectors.toList());

    int totalItems = items.stream().mapToInt(CartItemDto::quantity).sum();
    BigDecimal totalPrice = items.stream()
        .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    return new CartDto(
        entity.getId(),
        entity.getUser() != null ? entity.getUser().getId() : null,
        items,
        totalItems,
        totalPrice);
  }

  public CartItemDto fromCartItemJpaEntityToCartItemDto(CartItemJpaEntity entity) {
    if (entity == null) {
      return null;
    }

    return new CartItemDto(
        entity.getId(),
        entity.getProduct() != null ? entity.getProduct().getId() : null,
        entity.getProduct() != null ? ProductMapper.getInstance().fromProductJpaEntityToProductDto(entity.getProduct())
            : null,
        entity.getQuantity(),
        entity.getPrice());
  }
}
