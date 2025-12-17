package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import java.math.BigDecimal;
import java.util.List;

import cybereats.fpmislata.com.tiendaback.domain.model.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record UserOrderDto(
                Long id,
                @NotNull UserDto user,
                List<OrderItemDto> orderItems,
                OrderStatus status,
                java.time.LocalDateTime createdAt) {
}
