package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.PCJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.PCJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
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
        if (managed == null) {
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
        findBySlug(slug).ifPresent(entityManager::remove);
    }

    @Override
    public List<PCJpaEntity> search(String text, String category, int page, int size) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PCJpaEntity> cq = cb.createQuery(PCJpaEntity.class);
        Root<PCJpaEntity> pc = cq.from(PCJpaEntity.class);

        Predicate[] predicates = getSearchPredicates(cb, pc, text, category);
        if (predicates.length > 0) {
            cq.where(cb.and(predicates));
        }
        cq.orderBy(cb.asc(pc.get("id")));

        return entityManager.createQuery(cq)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public long countSearch(String text, String category) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PCJpaEntity> pc = cq.from(PCJpaEntity.class);

        cq.select(cb.count(pc));
        Predicate[] predicates = getSearchPredicates(cb, pc, text, category);
        if (predicates.length > 0) {
            cq.where(cb.and(predicates));
        }

        return entityManager.createQuery(cq).getSingleResult();
    }

    private Predicate[] getSearchPredicates(CriteriaBuilder cb, Root<PCJpaEntity> root, String text, String category) {
        List<Predicate> predicates = new ArrayList<>();

        if (text != null && !text.isBlank() && !text.equalsIgnoreCase("null")) {
            String pattern = "%" + text.toLowerCase() + "%";
            predicates.add(cb.or(
                    cb.like(cb.lower(root.get("id").as(String.class)), pattern),
                    cb.like(cb.lower(root.get("label")), pattern),
                    cb.like(cb.lower(root.get("slug")), pattern),
                    cb.like(cb.lower(root.get("specs")), pattern),
                    cb.like(cb.lower(root.get("category").get("label")), pattern)));
        }

        if (category != null && !category.isBlank() && !category.equalsIgnoreCase("Todas las categorías")
                && !category.equalsIgnoreCase("null")) {
            predicates.add(cb.or(
                    cb.equal(cb.lower(root.get("category").get("label")), category.toLowerCase()),
                    cb.equal(cb.lower(root.get("category").get("slug")), category.toLowerCase())));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
