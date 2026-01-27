package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.OrderItem;
import cybereats.fpmislata.com.tiendaback.domain.model.Product;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemMapperTest {

    private final OrderItemMapper mapper = OrderItemMapper.getInstance();

    @Test
    @DisplayName("Debería mapear de OrderItem a OrderItemDto")
    void shouldMapToDto() {
        Product product = new Product.Builder().id(1L).label("Product 1").build();
        OrderItem orderItem = new OrderItem.Builder().id(1L).product(product).quantity(5).build();

        OrderItemDto dto = mapper.fromOrderItemToOrderItemDto(orderItem);

        assertNotNull(dto);
        assertEquals(orderItem.getId(), dto.id());
        assertEquals(orderItem.getProduct().getId(), dto.product().id());
        assertEquals(orderItem.getQuantity(), dto.quantity());
    }

    @Test
    @DisplayName("Debería mapear de OrderItemDto a OrderItem")
    void shouldMapToDomain() {
        CategoryProductDto categoryDto = new CategoryProductDto(1L, "Category", "category");
        ProductDto productDto = new ProductDto(1L, "Product 1", "product-1", "Description", new BigDecimal("10.00"),
                "image.png",
                categoryDto);
        OrderItemDto dto = new OrderItemDto(1L, productDto, 5);

        OrderItem orderItem = mapper.fromOrderItemDtoToOrderItem(dto);

        assertNotNull(orderItem);
        assertEquals(dto.id(), orderItem.getId());
        assertEquals(dto.product().id(), orderItem.getProduct().getId());
        assertEquals(dto.quantity(), orderItem.getQuantity());
    }

    @Test
    @DisplayName("Debería devolver null si el input es null")
    void shouldReturnNullIfInputIsNull() {
        assertNull(mapper.fromOrderItemToOrderItemDto(null));
        assertNull(mapper.fromOrderItemDtoToOrderItem(null));
    }
}
