package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.OrderItemRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.OrderItemResponse;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;

public class OrderItemMapper {
    public static OrderItemRequest fromOrderItemDtoToOrderItemRequest(OrderItemDto orderItemDto) {
        return new OrderItemRequest(
                orderItemDto.id(),
                orderItemDto.product().id(),
                orderItemDto.quantity());
    }

    public static OrderItemDto fromOrderItemRequestToOrderItemDto(OrderItemRequest orderItemRequest) {
        return new OrderItemDto(
                orderItemRequest.id(),
                mapProduct(orderItemRequest.productId()),
                orderItemRequest.quantity());
    }

    public static OrderItemResponse fromOrderItemDtoToOrderItemResponse(OrderItemDto orderItemDto) {
        return new OrderItemResponse(
                orderItemDto.id(),
                ProductMapper.getInstance().fromProductDtoToProductResponse(orderItemDto.product()),
                orderItemDto.quantity());
    }

    public static ProductDto mapProduct(Long id) {
        return new ProductDto(id, null, null, null, null, null, null);
    }
}
