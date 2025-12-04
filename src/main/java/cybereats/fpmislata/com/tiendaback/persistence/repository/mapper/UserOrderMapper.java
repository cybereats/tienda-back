package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserOrderJpaEntity;

public class UserOrderMapper {
    public static UserOrderJpaEntity toUserOrderJpaEntity(UserOrderDto userOrderDto) {
        return new UserOrderJpaEntity(
                userOrderDto.id(),
                UserMapper.toUserJpaEntity(userOrderDto.user()),
                userOrderDto.orderItems().stream().map(OrderItemMapper::toOrderItemJpaEntity).toList(),
                userOrderDto.totalPrice(),
                userOrderDto.status());
    }

    public static UserOrderDto toUserOrderDto(UserOrderJpaEntity userOrderJpaEntity) {
        return new UserOrderDto(
                userOrderJpaEntity.getId(),
                UserMapper.toUserDto(userOrderJpaEntity.getUser()),
                userOrderJpaEntity.getOrderItems().stream().map(OrderItemMapper::toOrderItemDto).toList(),
                userOrderJpaEntity.getTotalPrice(),
                userOrderJpaEntity.getStatus());
    }
}
