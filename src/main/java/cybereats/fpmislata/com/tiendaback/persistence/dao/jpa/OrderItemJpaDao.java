package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.OrderItemJpaEntity;

public interface OrderItemJpaDao {
    OrderItemJpaEntity insert(OrderItemJpaEntity orderItemJpaEntity);

    OrderItemJpaEntity update(OrderItemJpaEntity orderItemJpaEntity);

    Optional<OrderItemJpaEntity> findById(Long id);

    List<OrderItemJpaEntity> findAll();

    void deleteById(Long id);

    long count();
}
