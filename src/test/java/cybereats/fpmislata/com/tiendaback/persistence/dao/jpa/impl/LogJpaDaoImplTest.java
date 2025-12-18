package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.persistence.TestConfig;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.LogJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.LogJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LogJpaDaoImplTest {

  @Autowired
  private LogJpaDao logJpaDao;

  @Nested
  @DisplayName("Tests para el método findAll")
  class FindAllTests {
    @Test
    @DisplayName("Debería devolver una página de logs")
    void shouldReturnPageOfLogs() {
      int page = 1;
      int size = 10;
      List<LogJpaEntity> logs = logJpaDao.findAll(page, size);

      assertNotNull(logs);
    }
  }

  @Nested
  @DisplayName("Tests para el método findById")
  class FindByIdTests {
    @Test
    @DisplayName("Debería devolver el log cuando el ID existe")
    void shouldReturnLogWhenExists() {
      LogJpaEntity log = new LogJpaEntity(null, "Test info", Instant.now().toString());
      LogJpaEntity saved = logJpaDao.insert(log);

      Optional<LogJpaEntity> found = logJpaDao.findById(saved.getId());
      assertTrue(found.isPresent());
      assertEquals(saved.getId(), found.get().getId());
    }

    @Test
    @DisplayName("Debería devolver Optional vacío cuando el ID no existe")
    void shouldReturnEmptyWhenDoesNotExist() {
      Optional<LogJpaEntity> log = logJpaDao.findById(999L);
      assertTrue(log.isEmpty());
    }
  }

  @Nested
  @DisplayName("Tests para el método insert")
  class InsertTests {
    @Test
    @DisplayName("Debería insertar un nuevo log")
    void shouldInsertNewLog() {
      LogJpaEntity log = new LogJpaEntity(null, "New Log", Instant.now().toString());

      LogJpaEntity saved = logJpaDao.insert(log);

      assertNotNull(saved.getId());
      assertEquals("New Log", saved.getInfo());
    }
  }

  @Nested
  @DisplayName("Tests para el método update")
  class UpdateTests {
    @Test
    @DisplayName("Debería actualizar un log existente")
    void shouldUpdateExistingLog() {
      LogJpaEntity log = new LogJpaEntity(null, "Before Update", Instant.now().toString());
      LogJpaEntity saved = logJpaDao.insert(log);

      saved.setInfo("After Update");
      LogJpaEntity updated = logJpaDao.update(saved);

      assertEquals("After Update", updated.getInfo());
    }
  }

  @Nested
  @DisplayName("Tests para el método deleteById")
  class DeleteTests {
    @Test
    @DisplayName("Debería eliminar un log por su ID")
    void shouldDeleteLogById() {
      LogJpaEntity log = new LogJpaEntity(null, "To Delete", Instant.now().toString());
      LogJpaEntity saved = logJpaDao.insert(log);
      Long id = saved.getId();

      logJpaDao.deleteById(id);

      assertTrue(logJpaDao.findById(id).isEmpty());
    }
  }

  @Nested
  @DisplayName("Tests para el método count")
  class CountTests {
    @Test
    @DisplayName("Debería devolver el total de logs")
    void shouldReturnTotalCount() {
      long initialCount = logJpaDao.count();

      LogJpaEntity log = new LogJpaEntity(null, "Count Test", Instant.now().toString());
      logJpaDao.insert(log);

      assertEquals(initialCount + 1, logJpaDao.count());
    }
  }
}
