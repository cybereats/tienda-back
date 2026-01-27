package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryProductJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.OrderItemJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ProductJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemMapperTest {

    private final OrderItemMapper mapper = OrderItemMapper.getInstance();

    @Test
    @DisplayName("Debería mapear de OrderItemJpaEntity a OrderItemDto")
    void shouldMapEntityToDto() {
        CategoryProductJpaEntity categoryEntity = new CategoryProductJpaEntity(1L, "Category", "category");
        ProductJpaEntity productEntity = new ProductJpaEntity(1L, "Product 1", "product-1", "Description",
                new BigDecimal("10.00"), "image.png", categoryEntity);
        OrderItemJpaEntity entity = new OrderItemJpaEntity(1L, 5, productEntity, null);

        OrderItemDto dto = mapper.fromOrderItemJpaEntityToOrderItemDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.id());
        assertEquals(entity.getProduct().getId(), dto.product().id());
        assertEquals(entity.getQuantity(), dto.quantity());
    }

    @Test
    @DisplayName("Debería mapear de OrderItemDto a OrderItemJpaEntity")
    void shouldMapDtoToEntity() {
        CategoryProductDto categoryDto = new CategoryProductDto(1L, "Category", "category");
        ProductDto productDto = new ProductDto(1L, "Product 1", "product-1", "Description", new BigDecimal("10.00"),
                "image.png",
                categoryDto);
        OrderItemDto dto = new OrderItemDto(1L, productDto, 5);

        OrderItemJpaEntity entity = mapper.fromOrderItemDtoToOrderItemJpaEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.id(), entity.getId());
        assertEquals(dto.product().id(), entity.getProduct().getId());
        assertEquals(dto.quantity(), entity.getQuantity());
    }

    @Test
    @DisplayName("Debería devolver null si el input es null")
    void shouldReturnNullIfInputIsNull() {
        assertNull(mapper.fromOrderItemJpaEntityToOrderItemDto(null));
        assertNull(mapper.fromOrderItemDtoToOrderItemJpaEntity(null));
    }
}
