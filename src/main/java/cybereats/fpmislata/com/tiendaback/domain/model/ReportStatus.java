package cybereats.fpmislata.com.tiendaback.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ReportStatus {
    OPEN,
    PENDING,
    IN_PROGRESS,
    RESOLVED;

    @JsonCreator
    public static ReportStatus fromString(String value) {
        return value == null ? null : ReportStatus.valueOf(value.toUpperCase());
    }
}
