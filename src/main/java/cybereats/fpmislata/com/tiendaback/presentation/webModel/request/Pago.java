package cybereats.fpmislata.com.tiendaback.presentation.webModel.request;

import java.math.BigDecimal;

public record Pago(BigDecimal importe, String concepto) {
}
