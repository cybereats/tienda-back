package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

import java.math.BigDecimal;

public record CartProductResponse(
    Long id,
    String label,
    BigDecimal price,
    String image) {
}
