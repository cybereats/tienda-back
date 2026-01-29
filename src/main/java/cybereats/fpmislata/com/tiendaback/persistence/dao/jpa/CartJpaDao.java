package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CartJpaEntity;

import java.util.Optional;

public interface CartJpaDao extends GenericJpaDao<CartJpaEntity> {
  Optional<CartJpaEntity> findByUserIdWithItems(Long userId);

  Optional<CartJpaEntity> findByUserId(Long userId);
}
