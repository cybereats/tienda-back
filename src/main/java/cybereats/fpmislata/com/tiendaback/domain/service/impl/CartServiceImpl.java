package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.DeliveryType;
import cybereats.fpmislata.com.tiendaback.domain.model.OrderStatus;
import cybereats.fpmislata.com.tiendaback.domain.repository.BookingRepository;
import cybereats.fpmislata.com.tiendaback.domain.repository.CartRepository;
import cybereats.fpmislata.com.tiendaback.domain.repository.UserOrderRepository;
import cybereats.fpmislata.com.tiendaback.domain.repository.UserRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.CartService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CartDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

public class CartServiceImpl implements CartService {
  private final CartRepository cartRepository;
  private final UserOrderRepository userOrderRepository;
  private final UserRepository userRepository;
  private final BookingRepository bookingRepository;

  public CartServiceImpl(CartRepository cartRepository, UserOrderRepository userOrderRepository,
      UserRepository userRepository, BookingRepository bookingRepository) {
    this.cartRepository = cartRepository;
    this.userOrderRepository = userOrderRepository;
    this.userRepository = userRepository;
    this.bookingRepository = bookingRepository;
  }

  @Override
  public CartDto getCart(Long userId) {
    return cartRepository.findByUserId(userId)
        .orElse(new CartDto(null, userId, new ArrayList<>(), 0, BigDecimal.ZERO));
  }

  @Override
  public CartDto addItem(Long userId, Long productId, int quantity) {
    return cartRepository.save(userId, productId, quantity);
  }

  @Override
  public CartDto updateItemQuantity(Long userId, Long productId, int quantity) {
    return cartRepository.updateItemQuantity(userId, productId, quantity);
  }

  @Override
  public CartDto removeItem(Long userId, Long productId) {
    return cartRepository.removeItem(userId, productId);
  }

  @Override
  public void clearCart(Long userId) {
    cartRepository.clearCart(userId);
  }

  @Override
  @Transactional
  public UserOrderDto checkout(Long userId, DeliveryType deliveryType) {
    // Solo verificar reserva activa si es entrega en mesa
    if (deliveryType == DeliveryType.TABLE) {
      boolean hasBooking = bookingRepository.hasActiveBooking(userId);

      if (!hasBooking) {
        throw new BusinessException(
            "Debes tener una reserva activa para entrega en mesa. Puedes elegir recogida en mostrador si no tienes reserva.");
      }
    }

    CartDto cart = getCart(userId);

    if (cart.items().isEmpty()) {
      throw new BusinessException("Cart is empty");
    }

    UserDto user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    java.util.List<OrderItemDto> orderItems = cart.items().stream()
        .map(cartItem -> new OrderItemDto(
            null,
            cartItem.product(),
            cartItem.quantity()))
        .collect(Collectors.toList());

    UserOrderDto orderDto = new UserOrderDto(
        null,
        user,
        orderItems,
        OrderStatus.PENDING,
        deliveryType,
        LocalDateTime.now());

    UserOrderDto createdOrder = userOrderRepository.save(orderDto);

    clearCart(userId);

    return createdOrder;
  }
}
