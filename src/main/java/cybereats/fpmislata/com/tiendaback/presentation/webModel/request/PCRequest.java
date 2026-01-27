package cybereats.fpmislata.com.tiendaback.presentation.webModel.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PCRequest(
        Long id,
        @NotNull String label,
        @NotNull String slug,
        @NotNull int runtime,
        @NotNull String specs,
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "El formato debe ser YYYY-MM-DD") String workingSince,
        String image,
        String status,
        CategoryPCRequest categoryPCRequest) {
}
