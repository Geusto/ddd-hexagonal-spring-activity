package co.edu.udec.ingsw.activity.domain.usuario.valueobject;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioNombre(
  @NotNull(message = "El nombre no puede ser nulo")
  @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
  String value
) {
  public UsuarioNombre {
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
    }
    if (value.trim().length() < 2 || value.trim().length() > 50) {
      throw new IllegalArgumentException("El nombre debe tener entre 2 y 50 caracteres");
    }
    value = value.trim();
  }

  public boolean equals(UsuarioNombre other) {
    return other != null && this.value.equals(other.value);
  }

  @Override
  public String toString() {
    return value;
  }
}
