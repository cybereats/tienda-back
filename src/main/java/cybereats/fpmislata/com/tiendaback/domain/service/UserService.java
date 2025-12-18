package cybereats.fpmislata.com.tiendaback.domain.service;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;

import java.util.Optional;

public interface UserService {
    UserDto insert(UserDto user);

    UserDto update(UserDto user);

    UserDto getById(Long id);

    Optional<UserDto> findById(Long id);

    Page<UserDto> findAll(int page, int size);

    void deleteById(Long id);
}
