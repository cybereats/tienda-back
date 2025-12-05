package cybereats.fpmislata.com.tiendaback.domain.repository;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    UserDto save(UserDto user);

    Optional<UserDto> findById(Long id);

    List<UserDto> findAll(int page, int size);

    void deleteById(Long id);
}
