package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.OrderItemJpaEntity;

public class OrderItemMapper {
    public static OrderItemJpaEntity toOrderItemJpaEntity(OrderItemDto orderItemDto) {
        return new OrderItemJpaEntity(
                orderItemDto.id(),
                orderItemDto.product(),
                orderItemDto.quantity(),
                orderItemDto.price());
    }

    public static OrderItemDto toOrderItemDto(OrderItemJpaEntity orderItemJpaEntity) {
        return new OrderItemDto(
                orderItemJpaEntity.getId(),
                orderItemJpaEntity.getProduct(),
                orderItemJpaEntity.getQuantity(),
                orderItemJpaEntity.getPrice());
    }

}
