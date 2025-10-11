package co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CarreraTaxiTaxi(
  @NotNull(message = "La placa del taxi no puede ser nula")
  @Pattern(regexp = "^[A-Z]{3}[0-9]{3}$", message = "La placa debe tener formato ABC123")
  String value
) {
  public CarreraTaxiTaxi {
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException("La placa del taxi no puede ser nula o vacía");
    }
    value = value.trim().toUpperCase();
    if (!value.matches("^[A-Z]{3}[0-9]{3}$")) {
      throw new IllegalArgumentException("La placa debe tener formato ABC123 (3 letras seguidas de 3 números)");
    }
  }

  public boolean equals(CarreraTaxiTaxi other) {
    return other != null && this.value.equals(other.value);
  }

  @Override
  public String toString() {
    return value;
  }
}
