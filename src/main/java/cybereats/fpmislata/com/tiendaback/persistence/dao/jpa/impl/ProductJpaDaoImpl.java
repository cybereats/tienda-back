package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.ProductJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ProductJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class ProductJpaDaoImpl implements ProductJpaDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductJpaEntity> findAll(int page, int size) {
        int pageIndex = Math.max(page - 1, 0);

        String sql = "SELECT b FROM ProductJpaEntity b ORDER BY b.id";
        TypedQuery<ProductJpaEntity> productJpaEntityPage = entityManager
                .createQuery(sql, ProductJpaEntity.class)
                .setFirstResult(pageIndex * size)
                .setMaxResults(size);

        return productJpaEntityPage.getResultList();
    }

    @Override
    public Optional<ProductJpaEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(ProductJpaEntity.class, id));
    }

    @Override
    public ProductJpaEntity insert(ProductJpaEntity jpaEntity) {
        entityManager.persist(jpaEntity);
        return jpaEntity;
    }

    @Override
    public ProductJpaEntity update(ProductJpaEntity jpaEntity) {
        ProductJpaEntity managed = entityManager.find(ProductJpaEntity.class, jpaEntity.getId());
        if(managed == null) {
            throw new ResourceNotFoundException("Product with id " + jpaEntity.getId() + " not found");
        }
        entityManager.flush();
        return entityManager.merge(jpaEntity);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(ProductJpaEntity.class, id));
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(b) FROM ProductJpaEntity b", Long.class)
                .getSingleResult();
    }

    @Override
    public Optional<ProductJpaEntity> findBySlug(String slug) {
        String sql = "SELECT b FROM ProductJpaEntity b WHERE b.slug = :slug";
        try {
            return Optional.of(entityManager.createQuery(sql, ProductJpaEntity.class)
                    .setParameter("slug", slug)
                    .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteBySlug(String slug) {
        entityManager.createQuery("DELETE FROM ProductJpaEntity b WHERE b.slug = :slug")
                .setParameter("slug", slug)
                .executeUpdate();
    }
}
