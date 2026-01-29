package cybereats.fpmislata.com.tiendaback.domain.service;

import cybereats.fpmislata.com.tiendaback.domain.model.DeliveryType;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CartDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;

public interface CartService {
  CartDto getCart(Long userId);

  CartDto addItem(Long userId, Long productId, int quantity);

  CartDto updateItemQuantity(Long userId, Long productId, int quantity);

  CartDto removeItem(Long userId, Long productId);

  void clearCart(Long userId);

  UserOrderDto checkout(Long userId, DeliveryType deliveryType);
}
