package cybereats.fpmislata.com.tiendaback.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum OrderStatus {
    PENDING,
    CONFIRMED,
    SHIPPED,
    DELIVERED,
    CANCELLED;

    @JsonCreator
    public static OrderStatus fromString(String value) {
        return value == null ? null : OrderStatus.valueOf(value.toUpperCase());
    }
}
