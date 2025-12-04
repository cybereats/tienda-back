package cybereats.fpmislata.com.tiendaback.persistence.repository;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.repository.OrderItemRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.OrderItemJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.OrderItemJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.repository.mapper.OrderItemMapper;

public class OrderItemRepositoryImpl implements OrderItemRepository {

    private final OrderItemJpaDao orderItemJpaDao;

    public OrderItemRepositoryImpl(OrderItemJpaDao orderItemJpaDao) {
        this.orderItemJpaDao = orderItemJpaDao;
    }

    @Override
    public List<OrderItemDto> getAll() {
        return orderItemJpaDao.getAll().stream()
                .map(entity -> OrderItemMapper.getInstance().orderItemJpaEntityToOrderItemDto(entity))
                .toList();
    }

    @Override
    public Optional<OrderItemDto> getOrderItemById(Long id) {
        return orderItemJpaDao.getOrderItemById(id)
                .map(entity -> OrderItemMapper.getInstance().orderItemJpaEntityToOrderItemDto(entity));
    }

    @Override
    public OrderItemDto save(OrderItemDto orderItemDto) {
        if (orderItemDto.id() == null) {
            OrderItemJpaEntity orderItemJpaEntity = OrderItemMapper.getInstance()
                    .orderItemDtoToOrderItemJpaEntity(orderItemDto);
            return OrderItemMapper.getInstance()
                    .orderItemJpaEntityToOrderItemDto(orderItemJpaDao.insert(orderItemJpaEntity));
        } else {
            OrderItemJpaEntity orderItemJpaEntity = OrderItemMapper.getInstance()
                    .orderItemDtoToOrderItemJpaEntity(orderItemDto);
            return OrderItemMapper.getInstance()
                    .orderItemJpaEntityToOrderItemDto(orderItemJpaDao.update(orderItemJpaEntity));
        }
    }

    @Override
    public void deleteById(Long id) {
        orderItemJpaDao.deleteById(id);
    }

}
