package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import jakarta.validation.constraints.Min;

public record BookingDto(
        Long id,
        @Min(1)
        int hours
) {
}
