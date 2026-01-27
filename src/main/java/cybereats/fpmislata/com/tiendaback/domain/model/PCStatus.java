package cybereats.fpmislata.com.tiendaback.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PCStatus {
    OCCUPIED,
    MAINTENANCE,
    AVAILABLE;

    @JsonCreator
    public static PCStatus fromString(String value) {
        return value == null ? null : PCStatus.valueOf(value.toUpperCase());
    }
}
