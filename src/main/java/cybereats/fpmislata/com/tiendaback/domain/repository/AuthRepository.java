package cybereats.fpmislata.com.tiendaback.domain.repository;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;

import java.util.Optional;

public interface AuthRepository {
    Optional<UserDto> findByUsername(String username);

    Optional<UserDto> findByUsernameOrEmail(String identifier);

    Optional<UserDto> findById(Long id);

    UserDto register(UserDto user);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
