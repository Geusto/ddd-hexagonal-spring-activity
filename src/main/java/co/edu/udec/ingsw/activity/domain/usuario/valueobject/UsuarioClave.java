package co.edu.udec.ingsw.activity.domain.usuario.valueobject;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioClave(
  @NotNull(message = "La clave no puede ser nula")
  @Size(min = 6, max = 100, message = "La clave debe tener entre 6 y 100 caracteres")
  String value
) {
  public UsuarioClave {
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException("La clave no puede ser nula o vacía");
    }
    if (value.trim().length() < 6 || value.trim().length() > 100) {
      throw new IllegalArgumentException("La clave debe tener entre 6 y 100 caracteres");
    }
    value = value.trim();
  }

  public boolean equals(UsuarioClave other) {
    return other != null && this.value.equals(other.value);
  }

  @Override
  public String toString() {
    return "***"; // Por seguridad, no mostramos la clave real
  }
}
