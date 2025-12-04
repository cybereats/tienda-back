package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import jakarta.validation.constraints.NotNull;

public record CategoryProductDto(
                Long id,
                @NotNull String name,
                @NotNull String slug) {

}
