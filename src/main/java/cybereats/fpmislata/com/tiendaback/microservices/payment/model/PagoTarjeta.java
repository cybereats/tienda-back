package cybereats.fpmislata.com.tiendaback.microservices.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.Pago;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.TarjetaCreditoDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PagoTarjeta(
                Autorizacion autorizacion,
                TarjetaCreditoDto origen,
                Destino destino,
                Pago pago) {

        public record Autorizacion(String login, @JsonProperty("api_token") String apiToken) {
        }

        public record Destino(String iban) {
        }

}
