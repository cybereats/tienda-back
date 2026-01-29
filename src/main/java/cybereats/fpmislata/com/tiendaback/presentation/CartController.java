package cybereats.fpmislata.com.tiendaback.presentation;

import cybereats.fpmislata.com.tiendaback.domain.model.DeliveryType;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.CartService;
import cybereats.fpmislata.com.tiendaback.domain.service.UserOrderService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CartDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;
import cybereats.fpmislata.com.tiendaback.presentation.mapper.CartMapper;
import cybereats.fpmislata.com.tiendaback.presentation.mapper.UserOrderMapper;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.AddToCartRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.UpdateCartItemRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.CartResponse;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.UserOrderResponse;
import cybereats.fpmislata.com.tiendaback.security.AllowedRoles;
import cybereats.fpmislata.com.tiendaback.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
  private final CartService cartService;
  private final UserOrderService userOrderService;

  public CartController(CartService cartService, UserOrderService userOrderService) {
    this.cartService = cartService;
    this.userOrderService = userOrderService;
  }

  @GetMapping
  @AllowedRoles({ UserRole.CLIENT, UserRole.ADMIN })
  public ResponseEntity<CartResponse> getCart(HttpServletRequest request) {
    Long userId = extractUserId(request);
    CartDto cartDto = cartService.getCart(userId);
    return new ResponseEntity<>(CartMapper.getInstance().fromCartDtoToCartResponse(cartDto), HttpStatus.OK);
  }

  @PostMapping("/items")
  @AllowedRoles({ UserRole.CLIENT, UserRole.ADMIN })
  public ResponseEntity<CartResponse> addItem(HttpServletRequest request,
      @RequestBody AddToCartRequest addToCartRequest) {
    Long userId = extractUserId(request);
    CartDto cartDto = cartService.addItem(userId, addToCartRequest.productId(), addToCartRequest.quantity());
    return new ResponseEntity<>(CartMapper.getInstance().fromCartDtoToCartResponse(cartDto), HttpStatus.OK);
  }

  @PutMapping("/items/{productId}")
  @AllowedRoles({ UserRole.CLIENT, UserRole.ADMIN })
  public ResponseEntity<CartResponse> updateItemQuantity(
      HttpServletRequest request,
      @PathVariable Long productId,
      @RequestBody UpdateCartItemRequest updateRequest) {
    Long userId = extractUserId(request);
    CartDto cartDto = cartService.updateItemQuantity(userId, productId, updateRequest.quantity());
    return new ResponseEntity<>(CartMapper.getInstance().fromCartDtoToCartResponse(cartDto), HttpStatus.OK);
  }

  @DeleteMapping("/items/{productId}")
  @AllowedRoles({ UserRole.CLIENT, UserRole.ADMIN })
  public ResponseEntity<CartResponse> removeItem(HttpServletRequest request, @PathVariable Long productId) {
    Long userId = extractUserId(request);
    CartDto cartDto = cartService.removeItem(userId, productId);
    return new ResponseEntity<>(CartMapper.getInstance().fromCartDtoToCartResponse(cartDto), HttpStatus.OK);
  }

  @DeleteMapping
  @AllowedRoles({ UserRole.CLIENT, UserRole.ADMIN })
  public ResponseEntity<Void> clearCart(HttpServletRequest request) {
    Long userId = extractUserId(request);
    cartService.clearCart(userId);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/checkout")
  @AllowedRoles({ UserRole.CLIENT, UserRole.ADMIN })
  public ResponseEntity<UserOrderResponse> checkout(
      HttpServletRequest request,
      @RequestParam(required = false, defaultValue = "TABLE") DeliveryType deliveryType) {
    Long userId = extractUserId(request);
    UserOrderDto orderDto = cartService.checkout(userId, deliveryType);
    return new ResponseEntity<>(UserOrderMapper.getInstance().fromUserOrderDtoToUserOrderResponse(orderDto),
        HttpStatus.CREATED);
  }

  @GetMapping("/my-orders")
  @AllowedRoles({ UserRole.CLIENT, UserRole.ADMIN })
  public ResponseEntity<Page<UserOrderResponse>> getMyOrders(
      HttpServletRequest request,
      @RequestParam(required = false, defaultValue = "1") int page,
      @RequestParam(required = false, defaultValue = "20") int size) {
    Long userId = extractUserId(request);
    Page<UserOrderDto> orderDtoPage = userOrderService.findByUserId(userId, page, size);
    List<UserOrderResponse> content = orderDtoPage.data().stream()
        .map(UserOrderMapper.getInstance()::fromUserOrderDtoToUserOrderResponse)
        .toList();
    return new ResponseEntity<>(new Page<>(content, page, size, orderDtoPage.totalElements()), HttpStatus.OK);
  }

  private Long extractUserId(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      String token = bearerToken.substring(7);
      return JwtUtil.extractUserId(token);
    }
    throw new IllegalArgumentException("Invalid authorization header");
  }
}
