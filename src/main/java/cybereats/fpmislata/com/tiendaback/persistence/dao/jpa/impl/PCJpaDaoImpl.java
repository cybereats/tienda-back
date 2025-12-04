package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.PCJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.PCJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class PCJpaDaoImpl implements PCJpaDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PCJpaEntity> findAll(int page, int size) {
        int pageIndex = Math.max(page - 1, 0);

        String sql = "SELECT b FROM PCJpaEntity b ORDER BY b.id";
        TypedQuery<PCJpaEntity> pcJpaEntityPage = entityManager
                .createQuery(sql, PCJpaEntity.class)
                .setFirstResult(pageIndex * size)
                .setMaxResults(size);

        return pcJpaEntityPage.getResultList();
    }

    @Override
    public Optional<PCJpaEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(PCJpaEntity.class, id));
    }

    @Override
    public PCJpaEntity insert(PCJpaEntity jpaEntity) {
        entityManager.persist(jpaEntity);
        return jpaEntity;
    }

    @Override
    public PCJpaEntity update(PCJpaEntity jpaEntity) {
        PCJpaEntity managed = entityManager.find(PCJpaEntity.class, jpaEntity.getId());
        if(managed == null) {
            throw new ResourceNotFoundException("PC with id " + jpaEntity.getId() + " not found");
        }
        entityManager.flush();
        return entityManager.merge(jpaEntity);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(PCJpaEntity.class, id));
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(b) FROM PCJpaEntity b", Long.class)
                .getSingleResult();
    }

    @Override
    public Optional<PCJpaEntity> findBySlug(String slug) {
        String sql = "SELECT b FROM PCJpaEntity b WHERE b.slug = :slug";
        try {
            return Optional.of(entityManager.createQuery(sql, PCJpaEntity.class)
                    .setParameter("slug", slug)
                    .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteBySlug(String slug) {
        entityManager.createQuery("DELETE FROM PCJpaEntity b WHERE b.slug = :slug")
                .setParameter("slug", slug)
                .executeUpdate();
    }
}
