package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.CategoryProductRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.ProductRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.ProductResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private final ProductMapper mapper = ProductMapper.getInstance();

    @Test
    @DisplayName("Debería mapear de ProductRequest a ProductDto")
    void shouldMapRequestToDto() {
        CategoryProductRequest categoryRequest = new CategoryProductRequest(1L, "Category", "category");
        ProductRequest request = new ProductRequest(1L, "Product 1", "product-1", "Description",
                new BigDecimal("10.00"), categoryRequest);

        ProductDto dto = mapper.fromProductRequestToProductDto(request);

        assertNotNull(dto);
        assertEquals(request.id(), dto.id());
        assertEquals(request.label(), dto.label());
        assertEquals(request.slug(), dto.slug());
        assertEquals(request.description(), dto.description());
        assertEquals(request.price(), dto.price());
        assertEquals(request.category().id(), dto.category().id());
    }

    @Test
    @DisplayName("Debería mapear de ProductDto a ProductResponse")
    void shouldMapDtoToResponse() {
        CategoryProductDto categoryDto = new CategoryProductDto(1L, "Category", "category");
        ProductDto dto = new ProductDto(1L, "Product 1", "product-1", "Description", new BigDecimal("10.00"),
                categoryDto);

        ProductResponse response = mapper.fromProductDtoToProductResponse(dto);

        assertNotNull(response);
        assertEquals(dto.id(), response.id());
        assertEquals(dto.label(), response.label());
        assertEquals(dto.slug(), response.slug());
        assertEquals(dto.description(), response.description());
        assertEquals(dto.price(), response.price());
        assertEquals(dto.category().id(), response.category().id());
    }

    @Test
    @DisplayName("Debería devolver null si el input es null")
    void shouldReturnNullIfInputIsNull() {
        assertNull(mapper.fromProductRequestToProductDto(null));
        assertNull(mapper.fromProductDtoToProductResponse(null));
    }
}
