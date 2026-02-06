package cybereats.fpmislata.com.tiendaback.microservices.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;

public record PagoTarjeta(
        Autorizacion autorizacion,
        TarjetaCreditoDto origen,
        DatosCuenta destino,
        Pago pago) {

    public record Autorizacion(String login, @JsonProperty("api_token") String apiToken) {
    }

    public record TarjetaCreditoDto(
            Long id,
            String numeroTarjeta,
            LocalDate fechaCaducidad,
            int cvc,
            String nombreCompleto) {
    }

    public record DatosCuenta(String iban) {
    }

    public record Pago(BigDecimal importe, String concepto) {
    }
}
