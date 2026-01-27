package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.UserJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;

public class UserJpaDaoImpl implements UserJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserJpaEntity insert(UserJpaEntity user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public UserJpaEntity update(UserJpaEntity user) {
        entityManager.merge(user);
        return user;
    }

    @Override
    public Optional<UserJpaEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(UserJpaEntity.class, id));
    }

    @Override
    public List<UserJpaEntity> findAll(int page, int size) {
        return entityManager.createQuery("SELECT u FROM UserJpaEntity u ORDER BY u.id ASC", UserJpaEntity.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        UserJpaEntity user = entityManager.find(UserJpaEntity.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(u) FROM UserJpaEntity u", Long.class).getSingleResult();
    }

    @Override
    public Optional<UserJpaEntity> findByUsername(String username) {
        try {
            UserJpaEntity user = entityManager.createQuery(
                    "SELECT u FROM UserJpaEntity u WHERE u.username = :username", UserJpaEntity.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserJpaEntity> findByEmail(String email) {
        try {
            UserJpaEntity user = entityManager.createQuery(
                    "SELECT u FROM UserJpaEntity u WHERE u.email = :email", UserJpaEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<UserJpaEntity> search(String text, String role, int page, int size) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserJpaEntity> cq = cb.createQuery(UserJpaEntity.class);
        Root<UserJpaEntity> user = cq.from(UserJpaEntity.class);

        Predicate[] predicates = getSearchPredicates(cb, user, text, role);
        if (predicates.length > 0) {
            cq.where(cb.and(predicates));
        }
        cq.orderBy(cb.asc(user.get("id")));

        return entityManager.createQuery(cq)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public long countSearch(String text, String role) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<UserJpaEntity> user = cq.from(UserJpaEntity.class);

        cq.select(cb.count(user));
        Predicate[] predicates = getSearchPredicates(cb, user, text, role);
        if (predicates.length > 0) {
            cq.where(cb.and(predicates));
        }

        return entityManager.createQuery(cq).getSingleResult();
    }

    private Predicate[] getSearchPredicates(CriteriaBuilder cb, Root<UserJpaEntity> root, String text, String role) {
        List<Predicate> predicates = new ArrayList<>();

        if (text != null && !text.isBlank() && !text.equalsIgnoreCase("null")) {
            String pattern = "%" + text.toLowerCase() + "%";
            predicates.add(cb.or(
                    cb.like(cb.lower(root.get("id").as(String.class)), pattern),
                    cb.like(cb.lower(root.get("name")), pattern),
                    cb.like(cb.lower(root.get("surname")), pattern),
                    cb.like(cb.lower(root.get("username")), pattern),
                    cb.like(cb.lower(root.get("email")), pattern)));
        }

        if (role != null && !role.isBlank() && !role.equalsIgnoreCase("Todos los roles")
                && !role.equalsIgnoreCase("null")) {
            predicates.add(cb.equal(cb.upper(root.get("role").as(String.class)), role.toUpperCase()));
        }

        return predicates.toArray(new Predicate[0]);
    }
}