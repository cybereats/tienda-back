package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import jakarta.validation.constraints.NotNull;

public record LoginRequestDto(
        @NotNull String username,
        @NotNull String password) {
}
