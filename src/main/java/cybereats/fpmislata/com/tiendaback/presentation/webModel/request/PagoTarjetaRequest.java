package cybereats.fpmislata.com.tiendaback.presentation.webModel.request;

public record PagoTarjetaRequest(
        Autorizacion autorizacion,
        TarjetaCreditoDto origen,
        DatosCuenta destino,
        Pago pago) {
}
