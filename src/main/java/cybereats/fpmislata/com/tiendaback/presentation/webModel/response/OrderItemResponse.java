package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

public record OrderItemResponse(
                Long id,
                ProductResponse product,
                int quantity
) {

}
