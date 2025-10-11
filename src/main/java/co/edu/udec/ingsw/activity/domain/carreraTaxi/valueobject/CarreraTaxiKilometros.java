package co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CarreraTaxiKilometros(
  @NotNull(message = "La distancia en kilómetros no puede ser nula")
  @Positive(message = "La distancia en kilómetros debe ser un número positivo mayor a 0")
  Double value
) {
  public CarreraTaxiKilometros {
    if (value == null || value <= 0) {
      throw new IllegalArgumentException("La distancia en kilómetros debe ser un número positivo mayor a 0");
    }
    if (value > 1000) {
      throw new IllegalArgumentException("La distancia no puede ser mayor a 1000 kilómetros");
    }
  }

  public boolean equals(CarreraTaxiKilometros other) {
    return other != null && this.value.equals(other.value);
  }

  @Override
  public String toString() {
    return String.format("%.2f km", value);
  }
}
