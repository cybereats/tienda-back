package cybereats.fpmislata.com.tiendaback.presentation.webModel.request;

public record PagoTarjetaRequest(
                TarjetaCreditoDto origen,
                Pago pago) {
}
