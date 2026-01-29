package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.OrderItemJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserOrderJpaEntity;

import java.util.Collections;

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

    public UserOrderJpaEntity fromUserOrderDtoToUserOrderJpaEntity(UserOrderDto userOrderDto) {
        if (userOrderDto == null) {
            return null;
        }

        UserOrderJpaEntity userOrder = new UserOrderJpaEntity(
                userOrderDto.id(),
                UserMapper.getInstance().fromUserDtoToUserJpaEntity(userOrderDto.user()),
                null,
                userOrderDto.status(),
                userOrderDto.deliveryType(),
                userOrderDto.createdAt());

        // Crear los order items y establecer la relación bidireccional
        if (userOrderDto.orderItems() != null) {
            java.util.List<OrderItemJpaEntity> items = new java.util.ArrayList<>(userOrderDto.orderItems().stream()
                    .map(item -> {
                        OrderItemJpaEntity orderItem = OrderItemMapper.getInstance()
                                .fromOrderItemDtoToOrderItemJpaEntity(item);
                        orderItem.setUser_order_id(userOrder);
                        return orderItem;
                    })
                    .toList());
            userOrder.setOrderItems(items);
        } else {
            userOrder.setOrderItems(new java.util.ArrayList<>());
        }

        return userOrder;
    }

    public UserOrderDto fromUserOrderJpaEntityToUserOrderDto(UserOrderJpaEntity userOrderJpaEntity) {
        if (userOrderJpaEntity == null) {
            return null;
        }

        return new UserOrderDto(
                userOrderJpaEntity.getId(),
                UserMapper.getInstance().fromUserJpaEntityToUserDto(userOrderJpaEntity.getUser()),
                userOrderJpaEntity.getOrderItems() == null ? Collections.emptyList()
                        : userOrderJpaEntity.getOrderItems().stream()
                                .map(item -> OrderItemMapper.getInstance().fromOrderItemJpaEntityToOrderItemDto(item))
                                .toList(),
                userOrderJpaEntity.getStatus(),
                userOrderJpaEntity.getDeliveryType(),
                userOrderJpaEntity.getCreatedAt());
    }
}
