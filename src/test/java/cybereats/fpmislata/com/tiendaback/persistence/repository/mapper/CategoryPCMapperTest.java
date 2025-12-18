package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryPCJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CategoryPCMapperTest {

  private final CategoryPCMapper mapper = CategoryPCMapper.getInstance();

  @Test
  @DisplayName("Debería mapear de CategoryPCJpaEntity a CategoryPCDto")
  void shouldMapEntityToDto() {
    CategoryPCJpaEntity entity = new CategoryPCJpaEntity(1L, "Gaming", "gaming", new BigDecimal("50.0"));

    CategoryPCDto dto = mapper.fromCategoryPCJpaEntityToCategoryPCDto(entity);

    assertNotNull(dto);
    assertEquals(entity.getId(), dto.id());
    assertEquals(entity.getLabel(), dto.label());
    assertEquals(entity.getSlug(), dto.slug());
    assertEquals(entity.getPrice(), dto.price());
  }

  @Test
  @DisplayName("Debería devolver null al mapear una entidad nula")
  void shouldReturnNullWhenEntityIsNull() {
    assertNull(mapper.fromCategoryPCJpaEntityToCategoryPCDto(null));
  }

  @Test
  @DisplayName("Debería mapear de CategoryPCDto a CategoryPCJpaEntity")
  void shouldMapDtoToEntity() {
    CategoryPCDto dto = new CategoryPCDto(1L, "Gaming", "gaming", new BigDecimal("50.0"));

    CategoryPCJpaEntity entity = mapper.fromCategoryPCDtoToCategoryPCJpaEntity(dto);

    assertNotNull(entity);
    assertEquals(dto.id(), entity.getId());
    assertEquals(dto.label(), entity.getLabel());
    assertEquals(dto.slug(), entity.getSlug());
    assertEquals(dto.price(), entity.getPrice());
  }

  @Test
  @DisplayName("Debería devolver null al mapear un dto nulo")
  void shouldReturnNullWhenDtoIsNull() {
    assertNull(mapper.fromCategoryPCDtoToCategoryPCJpaEntity(null));
  }
}
