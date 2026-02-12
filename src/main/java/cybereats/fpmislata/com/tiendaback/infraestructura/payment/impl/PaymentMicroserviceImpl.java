package cybereats.fpmislata.com.tiendaback.infraestructura.payment.impl;

import org.springframework.web.client.RestTemplate;

import cybereats.fpmislata.com.tiendaback.infraestructura.payment.PaymentMicroservice;
import cybereats.fpmislata.com.tiendaback.infraestructura.payment.model.PagoTarjeta;

public class PaymentMicroserviceImpl implements PaymentMicroservice {

    private final RestTemplate restTemplate;

    public PaymentMicroserviceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void payment(PagoTarjeta request) {
        restTemplate.postForEntity("http://banco-back:8080/api/pagos/tarjeta", request, Void.class);
    }
}
