package co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ResetPasswordHttpRequest(
  @NotBlank(message = "El token es requerido")
  String token,
  
  @NotBlank(message = "La nueva clave es requerida")
  @Size(min = 6, message = "La clave debe tener al menos 6 caracteres")
  String nuevaClave
) {}