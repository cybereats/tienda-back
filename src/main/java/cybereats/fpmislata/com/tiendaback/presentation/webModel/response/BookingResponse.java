package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

import jakarta.validation.constraints.Min;

public record BookingResponse(
        Long id,
        int hours
) {
}
