package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.OrderItem;
import cybereats.fpmislata.com.tiendaback.domain.model.OrderStatus;
import cybereats.fpmislata.com.tiendaback.domain.model.Product;
import cybereats.fpmislata.com.tiendaback.domain.model.User;
import cybereats.fpmislata.com.tiendaback.domain.model.UserOrder;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserOrderMapperTest {

    private final UserOrderMapper mapper = UserOrderMapper.getInstance();

    @Test
    @DisplayName("Debería mapear de UserOrder a UserOrderDto")
    void shouldMapToDto() {
        User user = new User.Builder().id(1L).name("Name").build();
        Product product = new Product.Builder().id(1L).label("Product 1").build();
        OrderItem orderItem = new OrderItem.Builder().id(1L).product(product).quantity(2).build();
        UserOrder userOrder = new UserOrder.Builder()
                .id(1L)
                .user(user)
                .orderItems(List.of(orderItem))
                .status(OrderStatus.CONFIRMED)
                .createdAt(LocalDateTime.now())
                .build();

        UserOrderDto dto = mapper.fromUserOrderToUserOrderDto(userOrder);

        assertNotNull(dto);
        assertEquals(userOrder.getId(), dto.id());
        assertEquals(userOrder.getUser().getId(), dto.user().id());
        assertEquals(userOrder.getOrderItems().size(), dto.orderItems().size());
        assertEquals(userOrder.getStatus(), dto.status());
        assertEquals(userOrder.getCreatedAt(), dto.createdAt());
    }

    @Test
    @DisplayName("Debería mapear de UserOrderDto a UserOrder")
    void shouldMapToDomain() {
        UserDto userDto = new UserDto(1L, "Name", "Surname", "email@test.com", "1990-01-01", "user", "pass",
                UserRole.CLIENT);
        CategoryProductDto categoryDto = new CategoryProductDto(1L, "Category", "category");
        ProductDto productDto = new ProductDto(1L, "Product 1", "product-1", "Description", new BigDecimal("10.00"),
                categoryDto);
        OrderItemDto orderItemDto = new OrderItemDto(1L, productDto, 2);
        UserOrderDto dto = new UserOrderDto(1L, userDto, List.of(orderItemDto), OrderStatus.CONFIRMED,
                LocalDateTime.now());

        UserOrder userOrder = mapper.fromUserOrderDtoToUserOrder(dto);

        assertNotNull(userOrder);
        assertEquals(dto.id(), userOrder.getId());
        assertEquals(dto.user().id(), userOrder.getUser().getId());
        assertEquals(dto.orderItems().size(), userOrder.getOrderItems().size());
        assertEquals(dto.status(), userOrder.getStatus());
        assertEquals(dto.createdAt(), userOrder.getCreatedAt());
    }

    @Test
    @DisplayName("Debería devolver null si el input es null")
    void shouldReturnNullIfInputIsNull() {
        assertNull(mapper.fromUserOrderToUserOrderDto(null));
        assertNull(mapper.fromUserOrderDtoToUserOrder(null));
    }
}
