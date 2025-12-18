package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.CategoryPCRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.CategoryPCResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CategoryPCMapperTest {

  private final CategoryPCMapper mapper = CategoryPCMapper.getInstance();

  @Test
  @DisplayName("Debería mapear de CategoryPCRequest a CategoryPCDto")
  void shouldMapRequestToDto() {
    CategoryPCRequest request = new CategoryPCRequest(1L, "Gaming", "gaming", new BigDecimal("50.0"));

    CategoryPCDto dto = mapper.fromCategoryPCRequestToCategoryPCDto(request);

    assertNotNull(dto);
    assertEquals(request.id(), dto.id());
    assertEquals(request.label(), dto.label());
    assertEquals(request.slug(), dto.slug());
    assertEquals(request.price(), dto.price());
  }

  @Test
  @DisplayName("Debería devolver null al mapear un request nulo")
  void shouldReturnNullWhenRequestIsNull() {
    assertNull(mapper.fromCategoryPCRequestToCategoryPCDto(null));
  }

  @Test
  @DisplayName("Debería mapear de CategoryPCDto a CategoryPCResponse")
  void shouldMapDtoToResponse() {
    CategoryPCDto dto = new CategoryPCDto(1L, "Gaming", "gaming", new BigDecimal("50.0"));

    CategoryPCResponse response = mapper.fromCategoryPCDtoToCategoryPCResponse(dto);

    assertNotNull(response);
    assertEquals(dto.id(), response.id());
    assertEquals(dto.label(), response.label());
    assertEquals(dto.slug(), response.slug());
    assertEquals(dto.price(), response.price());
  }

  @Test
  @DisplayName("Debería devolver null al mapear un dto nulo")
  void shouldReturnNullWhenDtoIsNull() {
    assertNull(mapper.fromCategoryPCDtoToCategoryPCResponse(null));
  }
}
