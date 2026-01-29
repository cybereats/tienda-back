package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.CartJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CartJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class CartJpaDaoImpl implements CartJpaDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<CartJpaEntity> findAll(int page, int size) {
    return entityManager.createQuery("SELECT c FROM CartJpaEntity c", CartJpaEntity.class)
        .setFirstResult((page - 1) * size)
        .setMaxResults(size)
        .getResultList();
  }

  @Override
  public Optional<CartJpaEntity> findById(Long id) {
    return Optional.ofNullable(entityManager.find(CartJpaEntity.class, id));
  }

  @Override
  public CartJpaEntity insert(CartJpaEntity cart) {
    entityManager.persist(cart);
    return cart;
  }

  @Override
  public CartJpaEntity update(CartJpaEntity cart) {
    return entityManager.merge(cart);
  }

  @Override
  public void deleteById(Long id) {
    CartJpaEntity cart = entityManager.find(CartJpaEntity.class, id);
    if (cart != null) {
      entityManager.remove(cart);
    }
  }

  @Override
  public long count() {
    return entityManager.createQuery("SELECT COUNT(c) FROM CartJpaEntity c", Long.class).getSingleResult();
  }

  @Override
  public Optional<CartJpaEntity> findByUserIdWithItems(Long userId) {
    try {
      CartJpaEntity cart = entityManager.createQuery(
          "SELECT c FROM CartJpaEntity c LEFT JOIN FETCH c.items i LEFT JOIN FETCH i.product WHERE c.user.id = :userId",
          CartJpaEntity.class)
          .setParameter("userId", userId)
          .getSingleResult();
      return Optional.of(cart);
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<CartJpaEntity> findByUserId(Long userId) {
    try {
      CartJpaEntity cart = entityManager.createQuery(
          "SELECT c FROM CartJpaEntity c WHERE c.user.id = :userId",
          CartJpaEntity.class)
          .setParameter("userId", userId)
          .getSingleResult();
      return Optional.of(cart);
    } catch (Exception e) {
      return Optional.empty();
    }
  }
}
