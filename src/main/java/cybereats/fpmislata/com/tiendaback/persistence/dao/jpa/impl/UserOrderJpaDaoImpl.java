package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.UserOrderJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserOrderJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import cybereats.fpmislata.com.tiendaback.domain.model.OrderStatus;

public class UserOrderJpaDaoImpl implements UserOrderJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<UserOrderJpaEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(UserOrderJpaEntity.class, id));
    }

    @Override
    public List<UserOrderJpaEntity> findAll(int page, int size) {
        return entityManager
                .createQuery("SELECT u FROM UserOrderJpaEntity u ORDER BY u.id ASC", UserOrderJpaEntity.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(u) FROM UserOrderJpaEntity u", Long.class).getSingleResult();
    }

    @Override
    public UserOrderJpaEntity insert(UserOrderJpaEntity userOrderJpaEntity) {
        entityManager.persist(userOrderJpaEntity);
        return userOrderJpaEntity;
    }

    @Override
    public UserOrderJpaEntity update(UserOrderJpaEntity userOrderJpaEntity) {
        return entityManager.merge(userOrderJpaEntity);
    }

    @Override
    public void deleteById(Long id) {
        UserOrderJpaEntity userOrderJpaEntity = entityManager.find(UserOrderJpaEntity.class, id);
        entityManager.remove(userOrderJpaEntity);
    }

    @Override
    public List<UserOrderJpaEntity> findAll() {
        return entityManager.createQuery("SELECT u FROM UserOrderJpaEntity u", UserOrderJpaEntity.class)
                .getResultList();
    }

    @Override
    public List<UserOrderJpaEntity> search(String text, String status, String date, int page, int size) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserOrderJpaEntity> cq = cb.createQuery(UserOrderJpaEntity.class);
        Root<UserOrderJpaEntity> userOrder = cq.from(UserOrderJpaEntity.class);

        Predicate[] predicates = getSearchPredicates(cb, userOrder, text, status, date);
        if (predicates.length > 0) {
            cq.where(cb.and(predicates));
        }
        cq.orderBy(cb.asc(userOrder.get("id")));

        return entityManager.createQuery(cq)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public long countSearch(String text, String status, String date) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<UserOrderJpaEntity> userOrder = cq.from(UserOrderJpaEntity.class);

        cq.select(cb.count(userOrder));
        Predicate[] predicates = getSearchPredicates(cb, userOrder, text, status, date);
        if (predicates.length > 0) {
            cq.where(cb.and(predicates));
        }

        return entityManager.createQuery(cq).getSingleResult();
    }

    private Predicate[] getSearchPredicates(CriteriaBuilder cb, Root<UserOrderJpaEntity> root, String text,
            String status,
            String date) {
        List<Predicate> predicates = new java.util.ArrayList<>();

        if (text != null && !text.isBlank() && !text.equalsIgnoreCase("null")) {
            String pattern = "%" + text.toLowerCase() + "%";
            predicates.add(cb.or(
                    cb.like(cb.lower(root.get("id").as(String.class)), pattern),
                    cb.like(cb.lower(root.get("user").get("username")), pattern),
                    cb.like(cb.lower(root.get("user").get("name")), pattern),
                    cb.like(cb.lower(root.get("user").get("surname")), pattern),
                    cb.like(cb.lower(root.get("user").get("email")), pattern)));
        }

        if (status != null && !status.isBlank() && !status.equalsIgnoreCase("Todos")
                && !status.equalsIgnoreCase("null")) {
            try {
                OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
                predicates.add(cb.equal(root.get("status"), orderStatus));
            } catch (IllegalArgumentException e) {
                // Ignore invalid status
            }
        }

        if (date != null && !date.isBlank() && !date.equalsIgnoreCase("null")) {
            predicates.add(cb.like(
                    cb.function("DATE", String.class, root.get("createdAt")),
                    date + "%"));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
