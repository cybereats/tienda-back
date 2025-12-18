package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.CategoryProductJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryProductJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class CategoryProductJpaDaoImpl implements CategoryProductJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CategoryProductJpaEntity> findAll(int page, int size) {
        int pageIndex = Math.max(page - 1, 0);

        String sql = "SELECT b FROM CategoryProductJpaEntity b ORDER BY b.id";
        TypedQuery<CategoryProductJpaEntity> categoryProductJpaEntityPage = entityManager
                .createQuery(sql, CategoryProductJpaEntity.class)
                .setFirstResult(pageIndex * size)
                .setMaxResults(size);

        return categoryProductJpaEntityPage.getResultList();
    }

    @Override
    public Optional<CategoryProductJpaEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(CategoryProductJpaEntity.class, id));
    }

    @Override
    public Optional<CategoryProductJpaEntity> findBySlug(String slug) {
        try {
            String sql = "SELECT c FROM CategoryProductJpaEntity c WHERE c.slug = :slug";
            return Optional.of(entityManager.createQuery(sql, CategoryProductJpaEntity.class)
                    .setParameter("slug", slug)
                    .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public CategoryProductJpaEntity insert(CategoryProductJpaEntity jpaEntity) {
        entityManager.persist(jpaEntity);
        return jpaEntity;
    }

    @Override
    public CategoryProductJpaEntity update(CategoryProductJpaEntity jpaEntity) {
        CategoryProductJpaEntity managed = entityManager.find(CategoryProductJpaEntity.class, jpaEntity.getId());
        if (managed == null) {
            throw new ResourceNotFoundException("CategoryProduct with id " + jpaEntity.getId() + " not found");
        }
        entityManager.flush();
        return entityManager.merge(jpaEntity);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(CategoryProductJpaEntity.class, id));
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(b) FROM CategoryProductJpaEntity b", Long.class)
                .getSingleResult();
    }

    @Override
    public void deleteBySlug(String slug) {
        Optional<CategoryProductJpaEntity> entity = findBySlug(slug);
        entity.ifPresent(categoryProductJpaEntity -> entityManager.remove(categoryProductJpaEntity));
    }
}
