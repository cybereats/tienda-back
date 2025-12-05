package cybereats.fpmislata.com.tiendaback.domain.service;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;

public interface UserOrderService {
    List<UserOrderDto> findAll();

    UserOrderDto getById(Long id);

    Optional<UserOrderDto> findById(Long id);

    UserOrderDto insert(UserOrderDto userOrderDto);

    UserOrderDto update(UserOrderDto userOrderDto);

    void deleteById(Long id);
}
