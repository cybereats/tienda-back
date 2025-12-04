package cybereats.fpmislata.com.tiendaback.presentation.webModel.request;

import jakarta.validation.constraints.Min;

public record BookingRequest(
        Long id,
        @Min(1)
        int hours
) {
}
