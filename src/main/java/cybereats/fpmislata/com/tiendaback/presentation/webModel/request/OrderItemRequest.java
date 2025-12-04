package cybereats.fpmislata.com.tiendaback.presentation.webModel.request;

public record OrderItemRequest(
                Long id,
                Long product_id,
                int quantity) {

}
