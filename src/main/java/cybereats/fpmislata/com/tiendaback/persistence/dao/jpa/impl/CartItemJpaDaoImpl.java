package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.CartItemJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CartItemJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class CartItemJpaDaoImpl implements CartItemJpaDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<CartItemJpaEntity> findAll(int page, int size) {
    return entityManager.createQuery("SELECT ci FROM CartItemJpaEntity ci", CartItemJpaEntity.class)
        .setFirstResult((page - 1) * size)
        .setMaxResults(size)
        .getResultList();
  }

  @Override
  public Optional<CartItemJpaEntity> findById(Long id) {
    return Optional.ofNullable(entityManager.find(CartItemJpaEntity.class, id));
  }

  @Override
  public CartItemJpaEntity insert(CartItemJpaEntity item) {
    entityManager.persist(item);
    return item;
  }

  @Override
  public CartItemJpaEntity update(CartItemJpaEntity item) {
    return entityManager.merge(item);
  }

  @Override
  public void deleteById(Long id) {
    CartItemJpaEntity item = entityManager.find(CartItemJpaEntity.class, id);
    if (item != null) {
      entityManager.remove(item);
    }
  }

  @Override
  public long count() {
    return entityManager.createQuery("SELECT COUNT(ci) FROM CartItemJpaEntity ci", Long.class).getSingleResult();
  }

  @Override
  public Optional<CartItemJpaEntity> findByCartIdAndProductId(Long cartId, Long productId) {
    try {
      CartItemJpaEntity item = entityManager.createQuery(
          "SELECT ci FROM CartItemJpaEntity ci WHERE ci.cart.id = :cartId AND ci.product.id = :productId",
          CartItemJpaEntity.class)
          .setParameter("cartId", cartId)
          .setParameter("productId", productId)
          .getSingleResult();
      return Optional.of(item);
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public void deleteByCartIdAndProductId(Long cartId, Long productId) {
    entityManager.createQuery(
        "DELETE FROM CartItemJpaEntity ci WHERE ci.cart.id = :cartId AND ci.product.id = :productId")
        .setParameter("cartId", cartId)
        .setParameter("productId", productId)
        .executeUpdate();
  }
}
