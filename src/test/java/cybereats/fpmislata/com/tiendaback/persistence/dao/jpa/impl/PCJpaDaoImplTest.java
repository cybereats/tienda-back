package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.persistence.TestConfig;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.PCJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryPCJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.PCJpaEntity;
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
class PCJpaDaoImplTest {

    @Autowired
    private PCJpaDao pcJpaDao;

    private List<PCJpaEntity> expectedPcs;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        CategoryPCJpaEntity categoryGaming = new CategoryPCJpaEntity(2L, "Gaming", "gaming",
                new BigDecimal("5.00"));
        CategoryPCJpaEntity categoryStreaming = new CategoryPCJpaEntity(3L, "Streaming", "streaming",
                new BigDecimal("7.00"));

        expectedPcs = List.of(
                new PCJpaEntity(1L, "PC Gamer 1", "pc-gamer-1", 12, "Ryzen 5, RTX 3060, 16GB RAM", "2023-05-12",
                        "pc-gamer-1.jpg", "AVAILABLE", categoryGaming),
                new PCJpaEntity(2L, "PC Gamer 2", "pc-gamer-2", 8, "Ryzen 5, RTX 3050, 8GB RAM", "2023-06-15",
                        "pc-gamer-2.jpg", "AVAILABLE", categoryGaming),
                new PCJpaEntity(3L, "PC Streaming 1", "pc-stream-1", 5, "Ryzen 7, Capture Card, 32GB RAM", "2023-01-20",
                        "pc-stream-1.jpg", "AVAILABLE", categoryStreaming));
    }

    @Nested
    @DisplayName("Tests para el método findAll")
    class FindAllTests {

        @Test
        @DisplayName("Debería devolver una página específica de PCs")
        void shouldReturnPageOfPCs() {
            int page = 1;
            int size = 1;
            List<PCJpaEntity> pcs = pcJpaDao.findAll(page, size);

            assertAll("Verificación de paginación",
                    () -> assertNotNull(pcs),
                    () -> assertEquals(size, pcs.size()),
                    () -> assertEquals(1L, pcs.get(0).getId()));
        }
    }

    @Nested
    @DisplayName("Tests para el método findById")
    class FindByIdTests {

        @Test
        @DisplayName("Debería devolver un PC cuando el ID existe")
        void shouldReturnPCWhenIdExists() {
            Long targetId = 1L;
            Optional<PCJpaEntity> pc = pcJpaDao.findById(targetId);

            assertTrue(pc.isPresent());
            assertAll("Verificación de datos del PC 1",
                    () -> assertEquals(targetId, pc.get().getId()),
                    () -> assertEquals(expectedPcs.get(0).getSlug(), pc.get().getSlug()));
        }

        @Test
        @DisplayName("Debería devolver Optional vacío cuando el ID no existe")
        void shouldReturnEmptyWhenIdDoesNotExist() {
            assertTrue(pcJpaDao.findById(999L).isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para el método findBySlug")
    class FindBySlugTests {

        @Test
        @DisplayName("Debería devolver un PC cuando el SLUG existe")
        void shouldReturnPCWhenSlugExists() {
            String targetSlug = "pc-gamer-1";
            Optional<PCJpaEntity> pc = pcJpaDao.findBySlug(targetSlug);

            assertTrue(pc.isPresent());
            assertEquals(targetSlug, pc.get().getSlug());
        }

        @Test
        @DisplayName("Debería devolver Optional vacío cuando el SLUG no existe")
        void shouldReturnEmptyWhenSlugDoesNotExist() {
            assertTrue(pcJpaDao.findBySlug("non-existent").isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para el método insert")
    class InsertTests {

        @Test
        @DisplayName("Debería insertar un nuevo PC")
        void shouldInsertNewPC() {
            CategoryPCJpaEntity category = new CategoryPCJpaEntity(2L, "Gaming", "gaming", new BigDecimal("5.0"));
            PCJpaEntity newPC = new PCJpaEntity(null, "New PC", "new-pc", 10, "Specs", "2023-01-01", "new-pc.jpg",
                    "AVAILABLE", category);

            PCJpaEntity saved = pcJpaDao.insert(newPC);

            assertAll("Verificación de inserción",
                    () -> assertNotNull(saved.getId()),
                    () -> assertEquals("new-pc", saved.getSlug()),
                    () -> assertEquals(2L, saved.getCategory().getId()));
        }
    }

    @Nested
    @DisplayName("Tests para el método update")
    class UpdateTests {

        @Test
        @DisplayName("Debería actualizar un PC existente")
        void shouldUpdateExistingPC() {
            PCJpaEntity existing = pcJpaDao.findById(1L).orElseThrow();
            String newLabel = "Updated Label";
            PCJpaEntity updateData = new PCJpaEntity(existing.getId(), newLabel, existing.getSlug(),
                    existing.getRuntime(), existing.getSpecs(), existing.getWorkingSince(), existing.getImage(),
                    existing.getStatus(), existing.getCategory());

            PCJpaEntity updated = pcJpaDao.update(updateData);

            assertEquals(newLabel, updated.getLabel());
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteById")
    class DeleteByIdTests {

        @Test
        @DisplayName("Debería eliminar un PC por su ID")
        void shouldDeletePCById() {
            Long idToDelete = 2L;
            assertTrue(pcJpaDao.findById(idToDelete).isPresent());

            pcJpaDao.deleteById(idToDelete);

            assertTrue(pcJpaDao.findById(idToDelete).isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteBySlug")
    class DeleteBySlugTests {

        @Test
        @DisplayName("Debería eliminar un PC por su SLUG")
        void shouldDeletePCBySlug() {
            String slugToDelete = "pc-stream-1";
            assertTrue(pcJpaDao.findBySlug(slugToDelete).isPresent());

            pcJpaDao.deleteBySlug(slugToDelete);

            assertTrue(pcJpaDao.findBySlug(slugToDelete).isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para el método count")
    class CountTests {

        @Test
        @DisplayName("Debería devolver el total de PCs")
        void shouldReturnTotalCount() {
            long count = pcJpaDao.count();
            assertTrue(count >= 3);
        }
    }
}
