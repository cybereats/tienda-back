package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record CategoryPCDto(
        Long id,
        @NotNull
        String label,
        BigDecimal price,
        @NotNull
        List<PCDto> pc_list
) {
}
