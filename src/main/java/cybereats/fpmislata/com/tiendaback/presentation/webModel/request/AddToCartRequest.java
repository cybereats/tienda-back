package cybereats.fpmislata.com.tiendaback.presentation.webModel.request;

public record AddToCartRequest(
    Long productId,
    int quantity) {
}
