package cybereats.fpmislata.com.tiendaback.presentation.webModel.request;

public record ReportRequest(
                Long id,
                Long userId,
                Long pcId,
                String description,
                String subject,
                String status,
                String createdAt,
                String priority) {

}
