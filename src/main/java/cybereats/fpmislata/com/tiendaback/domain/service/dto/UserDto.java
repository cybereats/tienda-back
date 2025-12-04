package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record UserDto(
                Long id,
                @NotNull String name,
                String surname,
                @Past String born_date,
                @NotNull String username,
                @NotNull String password) {

}