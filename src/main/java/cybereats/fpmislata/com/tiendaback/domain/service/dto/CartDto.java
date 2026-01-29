package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import java.math.BigDecimal;
import java.util.List;

public record CartDto(
    Long id,
    Long userId,
    List<CartItemDto> items,
    int totalItems,
    BigDecimal totalPrice) {
}
