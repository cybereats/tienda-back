package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record UserDto(
                Long id,
                @NotNull String name,
                String surname,
                String email,
                @Past String bornDate,
                @NotNull String username,
                @NotNull String password,
                @NotNull UserRole role) {

}