package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import java.util.List;

import cybereats.fpmislata.com.tiendaback.domain.model.DeliveryType;
import cybereats.fpmislata.com.tiendaback.domain.model.OrderStatus;

public record UserOrderDto(
                Long id,
                UserDto user,
                List<OrderItemDto> orderItems,
                OrderStatus status,
                DeliveryType deliveryType,
                java.time.LocalDateTime createdAt) {
}
