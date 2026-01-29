package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import java.math.BigDecimal;

public record CartItemDto(
    Long id,
    Long productId,
    ProductDto product,
    int quantity,
    BigDecimal price) {
}
