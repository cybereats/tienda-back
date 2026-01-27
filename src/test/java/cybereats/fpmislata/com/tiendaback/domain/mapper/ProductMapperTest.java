package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.CategoryProduct;
import cybereats.fpmislata.com.tiendaback.domain.model.Product;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private final ProductMapper mapper = ProductMapper.getInstance();

    @Test
    @DisplayName("Debería mapear de Product a ProductDto")
    void shouldMapToDto() {
        CategoryProduct category = new CategoryProduct.Builder().id(1L).name("Category").build();
        Product product = new Product.Builder()
                .id(1L)
                .label("Product 1")
                .slug("product-1")
                .description("Description")
                .price(new BigDecimal("10.00"))
                .categoryProduct(category)
                .build();

        ProductDto dto = mapper.fromProductToProductDto(product);

        assertNotNull(dto);
        assertEquals(product.getId(), dto.id());
        assertEquals(product.getLabel(), dto.label());
        assertEquals(product.getSlug(), dto.slug());
        assertEquals(product.getDescription(), dto.description());
        assertEquals(product.getPrice(), dto.price());
        assertEquals(product.getCategoryProduct().getId(), dto.category().id());
    }

    @Test
    @DisplayName("Debería mapear de ProductDto a Product")
    void shouldMapToDomain() {
        CategoryProductDto categoryDto = new CategoryProductDto(1L, "Category", "category");
        ProductDto dto = new ProductDto(1L, "Product 1", "product-1", "Description", new BigDecimal("10.00"),
                "image.png",
                categoryDto);

        Product product = mapper.fromProductDtoToProduct(dto);

        assertNotNull(product);
        assertEquals(dto.id(), product.getId());
        assertEquals(dto.label(), product.getLabel());
        assertEquals(dto.slug(), product.getSlug());
        assertEquals(dto.description(), product.getDescription());
        assertEquals(dto.price(), product.getPrice());
        assertEquals(dto.category().id(), product.getCategoryProduct().getId());
    }

    @Test
    @DisplayName("Debería devolver null si el input es null")
    void shouldReturnNullIfInputIsNull() {
        assertNull(mapper.fromProductToProductDto(null));
        assertNull(mapper.fromProductDtoToProduct(null));
    }
}
