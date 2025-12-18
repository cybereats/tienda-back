package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.ProductJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryProductJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ProductJpaEntity;
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
class ProductRepositoryImplTest {

    @Mock
    private ProductJpaDao productJpaDao;

    @InjectMocks
    private ProductRepositoryImpl productRepository;

    private ProductJpaEntity productJpaEntity;
    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        CategoryProductJpaEntity catEntity = new CategoryProductJpaEntity(1L, "Hamburguesas", "hamburguesas");
        productJpaEntity = new ProductJpaEntity(1L, "Teclado Mecánico", "teclado-mecanico", "Description",
                new BigDecimal("49.90"), catEntity);

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
            when(productJpaDao.findAll(1, 10)).thenReturn(List.of(productJpaEntity));
            when(productJpaDao.count()).thenReturn(1L);

            Page<ProductDto> result = productRepository.findAll(1, 10);

            assertAll("Verificación de página",
                    () -> assertNotNull(result),
                    () -> assertEquals(1, result.data().size()),
                    () -> assertEquals(1L, result.totalElements()),
                    () -> assertEquals(productDto.slug(), result.data().get(0).slug()));
        }
    }

    @Nested
    @DisplayName("Tests para el método findById")
    class FindByIdTests {
        @Test
        @DisplayName("Debería devolver un producto por ID")
        void shouldReturnProductById() {
            when(productJpaDao.findById(1L)).thenReturn(Optional.of(productJpaEntity));

            Optional<ProductDto> result = productRepository.findById(1L);

            assertTrue(result.isPresent());
            assertEquals(productDto.slug(), result.get().slug());
        }

        @Test
        @DisplayName("Debería devolver Optional vacío cuando el ID no existe")
        void shouldReturnEmptyWhenIdDoesNotExist() {
            when(productJpaDao.findById(999L)).thenReturn(Optional.empty());
            assertTrue(productRepository.findById(999L).isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para el método findBySlug")
    class FindBySlugTests {
        @Test
        @DisplayName("Debería devolver un producto por SLUG")
        void shouldReturnProductBySlug() {
            when(productJpaDao.findBySlug("teclado-mecanico")).thenReturn(Optional.of(productJpaEntity));

            Optional<ProductDto> result = productRepository.findBySlug("teclado-mecanico");

            assertTrue(result.isPresent());
            assertEquals(productDto.id(), result.get().id());
        }
    }

    @Nested
    @DisplayName("Tests para el método save")
    class SaveTests {
        @Test
        @DisplayName("Debería insertar un nuevo producto")
        void shouldInsertNewProduct() {
            ProductDto newProductDto = new ProductDto(null, "New", "new", "Desc", new BigDecimal("10.00"),
                    productDto.category());
            when(productJpaDao.insert(any(ProductJpaEntity.class))).thenReturn(productJpaEntity);

            ProductDto result = productRepository.save(newProductDto);

            assertNotNull(result);
            verify(productJpaDao, times(1)).insert(any(ProductJpaEntity.class));
        }

        @Test
        @DisplayName("Debería actualizar un producto")
        void shouldUpdateProduct() {
            when(productJpaDao.update(any(ProductJpaEntity.class))).thenReturn(productJpaEntity);

            ProductDto result = productRepository.save(productDto);

            assertNotNull(result);
            verify(productJpaDao, times(1)).update(any(ProductJpaEntity.class));
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteBySlug")
    class DeleteTests {
        @Test
        @DisplayName("Debería eliminar por SLUG")
        void shouldDeleteBySlug() {
            productRepository.deleteBySlug("teclado-mecanico");
            verify(productJpaDao, times(1)).deleteBySlug("teclado-mecanico");
        }
    }
}
