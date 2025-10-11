package co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CarreraTaxiCantidadPasajeros(
  @NotNull(message = "La cantidad de pasajeros no puede ser nula")
  @Positive(message = "La cantidad de pasajeros debe ser un número positivo mayor a 0")
  Integer value
) {
  public CarreraTaxiCantidadPasajeros {
    if (value == null || value <= 0) {
      throw new IllegalArgumentException("La cantidad de pasajeros debe ser un número positivo mayor a 0");
    }
    if (value > 8) { 
      throw new IllegalArgumentException("La cantidad de pasajeros no puede ser mayor a 8");
    }
  }

  public boolean equals(CarreraTaxiCantidadPasajeros other) {
    return other != null && this.value.equals(other.value);
  }

  @Override
  public String toString() {
    return value + " pasajero" + (value > 1 ? "s" : "");
  }
}