package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import cybereats.fpmislata.com.tiendaback.domain.model.ReportStatus;

public record ReportDto(
                Long id,
                Integer priority,
                String description,
                String subject,
                ReportStatus status,
                String createdAt,
                UserDto user,
                PCDto pc) {
}
