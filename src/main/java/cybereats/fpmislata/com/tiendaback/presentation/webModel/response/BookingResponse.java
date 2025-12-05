package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

public record BookingResponse(
                Long id,
                int hours,
                UserResponse user,
                PCResponse pc) {
}
