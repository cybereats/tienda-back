package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

import jakarta.validation.constraints.NotNull;

public record CategoryProductResponse(
        Long id,
        @NotNull String label,
        String slug) {
}
