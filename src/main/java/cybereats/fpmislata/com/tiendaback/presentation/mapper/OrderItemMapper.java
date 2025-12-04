package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.OrderItemRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.OrderItemResponse;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;

public class OrderItemMapper {
    public static OrderItemRequest fromOrderItemDtoToOrderItemRequest(OrderItemDto orderItemDto) {
        return new OrderItemRequest(
                orderItemDto.id(),
                orderItemDto.product_id(),
                orderItemDto.quantity());
    }

    public static OrderItemDto fromOrderItemRequestToOrderItemDto(OrderItemRequest orderItemRequest) {
        return new OrderItemDto(
                orderItemRequest.id(),
                mapProduct(orderItemRequest.product_id()),
                orderItemRequest.quantity(),
                null);
    }

    public static OrderItemDto fromOrderItemResponseToOrderItemDto(OrderItemResponse orderItemResponse) {
        return new OrderItemDto(
                orderItemResponse.id(),
                OrderItemMapper.fromProductResponseToProductDto(orderItemResponse.product()),
                orderItemResponse.quantity(),
                orderItemResponse.price());
    }

    public static OrderItemResponse fromOrderItemDtoToOrderItemResponse(OrderItemDto orderItemDto) {
        return new OrderItemResponse(
                orderItemDto.id(),
                OrderItemMapper.fromProductDtoToProductResponse(orderItemDto.product()),
                orderItemDto.quantity(),
                orderItemDto.price());
    }

    public static ProductDto mapProduct(Long id) {
        return new ProductDto(id, null, null, null, null, null);
    }
}
