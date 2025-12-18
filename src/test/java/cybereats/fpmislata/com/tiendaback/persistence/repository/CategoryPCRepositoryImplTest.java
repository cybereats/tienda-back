package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.CategoryPCJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryPCJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryPCRepositoryImplTest {

  @Mock
  private CategoryPCJpaDao categoryPCJpaDao;

  @InjectMocks
  private CategoryPCRepositoryImpl categoryPCRepository;

  private CategoryPCJpaEntity categoryPCJpaEntity;
  private CategoryPCDto categoryPCDto;

  @BeforeEach
  void setUp() {
    categoryPCJpaEntity = new CategoryPCJpaEntity(1L, "Gaming", "gaming", new BigDecimal("50.0"));
    categoryPCDto = new CategoryPCDto(1L, "Gaming", "gaming", new BigDecimal("50.0"));
  }

  @Nested
  @DisplayName("Tests para el método findAll")
  class FindAllTests {
    @Test
    @DisplayName("Debería devolver una página de DTOs")
    void shouldReturnPage() {
      when(categoryPCJpaDao.findAll(1, 10)).thenReturn(List.of(categoryPCJpaEntity));
      when(categoryPCJpaDao.count()).thenReturn(1L);

      Page<CategoryPCDto> result = categoryPCRepository.findAll(1, 10);

      assertNotNull(result);
      assertEquals(1, result.data().size());
      assertEquals(categoryPCDto.id(), result.data().get(0).id());
      assertEquals(1L, result.totalElements());
    }
  }

  @Nested
  @DisplayName("Tests para el método findById")
  class FindByIdTests {
    @Test
    @DisplayName("Debería devolver Optional con DTO cuando existe")
    void shouldReturnOptionalWithDtoWhenExists() {
      when(categoryPCJpaDao.findById(1L)).thenReturn(Optional.of(categoryPCJpaEntity));

      Optional<CategoryPCDto> result = categoryPCRepository.findById(1L);

      assertTrue(result.isPresent());
      assertEquals(categoryPCDto.id(), result.get().id());
    }

    @Test
    @DisplayName("Debería devolver Optional vacío cuando no existe")
    void shouldReturnEmptyOptionalWhenDoesNotExist() {
      when(categoryPCJpaDao.findById(1L)).thenReturn(Optional.empty());

      Optional<CategoryPCDto> result = categoryPCRepository.findById(1L);

      assertFalse(result.isPresent());
    }
  }

  @Nested
  @DisplayName("Tests para el método findBySlug")
  class FindBySlugTests {
    @Test
    @DisplayName("Debería devolver Optional con DTO cuando existe el slug")
    void shouldReturnOptionalWithDtoWhenSlugExists() {
      when(categoryPCJpaDao.findBySlug("gaming")).thenReturn(Optional.of(categoryPCJpaEntity));

      Optional<CategoryPCDto> result = categoryPCRepository.findBySlug("gaming");

      assertTrue(result.isPresent());
      assertEquals(categoryPCDto.slug(), result.get().slug());
    }
  }

  @Nested
  @DisplayName("Tests para el método save")
  class SaveTests {
    @Test
    @DisplayName("Debería llamar a insert cuando el ID es nulo")
    void shouldCallInsertWhenIdIsNull() {
      CategoryPCDto newDto = new CategoryPCDto(null, "Work", "work", new BigDecimal("30.0"));
      CategoryPCJpaEntity savedEntity = new CategoryPCJpaEntity(2L, "Work", "work", new BigDecimal("30.0"));

      when(categoryPCJpaDao.insert(any(CategoryPCJpaEntity.class))).thenReturn(savedEntity);

      CategoryPCDto result = categoryPCRepository.save(newDto);

      assertNotNull(result.id());
      assertEquals(2L, result.id());
      verify(categoryPCJpaDao).insert(any(CategoryPCJpaEntity.class));
      verify(categoryPCJpaDao, never()).update(any(CategoryPCJpaEntity.class));
    }

    @Test
    @DisplayName("Debería llamar a update cuando el ID no es nulo")
    void shouldCallUpdateWhenIdIsNotNull() {
      when(categoryPCJpaDao.update(any(CategoryPCJpaEntity.class))).thenReturn(categoryPCJpaEntity);

      CategoryPCDto result = categoryPCRepository.save(categoryPCDto);

      assertEquals(categoryPCDto.id(), result.id());
      verify(categoryPCJpaDao).update(any(CategoryPCJpaEntity.class));
      verify(categoryPCJpaDao, never()).insert(any(CategoryPCJpaEntity.class));
    }
  }

  @Nested
  @DisplayName("Tests para el método deleteById")
  class DeleteByIdTests {
    @Test
    @DisplayName("Debería llamar a deleteById del DAO")
    void shouldCallDaoDeleteById() {
      doNothing().when(categoryPCJpaDao).deleteById(1L);

      categoryPCRepository.deleteById(1L);

      verify(categoryPCJpaDao).deleteById(1L);
    }
  }
}
