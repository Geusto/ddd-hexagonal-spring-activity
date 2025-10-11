package co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CarreraTaxiCliente(
  @NotNull(message = "El cliente no puede ser nulo")
  @Size(min = 2, max = 50, message = "El cliente debe tener entre 2 y 50 caracteres")
  String value
) {
  public CarreraTaxiCliente {
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException("El cliente no puede ser nulo o vacío");
    }
    if (value.trim().length() < 2 || value.trim().length() > 50) {
      throw new IllegalArgumentException("El cliente debe tener entre 2 y 50 caracteres");
    }
    // Normalizar el valor
    value = value.trim();
  }

  public boolean equals(CarreraTaxiCliente other) {
    return other != null && this.value.equals(other.value);
  }

  @Override
  public String toString() {
    return value;
  }
}