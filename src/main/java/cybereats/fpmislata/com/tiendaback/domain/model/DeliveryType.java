package cybereats.fpmislata.com.tiendaback.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum DeliveryType {
  TABLE, // Entrega en mesa (requiere reserva activa)
  PICKUP; // Recogida en mostrador (no requiere reserva)

  @JsonCreator
  public static DeliveryType fromString(String value) {
    return value == null ? null : DeliveryType.valueOf(value.toUpperCase());
  }
}
