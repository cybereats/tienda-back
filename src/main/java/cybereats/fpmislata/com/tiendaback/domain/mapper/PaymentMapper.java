package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.microservices.payment.model.PagoTarjeta;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.PagoTarjetaRequest;

public class PaymentMapper {

        private static PaymentMapper INSTANCE;

        private PaymentMapper() {
        }

        public static PaymentMapper getInstance() {
                if (INSTANCE == null) {
                        INSTANCE = new PaymentMapper();
                }
                return INSTANCE;
        }

        public PagoTarjeta toPagoTarjeta(PagoTarjetaRequest request) {
                return new PagoTarjeta(
                                new PagoTarjeta.Autorizacion(
                                                request.autorizacion().login(),
                                                request.autorizacion().apiToken()),
                                new PagoTarjeta.TarjetaCreditoDto(
                                                request.origen().id(),
                                                request.origen().numeroTarjeta(),
                                                request.origen().fechaCaducidad(),
                                                request.origen().cvc(),
                                                request.origen().nombreCompleto()),
                                new PagoTarjeta.DatosCuenta(
                                                request.destino().iban()),
                                new PagoTarjeta.Pago(
                                                request.pago().importe(),
                                                request.pago().concepto()));
        }
}
