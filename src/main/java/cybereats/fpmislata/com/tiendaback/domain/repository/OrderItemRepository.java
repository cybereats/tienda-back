package cybereats.fpmislata.com.tiendaback.domain.repository;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;

public interface OrderItemRepository {
    OrderItemDto save(OrderItemDto orderItemDto);

    Optional<OrderItemDto> getOrderItemById(Long id);

    List<OrderItemDto> getAll();

    void deleteById(Long id);
}
