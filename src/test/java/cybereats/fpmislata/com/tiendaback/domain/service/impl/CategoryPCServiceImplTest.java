package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.CategoryPCRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryPCServiceImplTest {

  @Mock
  private CategoryPCRepository categoryPCRepository;

  @InjectMocks
  private CategoryPCServiceImpl categoryPCService;

  private CategoryPCDto categoryPCDto;

  @BeforeEach
  void setUp() {
    categoryPCDto = new CategoryPCDto(1L, "Gaming", "gaming", new BigDecimal("50.0"));
  }

  @Nested
  @DisplayName("Tests para el método findAll")
  class FindAllTests {
    @Test
    @DisplayName("Debería devolver una página de categorías")
    void shouldReturnPage() {
      Page<CategoryPCDto> page = new Page<>(List.of(categoryPCDto), 1, 10, 1L);
      when(categoryPCRepository.findAll(1, 10)).thenReturn(page);

      Page<CategoryPCDto> result = categoryPCService.findAll(1, 10);

      assertNotNull(result);
      assertEquals(1, result.data().size());
      assertEquals(categoryPCDto, result.data().get(0));
      verify(categoryPCRepository).findAll(1, 10);
    }
  }

  @Nested
  @DisplayName("Tests para el método getById")
  class GetByIdTests {
    @Test
    @DisplayName("Debería devolver la categoría cuando existe")
    void shouldReturnCategoryWhenExists() {
      when(categoryPCRepository.findById(1L)).thenReturn(Optional.of(categoryPCDto));

      CategoryPCDto result = categoryPCService.getById(1L);

      assertEquals(categoryPCDto, result);
    }

    @Test
    @DisplayName("Debería lanzar ResourceNotFoundException cuando no existe")
    void shouldThrowExceptionWhenDoesNotExist() {
      when(categoryPCRepository.findById(1L)).thenReturn(Optional.empty());

      assertThrows(ResourceNotFoundException.class, () -> categoryPCService.getById(1L));
    }
  }

  @Nested
  @DisplayName("Tests para el método create")
  class CreateTests {
    @Test
    @DisplayName("Debería crear la categoría cuando no existe otra con el mismo ID")
    void shouldCreateWhenDoesNotExist() {
      when(categoryPCRepository.findById(1L)).thenReturn(Optional.empty());
      when(categoryPCRepository.save(categoryPCDto)).thenReturn(categoryPCDto);

      CategoryPCDto result = categoryPCService.create(categoryPCDto);

      assertEquals(categoryPCDto, result);
      verify(categoryPCRepository).save(categoryPCDto);
    }

    @Test
    @DisplayName("Debería lanzar BusinessException cuando ya existe")
    void shouldThrowExceptionWhenAlreadyExists() {
      when(categoryPCRepository.findById(1L)).thenReturn(Optional.of(categoryPCDto));

      assertThrows(BusinessException.class, () -> categoryPCService.create(categoryPCDto));
    }
  }

  @Nested
  @DisplayName("Tests para el método update")
  class UpdateTests {
    @Test
    @DisplayName("Debería actualizar la categoría cuando existe")
    void shouldUpdateWhenExists() {
      when(categoryPCRepository.findById(1L)).thenReturn(Optional.of(categoryPCDto));
      when(categoryPCRepository.save(categoryPCDto)).thenReturn(categoryPCDto);

      CategoryPCDto result = categoryPCService.update(categoryPCDto);

      assertEquals(categoryPCDto, result);
      verify(categoryPCRepository).save(categoryPCDto);
    }

    @Test
    @DisplayName("Debería lanzar ResourceNotFoundException cuando no existe")
    void shouldThrowExceptionWhenDoesNotExist() {
      when(categoryPCRepository.findById(1L)).thenReturn(Optional.empty());

      assertThrows(ResourceNotFoundException.class, () -> categoryPCService.update(categoryPCDto));
    }
  }

  @Nested
  @DisplayName("Tests para el método deleteById")
  class DeleteByIdTests {
    @Test
    @DisplayName("Debería borrar la categoría cuando existe")
    void shouldDeleteWhenExists() {
      when(categoryPCRepository.findById(1L)).thenReturn(Optional.of(categoryPCDto));
      doNothing().when(categoryPCRepository).deleteById(1L);

      categoryPCService.deleteById(1L);

      verify(categoryPCRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Debería lanzar ResourceNotFoundException cuando no existe")
    void shouldThrowExceptionWhenDoesNotExist() {
      when(categoryPCRepository.findById(1L)).thenReturn(Optional.empty());

      assertThrows(ResourceNotFoundException.class, () -> categoryPCService.deleteById(1L));
    }
  }
}
