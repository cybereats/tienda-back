package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.CategoryProductJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryProductJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryProductRepositoryImplTest {

  @Mock
  private CategoryProductJpaDao categoryProductJpaDao;

  @InjectMocks
  private CategoryProductRepositoryImpl categoryProductRepository;

  private CategoryProductJpaEntity categoryProductJpaEntity;
  private CategoryProductDto categoryProductDto;

  @BeforeEach
  void setUp() {
    categoryProductJpaEntity = new CategoryProductJpaEntity(1L, "Food", "food");
    categoryProductDto = new CategoryProductDto(1L, "Food", "food");
  }

  @Nested
  @DisplayName("Tests para el método findAll")
  class FindAllTests {
    @Test
    @DisplayName("Debería devolver una página de DTOs")
    void shouldReturnPage() {
      when(categoryProductJpaDao.findAll(1, 10)).thenReturn(List.of(categoryProductJpaEntity));
      when(categoryProductJpaDao.count()).thenReturn(1L);

      Page<CategoryProductDto> result = categoryProductRepository.findAll(1, 10);

      assertNotNull(result);
      assertEquals(1, result.data().size());
      assertEquals(categoryProductDto.id(), result.data().get(0).id());
      assertEquals(1L, result.totalElements());
    }
  }

  @Nested
  @DisplayName("Tests para el método findById")
  class FindByIdTests {
    @Test
    @DisplayName("Debería devolver Optional con DTO cuando existe")
    void shouldReturnOptionalWithDtoWhenExists() {
      when(categoryProductJpaDao.findById(1L)).thenReturn(Optional.of(categoryProductJpaEntity));

      Optional<CategoryProductDto> result = categoryProductRepository.findById(1L);

      assertTrue(result.isPresent());
      assertEquals(categoryProductDto.id(), result.get().id());
    }

    @Test
    @DisplayName("Debería devolver Optional vacío cuando no existe")
    void shouldReturnEmptyOptionalWhenDoesNotExist() {
      when(categoryProductJpaDao.findById(1L)).thenReturn(Optional.empty());

      Optional<CategoryProductDto> result = categoryProductRepository.findById(1L);

      assertFalse(result.isPresent());
    }
  }

  @Nested
  @DisplayName("Tests para el método findBySlug")
  class FindBySlugTests {
    @Test
    @DisplayName("Debería devolver Optional con DTO cuando existe el slug")
    void shouldReturnOptionalWithDtoWhenSlugExists() {
      when(categoryProductJpaDao.findBySlug("food")).thenReturn(Optional.of(categoryProductJpaEntity));

      Optional<CategoryProductDto> result = categoryProductRepository.findBySlug("food");

      assertTrue(result.isPresent());
      assertEquals(categoryProductDto.slug(), result.get().slug());
    }
  }

  @Nested
  @DisplayName("Tests para el método save")
  class SaveTests {
    @Test
    @DisplayName("Debería llamar a insert cuando el ID es nulo")
    void shouldCallInsertWhenIdIsNull() {
      CategoryProductDto newDto = new CategoryProductDto(null, "Drinks", "drinks");
      CategoryProductJpaEntity savedEntity = new CategoryProductJpaEntity(2L, "Drinks", "drinks");

      when(categoryProductJpaDao.insert(any(CategoryProductJpaEntity.class))).thenReturn(savedEntity);

      CategoryProductDto result = categoryProductRepository.save(newDto);

      assertNotNull(result.id());
      assertEquals(2L, result.id());
      verify(categoryProductJpaDao).insert(any(CategoryProductJpaEntity.class));
    }

    @Test
    @DisplayName("Debería llamar a update cuando el ID no es nulo")
    void shouldCallUpdateWhenIdIsNotNull() {
      when(categoryProductJpaDao.update(any(CategoryProductJpaEntity.class))).thenReturn(categoryProductJpaEntity);

      CategoryProductDto result = categoryProductRepository.save(categoryProductDto);

      assertEquals(categoryProductDto.id(), result.id());
      verify(categoryProductJpaDao).update(any(CategoryProductJpaEntity.class));
    }
  }

  @Nested
  @DisplayName("Tests para el método deleteBySlug")
  class DeleteTests {
    @Test
    @DisplayName("Debería llamar a deleteBySlug del DAO")
    void shouldCallDaoDeleteBySlug() {
      doNothing().when(categoryProductJpaDao).deleteBySlug("food");

      categoryProductRepository.deleteBySlug("food");

      verify(categoryProductJpaDao).deleteBySlug("food");
    }
  }
}
