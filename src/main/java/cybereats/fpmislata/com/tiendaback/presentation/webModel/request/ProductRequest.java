package cybereats.fpmislata.com.tiendaback.presentation.webModel.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(
                Long id,
                @NotNull String label,
                @NotNull String slug,
                @NotNull String description,
                BigDecimal price,
                String image,
                @NotNull CategoryProductRequest category) {
}
