package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.CategoryPC;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CategoryPCMapperTest {

  private final CategoryPCMapper mapper = CategoryPCMapper.getInstance();

  @Test
  @DisplayName("Debería mapear de CategoryPC a CategoryPCDto")
  void shouldMapModelToDto() {
    CategoryPC categoryPC = new CategoryPC.Builder()
        .id(1L)
        .label("Gaming")
        .slug("gaming")
        .price(new BigDecimal("50.0"))
        .build();

    CategoryPCDto dto = mapper.fromCategoryPCToCategoryPCDto(categoryPC);

    assertNotNull(dto);
    assertEquals(categoryPC.getId(), dto.id());
    assertEquals(categoryPC.getLabel(), dto.label());
    assertEquals(categoryPC.getSlug(), dto.slug());
    assertEquals(categoryPC.getPrice(), dto.price());
  }

  @Test
  @DisplayName("Debería devolver null al mapear un modelo nulo")
  void shouldReturnNullWhenModelIsNull() {
    assertNull(mapper.fromCategoryPCToCategoryPCDto(null));
  }

  @Test
  @DisplayName("Debería mapear de CategoryPCDto a CategoryPC")
  void shouldMapDtoToModel() {
    CategoryPCDto dto = new CategoryPCDto(1L, "Gaming", "gaming", new BigDecimal("50.0"));

    CategoryPC model = mapper.fromCategoryPCDtoToCategoryPC(dto);

    assertNotNull(model);
    assertEquals(dto.id(), model.getId());
    assertEquals(dto.label(), model.getLabel());
    assertEquals(dto.slug(), model.getSlug());
    assertEquals(dto.price(), model.getPrice());
  }

  @Test
  @DisplayName("Debería devolver null al mapear un dto nulo")
  void shouldReturnNullWhenDtoIsNull() {
    assertNull(mapper.fromCategoryPCDtoToCategoryPC(null));
  }
}
