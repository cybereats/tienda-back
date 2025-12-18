package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.persistence.TestConfig;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.CategoryProductJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryProductJpaEntity;
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
class CategoryProductJpaDaoImplTest {

  @Autowired
  private CategoryProductJpaDao categoryProductJpaDao;

  @Nested
  @DisplayName("Tests para el método findAll")
  class FindAllTests {
    @Test
    @DisplayName("Debería devolver una página de categorías")
    void shouldReturnPageOfCategories() {
      int page = 1;
      int size = 10;
      List<CategoryProductJpaEntity> categories = categoryProductJpaDao.findAll(page, size);

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
      Optional<CategoryProductJpaEntity> category = categoryProductJpaDao.findById(1L);
      if (category.isPresent()) {
        assertEquals(1L, category.get().getId());
      }
    }

    @Test
    @DisplayName("Debería devolver Optional vacío cuando el ID no existe")
    void shouldReturnEmptyWhenDoesNotExist() {
      Optional<CategoryProductJpaEntity> category = categoryProductJpaDao.findById(999L);
      assertTrue(category.isEmpty());
    }
  }

  @Nested
  @DisplayName("Tests para el método findBySlug")
  class FindBySlugTests {
    @Test
    @DisplayName("Debería devolver la categoría cuando el slug existe")
    void shouldReturnCategoryWhenSlugExists() {
      CategoryProductJpaEntity newCategory = new CategoryProductJpaEntity(null, "Test Category", "test-category");
      categoryProductJpaDao.insert(newCategory);

      Optional<CategoryProductJpaEntity> category = categoryProductJpaDao.findBySlug("test-category");
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
      CategoryProductJpaEntity newCategory = new CategoryProductJpaEntity(null, "New Category", "new-category");

      CategoryProductJpaEntity saved = categoryProductJpaDao.insert(newCategory);

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
      CategoryProductJpaEntity newCategory = new CategoryProductJpaEntity(null, "To Update", "to-update");
      CategoryProductJpaEntity saved = categoryProductJpaDao.insert(newCategory);

      saved.setLabel("Updated Label");
      CategoryProductJpaEntity updated = categoryProductJpaDao.update(saved);

      assertEquals("Updated Label", updated.getLabel());
    }
  }

  @Nested
  @DisplayName("Tests para el método deleteBySlug")
  class DeleteTests {
    @Test
    @DisplayName("Debería eliminar una categoría por su slug")
    void shouldDeleteCategoryBySlug() {
      CategoryProductJpaEntity newCategory = new CategoryProductJpaEntity(null, "To Delete", "to-delete");
      categoryProductJpaDao.insert(newCategory);

      categoryProductJpaDao.deleteBySlug("to-delete");

      assertTrue(categoryProductJpaDao.findBySlug("to-delete").isEmpty());
    }
  }

  @Nested
  @DisplayName("Tests para el método count")
  class CountTests {
    @Test
    @DisplayName("Debería devolver el total de categorías")
    void shouldReturnTotalCount() {
      long initialCount = categoryProductJpaDao.count();

      CategoryProductJpaEntity newCategory = new CategoryProductJpaEntity(null, "Count Test", "count-test");
      categoryProductJpaDao.insert(newCategory);

      assertEquals(initialCount + 1, categoryProductJpaDao.count());
    }
  }
}
