package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.persistence.TestConfig;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.OrderItemJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.OrderItemJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ProductJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserOrderJpaEntity;
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
class OrderItemJpaDaoImplTest {

    @Autowired
    private OrderItemJpaDao orderItemJpaDao;

    private List<ProductJpaEntity> expectedProducts;
    private List<OrderItemJpaEntity> expectedOrderItems;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        expectedProducts = List.of(
                new ProductJpaEntity(1L, "Teclado Mecánico", "teclado-mecanico", "Teclado con switches rojos.",
                        new BigDecimal("49.90"), null),
                new ProductJpaEntity(2L, "Ratón Gaming", "raton-gaming", "Ratón RGB de alta precisión.",
                        new BigDecimal("29.90"), null));

        expectedOrderItems = List.of(
                new OrderItemJpaEntity(1L, 1, expectedProducts.get(0), null),
                new OrderItemJpaEntity(2L, 2, expectedProducts.get(1), null));
    }

    @Nested
    @DisplayName("Tests para el método findAll")
    class FindAllTests {

        @Test
        @DisplayName("Debería devolver todos los items de pedido (sin paginación)")
        void shouldReturnAllOrderItems() {
            List<OrderItemJpaEntity> items = orderItemJpaDao.findAll();
            long totalCount = orderItemJpaDao.count();

            assertFalse(items.isEmpty());
            assertTrue(items.size() >= totalCount);

            OrderItemJpaEntity first = items.get(0);
            assertAll("Verificación del primer item sembrado",
                    () -> assertEquals(expectedOrderItems.get(0).getQuantity(), first.getQuantity()),
                    () -> assertEquals(expectedOrderItems.get(0).getProduct().getId(), first.getProduct().getId()));
        }

        @Test
        @DisplayName("Debería devolver una página específica de items")
        void shouldReturnPageOfOrderItems() {
            int page = 1;
            int size = 1;
            List<OrderItemJpaEntity> items = orderItemJpaDao.findAll(page, size);

            assertAll("Verificación de paginación",
                    () -> assertNotNull(items),
                    () -> assertEquals(size, items.size(), "El tamaño de la página debería ser " + size),
                    () -> assertEquals(1L, items.get(0).getId(), "El primer item de la página 1 debería tener ID 1"));
        }

        @Test
        @DisplayName("Debería devolver la segunda página de items")
        void shouldReturnSecondPageOfOrderItems() {
            int page = 2;
            int size = 1;
            List<OrderItemJpaEntity> items = orderItemJpaDao.findAll(page, size);

            assertAll("Verificación de la segunda página",
                    () -> assertNotNull(items),
                    () -> assertEquals(size, items.size()),
                    () -> assertEquals(2L, items.get(0).getId(), "El primer item de la página 2 debería tener ID 2"));
        }

        @Test
        @DisplayName("Debería devolver una lista vacía si la página no tiene resultados")
        void shouldReturnEmptyListWhenPageHasNoResults() {
            int page = 100;
            int size = 10;
            List<OrderItemJpaEntity> items = orderItemJpaDao.findAll(page, size);

            assertTrue(items.isEmpty(), "La lista debería estar vacía para una página fuera de rango");
        }
    }

    @Nested
    @DisplayName("Tests para el método findById")
    class FindByIdTests {

        @Test
        @DisplayName("Debería devolver un item cuando el ID existe")
        void shouldReturnItemWhenExists() {
            Long targetId = 1L;
            Optional<OrderItemJpaEntity> item = orderItemJpaDao.findById(targetId);

            assertTrue(item.isPresent());
            assertAll("Verificación de datos del item",
                    () -> assertEquals(targetId, item.get().getId()),
                    () -> assertEquals(expectedOrderItems.get(0).getQuantity(), item.get().getQuantity()),
                    () -> assertEquals(expectedOrderItems.get(0).getProduct().getId(),
                            item.get().getProduct().getId()));
        }

        @Test
        @DisplayName("Debería devolver un Optional vacío cuando el ID no existe")
        void shouldReturnEmptyWhenDoesNotExist() {
            Optional<OrderItemJpaEntity> item = orderItemJpaDao.findById(9999L);
            assertTrue(item.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para el método insert")
    class InsertTests {

        @Test
        @DisplayName("Debería insertar un nuevo item de pedido")
        void shouldInsertNewOrderItem() {
            ProductJpaEntity product = new ProductJpaEntity(1L, "Product", "product", "Description",
                    new BigDecimal("10.0"),
                    null);
            UserOrderJpaEntity order = new UserOrderJpaEntity();
            order.setId(3L);

            OrderItemJpaEntity newItem = new OrderItemJpaEntity(null, 5, product, order);
            OrderItemJpaEntity saved = orderItemJpaDao.insert(newItem);

            assertAll("Verificación de inserción",
                    () -> assertNotNull(saved.getId()),
                    () -> assertEquals(5, saved.getQuantity()),
                    () -> assertNotNull(saved.getProduct()),
                    () -> assertEquals(1L, saved.getProduct().getId()),
                    () -> assertNotNull(saved.getUser_order_id()),
                    () -> assertEquals(3L, saved.getUser_order_id().getId()));
        }
    }

    @Nested
    @DisplayName("Tests para el método update")
    class UpdateTests {

        @Test
        @DisplayName("Debería actualizar un item existente")
        void shouldUpdateExistingOrderItem() {
            OrderItemJpaEntity existing = orderItemJpaDao.findById(1L).orElseThrow();
            existing.setQuantity(50);

            OrderItemJpaEntity updated = orderItemJpaDao.update(existing);

            assertEquals(50, updated.getQuantity());
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteById")
    class DeleteTests {

        @Test
        @DisplayName("Debería eliminar un item por ID")
        void shouldDeleteItemById() {
            Long idToDelete = 2L;
            assertTrue(orderItemJpaDao.findById(idToDelete).isPresent());

            orderItemJpaDao.deleteById(idToDelete);

            assertTrue(orderItemJpaDao.findById(idToDelete).isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para el método count")
    class CountTests {

        @Test
        @DisplayName("Debería devolver el total de items de pedido")
        void shouldReturnTotalCount() {
            long count = orderItemJpaDao.count();
            assertTrue(count >= 2, "El conteo debería ser al menos los 2 items iniciales");
        }
    }
}
