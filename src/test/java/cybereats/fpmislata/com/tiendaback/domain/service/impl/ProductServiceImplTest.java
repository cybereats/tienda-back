package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.ProductRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
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
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        CategoryProductDto catDto = new CategoryProductDto(1L, "Hamburguesas", "hamburguesas");
        productDto = new ProductDto(1L, "Teclado Mecánico", "teclado-mecanico", "Description", new BigDecimal("49.90"),
                catDto);
    }

    @Nested
    @DisplayName("Tests para el método findAll")
    class FindAllTests {
        @Test
        @DisplayName("Debería devolver una página de productos")
        void shouldReturnPageOfProducts() {
            Page<ProductDto> page = new Page<>(List.of(productDto), 1, 10, 1L);
            when(productRepository.findAll(1, 10)).thenReturn(page);

            Page<ProductDto> result = productService.findAll(1, 10);

            assertAll("Verificación de página",
                    () -> assertNotNull(result),
                    () -> assertEquals(1, result.data().size()),
                    () -> assertEquals(1L, result.totalElements()));
        }
    }

    @Nested
    @DisplayName("Tests para el método getBySlug")
    class GetBySlugTests {
        @Test
        @DisplayName("Debería devolver el producto cuando existe")
        void shouldReturnProductWhenExists() {
            when(productRepository.findBySlug("teclado-mecanico")).thenReturn(Optional.of(productDto));

            ProductDto result = productService.getBySlug("teclado-mecanico");

            assertNotNull(result);
            assertEquals("teclado-mecanico", result.slug());
        }

        @Test
        @DisplayName("Debería lanzar ResourceNotFoundException cuando no existe")
        void shouldThrowExceptionWhenNotExists() {
            when(productRepository.findBySlug("non-existent")).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> productService.getBySlug("non-existent"));
        }
    }

    @Nested
    @DisplayName("Tests para el método create")
    class CreateTests {
        @Test
        @DisplayName("Debería crear un nuevo producto")
        void shouldCreateNewProduct() {
            when(productRepository.findBySlug(productDto.slug())).thenReturn(Optional.empty());
            when(productRepository.save(productDto)).thenReturn(productDto);

            ProductDto result = productService.create(productDto);

            assertNotNull(result);
            verify(productRepository, times(1)).save(productDto);
        }

        @Test
        @DisplayName("Debería lanzar BusinessException si el producto ya existe")
        void shouldThrowExceptionIfProductExists() {
            when(productRepository.findBySlug(productDto.slug())).thenReturn(Optional.of(productDto));

            assertThrows(BusinessException.class, () -> productService.create(productDto));
            verify(productRepository, never()).save(any());
        }
    }

    @Nested
    @DisplayName("Tests para el método update")
    class UpdateTests {
        @Test
        @DisplayName("Debería actualizar un producto existente")
        void shouldUpdateExistingProduct() {
            when(productRepository.findBySlug(productDto.slug())).thenReturn(Optional.of(productDto));
            when(productRepository.save(productDto)).thenReturn(productDto);

            ProductDto result = productService.update(productDto);

            assertNotNull(result);
            verify(productRepository, times(1)).save(productDto);
        }

        @Test
        @DisplayName("Debería lanzar ResourceNotFoundException si el producto no existe")
        void shouldThrowExceptionWhenNotExists() {
            when(productRepository.findBySlug(productDto.slug())).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> productService.update(productDto));
            verify(productRepository, never()).save(any());
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteBySlug")
    class DeleteTests {
        @Test
        @DisplayName("Debería eliminar un producto por SLUG")
        void shouldDeleteBySlug() {
            when(productRepository.findBySlug("teclado-mecanico")).thenReturn(Optional.of(productDto));

            productService.deleteBySlug("teclado-mecanico");

            verify(productRepository, times(1)).deleteBySlug("teclado-mecanico");
        }

        @Test
        @DisplayName("Debería lanzar ResourceNotFoundException si el producto no existe")
        void shouldThrowExceptionWhenNotExists() {
            when(productRepository.findBySlug("non-existent")).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> productService.deleteBySlug("non-existent"));
            verify(productRepository, never()).deleteBySlug(any());
        }
    }
}
