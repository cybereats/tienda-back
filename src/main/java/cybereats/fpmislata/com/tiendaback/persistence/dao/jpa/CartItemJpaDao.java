package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CartItemJpaEntity;

import java.util.Optional;

public interface CartItemJpaDao extends GenericJpaDao<CartItemJpaEntity> {
  Optional<CartItemJpaEntity> findByCartIdAndProductId(Long cartId, Long productId);

  void deleteByCartIdAndProductId(Long cartId, Long productId);
}
