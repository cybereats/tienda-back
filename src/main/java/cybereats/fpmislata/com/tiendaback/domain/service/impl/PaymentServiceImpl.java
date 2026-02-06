package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.mapper.PaymentMapper;
import cybereats.fpmislata.com.tiendaback.domain.service.PaymentService;
import cybereats.fpmislata.com.tiendaback.microservices.payment.PaymentMicroservice;
import cybereats.fpmislata.com.tiendaback.microservices.payment.model.PagoTarjeta;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.PagoTarjetaRequest;

public class PaymentServiceImpl implements PaymentService {

    private final PaymentMicroservice paymentMicroservice;

    public PaymentServiceImpl(PaymentMicroservice paymentMicroservice) {
        this.paymentMicroservice = paymentMicroservice;
    }

    @Override
    public void payment(PagoTarjetaRequest request) {
        PagoTarjeta dto = PaymentMapper.getInstance().toPagoTarjeta(request);

        paymentMicroservice.payment(dto);
    }
}
