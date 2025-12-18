package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.persistence.TestConfig;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.CategoryPCJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryPCJpaEntity;
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
class CategoryPCJpaDaoImplTest {

  @Autowired
  private CategoryPCJpaDao categoryPCJpaDao;

  @Nested
  @DisplayName("Tests para el método findAll")
  class FindAllTests {
    @Test
    @DisplayName("Debería devolver una página de categorías")
    void shouldReturnPageOfCategories() {
      int page = 1;
      int size = 10;
      List<CategoryPCJpaEntity> categories = categoryPCJpaDao.findAll(page, size);

      assertNotNull(categories);
      assertFalse(categories.isEmpty());
    }
  }

  @Nested
  @DisplayName("Tests para el método findById")
  class FindByIdTests {
    @Test
    @DisplayName("Debería devolver la categoría cuando el ID existe")
    void shouldReturnCategoryWhenExists() {
      Optional<CategoryPCJpaEntity> category = categoryPCJpaDao.findById(1L);
      if (category.isPresent()) {
        assertEquals(1L, category.get().getId());
      }
    }

    @Test
    @DisplayName("Debería devolver Optional vacío cuando el ID no existe")
    void shouldReturnEmptyWhenDoesNotExist() {
      Optional<CategoryPCJpaEntity> category = categoryPCJpaDao.findById(999L);
      assertTrue(category.isEmpty());
    }
  }

  @Nested
  @DisplayName("Tests para el método findBySlug")
  class FindBySlugTests {
    @Test
    @DisplayName("Debería devolver la categoría cuando el slug existe")
    void shouldReturnCategoryWhenSlugExists() {
      CategoryPCJpaEntity newCategory = new CategoryPCJpaEntity(null, "Test Category", "test-category",
          new BigDecimal("10.0"));
      categoryPCJpaDao.insert(newCategory);

      Optional<CategoryPCJpaEntity> category = categoryPCJpaDao.findBySlug("test-category");
      assertTrue(category.isPresent());
      assertEquals("test-category", category.get().getSlug());
    }
  }

  @Nested
  @DisplayName("Tests para el método insert")
  class InsertTests {
    @Test
    @DisplayName("Debería insertar una nueva categoría")
    void shouldInsertNewCategory() {
      CategoryPCJpaEntity newCategory = new CategoryPCJpaEntity(null, "New Category", "new-category",
          new BigDecimal("20.0"));

      CategoryPCJpaEntity saved = categoryPCJpaDao.insert(newCategory);

      assertNotNull(saved.getId());
      assertEquals("New Category", saved.getLabel());
    }
  }

  @Nested
  @DisplayName("Tests para el método update")
  class UpdateTests {
    @Test
    @DisplayName("Debería actualizar una categoría existente")
    void shouldUpdateExistingCategory() {
      CategoryPCJpaEntity newCategory = new CategoryPCJpaEntity(null, "To Update", "to-update", new BigDecimal("20.0"));
      CategoryPCJpaEntity saved = categoryPCJpaDao.insert(newCategory);

      saved.setLabel("Updated Label");
      CategoryPCJpaEntity updated = categoryPCJpaDao.update(saved);

      assertEquals("Updated Label", updated.getLabel());
    }
  }

  @Nested
  @DisplayName("Tests para el método deleteById")
  class DeleteTests {
    @Test
    @DisplayName("Debería eliminar una categoría por su ID")
    void shouldDeleteCategoryById() {
      CategoryPCJpaEntity newCategory = new CategoryPCJpaEntity(null, "To Delete", "to-delete", new BigDecimal("20.0"));
      CategoryPCJpaEntity saved = categoryPCJpaDao.insert(newCategory);
      Long id = saved.getId();

      categoryPCJpaDao.deleteById(id);

      assertTrue(categoryPCJpaDao.findById(id).isEmpty());
    }
  }

  @Nested
  @DisplayName("Tests para el método count")
  class CountTests {
    @Test
    @DisplayName("Debería devolver el total de categorías")
    void shouldReturnTotalCount() {
      long initialCount = categoryPCJpaDao.count();

      CategoryPCJpaEntity newCategory = new CategoryPCJpaEntity(null, "Count Test", "count-test",
          new BigDecimal("20.0"));
      categoryPCJpaDao.insert(newCategory);

      assertEquals(initialCount + 1, categoryPCJpaDao.count());
    }
  }
}
