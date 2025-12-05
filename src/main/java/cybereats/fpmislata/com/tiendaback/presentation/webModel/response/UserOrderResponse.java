package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

import java.math.BigDecimal;
import java.util.List;

public record UserOrderResponse(
                Long id,
                UserResponse user,
                List<OrderItemResponse> orderItems,
                String status) {

}
