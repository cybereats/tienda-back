package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.OrderItemJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.OrderItemJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class OrderItemJpaDaoImpl implements OrderItemJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OrderItemJpaEntity> getAll() {
        return entityManager
                .createQuery("SELECT e FROM OrderItemJpaEntity e ORDER BY e.id ASC", OrderItemJpaEntity.class)
                .getResultList();
    }

    @Override
    public Optional<OrderItemJpaEntity> getOrderItemById(Long id) {
        return Optional.ofNullable(entityManager.find(OrderItemJpaEntity.class, id));
    }

    @Override
    public OrderItemJpaEntity insert(OrderItemJpaEntity orderItemJpaEntity) {
        entityManager.persist(orderItemJpaEntity);
        return orderItemJpaEntity;
    }

    @Override
    public OrderItemJpaEntity update(OrderItemJpaEntity orderItemJpaEntity) {
        return entityManager.merge(orderItemJpaEntity);
    }

    @Override
    public void deleteById(Long id) {
        OrderItemJpaEntity orderItemJpaEntity = entityManager.find(OrderItemJpaEntity.class, id);
        if (orderItemJpaEntity != null) {
            entityManager.remove(orderItemJpaEntity);
        }
    }

}
