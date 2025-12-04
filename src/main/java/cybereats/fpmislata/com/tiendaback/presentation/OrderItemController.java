package cybereats.fpmislata.com.tiendaback.presentation;

import cybereats.fpmislata.com.tiendaback.domain.service.OrderItemService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;
import cybereats.fpmislata.com.tiendaback.domain.validation.DtoValidator;
import cybereats.fpmislata.com.tiendaback.presentation.mapper.OrderItemMapper;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.OrderItemRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.OrderItemResponse;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public ResponseEntity<OrderItemResponse> createOrderItem(@RequestBody OrderItemRequest orderItemRequest) {
        OrderItemDto orderItemDto = OrderItemMapper.fromOrderItemRequestToOrderItemDto(orderItemRequest);
        DtoValidator.validate(orderItemDto);
        OrderItemDto createdOrderItemDto = orderItemService.insert(orderItemDto);
        return new ResponseEntity<>(OrderItemMapper.fromOrderItemDtoToOrderItemResponse(createdOrderItemDto),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderItemResponse>> getAllOrderItems() {
        List<OrderItemDto> orderItemDtoList = orderItemService.getAll();
        return new ResponseEntity<>(
                orderItemDtoList.stream().map(OrderItemMapper::fromOrderItemDtoToOrderItemResponse).toList(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponse> getOrderItemById(@PathVariable Long id) {
        OrderItemDto orderItemDto = orderItemService.getOrderItemById(id).get();
        return new ResponseEntity<>(OrderItemMapper.fromOrderItemDtoToOrderItemResponse(orderItemDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemResponse> updateOrderItem(@PathVariable Long id,
            @RequestBody OrderItemRequest orderItemRequest) {
        if (!id.equals(orderItemRequest.id())) {
            throw new IllegalArgumentException("El id del pedido no coincide con el id proporcionado");
        }
        OrderItemDto orderItemDto = OrderItemMapper.fromOrderItemRequestToOrderItemDto(orderItemRequest);
        DtoValidator.validate(orderItemDto);
        OrderItemDto updatedOrderItemDto = orderItemService.update(orderItemDto);
        return new ResponseEntity<>(OrderItemMapper.fromOrderItemDtoToOrderItemResponse(updatedOrderItemDto),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
