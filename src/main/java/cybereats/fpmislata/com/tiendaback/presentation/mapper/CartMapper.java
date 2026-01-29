package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CartDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CartItemDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.CartResponse;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.CartItemResponse;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.CartProductResponse;

import java.util.stream.Collectors;

public class CartMapper {
  private static final CartMapper instance = new CartMapper();

  private CartMapper() {
  }

  public static CartMapper getInstance() {
    return instance;
  }

  public CartResponse fromCartDtoToCartResponse(CartDto cartDto) {
    if (cartDto == null) {
      return null;
    }

    return new CartResponse(
        cartDto.id(),
        cartDto.items().stream()
            .map(this::fromCartItemDtoToCartItemResponse)
            .collect(Collectors.toList()),
        cartDto.totalItems(),
        cartDto.totalPrice());
  }

  public CartItemResponse fromCartItemDtoToCartItemResponse(CartItemDto itemDto) {
    if (itemDto == null) {
      return null;
    }

    CartProductResponse productResponse = null;
    if (itemDto.product() != null) {
      productResponse = new CartProductResponse(
          itemDto.product().id(),
          itemDto.product().label(),
          itemDto.product().price(),
          itemDto.product().image());
    }

    return new CartItemResponse(
        itemDto.id(),
        itemDto.productId(),
        productResponse,
        itemDto.quantity(),
        itemDto.price());
  }
}
