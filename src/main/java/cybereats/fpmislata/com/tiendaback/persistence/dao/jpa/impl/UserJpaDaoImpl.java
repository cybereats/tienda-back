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
<<<<<<< HEAD
    public Optional<UserJpaEntity> findById(Long id) {
=======
    public Optional<UserJpaEntity> getById(Long id) {
>>>>>>> ismael_7
        return Optional.ofNullable(entityManager.find(UserJpaEntity.class, id));
    }

    @Override
<<<<<<< HEAD
    public List<UserJpaEntity> findAll() {
=======
    public List<UserJpaEntity> getAll() {
>>>>>>> ismael_7
        return entityManager.createQuery("SELECT u FROM UserJpaEntity u ORDER BY u.id ASC", UserJpaEntity.class)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        UserJpaEntity user = entityManager.find(UserJpaEntity.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

}