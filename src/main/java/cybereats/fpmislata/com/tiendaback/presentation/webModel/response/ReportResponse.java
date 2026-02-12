package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

import cybereats.fpmislata.com.tiendaback.domain.model.ReportStatus;

public record ReportResponse(
        Long id,
        Integer priority,
        String description,
        String subject,
        ReportStatus status,
        String createdAt,
        UserResponse user,
        PCResponse pc) {
}
