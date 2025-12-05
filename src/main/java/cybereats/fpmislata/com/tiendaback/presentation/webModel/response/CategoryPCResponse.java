package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

import java.math.BigDecimal;

public record CategoryPCResponse(
                Long id,
                String label,
                BigDecimal price) {
}
