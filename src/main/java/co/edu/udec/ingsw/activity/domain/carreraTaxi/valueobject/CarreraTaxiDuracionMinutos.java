package co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CarreraTaxiDuracionMinutos(
  @NotNull(message = "La duración en minutos no puede ser nula")
  @Positive(message = "La duración en minutos debe ser un número positivo mayor a 0")
  Integer value
) {
  public CarreraTaxiDuracionMinutos {
    if (value == null || value <= 0) {
      throw new IllegalArgumentException("La duración en minutos debe ser un número positivo mayor a 0");
    }
    if (value > 1440) { // 24 horas en minutos
      throw new IllegalArgumentException("La duración no puede ser mayor a 1440 minutos (24 horas)");
    }
  }

  public boolean equals(CarreraTaxiDuracionMinutos other) {
    return other != null && this.value.equals(other.value);
  }

  @Override
  public String toString() {
    return value + " min";
  }
}