package cybereats.fpmislata.com.tiendaback.presentation.webModel.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CategoryPCRequest(
        Long id,
        @NotNull
        String label,
        BigDecimal price
) { }
