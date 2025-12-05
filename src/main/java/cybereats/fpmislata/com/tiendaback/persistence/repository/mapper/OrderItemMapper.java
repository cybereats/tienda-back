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

    public OrderItemJpaEntity fromOrderItemDtoToOrderItemJpaEntity(OrderItemDto orderItemDto) {
        if (orderItemDto == null) {
            return null;
        }

        return new OrderItemJpaEntity(
                orderItemDto.id(),
                orderItemDto.quantity(),
                ProductMapper.getInstance().fromProductDtoToProductJpaEntity(orderItemDto.product()),
                null);
    }

    public OrderItemDto fromOrderItemJpaEntityToOrderItemDto(OrderItemJpaEntity orderItemJpaEntity) {
        if (orderItemJpaEntity == null) {
            return null;
        }

        return new OrderItemDto(
                orderItemJpaEntity.getId(),
                ProductMapper.getInstance().fromProductJpaEntityToProductDto(orderItemJpaEntity.getProduct()),
                orderItemJpaEntity.getQuantity());
    }
}
