package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.DeliveryType;
import cybereats.fpmislata.com.tiendaback.domain.model.OrderStatus;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.persistence.TestConfig;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.UserOrderJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserOrderJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserOrderJpaDaoImplTest {

    @Autowired
    private UserOrderJpaDao userOrderJpaDao;

    private List<UserJpaEntity> expectedUsers;
    private List<UserOrderJpaEntity> expectedOrders;

    @BeforeEach
    void setUp() {
        expectedUsers = List.of(
                new UserJpaEntity(3L, "Carlos", "Gómez", "carlos.gomez@example.com", "1985-03-15", "carlos", "password",
                        UserRole.CLIENT),
                new UserJpaEntity(4L, "User14", "Surname14", "user14@example.com", "1995-09-10", "user14", "password",
                        UserRole.CLIENT));

        expectedOrders = List.of(
                new UserOrderJpaEntity(1L, expectedUsers.get(0), null, OrderStatus.CONFIRMED,
                        DeliveryType.PICKUP, LocalDateTime.parse("2025-01-15T10:00:00")),
                new UserOrderJpaEntity(2L, expectedUsers.get(1), null, OrderStatus.SHIPPED,
                        DeliveryType.PICKUP, LocalDateTime.parse("2025-01-20T14:00:00")));
    }

    @Nested
    @DisplayName("Tests para el método findAll")
    class FindAllTests {

        @Test
        @DisplayName("Debería devolver todos los pedidos (sin paginación)")
        void shouldReturnAllOrders() {
            List<UserOrderJpaEntity> orders = userOrderJpaDao.findAll();
            assertFalse(orders.isEmpty());
            assertTrue(orders.size() >= 8);
        }

        @Test
        @DisplayName("Debería devolver una página de pedidos")
        void shouldReturnPageOfOrders() {
            List<UserOrderJpaEntity> orders = userOrderJpaDao.findAll(1, 10);
            assertNotNull(orders);
            assertFalse(orders.isEmpty());

            UserOrderJpaEntity first = orders.get(0);
            assertAll("Verificación del primer pedido sembrado",
                    () -> assertEquals(expectedOrders.get(0).getId(), first.getId()),
                    () -> assertEquals(expectedOrders.get(0).getStatus(), first.getStatus()),
                    () -> assertEquals(expectedOrders.get(0).getUser().getId(), first.getUser().getId()));
        }
    }

    @Nested
    @DisplayName("Tests para el método findById")
    class FindByIdTests {

        @Test
        @DisplayName("Debería devolver un pedido cuando el ID existe")
        void shouldReturnOrderWhenIdExists() {
            Optional<UserOrderJpaEntity> order = userOrderJpaDao.findById(1L);
            assertTrue(order.isPresent());
            assertAll("Verificación de datos del pedido 1",
                    () -> assertEquals(1L, order.get().getId()),
                    () -> assertEquals(OrderStatus.CONFIRMED, order.get().getStatus()),
                    () -> assertEquals(3L, order.get().getUser().getId()));
        }

        @Test
        @DisplayName("Debería devolver Optional vacío cuando el ID no existe")
        void shouldReturnEmptyWhenIdDoesNotExist() {
            Optional<UserOrderJpaEntity> order = userOrderJpaDao.findById(999L);
            assertTrue(order.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para el método insert")
    class InsertTests {

        @Test
        @DisplayName("Debería insertar un nuevo pedido")
        void shouldInsertOrder() {
            UserJpaEntity user = new UserJpaEntity();
            user.setId(1L);
            UserOrderJpaEntity newOrder = new UserOrderJpaEntity(null, user, null, OrderStatus.CONFIRMED,
                    DeliveryType.PICKUP, LocalDateTime.now());

            UserOrderJpaEntity saved = userOrderJpaDao.insert(newOrder);

            assertNotNull(saved.getId());
            assertEquals(OrderStatus.CONFIRMED, saved.getStatus());
            assertEquals(1L, saved.getUser().getId());
        }
    }

    @Nested
    @DisplayName("Tests para el método update")
    class UpdateTests {

        @Test
        @DisplayName("Debería actualizar un pedido existente")
        void shouldUpdateOrder() {
            UserOrderJpaEntity existing = userOrderJpaDao.findById(1L).orElseThrow();
            existing.setStatus(OrderStatus.DELIVERED);

            UserOrderJpaEntity updated = userOrderJpaDao.update(existing);

            assertEquals(OrderStatus.DELIVERED, updated.getStatus());
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteById")
    class DeleteTests {

        @Test
        @DisplayName("Debería eliminar un pedido")
        void shouldDeleteOrder() {
            Long idToDelete = 8L;
            assertTrue(userOrderJpaDao.findById(idToDelete).isPresent());

            userOrderJpaDao.deleteById(idToDelete);

            assertTrue(userOrderJpaDao.findById(idToDelete).isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para el método count")
    class CountTests {

        @Test
        @DisplayName("Debería devolver el total de pedidos")
        void shouldReturnTotalCount() {
            long count = userOrderJpaDao.count();
            assertTrue(count >= 8);
        }
    }
}
