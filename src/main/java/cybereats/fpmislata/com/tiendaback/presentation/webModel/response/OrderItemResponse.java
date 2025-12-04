package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

import java.math.BigDecimal;

public record OrderItemResponse(
                Long id,
                ProductResponse product,
                int quantity,
                BigDecimal price) {

}
