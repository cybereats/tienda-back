package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import java.time.LocalDateTime;

public record AuthResponseDto(
        String token,
        LocalDateTime expiresAt,
        UserDto user) {
}
