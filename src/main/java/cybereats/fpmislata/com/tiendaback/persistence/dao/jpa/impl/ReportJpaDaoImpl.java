package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.ReportJpaDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ReportJpaEntity;
import java.util.List;
import java.util.Optional;

public class ReportJpaDaoImpl implements ReportJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ReportJpaEntity insert(ReportJpaEntity reportJpaEntity) {
        return entityManager.merge(reportJpaEntity);
    }

    @Override
    public ReportJpaEntity update(ReportJpaEntity reportJpaEntity) {
        return entityManager.merge(reportJpaEntity);
    }

    @Override
    public void deleteById(Long id) {
        ReportJpaEntity reportJpaEntity = entityManager.find(ReportJpaEntity.class, id);
        entityManager.remove(reportJpaEntity);
    }

    @Override
    public List<ReportJpaEntity> getAll() {
        return entityManager.createQuery("SELECT r FROM ReportJpaEntity r ORDER BY r.id ASC", ReportJpaEntity.class)
                .getResultList();
    }

    @Override
    public Optional<ReportJpaEntity> getById(Long id) {
        return Optional.ofNullable(entityManager.find(ReportJpaEntity.class, id));
    }

    @Override
    public List<ReportJpaEntity> getByUserId(Long userId) {
<<<<<<< HEAD
        return entityManager.createQuery("SELECT r FROM ReportJpaEntity r WHERE r.user_id = :userId")
=======
        return entityManager
                .createQuery("SELECT r FROM ReportJpaEntity r WHERE r.user_id = :userId", ReportJpaEntity.class)
>>>>>>> ismael_8
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<ReportJpaEntity> getByPcId(Long pcId) {
<<<<<<< HEAD
        return entityManager.createQuery("SELECT r FROM ReportJpaEntity r WHERE r.pc_id = :pcId")
=======
        return entityManager.createQuery("SELECT r FROM ReportJpaEntity r WHERE r.pc_id = :pcId", ReportJpaEntity.class)
>>>>>>> ismael_8
                .setParameter("pcId", pcId)
                .getResultList();
    }
}
