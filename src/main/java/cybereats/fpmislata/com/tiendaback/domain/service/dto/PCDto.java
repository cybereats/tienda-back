package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import cybereats.fpmislata.com.tiendaback.domain.model.PCStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PCDto(
        Long id,
        @NotNull String label,
        @NotNull @Pattern(regexp = "^[a-z0-9-]+$", message = "El slug debe ser en minúsculas y con guiones en lugar de espacios") String slug,
        @NotNull int runtime,
        @NotNull String specs,
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "El formato debe ser YYYY-MM-DD") String workingSince,
        String image,
        PCStatus status,
        @NotNull CategoryPCDto categoryPCDto) {
}
