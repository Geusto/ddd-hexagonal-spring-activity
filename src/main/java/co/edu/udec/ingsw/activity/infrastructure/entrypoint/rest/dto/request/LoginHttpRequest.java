package co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginHttpRequest(
  @NotBlank(message = "El nombre es requerido")
  String nombre,
  
  @NotBlank(message = "La clave es requerida")
  String clave
) {}