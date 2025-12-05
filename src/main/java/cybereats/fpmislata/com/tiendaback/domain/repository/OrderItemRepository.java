package cybereats.fpmislata.com.tiendaback.domain.repository;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;

public interface OrderItemRepository {
    OrderItemDto save(OrderItemDto orderItemDto);

    Optional<OrderItemDto> findById(Long id);

    List<OrderItemDto> findAll();

    void deleteById(Long id);
}
