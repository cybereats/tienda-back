package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.CategoryProductRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.CategoryProductResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryProductMapperTest {

  private final CategoryProductMapper mapper = CategoryProductMapper.getInstance();

  @Test
  @DisplayName("Debería mapear de CategoryProductRequest a CategoryProductDto")
  void shouldMapRequestToDto() {
    CategoryProductRequest request = new CategoryProductRequest(1L, "Food", "food");

    CategoryProductDto dto = mapper.fromCategoryProductRequestToCategoryProductDto(request);

    assertNotNull(dto);
    assertEquals(request.id(), dto.id());
    assertEquals(request.label(), dto.label());
    assertEquals(request.slug(), dto.slug());
  }

  @Test
  @DisplayName("Debería devolver null al mapear un request nulo")
  void shouldReturnNullWhenRequestIsNull() {
    assertNull(mapper.fromCategoryProductRequestToCategoryProductDto(null));
  }

  @Test
  @DisplayName("Debería mapear de CategoryProductDto a CategoryProductResponse")
  void shouldMapDtoToResponse() {
    CategoryProductDto dto = new CategoryProductDto(1L, "Food", "food");

    CategoryProductResponse response = mapper.fromCategoryProductDtoToCategoryProductResponse(dto);

    assertNotNull(response);
    assertEquals(dto.id(), response.id());
    assertEquals(dto.label(), response.label());
    assertEquals(dto.slug(), response.slug());
  }

  @Test
  @DisplayName("Debería devolver null al mapear un dto nulo")
  void shouldReturnNullWhenDtoIsNull() {
    assertNull(mapper.fromCategoryProductDtoToCategoryProductResponse(null));
  }
}
