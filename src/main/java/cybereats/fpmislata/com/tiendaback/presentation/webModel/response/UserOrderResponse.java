package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

import java.math.BigDecimal;
import java.util.List;

import java.time.LocalDateTime;

import cybereats.fpmislata.com.tiendaback.domain.model.DeliveryType;
import cybereats.fpmislata.com.tiendaback.domain.model.OrderStatus;

public record UserOrderResponse(
                Long id,
                UserResponse user,
                List<OrderItemResponse> orderItems,
                OrderStatus status,
                DeliveryType deliveryType,
                LocalDateTime createdAt) {

}
