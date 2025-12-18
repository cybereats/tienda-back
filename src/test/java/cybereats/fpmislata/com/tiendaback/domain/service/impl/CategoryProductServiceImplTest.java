package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.CategoryProductRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryProductServiceImplTest {

  @Mock
  private CategoryProductRepository categoryProductRepository;

  @InjectMocks
  private CategoryProductServiceImpl categoryProductService;

  private CategoryProductDto categoryProductDto;

  @BeforeEach
  void setUp() {
    categoryProductDto = new CategoryProductDto(1L, "Food", "food");
  }

  @Nested
  @DisplayName("Tests para el método findAll")
  class FindAllTests {
    @Test
    @DisplayName("Debería devolver una página de categorías")
    void shouldReturnPage() {
      Page<CategoryProductDto> page = new Page<>(List.of(categoryProductDto), 1, 10, 1L);
      when(categoryProductRepository.findAll(1, 10)).thenReturn(page);

      Page<CategoryProductDto> result = categoryProductService.findAll(1, 10);

      assertNotNull(result);
      assertEquals(1, result.data().size());
      assertEquals(categoryProductDto, result.data().get(0));
    }
  }

  @Nested
  @DisplayName("Tests para el método getById")
  class GetByIdTests {
    @Test
    @DisplayName("Debería devolver la categoría cuando existe")
    void shouldReturnCategoryWhenExists() {
      when(categoryProductRepository.findById(1L)).thenReturn(Optional.of(categoryProductDto));

      CategoryProductDto result = categoryProductService.getById(1L);

      assertEquals(categoryProductDto, result);
    }

    @Test
    @DisplayName("Debería lanzar ResourceNotFoundException cuando no existe")
    void shouldThrowExceptionWhenDoesNotExist() {
      when(categoryProductRepository.findById(1L)).thenReturn(Optional.empty());

      assertThrows(ResourceNotFoundException.class, () -> categoryProductService.getById(1L));
    }
  }

  @Nested
  @DisplayName("Tests para el método insert")
  class InsertTests {
    @Test
    @DisplayName("Debería insertar la categoría cuando no existe")
    void shouldInsertWhenDoesNotExist() {
      when(categoryProductRepository.findById(1L)).thenReturn(Optional.empty());
      when(categoryProductRepository.save(categoryProductDto)).thenReturn(categoryProductDto);

      CategoryProductDto result = categoryProductService.insert(categoryProductDto);

      assertEquals(categoryProductDto, result);
      verify(categoryProductRepository).save(categoryProductDto);
    }

    @Test
    @DisplayName("Debería lanzar BusinessException cuando ya existe")
    void shouldThrowExceptionWhenAlreadyExists() {
      when(categoryProductRepository.findById(1L)).thenReturn(Optional.of(categoryProductDto));

      assertThrows(BusinessException.class, () -> categoryProductService.insert(categoryProductDto));
    }
  }

  @Nested
  @DisplayName("Tests para el método update")
  class UpdateTests {
    @Test
    @DisplayName("Debería actualizar la categoría cuando existe")
    void shouldUpdateWhenExists() {
      when(categoryProductRepository.findById(1L)).thenReturn(Optional.of(categoryProductDto));
      when(categoryProductRepository.save(categoryProductDto)).thenReturn(categoryProductDto);

      CategoryProductDto result = categoryProductService.update(categoryProductDto);

      assertEquals(categoryProductDto, result);
      verify(categoryProductRepository).save(categoryProductDto);
    }

    @Test
    @DisplayName("Debería lanzar ResourceNotFoundException cuando no existe")
    void shouldThrowExceptionWhenDoesNotExist() {
      when(categoryProductRepository.findById(1L)).thenReturn(Optional.empty());

      assertThrows(ResourceNotFoundException.class, () -> categoryProductService.update(categoryProductDto));
    }
  }

  @Nested
  @DisplayName("Tests para el método deleteBySlug")
  class DeleteBySlugTests {
    @Test
    @DisplayName("Debería borrar la categoría cuando existe")
    void shouldDeleteWhenExists() {
      when(categoryProductRepository.findBySlug("food")).thenReturn(Optional.of(categoryProductDto));
      doNothing().when(categoryProductRepository).deleteBySlug("food");

      categoryProductService.deleteBySlug("food");

      verify(categoryProductRepository).deleteBySlug("food");
    }

    @Test
    @DisplayName("Debería lanzar ResourceNotFoundException cuando no existe")
    void shouldThrowExceptionWhenDoesNotExist() {
      when(categoryProductRepository.findBySlug("food")).thenReturn(Optional.empty());

      assertThrows(ResourceNotFoundException.class, () -> categoryProductService.deleteBySlug("food"));
    }
  }
}
