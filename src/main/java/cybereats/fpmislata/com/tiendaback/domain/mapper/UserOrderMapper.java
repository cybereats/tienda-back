package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.UserOrder;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;

public class UserOrderMapper {
    public static UserOrderDto toUserOrderDto(UserOrder userOrder) {
        return new UserOrderDto(
                userOrder.getId(),
                UserMapper.toUserDto(userOrder.getUser()),
                userOrder.getOrderItems().stream().map(OrderItemMapper::toOrderItemDto).toList(),
                userOrder.getTotalPrice(),
                userOrder.getStatus());
    }

    public static UserOrder toUserOrder(UserOrderDto userOrderDto) {
        return new UserOrder().Builder()
                .id(userOrderDto.id())
                .user(UserMapper.toUser(userOrderDto.user()))
                .orderItems(userOrderDto.orderItems().stream().map(OrderItemMapper::toOrderItem).toList())
                .totalPrice(userOrderDto.totalPrice())
                .status(userOrderDto.status())
                .build();
    }
}
