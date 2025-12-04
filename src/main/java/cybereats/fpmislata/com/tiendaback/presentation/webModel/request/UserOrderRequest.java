package cybereats.fpmislata.com.tiendaback.presentation.webModel.request;

import java.util.List;

public record UserOrderRequest(
                Long id,
                Long userId,
                List<Long> orderItemIds,
                String status) {

}
