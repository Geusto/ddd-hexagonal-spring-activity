package co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LogoutHttpRequest(
  @NotBlank(message = "El token es requerido")
  String token
) {}