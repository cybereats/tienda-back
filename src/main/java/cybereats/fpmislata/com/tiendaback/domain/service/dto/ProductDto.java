package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record ProductDto(
                Long id,
                @NotNull String label,
                @NotNull @Pattern(regexp = "^[a-z0-9-]+$", message = "El slug debe ser en minúsculas y con guiones en lugar de espacios") String slug,
                @NotNull String description,
                BigDecimal price,
                @NotNull CategoryProductDto category) {
}
