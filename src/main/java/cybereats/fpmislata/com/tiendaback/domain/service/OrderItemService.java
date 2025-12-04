package cybereats.fpmislata.com.tiendaback.domain.service;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;

public interface OrderItemService {
    OrderItemDto insert(OrderItemDto orderItemDto);

    OrderItemDto update(OrderItemDto orderItemDto);

    Optional<OrderItemDto> getOrderItemById(Long id);

    List<OrderItemDto> getAll();

    void deleteById(Long id);
}
