package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

public record ReportResponse(
        Long id,
        String priority,
        String desc,
        String subject,
        String status,
        String createdAt,
        UserResponse user,
        PCResponse pc) {
}
