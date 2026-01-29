package cybereats.fpmislata.com.tiendaback.domain.repository;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CartDto;

import java.util.Optional;

public interface CartRepository {
  Optional<CartDto> findByUserId(Long userId);

  CartDto save(Long userId, Long productId, int quantity);

  CartDto updateItemQuantity(Long userId, Long productId, int quantity);

  CartDto removeItem(Long userId, Long productId);

  void clearCart(Long userId);
}
