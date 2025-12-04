package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PCResponse(
        Long id,
        String label,
        String slug,
        int runtime,
        String specs,
        String working_since
) {
}
