package cybereats.fpmislata.com.tiendaback.presentation;

import cybereats.fpmislata.com.tiendaback.domain.model.OrderStatus;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.UserOrderService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserOrderController.class)
@SuppressWarnings("null")
class UserOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserOrderService userOrderService;

    private UserOrderDto userOrderDto;

    @BeforeEach
    void setUp() {
        UserDto userDto = new UserDto(1L, "Name", "Surname", "Email", "BornDate", "Username", "Password",
                UserRole.CLIENT);
        userOrderDto = new UserOrderDto(1L, userDto, List.of(), OrderStatus.CONFIRMED,
                LocalDateTime.parse("2025-01-15T10:00:00"));
    }

    @Nested
    @DisplayName("Tests para GET /api/orders")
    class GetAllTests {

        @Test
        @DisplayName("Debería devolver 200 OK con una página de pedidos")
        void shouldReturn200OK() throws Exception {
            when(userOrderService.findAll(1, 10)).thenReturn(new Page<>(List.of(userOrderDto), 1, 10, 1L));

            mockMvc.perform(get("/api/orders?page=1&size=10"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.data[0].id").value(1))
                    .andExpect(jsonPath("$.data[0].status").value("CONFIRMED"));
        }
    }

    @Nested
    @DisplayName("Tests para GET /api/orders/{id}")
    class GetByIdTests {

        @Test
        @DisplayName("Debería devolver 200 OK cuando el pedido existe")
        void shouldReturn200OK() throws Exception {
            when(userOrderService.getById(1L)).thenReturn(userOrderDto);

            mockMvc.perform(get("/api/orders/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.status").value("CONFIRMED"));
        }
    }

    @Nested
    @DisplayName("Tests para POST /api/orders")
    class CreateTests {

        @Test
        @DisplayName("Debería devolver 201 Created al crear un pedido")
        void shouldReturn201Created() throws Exception {
            when(userOrderService.insert(any(UserOrderDto.class))).thenReturn(userOrderDto);

            String requestJson = """
                    {
                        "userId": 1,
                        "status": "CONFIRMED",
                        "orderItems": []
                    }
                    """;

            mockMvc.perform(post("/api/orders")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestJson))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(1));
        }
    }

    @Nested
    @DisplayName("Tests para PUT /api/orders/{id}")
    class UpdateTests {

        @Test
        @DisplayName("Debería devolver 200 OK al actualizar un pedido")
        void shouldReturn200OK() throws Exception {
            when(userOrderService.update(any(UserOrderDto.class))).thenReturn(userOrderDto);

            String requestJson = """
                    {
                        "id": 1,
                        "userId": 1,
                        "status": "DELIVERED",
                        "orderItems": []
                    }
                    """;

            mockMvc.perform(put("/api/orders/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestJson))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1));
        }
    }

    @Nested
    @DisplayName("Tests para DELETE /api/orders/{id}")
    class DeleteTests {

        @Test
        @DisplayName("Debería devolver 204 No Content al eliminar un pedido")
        void shouldReturn204NoContent() throws Exception {
            mockMvc.perform(delete("/api/orders/1"))
                    .andExpect(status().isNoContent());
        }
    }
}
