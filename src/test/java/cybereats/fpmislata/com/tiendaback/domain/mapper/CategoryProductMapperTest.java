package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.CategoryProduct;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryProductMapperTest {

    private final CategoryProductMapper mapper = CategoryProductMapper.getInstance();

    @Test
    @DisplayName("Debería mapear de CategoryProduct a CategoryProductDto")
    void shouldMapToDto() {
        CategoryProduct category = new CategoryProduct.Builder()
                .id(1L)
                .name("Category 1")
                .slug("category-1")
                .build();

        CategoryProductDto dto = mapper.fromCategoryProductToCategoryProductDto(category);

        assertNotNull(dto);
        assertEquals(category.getId(), dto.id());
        assertEquals(category.getName(), dto.label());
        assertEquals(category.getSlug(), dto.slug());
    }

    @Test
    @DisplayName("Debería mapear de CategoryProductDto a CategoryProduct")
    void shouldMapToDomain() {
        CategoryProductDto dto = new CategoryProductDto(1L, "Category 1", "category-1");

        CategoryProduct category = mapper.fromCategoryProductDtoToCategoryProduct(dto);

        assertNotNull(category);
        assertEquals(dto.id(), category.getId());
        assertEquals(dto.label(), category.getName());
        assertEquals(dto.slug(), category.getSlug());
    }

    @Test
    @DisplayName("Debería devolver null si el input es null")
    void shouldReturnNullIfInputIsNull() {
        assertNull(mapper.fromCategoryProductToCategoryProductDto(null));
        assertNull(mapper.fromCategoryProductDtoToCategoryProduct(null));
    }
}
