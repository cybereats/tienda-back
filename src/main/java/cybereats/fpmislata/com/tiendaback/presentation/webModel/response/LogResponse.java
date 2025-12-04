package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record LogResponse(
        Long id,
        String info,
        String timestamp
) {
}
