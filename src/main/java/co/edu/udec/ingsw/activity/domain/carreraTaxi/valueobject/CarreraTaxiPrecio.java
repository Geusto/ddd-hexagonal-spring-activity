package co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CarreraTaxiPrecio(
  @NotNull(message = "El precio no puede ser nulo")
  @Positive(message = "El precio debe ser un número positivo mayor a 0")
  Double value
) {
  public CarreraTaxiPrecio {
    if (value == null || value <= 0) {
      throw new IllegalArgumentException("El precio debe ser un número positivo mayor a 0");
    }
    if (value > 1000000) {
      throw new IllegalArgumentException("El precio no puede ser mayor a 1,000,000");
    }
  }

  public boolean equals(CarreraTaxiPrecio other) {
    return other != null && this.value.equals(other.value);
  }

  @Override
  public String toString() {
    return String.format("$%,.2f", value);
  }
}