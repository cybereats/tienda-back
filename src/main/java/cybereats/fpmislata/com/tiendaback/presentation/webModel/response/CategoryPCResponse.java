package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

import java.math.BigDecimal;
import java.util.List;

public record CategoryPCResponse(
        Long id,
        String label,
        BigDecimal price,
        List<PCResponse> pcs
) { }
