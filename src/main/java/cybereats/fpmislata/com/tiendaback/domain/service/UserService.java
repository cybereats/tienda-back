package cybereats.fpmislata.com.tiendaback.domain.service;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto insert(UserDto user);

    UserDto update(UserDto user);

    Optional<UserDto> getUserById(Long id);

    List<UserDto> getAll();

    void deleteById(Long id);
}
