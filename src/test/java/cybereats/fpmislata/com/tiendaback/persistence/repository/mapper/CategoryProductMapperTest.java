package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryProductJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryProductMapperTest {

  private final CategoryProductMapper mapper = CategoryProductMapper.getInstance();

  @Test
  @DisplayName("Debería mapear de CategoryProductJpaEntity a CategoryProductDto")
  void shouldMapEntityToDto() {
    CategoryProductJpaEntity entity = new CategoryProductJpaEntity(1L, "Food", "food");

    CategoryProductDto dto = mapper.fromCategoryProductJpaEntityToCategoryProductDto(entity);

    assertNotNull(dto);
    assertEquals(entity.getId(), dto.id());
    assertEquals(entity.getLabel(), dto.label());
    assertEquals(entity.getSlug(), dto.slug());
  }

  @Test
  @DisplayName("Debería devolver null al mapear una entidad nula")
  void shouldReturnNullWhenEntityIsNull() {
    assertNull(mapper.fromCategoryProductJpaEntityToCategoryProductDto(null));
  }

  @Test
  @DisplayName("Debería mapear de CategoryProductDto a CategoryProductJpaEntity")
  void shouldMapDtoToEntity() {
    CategoryProductDto dto = new CategoryProductDto(1L, "Food", "food");

    CategoryProductJpaEntity entity = mapper.fromCategoryProductDtoToCategoryProductJpaEntity(dto);

    assertNotNull(entity);
    assertEquals(dto.id(), entity.getId());
    assertEquals(dto.label(), entity.getLabel());
    assertEquals(dto.slug(), entity.getSlug());
  }

  @Test
  @DisplayName("Debería devolver null al mapear un dto nulo")
  void shouldReturnNullWhenDtoIsNull() {
    assertNull(mapper.fromCategoryProductDtoToCategoryProductJpaEntity(null));
  }
}
