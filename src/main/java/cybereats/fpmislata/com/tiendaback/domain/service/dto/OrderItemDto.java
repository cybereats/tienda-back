package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record OrderItemDto(
        Long id,
        @NotNull Product product,
        @NotNull @DecimalMin(value = "0.0", inclusive = true, message = "Quantity must be greater than or equal to 0") int quantity,
        BigDecimal price) {

}
