package cybereats.fpmislata.com.tiendaback.domain.repository;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;

import java.util.Optional;

public interface AuthRepository {
    Optional<UserDto> findByUsername(String username);
    UserDto register(UserDto user);
    boolean existsByUsername(String username);
}
