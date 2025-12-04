package cybereats.fpmislata.com.tiendaback.presentation.webModel.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record LogRequest(
        Long id,
        @NotNull
        String info,
        @Pattern(
                regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}(?::\\d{2}(?:\\.\\d{1,9})?)?(Z|[+-]\\d{2}:\\d{2})$",
                message = "El timestamp debe ser ISO 8601. Ej: 2023-10-05T14:48Z, 2023-10-05T14:48:00Z o 2023-10-05T14:48:00.123+01:00"
        )
        String timestamp
) {
}
