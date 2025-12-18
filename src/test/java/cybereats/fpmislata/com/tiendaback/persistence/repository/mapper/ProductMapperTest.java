package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryProductJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ProductJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private final ProductMapper mapper = ProductMapper.getInstance();

    @Test
    @DisplayName("Debería mapear de ProductJpaEntity a ProductDto")
    void shouldMapEntityToDto() {
        CategoryProductJpaEntity categoryEntity = new CategoryProductJpaEntity(1L, "Category", "category");
        ProductJpaEntity entity = new ProductJpaEntity(1L, "Product 1", "product-1", "Description",
                new BigDecimal("10.00"), categoryEntity);

        ProductDto dto = mapper.fromProductJpaEntityToProductDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.id());
        assertEquals(entity.getLabel(), dto.label());
        assertEquals(entity.getSlug(), dto.slug());
        assertEquals(entity.getDescription(), dto.description());
        assertEquals(entity.getPrice(), dto.price());
        assertEquals(entity.getCategoryProductJpaEntity().getId(), dto.category().id());
    }

    @Test
    @DisplayName("Debería mapear de ProductDto a ProductJpaEntity")
    void shouldMapDtoToEntity() {
        CategoryProductDto categoryDto = new CategoryProductDto(1L, "Category", "category");
        ProductDto dto = new ProductDto(1L, "Product 1", "product-1", "Description", new BigDecimal("10.00"),
                categoryDto);

        ProductJpaEntity entity = mapper.fromProductDtoToProductJpaEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.id(), entity.getId());
        assertEquals(dto.label(), entity.getLabel());
        assertEquals(dto.slug(), entity.getSlug());
        assertEquals(dto.description(), entity.getDescription());
        assertEquals(dto.price(), entity.getPrice());
        assertEquals(dto.category().id(), entity.getCategoryProductJpaEntity().getId());
    }

    @Test
    @DisplayName("Debería devolver null si el input es null")
    void shouldReturnNullIfInputIsNull() {
        assertNull(mapper.fromProductJpaEntityToProductDto(null));
        assertNull(mapper.fromProductDtoToProductJpaEntity(null));
    }
}
