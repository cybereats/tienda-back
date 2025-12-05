package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductDto(
        Long id,
        @NotNull
        String label,
        @NotNull
        String slug,
        @NotNull
        String desc,
        BigDecimal price,
        @NotNull
        CategoryProductDto category
) {
}
