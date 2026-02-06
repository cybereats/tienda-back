package cybereats.fpmislata.com.tiendaback.presentation.webModel.request;

import java.time.LocalDate;

public record TarjetaCreditoDto(
        Long id,
        String numeroTarjeta,
        LocalDate fechaCaducidad,
        int cvc,
        String nombreCompleto) {
}
