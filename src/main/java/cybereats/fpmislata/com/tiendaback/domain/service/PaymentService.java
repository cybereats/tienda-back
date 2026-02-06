package cybereats.fpmislata.com.tiendaback.domain.service;

import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.PagoTarjetaRequest;

public interface PaymentService {
    void payment(PagoTarjetaRequest request);
}
