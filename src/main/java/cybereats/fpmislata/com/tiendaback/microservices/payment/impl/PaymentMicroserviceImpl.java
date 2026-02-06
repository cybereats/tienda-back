package cybereats.fpmislata.com.tiendaback.microservices.payment.impl;

import cybereats.fpmislata.com.tiendaback.microservices.payment.PaymentMicroservice;
import cybereats.fpmislata.com.tiendaback.microservices.payment.model.PagoTarjeta;
import org.springframework.web.client.RestTemplate;

public class PaymentMicroserviceImpl implements PaymentMicroservice {

    private final RestTemplate restTemplate;

    public PaymentMicroserviceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void payment(PagoTarjeta request) {
        restTemplate.postForEntity("http://localhost:8081/api/pagos/tarjeta", request, Void.class);
    }
}
