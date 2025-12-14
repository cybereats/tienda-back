package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CategoryProductDto(
        Long id,
        @NotNull String label,
        @NotNull @Pattern(regexp = "^[a-z0-9-]+$", message = "El slug debe ser en minúsculas y con guiones en lugar de espacios") String slug) {

}
