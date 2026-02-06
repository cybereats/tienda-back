package cybereats.fpmislata.com.tiendaback.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import cybereats.fpmislata.com.tiendaback.domain.model.User;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.PaymentService;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.PagoTarjetaRequest;
import cybereats.fpmislata.com.tiendaback.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PaymentService paymentService;

    private PagoTarjetaRequest pagoTarjetaRequest;
    private String clientToken;

    @BeforeEach
    void setUp() {
        // pagoTarjetaRequest = new PagoTarjetaRequest("1234567890123456", "12/25",
        // "123");
        User clientUser = new User.Builder().id(2L).role(UserRole.CLIENT).build();
        clientToken = JwtUtil.generateToken(clientUser);
    }

    @Nested
    @DisplayName("POST /api/payments/card")
    class PaymentCardTests {

        @Test
        @DisplayName("Debería procesar el pago correctamente")
        void shouldProcessPayment() throws Exception {
            mockMvc.perform(post("/api/payments/card")
                    .header("Authorization", "Bearer " + clientToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(pagoTarjetaRequest)))
                    .andExpect(status().isOk());

            verify(paymentService).payment(pagoTarjetaRequest); // Verify that the service was called
        }
    }
}
