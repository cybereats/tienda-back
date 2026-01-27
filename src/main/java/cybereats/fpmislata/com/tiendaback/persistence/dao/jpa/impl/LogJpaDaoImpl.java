package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.LogJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.LogJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class LogJpaDaoImpl implements LogJpaDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<LogJpaEntity> findAll(int page, int size) {
        int pageIndex = Math.max(page - 1, 0);

        String sql = "SELECT b FROM LogJpaEntity b ORDER BY b.id";
        TypedQuery<LogJpaEntity> logJpaEntityPage = entityManager
                .createQuery(sql, LogJpaEntity.class)
                .setFirstResult(pageIndex * size)
                .setMaxResults(size);

        return logJpaEntityPage.getResultList();
    }

    @Override
    public Optional<LogJpaEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(LogJpaEntity.class, id));
    }

    @Override
    public LogJpaEntity insert(LogJpaEntity jpaEntity) {
        entityManager.persist(jpaEntity);
        return jpaEntity;
    }

    @Override
    public LogJpaEntity update(LogJpaEntity jpaEntity) {
        LogJpaEntity managed = entityManager.find(LogJpaEntity.class, jpaEntity.getId());
        if (managed == null) {
            throw new ResourceNotFoundException("Log with id " + jpaEntity.getId() + " not found");
        }
        entityManager.flush();
        return entityManager.merge(jpaEntity);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(LogJpaEntity.class, id));
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(b) FROM LogJpaEntity b", Long.class)
                .getSingleResult();
    }
}
