package cybereats.fpmislata.com.tiendaback.domain.service;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto insert(UserDto user);

    UserDto update(UserDto user);

    UserDto getById(Long id);

    Optional<UserDto> findById(Long id);

    List<UserDto> findAll(int page, int size);

    void deleteById(Long id);
}
