package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.OrderItem;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;

public class OrderItemMapper {
    private static OrderItemMapper INSTANCE;

    private OrderItemMapper() {
    }

    public static OrderItemMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderItemMapper();
        }
        return INSTANCE;
    }

    public OrderItemDto orderItemToOrderItemDto(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }

        return new OrderItemDto(
                orderItem.getId(),
                ProductMapper.getInstance().productToProductDto(orderItem.getProduct()),
                orderItem.getQuantity(),
                orderItem.getPrice());
    }

    public OrderItem orderItemDtoToOrderItem(OrderItemDto orderItemDto) {
        if (orderItemDto == null) {
            return null;
        }

        return new OrderItem.Builder()
                .id(orderItemDto.id())
                .product(ProductMapper.getInstance().productDtoToProduct(orderItemDto.product()))
                .quantity(orderItemDto.quantity())
                .build();
    }
}
