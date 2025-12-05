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

    public OrderItemDto fromOrderItemToOrderItemDto(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }

        return new OrderItemDto(
                orderItem.getId(),
                ProductMapper.getInstance().fromProductToProductDto(orderItem.getProduct()),
                orderItem.getQuantity());
    }

    public OrderItem fromOrderItemDtoToOrderItem(OrderItemDto orderItemDto) {
        if (orderItemDto == null) {
            return null;
        }

        return new OrderItem.Builder()
                .id(orderItemDto.id())
                .product(ProductMapper.getInstance().fromProductDtoToProduct(orderItemDto.product()))
                .quantity(orderItemDto.quantity())
                .build();
    }
}
