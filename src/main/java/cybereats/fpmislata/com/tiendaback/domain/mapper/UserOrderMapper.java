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

    public UserOrderDto userOrderToUserOrderDto(UserOrder userOrder) {
        if (userOrder == null) {
            return null;
        }

        return new UserOrderDto(
                userOrder.getId(),
                UserMapper.getInstance().userToUserDto(userOrder.getUser()),
                userOrder.getOrderItems().stream()
                        .map(orderItem -> OrderItemMapper.getInstance().orderItemToOrderItemDto(orderItem))
                        .toList(),
                userOrder.getTotalPrice(),
                userOrder.getStatus());
    }

    public UserOrder userOrderDtoToUserOrder(UserOrderDto userOrderDto) {
        if (userOrderDto == null) {
            return null;
        }

        return new UserOrder.Builder()
                .id(userOrderDto.id())
                .user(UserMapper.getInstance().userDtoToUser(userOrderDto.user()))
                .orderItems(userOrderDto.orderItems().stream()
                        .map(orderItemDto -> OrderItemMapper.getInstance().orderItemDtoToOrderItem(orderItemDto))
                        .toList())
                .status(userOrderDto.status())
                .build();
    }
}
