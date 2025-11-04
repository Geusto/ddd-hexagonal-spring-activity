package co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.request;

import jakarta.validation.constraints.*;

public record UsuarioHttpRequest(
  @NotBlank(message = "nombre requerido")
  @Size(min = 2, max = 50, message = "nombre debe tener entre 2 y 50 caracteres")
  String nombre,

  @NotBlank(message = "rol requerido")
  @Size(min = 2, max = 20, message = "rol debe tener entre 2 y 20 caracteres")
  String rol,

  @NotBlank(message = "clave requerida")
  @Size(min = 6, max = 100, message = "clave debe tener entre 6 y 100 caracteres")
  String clave
) {}
