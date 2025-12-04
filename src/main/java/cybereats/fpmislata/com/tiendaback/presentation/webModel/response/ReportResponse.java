package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

public record ReportResponse(
        Long id,
        String priority,
        String desc,
        UserResponse user,
        PcResponse pc) {
}
