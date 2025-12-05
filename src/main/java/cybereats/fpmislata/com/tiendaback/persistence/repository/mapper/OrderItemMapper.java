package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.OrderItemJpaEntity;

public class OrderItemMapper {
    private static OrderItemMapper INSTANCE;

    private OrderItemMapper() {
    }

    public static OrderItemMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderItemMapper();
        }
        return INSTANCE;
    }

    public OrderItemJpaEntity orderItemDtoToOrderItemJpaEntity(OrderItemDto orderItemDto) {
        if (orderItemDto == null) {
            return null;
        }

        return new OrderItemJpaEntity(
                orderItemDto.id(),
                ProductMapper.getInstance().productDtoToProductJpaEntity(orderItemDto.product()),
                orderItemDto.quantity());
    }

    public OrderItemDto orderItemJpaEntityToOrderItemDto(OrderItemJpaEntity orderItemJpaEntity) {
        if (orderItemJpaEntity == null) {
            return null;
        }

        return new OrderItemDto(
                orderItemJpaEntity.getId(),
                ProductMapper.getInstance().productJpaEntityToProductDto(orderItemJpaEntity.getProduct()),
                orderItemJpaEntity.getQuantity(),
                null);
    }
}
