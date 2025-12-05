package cybereats.fpmislata.com.tiendaback.domain.service;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;

public interface OrderItemService {
    OrderItemDto insert(OrderItemDto orderItemDto);

    OrderItemDto update(OrderItemDto orderItemDto);

    Optional<OrderItemDto> findById(Long id);

    OrderItemDto getById(Long id);

    List<OrderItemDto> findAll();

    void deleteById(Long id);
}
