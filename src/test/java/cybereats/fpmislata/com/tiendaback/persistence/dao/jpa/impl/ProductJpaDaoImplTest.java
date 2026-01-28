package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.persistence.TestConfig;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.ProductJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryProductJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ProductJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductJpaDaoImplTest {

    @Autowired
    private ProductJpaDao productJpaDao;

    private List<ProductJpaEntity> expectedProducts;

    @BeforeEach
    void setUp() {
        CategoryProductJpaEntity cat1 = new CategoryProductJpaEntity(1L, "Hamburguesas", "hamburguesas");
        CategoryProductJpaEntity cat2 = new CategoryProductJpaEntity(2L, "Acompañamientos", "acompanamientos");

        expectedProducts = List.of(
                new ProductJpaEntity(1L, "Teclado Mecánico", "teclado-mecanico", "Teclado con switches rojos.",
                        new BigDecimal("49.90"), "image.png", cat1),
                new ProductJpaEntity(2L, "Ratón Gaming", "raton-gaming", "Ratón RGB de alta precisión.",
                        new BigDecimal("29.90"), "image.png", cat1),
                new ProductJpaEntity(3L, "Tarjeta Gráfica GTX 1660", "gtx-1660", "GeForce GTX 1660 6GB.",
                        new BigDecimal("229.00"), "image.png", cat2));
    }

    @Nested
    @DisplayName("Tests para el método findAll")
    class FindAllTests {

        @Test
        @DisplayName("Debería devolver una página específica de productos")
        void shouldReturnPageOfProducts() {
            int page = 1;
            int size = 1;
            List<ProductJpaEntity> products = productJpaDao.findAll(page, size);

            assertAll("Verificación de paginación",
                    () -> assertNotNull(products),
                    () -> assertEquals(size, products.size()),
                    () -> assertEquals(1L, products.get(0).getId()));
        }
    }

    @Nested
    @DisplayName("Tests para el método findById")
    class FindByIdTests {

        @Test
        @DisplayName("Debería devolver un producto cuando el ID existe")
        void shouldReturnProductWhenIdExists() {
            Long targetId = 1L;
            Optional<ProductJpaEntity> product = productJpaDao.findById(targetId);

            assertTrue(product.isPresent());
            assertAll("Verificación de datos del producto 1",
                    () -> assertEquals(targetId, product.get().getId()),
                    () -> assertEquals(expectedProducts.get(0).getSlug(), product.get().getSlug()),
                    () -> assertEquals(expectedProducts.get(0).getCategoryProductJpaEntity().getId(),
                            product.get().getCategoryProductJpaEntity().getId()));
        }

        @Test
        @DisplayName("Debería devolver Optional vacío cuando el ID no existe")
        void shouldReturnEmptyWhenIdDoesNotExist() {
            assertTrue(productJpaDao.findById(999L).isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para el método findBySlug")
    class FindBySlugTests {

        @Test
        @DisplayName("Debería devolver un producto cuando el SLUG existe")
        void shouldReturnProductWhenSlugExists() {
            String targetSlug = "teclado-mecanico";
            Optional<ProductJpaEntity> product = productJpaDao.findBySlug(targetSlug);

            assertTrue(product.isPresent());
            assertEquals(targetSlug, product.get().getSlug());
        }

        @Test
        @DisplayName("Debería devolver Optional vacío cuando el SLUG no existe")
        void shouldReturnEmptyWhenSlugDoesNotExist() {
            assertTrue(productJpaDao.findBySlug("non-existent").isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para el método insert")
    class InsertTests {

        @Test
        @DisplayName("Debería insertar un nuevo producto")
        void shouldInsertNewProduct() {
            CategoryProductJpaEntity category = new CategoryProductJpaEntity(1L, "Hamburguesas", "hamburguesas");
            ProductJpaEntity newProduct = new ProductJpaEntity(null, "New Product", "new-product", "Description",
                    new BigDecimal("10.00"), "image.png", category);

            ProductJpaEntity saved = productJpaDao.insert(newProduct);

            assertAll("Verificación de inserción",
                    () -> assertNotNull(saved.getId()),
                    () -> assertEquals("new-product", saved.getSlug()),
                    () -> assertEquals(1L, saved.getCategoryProductJpaEntity().getId()));
        }
    }

    @Nested
    @DisplayName("Tests para el método update")
    class UpdateTests {

        @Test
        @DisplayName("Debería actualizar un producto existente")
        void shouldUpdateExistingProduct() {
            ProductJpaEntity existing = productJpaDao.findById(1L).orElseThrow();
            String newLabel = "Updated Label";
            ProductJpaEntity updateData = new ProductJpaEntity(existing.getId(), newLabel, existing.getSlug(),
                    existing.getDescription(), existing.getPrice(), "image.png",
                    existing.getCategoryProductJpaEntity());

            ProductJpaEntity updated = productJpaDao.update(updateData);

            assertEquals(newLabel, updated.getLabel());
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteById")
    class DeleteByIdTests {

        @Test
        @DisplayName("Debería eliminar un producto por su ID")
        void shouldDeleteProductById() {
            Long idToDelete = 5L;
            assertTrue(productJpaDao.findById(idToDelete).isPresent());

            productJpaDao.deleteById(idToDelete);

            assertTrue(productJpaDao.findById(idToDelete).isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteBySlug")
    class DeleteBySlugTests {

        @Test
        @DisplayName("Debería eliminar un producto por su SLUG")
        void shouldDeleteProductBySlug() {
            String slugToDelete = "ram-16gb";
            assertTrue(productJpaDao.findBySlug(slugToDelete).isPresent());

            productJpaDao.deleteBySlug(slugToDelete);

            assertTrue(productJpaDao.findBySlug(slugToDelete).isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para el método count")
    class CountTests {

        @Test
        @DisplayName("Debería devolver el total de productos")
        void shouldReturnTotalCount() {
            long count = productJpaDao.count();
            assertTrue(count >= 5);
        }
    }
}
