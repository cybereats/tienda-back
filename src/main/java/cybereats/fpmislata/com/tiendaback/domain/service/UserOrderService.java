package cybereats.fpmislata.com.tiendaback.domain.service;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;

public interface UserOrderService {
    Page<UserOrderDto> findAll(int page, int size);

    Page<UserOrderDto> findByUserId(Long userId, int page, int size);

    UserOrderDto getById(Long id);

    Optional<UserOrderDto> findById(Long id);

    public Page<UserOrderDto> search(String text, String status, String date, int page, int size);

    UserOrderDto insert(UserOrderDto userOrderDto);

    UserOrderDto update(UserOrderDto userOrderDto);

    void deleteById(Long id);

    long getNextId();
}
