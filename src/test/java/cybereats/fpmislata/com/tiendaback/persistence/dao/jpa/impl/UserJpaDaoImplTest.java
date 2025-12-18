package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.persistence.TestConfig;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.UserJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserJpaDaoImplTest {

  @Autowired
  private UserJpaDao userJpaDao;

  @Nested
  @DisplayName("Tests para el método findById")
  @Sql(statements = "INSERT INTO users (id, name, surname, email, born_date, username, password, role) VALUES (999, 'Test', 'User', 'test@test.com', '1990-01-01', 'testuser', 'pass', 'CLIENT')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(statements = "DELETE FROM users WHERE id = 999", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  class FindByIdTests {
    @Test
    @DisplayName("Debería encontrar un usuario por ID")
    void shouldFindById() {
      Optional<UserJpaEntity> result = userJpaDao.findById(999L);
      assertTrue(result.isPresent());
      assertEquals("testuser", result.get().getUsername());
    }
  }

  @Nested
  @DisplayName("Tests para el método findByUsername")
  @Sql(statements = "INSERT INTO users (id, name, surname, email, born_date, username, password, role) VALUES (999, 'Test', 'User', 'test@test.com', '1990-01-01', 'testuser', 'pass', 'CLIENT')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(statements = "DELETE FROM users WHERE id = 999", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  class FindByUsernameTests {
    @Test
    @DisplayName("Debería encontrar un usuario por nombre de usuario")
    void shouldFindByUsername() {
      Optional<UserJpaEntity> result = userJpaDao.findByUsername("testuser");
      assertTrue(result.isPresent());
      assertEquals(999L, result.get().getId());
    }
  }

  @Nested
  @DisplayName("Tests para el método findAll")
  @Sql(statements = "INSERT INTO users (id, name, surname, email, born_date, username, password, role) VALUES (999, 'Test', 'User', 'test@test.com', '1990-01-01', 'testuser', 'pass', 'CLIENT')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(statements = "DELETE FROM users WHERE id = 999", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  class FindAllTests {
    @Test
    @DisplayName("Debería devolver todos los usuarios")
    void shouldFindAll() {
      List<UserJpaEntity> result = userJpaDao.findAll(1, 100);
      assertFalse(result.isEmpty());
      assertTrue(result.size() >= 1);
    }
  }

  @Nested
  @DisplayName("Tests para el método count")
  @Sql(statements = "INSERT INTO users (id, name, surname, email, born_date, username, password, role) VALUES (999, 'Test', 'User', 'test@test.com', '1990-01-01', 'testuser', 'pass', 'CLIENT')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(statements = "DELETE FROM users WHERE id = 999", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  class CountTests {
    @Test
    @DisplayName("Debería contar el total de usuarios")
    void shouldCount() {
      long count = userJpaDao.count();
      assertTrue(count >= 1);
    }
  }

  @Nested
  @DisplayName("Tests para el método insert")
  @Sql(statements = "DELETE FROM users WHERE username = 'newuser'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  class InsertTests {
    @Test
    @DisplayName("Debería insertar un nuevo usuario")
    void shouldInsert() {
      UserJpaEntity newUser = new UserJpaEntity(null, "New", "User", "new@test.com", "1990-01-01", "newuser", "pass",
          UserRole.CLIENT);
      UserJpaEntity result = userJpaDao.insert(newUser);
      assertNotNull(result.getId());
      assertEquals("newuser", result.getUsername());
    }
  }

  @Nested
  @DisplayName("Tests para el método update")
  @Sql(statements = "INSERT INTO users (id, name, surname, email, born_date, username, password, role) VALUES (999, 'Test', 'User', 'test@test.com', '1990-01-01', 'testuser', 'pass', 'CLIENT')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(statements = "DELETE FROM users WHERE id = 999", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  class UpdateTests {
    @Test
    @DisplayName("Debería actualizar un usuario")
    void shouldUpdate() {
      UserJpaEntity user = userJpaDao.findById(999L).get();
      user.setName("Updated");
      UserJpaEntity result = userJpaDao.update(user);
      assertEquals("Updated", result.getName());
    }
  }

  @Nested
  @DisplayName("Tests para el método deleteById")
  @Sql(statements = "INSERT INTO users (id, name, surname, email, born_date, username, password, role) VALUES (999, 'Test', 'User', 'test@test.com', '1990-01-01', 'testuser', 'pass', 'CLIENT')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(statements = "DELETE FROM users WHERE id = 999", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  class DeleteTests {
    @Test
    @DisplayName("Debería eliminar un usuario")
    void shouldDelete() {
      userJpaDao.deleteById(999L);
      Optional<UserJpaEntity> result = userJpaDao.findById(999L);
      assertFalse(result.isPresent());
    }
  }
}
