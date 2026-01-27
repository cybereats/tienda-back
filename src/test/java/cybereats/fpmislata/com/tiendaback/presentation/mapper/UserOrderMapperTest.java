package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.OrderStatus;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.UserOrderRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.UserOrderResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserOrderMapperTest {

    private final UserOrderMapper mapper = UserOrderMapper.getInstance();

    @Test
    @DisplayName("Debería mapear de UserOrderDto a UserOrderResponse")
    void shouldMapDtoToResponse() {
        UserDto userDto = new UserDto(1L, "Name", "Surname", "email@test.com", "1990-01-01", "user", "pass",
                UserRole.CLIENT);
        CategoryProductDto categoryDto = new CategoryProductDto(1L, "Category", "category");
        ProductDto productDto = new ProductDto(1L, "Product", "product", "Description", new BigDecimal("10.00"),
                "image.png",
                categoryDto);
        OrderItemDto orderItemDto = new OrderItemDto(1L, productDto, 2);
        UserOrderDto dto = new UserOrderDto(1L, userDto, List.of(orderItemDto), OrderStatus.CONFIRMED,
                LocalDateTime.now());

        UserOrderResponse response = mapper.fromUserOrderDtoToUserOrderResponse(dto);

        assertNotNull(response);
        assertEquals(dto.id(), response.id());
        assertEquals(dto.user().id(), response.user().id());
        assertEquals(dto.orderItems().size(), response.orderItems().size());
        assertEquals(dto.status(), response.status());
        assertEquals(dto.createdAt(), response.createdAt());
    }

    @Test
    @DisplayName("Debería mapear de UserOrderRequest a UserOrderDto")
    void shouldMapRequestToDto() {
        UserOrderRequest request = new UserOrderRequest(1L, 10L, List.of(1L, 2L), "CONFIRMED");

        UserOrderDto dto = mapper.fromUserOrderRequestToUserOrderDto(request);

        assertNotNull(dto);
        assertEquals(request.id(), dto.id());
        assertEquals(request.userId(), dto.user().id());
        assertEquals(request.orderItemIds().size(), dto.orderItems().size());
        assertEquals(OrderStatus.CONFIRMED, dto.status());
    }
}
