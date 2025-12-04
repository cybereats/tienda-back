package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import jakarta.validation.constraints.NotNull;

public record ReportDto(
                Long id,
                String priority,
                String desc,
                @NotNull UserDto user,
                @NotNull PcDto pc) {
}
