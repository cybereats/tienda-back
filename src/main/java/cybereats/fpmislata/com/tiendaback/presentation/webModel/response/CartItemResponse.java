package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

import java.math.BigDecimal;

public record CartItemResponse(
    Long id,
    Long productId,
    CartProductResponse product,
    int quantity,
    BigDecimal price
) {}
