package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import jakarta.validation.constraints.NotNull;

public record RegisterRequestDto(
        @NotNull String name,
        String surname,
        String bornDate,
        @NotNull String username,
        @NotNull String password) {
}
