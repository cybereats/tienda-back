package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.CategoryPCJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryPCJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class CategoryPCJpaDaoImpl implements CategoryPCJpaDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CategoryPCJpaEntity> findAll(int page, int size) {
        int pageIndex = Math.max(page - 1, 0);

        String sql = "SELECT b FROM CategoryPCJpaEntity b ORDER BY b.id";
        TypedQuery<CategoryPCJpaEntity> categoryPCJpaEntityPage = entityManager
                .createQuery(sql, CategoryPCJpaEntity.class)
                .setFirstResult(pageIndex * size)
                .setMaxResults(size);

        return categoryPCJpaEntityPage.getResultList();
    }

    @Override
    public Optional<CategoryPCJpaEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(CategoryPCJpaEntity.class, id));
    }

    @Override
    public CategoryPCJpaEntity insert(CategoryPCJpaEntity jpaEntity) {
        entityManager.persist(jpaEntity);
        return jpaEntity;
    }

    @Override
    public CategoryPCJpaEntity update(CategoryPCJpaEntity jpaEntity) {
        CategoryPCJpaEntity managed = entityManager.find(CategoryPCJpaEntity.class, jpaEntity.getId());
        if (managed == null) {
            throw new ResourceNotFoundException("CategoryPC with id " + jpaEntity.getId() + " not found");
        }
        entityManager.flush();
        return entityManager.merge(jpaEntity);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(CategoryPCJpaEntity.class, id));
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(b) FROM CategoryPCJpaEntity b", Long.class)
                .getSingleResult();
    }
}
