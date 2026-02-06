package cybereats.fpmislata.com.tiendaback.microservices.payment;

import cybereats.fpmislata.com.tiendaback.microservices.payment.model.PagoTarjeta;

public interface PaymentMicroservice {
    void payment(PagoTarjeta request);

}
