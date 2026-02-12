package cybereats.fpmislata.com.tiendaback.infraestructura.payment;

import cybereats.fpmislata.com.tiendaback.infraestructura.payment.model.PagoTarjeta;

public interface PaymentMicroservice {
    void payment(PagoTarjeta request);

}
