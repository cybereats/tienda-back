package cybereats.fpmislata.com.tiendaback.presentation;

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

import cybereats.fpmislata.com.tiendaback.domain.service.UserOrderService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;
import cybereats.fpmislata.com.tiendaback.domain.validation.DtoValidator;
import cybereats.fpmislata.com.tiendaback.presentation.mapper.UserOrderMapper;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.UserOrderRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.UserOrderResponse;

@RestController
@RequestMapping("/api/user-orders")
public class UserOrderController {
    private final UserOrderService userOrderService;

    public UserOrderController(UserOrderService userOrderService) {
        this.userOrderService = userOrderService;
    }

    @PostMapping
    public ResponseEntity<UserOrderResponse> createUserOrder(@RequestBody UserOrderRequest userOrderRequest) {
        UserOrderDto userOrderDto = UserOrderMapper.fromUserOrderRequestToUserOrderDto(userOrderRequest);
        DtoValidator.validate(userOrderDto);
        UserOrderDto createdUserOrderDto = userOrderService.insert(userOrderDto);
        return new ResponseEntity<>(UserOrderMapper.fromUserOrderDtoToUserOrderResponse(createdUserOrderDto),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserOrderResponse>> getAllUserOrders() {
        List<UserOrderDto> userOrderDtoList = userOrderService.getAll();
        return new ResponseEntity<>(
                userOrderDtoList.stream().map(UserOrderMapper::fromUserOrderDtoToUserOrderResponse).toList(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOrderResponse> getUserOrderById(@PathVariable Long id) {
        UserOrderDto userOrderDto = userOrderService.getUserOrderById(id).get();
        return new ResponseEntity<>(UserOrderMapper.fromUserOrderDtoToUserOrderResponse(userOrderDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserOrderResponse> updateUserOrder(@PathVariable Long id,
            @RequestBody UserOrderRequest userOrderRequest) {
        if (!id.equals(userOrderRequest.id())) {
            throw new IllegalArgumentException("El id del pedido no coincide con el id proporcionado");
        }
        UserOrderDto userOrderDto = UserOrderMapper.fromUserOrderRequestToUserOrderDto(userOrderRequest);
        DtoValidator.validate(userOrderDto);
        UserOrderDto updatedUserOrderDto = userOrderService.update(userOrderDto);
        return new ResponseEntity<>(UserOrderMapper.fromUserOrderDtoToUserOrderResponse(updatedUserOrderDto),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserOrder(@PathVariable Long id) {
        userOrderService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
