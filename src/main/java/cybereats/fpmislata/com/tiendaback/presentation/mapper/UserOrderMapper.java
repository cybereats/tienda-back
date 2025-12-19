package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.UserOrderRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.UserOrderResponse;

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

    public UserOrderResponse fromUserOrderDtoToUserOrderResponse(UserOrderDto userOrderDto) {
        return new UserOrderResponse(
                userOrderDto.id(),
                UserMapper.getInstance().fromUserDtoToUserResponse(userOrderDto.user()),
                userOrderDto.orderItems().stream().map(OrderItemMapper::fromOrderItemDtoToOrderItemResponse).toList(),
                userOrderDto.status(),
                userOrderDto.createdAt());
    }

    public UserOrderDto fromUserOrderRequestToUserOrderDto(UserOrderRequest userOrderRequest) {
        return new UserOrderDto(
                userOrderRequest.id(),
                userOrderRequest.userId() != null ? mapUser(userOrderRequest.userId()) : null,
                userOrderRequest.orderItemIds() != null
                        ? userOrderRequest.orderItemIds().stream().map(id -> mapOrderItem(id)).toList()
                        : null,
                userOrderRequest.status() != null
                        ? cybereats.fpmislata.com.tiendaback.domain.model.OrderStatus
                                .valueOf(userOrderRequest.status().toUpperCase())
                        : null,
                null);
    }

    public UserDto mapUser(Long id) {
        return new UserDto(id, null, null, null, null, null, null, null);
    }

    public OrderItemDto mapOrderItem(Long id) {
        return new OrderItemDto(id, null, 0);
    }

}
