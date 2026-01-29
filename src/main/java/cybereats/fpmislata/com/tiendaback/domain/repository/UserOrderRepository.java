package cybereats.fpmislata.com.tiendaback.domain.repository;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;

public interface UserOrderRepository {

    List<UserOrderDto> findAll();

    Page<UserOrderDto> findAll(int page, int size);

    Page<UserOrderDto> findByUserId(Long userId, int page, int size);

    public Page<UserOrderDto> search(String text, String status, String date, int page, int size);

    Optional<UserOrderDto> findById(Long id);

    UserOrderDto save(UserOrderDto userOrderDto);

    void deleteById(Long id);
}
