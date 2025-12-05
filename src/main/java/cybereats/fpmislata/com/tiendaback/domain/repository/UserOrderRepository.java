package cybereats.fpmislata.com.tiendaback.domain.repository;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;

public interface UserOrderRepository {

    List<UserOrderDto> findAll();

    Optional<UserOrderDto> findById(Long id);

    UserOrderDto save(UserOrderDto userOrderDto);

    void deleteById(Long id);
}
