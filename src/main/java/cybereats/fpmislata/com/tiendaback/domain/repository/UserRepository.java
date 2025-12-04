package cybereats.fpmislata.com.tiendaback.domain.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.User;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    UserDto save(UserDto user);

    Optional<UserDto> getUserById(Long id);

    List<UserDto> getAll();

    void deleteById(Long id);
}
