package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.ReportJpaDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ReportJpaEntity;

import java.util.ArrayList;
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
    public List<ReportJpaEntity> findAll(int page, int size) {
        return entityManager.createQuery("SELECT r FROM ReportJpaEntity r ORDER BY r.id ASC", ReportJpaEntity.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public Optional<ReportJpaEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(ReportJpaEntity.class, id));
    }

    @Override
    public List<ReportJpaEntity> findByUserId(Long userId) {
        return entityManager
                .createQuery("SELECT r FROM ReportJpaEntity r WHERE r.user.id = :userId", ReportJpaEntity.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<ReportJpaEntity> findByPCId(Long pcId) {
        String jpql = "SELECT r FROM ReportJpaEntity r WHERE r.pc.id = :pcId";
        return entityManager.createQuery(jpql, ReportJpaEntity.class)
                .setParameter("pcId", pcId)
                .getResultList();
    }

    @Override
    public List<ReportJpaEntity> findByStatus(String status) {
        String jpql = "SELECT r FROM ReportJpaEntity r WHERE r.status = :status";
        return entityManager.createQuery(jpql, ReportJpaEntity.class)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    public List<ReportJpaEntity> findByCreatedAt(String createdAt) {
        String jpql = "SELECT r FROM ReportJpaEntity r WHERE r.createdAt = :createdAt";
        return entityManager.createQuery(jpql, ReportJpaEntity.class)
                .setParameter("createdAt", createdAt)
                .getResultList();
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(r) FROM ReportJpaEntity r", Long.class).getSingleResult();
    }

    @Override
    public List<Object[]> countReportsByStatus() {
        return entityManager
                .createQuery("SELECT r.status, COUNT(r) FROM ReportJpaEntity r GROUP BY r.status", Object[].class)
                .getResultList();
    }

    @Override
    public List<ReportJpaEntity> search(String text, String status, String date, int page, int size) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ReportJpaEntity> cq = cb.createQuery(ReportJpaEntity.class);
        Root<ReportJpaEntity> report = cq.from(ReportJpaEntity.class);

        Predicate[] predicates = getSearchPredicates(cb, report, text, status, date);
        if (predicates.length > 0) {
            cq.where(cb.and(predicates));
        }
        cq.orderBy(cb.asc(report.get("id")));

        return entityManager.createQuery(cq)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public long countSearch(String text, String status, String date) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ReportJpaEntity> report = cq.from(ReportJpaEntity.class);

        cq.select(cb.count(report));
        Predicate[] predicates = getSearchPredicates(cb, report, text, status, date);
        if (predicates.length > 0) {
            cq.where(cb.and(predicates));
        }

        return entityManager.createQuery(cq).getSingleResult();
    }

    private Predicate[] getSearchPredicates(CriteriaBuilder cb, Root<ReportJpaEntity> report, String text,
            String status,
            String date) {
        List<Predicate> predicates = new ArrayList<>();

        if (text != null && !text.isBlank() && !text.equalsIgnoreCase("null")) {
            String pattern = "%" + text.toLowerCase() + "%";
            predicates.add(cb.or(
                    cb.like(cb.lower(report.get("id").as(String.class)), pattern),
                    cb.like(cb.lower(report.get("user").get("username")), pattern),
                    cb.like(cb.lower(report.get("user").get("name")), pattern),
                    cb.like(cb.lower(report.get("user").get("surname")), pattern),
                    cb.like(cb.lower(report.get("subject")), pattern),
                    cb.like(cb.lower(report.get("description")), pattern)));
        }

        if (status != null && !status.isBlank() && !status.equalsIgnoreCase("Todos los estados")
                && !status.equalsIgnoreCase("null")) {
            predicates.add(cb.equal(report.get("status"), status));
        }

        if (date != null && !date.isBlank() && !date.equalsIgnoreCase("null")) {
            predicates.add(cb.like(report.get("createdAt"), date + "%"));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
