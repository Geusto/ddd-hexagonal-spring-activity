package co.edu.udec.ingsw.activity.domain.usuario.valueobject;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UsuarioId(
  @NotNull(message = "El ID no puede ser nulo")
  @Positive(message = "El ID debe ser un número positivo mayor a 0")
  Integer value
) {
  public UsuarioId {
    if (value == null || value <= 0) {
      throw new IllegalArgumentException("El ID del usuario debe ser un número positivo mayor a 0");
    }
  }

  public boolean equals(UsuarioId other) {
    return other != null && this.value.equals(other.value);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
