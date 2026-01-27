package cybereats.fpmislata.com.tiendaback.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.model.User;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.OrderItemService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.OrderItemRequest;
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

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderItemController.class)
class OrderItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderItemService orderItemService;

    @Autowired
    private ObjectMapper objectMapper;

    private OrderItemDto orderItemDto;
    private String adminToken;
    private String clientToken;

    @BeforeEach
    void setUp() {
        CategoryProductDto categoryDto = new CategoryProductDto(1L, "Category", "category");
        ProductDto productDto = new ProductDto(1L, "Product", "product", "Desc", new BigDecimal("10.0"), "image.png",
                categoryDto);
        orderItemDto = new OrderItemDto(1L, productDto, 2);

        User adminUser = new User.Builder()
                .id(1L)
                .username("admin")
                .role(UserRole.ADMIN)
                .build();
        adminToken = JwtUtil.generateToken(adminUser);

        User clientUser = new User.Builder()
                .id(2L)
                .username("client")
                .role(UserRole.CLIENT)
                .build();
        clientToken = JwtUtil.generateToken(clientUser);
    }

    @Nested
    @DisplayName("Tests para el método getAllOrderItems")
    class GetAllOrderItemsTests {
        @Test
        @DisplayName("Debería devolver 200 y una página de items")
        void shouldReturnOkAndPage() throws Exception {
            Page<OrderItemDto> page = new Page<>(List.of(orderItemDto), 1, 10, 1L);
            when(orderItemService.findAll(1, 10)).thenReturn(page);

            mockMvc.perform(get("/api/order-items?page=1&size=10")
                    .header("Authorization", "Bearer " + adminToken))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data[0].id").value(orderItemDto.id()))
                    .andExpect(jsonPath("$.data[0].product.id").value(orderItemDto.product().id()))
                    .andExpect(jsonPath("$.totalElements").value(1));
        }
    }

    @Nested
    @DisplayName("Tests para el método getOrderItemById")
    class GetOrderItemByIdTests {
        @Test
        @DisplayName("Debería devolver 200 cuando existe")
        void shouldReturnOkWhenExists() throws Exception {
            when(orderItemService.getById(1L)).thenReturn(orderItemDto);

            mockMvc.perform(get("/api/order-items/1")
                    .header("Authorization", "Bearer " + adminToken))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(orderItemDto.id()))
                    .andExpect(jsonPath("$.product.id").value(orderItemDto.product().id()));
        }

        @Test
        @DisplayName("Debería devolver 404 cuando no existe")
        void shouldReturnNotFoundWhenNotExists() throws Exception {
            when(orderItemService.getById(1L)).thenThrow(new ResourceNotFoundException("Not found"));

            mockMvc.perform(get("/api/order-items/1")
                    .header("Authorization", "Bearer " + adminToken))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("Tests para el método createOrderItem")
    class CreateOrderItemTests {
        @Test
        @DisplayName("Debería devolver 201 cuando es válido")
        void shouldReturnCreatedWhenValid() throws Exception {
            OrderItemRequest request = new OrderItemRequest(null, 1L, 2);
            when(orderItemService.insert(any(OrderItemDto.class))).thenReturn(orderItemDto);

            mockMvc.perform(post("/api/order-items")
                    .header("Authorization", "Bearer " + clientToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(orderItemDto.id()));
        }

        @Test
        @DisplayName("Debería devolver 400 cuando falla la validación (cantidad < 0)")
        void shouldReturnBadRequestWhenInvalid() throws Exception {
            OrderItemRequest request = new OrderItemRequest(null, 1L, -1);

            mockMvc.perform(post("/api/order-items")
                    .header("Authorization", "Bearer " + clientToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("Tests para el método updateOrderItem")
    class UpdateOrderItemTests {
        @Test
        @DisplayName("Debería devolver 200 cuando es válido")
        void shouldReturnOkWhenValid() throws Exception {
            OrderItemRequest request = new OrderItemRequest(1L, 1L, 5);
            when(orderItemService.update(any(OrderItemDto.class))).thenReturn(orderItemDto);

            mockMvc.perform(put("/api/order-items/1")
                    .header("Authorization", "Bearer " + clientToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Debería devolver 400 cuando los IDs no coinciden")
        void shouldReturnBadRequestWhenIdMismatch() throws Exception {
            OrderItemRequest request = new OrderItemRequest(2L, 1L, 5);

            mockMvc.perform(put("/api/order-items/1")
                    .header("Authorization", "Bearer " + clientToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteOrderItem")
    class DeleteOrderItemTests {
        @Test
        @DisplayName("Debería devolver 204")
        void shouldReturnNoContent() throws Exception {
            doNothing().when(orderItemService).deleteById(1L);

            mockMvc.perform(delete("/api/order-items/1")
                    .header("Authorization", "Bearer " + adminToken))
                    .andExpect(status().isNoContent());
        }
    }
}
