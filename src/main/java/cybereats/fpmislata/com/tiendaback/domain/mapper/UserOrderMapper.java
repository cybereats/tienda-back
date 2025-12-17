package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.UserOrder;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;

public class UserOrderMapper {
    private static UserOrderMapper INSTANCE;

    private UserOrderMapper() {
    }

    public static UserOrderMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserOrderMapper();
        }
        return INSTANCE;
    }

    public UserOrderDto fromUserOrderToUserOrderDto(UserOrder userOrder) {
        if (userOrder == null) {
            return null;
        }

        return new UserOrderDto(
                userOrder.getId(),
                UserMapper.getInstance().fromUserToUserDto(userOrder.getUser()),
                userOrder.getOrderItems().stream()
                        .map(orderItem -> OrderItemMapper.getInstance().fromOrderItemToOrderItemDto(orderItem))
                        .toList(),
                userOrder.getStatus(),
                userOrder.getCreatedAt());
    }

    public UserOrder fromUserOrderDtoToUserOrder(UserOrderDto userOrderDto) {
        if (userOrderDto == null) {
            return null;
        }

        return new UserOrder.Builder()
                .id(userOrderDto.id())
                .user(UserMapper.getInstance().fromUserDtoToUser(userOrderDto.user()))
                .orderItems(userOrderDto.orderItems().stream()
                        .map(orderItemDto -> OrderItemMapper.getInstance().fromOrderItemDtoToOrderItem(orderItemDto))
                        .toList())
                .status(userOrderDto.status())
                .createdAt(userOrderDto.createdAt())
                .build();
    }
}
