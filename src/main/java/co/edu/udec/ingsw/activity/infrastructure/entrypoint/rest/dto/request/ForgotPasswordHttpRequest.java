package co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordHttpRequest(
  @NotBlank(message = "El nombre es requerido")
  String nombre
) {}

