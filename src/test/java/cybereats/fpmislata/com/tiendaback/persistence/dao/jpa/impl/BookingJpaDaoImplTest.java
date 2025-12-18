package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.persistence.TestConfig;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.BookingJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.BookingJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryPCJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.PCJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookingJpaDaoImplTest {

    @Autowired
    private BookingJpaDao bookingJpaDao;

    private List<UserJpaEntity> expectedUsers;
    private List<PCJpaEntity> expectedPcs;
    private List<BookingJpaEntity> expectedBookings;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        CategoryPCJpaEntity categoryGaming = new CategoryPCJpaEntity(2L, "Gaming", "gaming",
                new java.math.BigDecimal("5.00"));
        CategoryPCJpaEntity categoryStreaming = new CategoryPCJpaEntity(3L, "Streaming", "streaming",
                new java.math.BigDecimal("7.00"));

        expectedUsers = List.of(
                new UserJpaEntity(1L, "Juan", "Pérez", "juan.perez@example.com", "1990-05-12", "user", "password",
                        UserRole.CLIENT),
                new UserJpaEntity(2L, "Ana", "Ruiz", "ana.ruiz@example.com", "2000-10-08", "admin", "password",
                        UserRole.ADMIN));

        expectedPcs = List.of(
                new PCJpaEntity(1L, "PC Gamer 1", "pc-gamer-1", 12, "Ryzen 5, RTX 3060, 16GB RAM", "2023-05-12",
                        "pc-gamer-1.jpg", categoryGaming),
                new PCJpaEntity(3L, "PC Streaming 1", "pc-stream-1", 5, "Ryzen 7, Capture Card, 32GB RAM", "2023-01-20",
                        "pc-stream-1.jpg", categoryStreaming));

        expectedBookings = List.of(
                new BookingJpaEntity(1L, 2, expectedUsers.get(0), expectedPcs.get(0), null),
                new BookingJpaEntity(2L, 4, expectedUsers.get(1), expectedPcs.get(1), null));
    }

    @Nested
    @DisplayName("Tests para el método findAll")
    class FindAllTests {

        @Test
        @DisplayName("Debería devolver todas las reservas (sin paginación)")
        void shouldReturnAllBookings() {
            List<BookingJpaEntity> bookings = bookingJpaDao.findAll();
            Long totalBookings = bookingJpaDao.count();

            assertFalse(bookings.isEmpty(), "La lista de reservas no debería estar vacía");
            assertTrue(bookings.size() >= totalBookings,
                    "La lista de reservas debería contener al menos " + totalBookings + " reservas");

            BookingJpaEntity first = bookings.get(0);
            assertAll("Verificación de la primera reserva sembrada",
                    () -> assertEquals(expectedBookings.get(0).getHours(), first.getHours()),
                    () -> assertEquals(expectedBookings.get(0).getUserJpaEntity().getId(),
                            first.getUserJpaEntity().getId()),
                    () -> assertEquals(expectedBookings.get(0).getPcJpaEntity().getId(),
                            first.getPcJpaEntity().getId()));
        }

        @Test
        @DisplayName("Debería devolver una página específica de reservas")
        void shouldReturnPageOfBookings() {
            int page = 1;
            int size = 1;
            List<BookingJpaEntity> bookings = bookingJpaDao.findAll(page, size);

            assertAll("Verificación de paginación",
                    () -> assertNotNull(bookings),
                    () -> assertEquals(size, bookings.size(), "El tamaño de la página debería ser " + size),
                    () -> assertEquals(1L, bookings.get(0).getId(),
                            "La primera reserva de la página 1 debería tener ID 1"));
        }

        @Test
        @DisplayName("Debería devolver la segunda página de reservas")
        void shouldReturnSecondPageOfBookings() {
            int page = 2;
            int size = 1;
            List<BookingJpaEntity> bookings = bookingJpaDao.findAll(page, size);

            assertAll("Verificación de la segunda página",
                    () -> assertNotNull(bookings),
                    () -> assertEquals(size, bookings.size()),
                    () -> assertEquals(2L, bookings.get(0).getId(),
                            "La primera reserva de la página 2 debería tener ID 2"));
        }

        @Test
        @DisplayName("Debería devolver una lista vacía si la página no tiene resultados")
        void shouldReturnEmptyListWhenPageHasNoResults() {
            int page = 100;
            int size = 10;
            List<BookingJpaEntity> bookings = bookingJpaDao.findAll(page, size);

            assertTrue(bookings.isEmpty(), "La lista debería estar vacía para una página fuera de rango");
        }
    }

    @Nested
    @DisplayName("Tests para el método findById")
    class FindByIdTests {

        @Test
        @DisplayName("Debería devolver una reserva cuando el ID existe")
        void shouldReturnBookingWhenExists() {
            Long targetId = 1L;
            Optional<BookingJpaEntity> booking = bookingJpaDao.findById(targetId);

            assertTrue(booking.isPresent(), "La reserva con ID 1 debería existir");
            assertAll("Verificación de datos de la reserva 1",
                    () -> assertEquals(targetId, booking.get().getId()),
                    () -> assertEquals(expectedBookings.get(0).getHours(), booking.get().getHours()),
                    () -> assertEquals(expectedBookings.get(0).getUserJpaEntity().getId(),
                            booking.get().getUserJpaEntity().getId()),
                    () -> assertEquals(expectedBookings.get(0).getPcJpaEntity().getId(),
                            booking.get().getPcJpaEntity().getId()));
        }

        @Test
        @DisplayName("Debería devolver un Optional vacío cuando el ID no existe")
        void shouldReturnEmptyWhenDoesNotExist() {
            Optional<BookingJpaEntity> booking = bookingJpaDao.findById(999L);
            assertTrue(booking.isEmpty(), "La reserva con ID 999 no debería existir");
        }
    }

    @Nested
    @DisplayName("Tests para el método insert")
    class InsertTests {

        @Test
        @DisplayName("Debería insertar una nueva reserva")
        void shouldInsertNewBooking() {
            UserJpaEntity user = new UserJpaEntity();
            user.setId(1L);
            PCJpaEntity pc = new PCJpaEntity(1L, "label", "slug", 1, "working_since", "runtime", "image", null);

            BookingJpaEntity newBooking = new BookingJpaEntity(null, 3, user, pc, null);

            BookingJpaEntity saved = bookingJpaDao.insert(newBooking);

            assertAll("Verificación de inserción",
                    () -> assertNotNull(saved.getId(), "El ID no debería ser nulo tras insertar"),
                    () -> assertEquals(3, saved.getHours()),
                    () -> assertEquals(1L, saved.getUserJpaEntity().getId()),
                    () -> assertEquals(1L, saved.getPcJpaEntity().getId()));
        }
    }

    @Nested
    @DisplayName("Tests para el método update")
    class UpdateTests {

        @Test
        @DisplayName("Debería actualizar una reserva existente")
        void shouldUpdateExistingBooking() {
            BookingJpaEntity existing = bookingJpaDao.findById(1L).orElseThrow();
            int newHours = existing.getHours() + 1;
            BookingJpaEntity updateData = new BookingJpaEntity(existing.getId(), newHours, existing.getUserJpaEntity(),
                    existing.getPcJpaEntity(), existing.getCreatedAt());

            BookingJpaEntity updated = bookingJpaDao.update(updateData);

            assertEquals(newHours, updated.getHours(), "Las horas deberían haberse actualizado");
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteById")
    class DeleteTests {

        @Test
        @DisplayName("Debería eliminar una reserva por su ID")
        void shouldDeleteBookingById() {
            Long idToDelete = 2L;
            assertTrue(bookingJpaDao.findById(idToDelete).isPresent(), "La reserva debería existir antes de borrar");

            bookingJpaDao.deleteById(idToDelete);

            assertTrue(bookingJpaDao.findById(idToDelete).isEmpty(), "La reserva no debería existir tras borrar");
        }
    }

    @Nested
    @DisplayName("Tests para el método count")
    class CountTests {

        @Test
        @DisplayName("Debería devolver el total de reservas")
        void shouldReturnTotalCount() {
            long count = bookingJpaDao.count();
            assertTrue(count >= 2, "El conteo debería ser al menos las 2 reservas iniciales");
        }
    }
}
