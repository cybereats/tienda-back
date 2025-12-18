package cybereats.fpmislata.com.tiendaback.domain.repository;

import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;

public interface OrderItemRepository {
    OrderItemDto save(OrderItemDto orderItemDto);

    Optional<OrderItemDto> findById(Long id);

    Page<OrderItemDto> findAll(int page, int size);

    void deleteById(Long id);
}
