package cybereats.fpmislata.com.tiendaback.presentation.webModel.request;

public record ReportRequest(
        Long id,
        Long user_id,
        Long pc_id,
        String description,
        String priority) {

}
