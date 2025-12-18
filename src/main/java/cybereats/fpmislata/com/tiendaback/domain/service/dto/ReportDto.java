package cybereats.fpmislata.com.tiendaback.domain.service.dto;

public record ReportDto(
        Long id,
        String priority,
        String desc,
        String subject,
        String status,
        String createdAt,
        UserDto user,
        PCDto pc) {
}
