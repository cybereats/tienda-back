package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record UserDto(
        Long id,
        @NotNull String name,
        String surname,
        @Past String bornDate,
        @NotNull String username,
        @NotNull String password) {

}