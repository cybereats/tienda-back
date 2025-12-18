package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.UserOrderJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserOrderJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class UserOrderJpaDaoImpl implements UserOrderJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<UserOrderJpaEntity> findById(Long id) {
        return Optional.of(entityManager.find(UserOrderJpaEntity.class, id));
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
}
