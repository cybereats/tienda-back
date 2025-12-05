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
    public List<OrderItemDto> findAll() {
        return orderItemJpaDao.findAll().stream()
                .map(entity -> OrderItemMapper.getInstance().fromOrderItemJpaEntityToOrderItemDto(entity))
                .toList();
    }

    @Override
    public Optional<OrderItemDto> findById(Long id) {
        return orderItemJpaDao.findById(id)
                .map(entity -> OrderItemMapper.getInstance().fromOrderItemJpaEntityToOrderItemDto(entity));
    }

    @Override
    public OrderItemDto save(OrderItemDto orderItemDto) {
        if (orderItemDto.id() == null) {
            OrderItemJpaEntity orderItemJpaEntity = OrderItemMapper.getInstance()
                    .fromOrderItemDtoToOrderItemJpaEntity(orderItemDto);
            return OrderItemMapper.getInstance()
                    .fromOrderItemJpaEntityToOrderItemDto(orderItemJpaDao.insert(orderItemJpaEntity));
        } else {
            OrderItemJpaEntity orderItemJpaEntity = OrderItemMapper.getInstance()
                    .fromOrderItemDtoToOrderItemJpaEntity(orderItemDto);
            return OrderItemMapper.getInstance()
                    .fromOrderItemJpaEntityToOrderItemDto(orderItemJpaDao.update(orderItemJpaEntity));
        }
    }

    @Override
    public void deleteById(Long id) {
        orderItemJpaDao.deleteById(id);
    }

}
