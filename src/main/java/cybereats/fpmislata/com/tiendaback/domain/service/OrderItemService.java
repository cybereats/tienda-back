package cybereats.fpmislata.com.tiendaback.domain.service;

import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;

public interface OrderItemService {
    OrderItemDto insert(OrderItemDto orderItemDto);

    OrderItemDto update(OrderItemDto orderItemDto);

    Optional<OrderItemDto> findById(Long id);

    OrderItemDto getById(Long id);

    Page<OrderItemDto> findAll(int page, int size);

    void deleteById(Long id);
}
