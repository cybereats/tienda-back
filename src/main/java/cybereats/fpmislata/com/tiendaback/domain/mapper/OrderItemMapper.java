package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.OrderItem;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;

public class OrderItemMapper {
    public static OrderItemDto toOrderItemDto(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getId(),
                ProductMapper.productToProductDto(orderItem.getProduct()),
                orderItem.getQuantity(),
                orderItem.getPrice());
    }

    public static OrderItem toOrderItem(OrderItemDto orderItemDto) {
        return new OrderItem.Builder()
                .id(orderItemDto.id())
                .product(ProductMapper.productDtoToProduct(orderItemDto.product()))
                .quantity(orderItemDto.quantity())
                .price(orderItemDto.price())
                .build();
    }
}
