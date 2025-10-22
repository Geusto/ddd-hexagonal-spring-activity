package co.edu.udec.ingsw.activity.domain.usuario.valueobject;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioRol(
  @NotNull(message = "El rol no puede ser nulo")
  @Size(min = 2, max = 20, message = "El rol debe tener entre 2 y 20 caracteres")
  String value
) {
  public UsuarioRol {
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException("El rol no puede ser nulo o vacío");
    }
    if (value.trim().length() < 2 || value.trim().length() > 20) {
      throw new IllegalArgumentException("El rol debe tener entre 2 y 20 caracteres");
    }
    value = value.trim();
  }

  public boolean equals(UsuarioRol other) {
    return other != null && this.value.equals(other.value);
  }

  @Override
  public String toString() {
    return value;
  }
}
