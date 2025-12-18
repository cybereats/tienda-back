package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String label,
        String slug,
        String description,
        BigDecimal price,
        CategoryProductResponse category) {
}
