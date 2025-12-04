package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.OrderItemRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.UserOrderRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.UserOrderResponse;

public class UserOrderMapper {
    public static UserOrderDto fromUserOrderResponseToUserOrderDto(UserOrderResponse userOrderResponse) {
        return new UserOrderDto(
                userOrderResponse.id(),
                UserMapper.fromUserResponseToDto(userOrderResponse.user()),
                userOrderResponse.orderItems().stream().map(OrderItemMapper::fromOrderItemResponseToOrderItemDto)
                        .toList(),
                userOrderResponse.quantity(),
                userOrderResponse.price(),
                userOrderResponse.status());
    }

    public static UserOrderResponse fromUserOrderDtoToUserOrderResponse(UserOrderDto userOrderDto) {
        return new UserOrderResponse(
                userOrderDto.id(),
                UserMapper.fromDtoToUserResponse(userOrderDto.user()),
                userOrderDto.orderItems().stream().map(OrderItemMapper::fromOrderItemDtoToOrderItemResponse).toList(),
                userOrderDto.quantity(),
                userOrderDto.totalPrice(),
                userOrderDto.status());
    }

    public static UserOrderDto fromUserOrderRequestToUserOrderDto(UserOrderRequest userOrderRequest) {
        return new UserOrderDto(
                userOrderRequest.id(),
                mapUser(userOrderRequest.userId()),
                userOrderRequest.orderItemIds().stream().map(id -> mapOrderItem(id)).toList(),
                userOrderRequest.quantity(),
                null,
                userOrderRequest.status());
    }

    public static UserOrderRequest fromUserOrderDtoToUserOrderRequest(UserOrderDto userOrderDto) {
        return new UserOrderRequest(
                userOrderDto.id(),
                userOrderDto.user().id(),
                userOrderDto.orderItems().stream().map(OrderItemMapper::fromOrderItemDtoToOrderItemRequest)
                        .map(OrderItemRequest::id).toList(),
                userOrderDto.quantity(),
                userOrderDto.status());
    }

    public static UserDto mapUser(Long id) {
        return new UserDto(id, null, null, null, null, null);
    }

    public static OrderItemDto mapOrderItem(Long id) {
        return new OrderItemDto(id, null, null, null);
    }

}
