package cybereats.fpmislata.com.tiendaback.presentation.webModel.request;

import java.util.List;

import cybereats.fpmislata.com.tiendaback.domain.model.OrderStatus;

public record UserOrderRequest(
        Long id,
        Long userId,
        List<Long> orderItemIds,
        OrderStatus status) {

}
