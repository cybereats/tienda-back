package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import jakarta.validation.constraints.NotNull;

public record RegisterRequestDto(
                @NotNull String name,
                String surname,
                @NotNull String email,
                String bornDate,
                @NotNull String username,
                @NotNull String password,
                UserRole role) {
}
