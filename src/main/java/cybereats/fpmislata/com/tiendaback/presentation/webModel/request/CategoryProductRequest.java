package cybereats.fpmislata.com.tiendaback.presentation.webModel.request;

import jakarta.validation.constraints.NotNull;

public record CategoryProductRequest(
        Long id,
        @NotNull String label,
        String slug) {

}
