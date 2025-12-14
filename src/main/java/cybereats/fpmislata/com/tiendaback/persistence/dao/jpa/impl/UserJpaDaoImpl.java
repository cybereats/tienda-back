package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.UserJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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
}