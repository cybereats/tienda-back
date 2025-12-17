package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Min;

public record BookingDto(
                Long id,
                @Min(1) int hours,
                @NonNull UserDto user,
                @NonNull PCDto pc,
                java.time.LocalDateTime createdAt) {
}
