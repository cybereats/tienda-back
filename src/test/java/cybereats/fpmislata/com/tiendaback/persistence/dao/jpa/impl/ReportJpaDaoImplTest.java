package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.persistence.TestConfig;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.ReportJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.PCJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ReportJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;
import org.junit.jupiter.api.BeforeEach;
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
class ReportJpaDaoImplTest {

    @Autowired
    private ReportJpaDao reportJpaDao;

    private List<UserJpaEntity> expectedUsers;
    private List<PCJpaEntity> expectedPcs;
    private List<ReportJpaEntity> expectedReports;

    @BeforeEach
    void setUp() {
        expectedUsers = List.of(
                new UserJpaEntity(3L, "Carlos", "Gómez", "carlos.gomez@example.com", "1985-03-15", "carlos", "password",
                        cybereats.fpmislata.com.tiendaback.domain.model.UserRole.CLIENT),
                new UserJpaEntity(4L, "User14", "Surname14", "user14@example.com", "1995-09-10", "user14", "password",
                        cybereats.fpmislata.com.tiendaback.domain.model.UserRole.CLIENT));

        expectedPcs = List.of(
                new PCJpaEntity(3L, "PC Streaming 1", "pc-stream-1", 5, "Ryzen 7, Capture Card, 32GB RAM", "2023-01-20",
                        "pc-stream-1.jpg", "AVAILABLE", null),
                new PCJpaEntity(4L, "PC Básico 1", "pc-basic-1", 20, "i3, Integrada, 8GB RAM", "2021-08-10",
                        "pc-basic-1.jpg", "AVAILABLE", null));

        expectedReports = List.of(
                new ReportJpaEntity(1L, "3", "Pantalla parpadeando, la pantalla parpadea constantemente.",
                        "Pantalla parpadeando", "PENDING", "2025-01-15 10:00:00", expectedUsers.get(0),
                        expectedPcs.get(0)),
                new ReportJpaEntity(10L, "3",
                        "Configuración incorrecta, la computadora tiene una configuración incorrecta.",
                        "Configuración incorrecta", "RESOLVED", "2025-04-25 16:00:00", expectedUsers.get(1),
                        expectedPcs.get(1)));
    }

    @Nested
    @DisplayName("Tests para el método findAll")
    class FindAllTests {
        @Test
        @DisplayName("Debería devolver una página de reportes")
        void shouldReturnPageOfReports() {
            List<ReportJpaEntity> reports = reportJpaDao.findAll(1, 10);
            assertNotNull(reports);
            assertFalse(reports.isEmpty());
            assertTrue(reports.size() >= 10);

            ReportJpaEntity first = reports.get(0);
            assertAll("Verificación del primer reporte sembrado",
                    () -> assertEquals(expectedReports.get(0).getId(), first.getId()),
                    () -> assertEquals(expectedReports.get(0).getSubject(), first.getSubject()),
                    () -> assertEquals(expectedReports.get(0).getUser().getId(), first.getUser().getId()),
                    () -> assertEquals(expectedReports.get(0).getPc().getId(), first.getPc().getId()));
        }
    }

    @Nested
    @DisplayName("Tests para el método findById")
    class FindByIdTests {
        @Test
        @DisplayName("Debería devolver un reporte cuando el ID existe")
        void shouldReturnReportWhenIdExists() {
            Long targetId = 1L;
            Optional<ReportJpaEntity> report = reportJpaDao.findById(targetId);

            assertTrue(report.isPresent());
            assertAll("Verificación de datos del reporte 1",
                    () -> assertEquals(targetId, report.get().getId()),
                    () -> assertEquals(expectedReports.get(0).getSubject(), report.get().getSubject()),
                    () -> assertEquals(expectedReports.get(0).getUser().getId(), report.get().getUser().getId()),
                    () -> assertEquals(expectedReports.get(0).getPc().getId(), report.get().getPc().getId()));
        }

        @Test
        @DisplayName("Debería devolver Optional vacío cuando el ID no existe")
        void shouldReturnEmptyWhenIdDoesNotExist() {
            assertTrue(reportJpaDao.findById(999L).isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para métodos de búsqueda personalizados")
    class CustomSearchTests {
        @Test
        @DisplayName("Debería buscar por ID de usuario")
        void shouldFindByUserId() {
            Long userId = 3L;
            List<ReportJpaEntity> reports = reportJpaDao.findByUserId(userId);
            assertFalse(reports.isEmpty());
            reports.forEach(r -> assertEquals(userId, r.getUser().getId()));
        }

        @Test
        @DisplayName("Debería buscar por ID de PC")
        void shouldFindByPCId() {
            Long pcId = 3L;
            List<ReportJpaEntity> reports = reportJpaDao.findByPCId(pcId);
            assertFalse(reports.isEmpty());
            reports.forEach(r -> assertEquals(pcId, r.getPc().getId()));
        }

        @Test
        @DisplayName("Debería buscar por estado")
        void shouldFindByStatus() {
            String status = "PENDING";
            List<ReportJpaEntity> reports = reportJpaDao.findByStatus(status);
            assertFalse(reports.isEmpty());
            reports.forEach(r -> assertEquals(status, r.getStatus()));
        }
    }

    @Nested
    @DisplayName("Tests para el método insert")
    class InsertTests {
        @Test
        @DisplayName("Debería insertar un nuevo reporte")
        void shouldInsertReport() {
            UserJpaEntity user = new UserJpaEntity();
            user.setId(1L);
            PCJpaEntity pc = new PCJpaEntity(1L, "label", "slug", 10, "specs", "2023-01-01", "image", "AVAILABLE",
                    null);

            ReportJpaEntity newReport = new ReportJpaEntity(null, "1", "Desc", "Subject", "OPEN", "2025-01-01", user,
                    pc);
            ReportJpaEntity saved = reportJpaDao.insert(newReport);

            assertAll("Verificación de inserción",
                    () -> assertNotNull(saved.getId()),
                    () -> assertEquals("Subject", saved.getSubject()),
                    () -> assertEquals(1L, saved.getUser().getId()),
                    () -> assertEquals(1L, saved.getPc().getId()));
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteById")
    class DeleteTests {
        @Test
        @DisplayName("Debería eliminar un reporte")
        void shouldDeleteReport() {
            Long idToDelete = 10L;
            assertTrue(reportJpaDao.findById(idToDelete).isPresent());

            reportJpaDao.deleteById(idToDelete);

            assertTrue(reportJpaDao.findById(idToDelete).isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para el método count")
    class CountTests {
        @Test
        @DisplayName("Debería devolver el total de reportes")
        void shouldReturnTotalCount() {
            long count = reportJpaDao.count();
            assertTrue(count >= 10);
        }
    }
}
