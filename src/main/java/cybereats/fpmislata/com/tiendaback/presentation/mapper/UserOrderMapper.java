package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.OrderItemRequest;
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
                UserMapper.getInstance().fromDtoToUserResponse(userOrderDto.user()),
                userOrderDto.orderItems().stream().map(OrderItemMapper::fromOrderItemDtoToOrderItemResponse).toList(),
                userOrderDto.totalPrice(),
                userOrderDto.status());
    }

    public UserOrderDto fromUserOrderRequestToUserOrderDto(UserOrderRequest userOrderRequest) {
        return new UserOrderDto(
                userOrderRequest.id(),
                mapUser(userOrderRequest.userId()),
                userOrderRequest.orderItemIds().stream().map(id -> mapOrderItem(id)).toList(),
                null,
                userOrderRequest.status());
    }

    public UserOrderRequest fromUserOrderDtoToUserOrderRequest(UserOrderDto userOrderDto) {
        return new UserOrderRequest(
                userOrderDto.id(),
                userOrderDto.user().id(),
                userOrderDto.orderItems().stream().map(OrderItemMapper::fromOrderItemDtoToOrderItemRequest)
                        .map(OrderItemRequest::id).toList(),
                userOrderDto.status());
    }

    public UserDto mapUser(Long id) {
        return new UserDto(id, null, null, null, null, null);
    }

    public OrderItemDto mapOrderItem(Long id) {
        return new OrderItemDto(id, null, 0, null);
    }

}
