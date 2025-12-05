package cybereats.fpmislata.com.tiendaback.presentation.webModel.request;

public record OrderItemRequest(
                Long id,
                Long productId,
                int quantity) {

}
