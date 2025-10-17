package co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.request;

import jakarta.validation.constraints.*;

public record CarreraTaxiHttpRequest(
  @NotBlank(message = "cliente requerido")
  String cliente,

  @NotBlank(message = "placa requerida")
  @Pattern(regexp = "^[A-Z]{3}\\d{3}$", message = "placa formato ABC123")
  String taxi,

  @NotBlank(message = "taxista requerido")
  String taxista,

  @NotNull @Positive(message = "kilometros > 0")
  Double kilometros,

  @NotBlank(message = "barrioInicio requerido")
  String barrioInicio,

  @NotBlank(message = "barrioLlegada requerido")
  String barrioLlegada,

  @NotNull @Positive @Max(value = 8, message = "máximo 8 pasajeros")
  Integer cantidadPasajeros,

  @NotNull @Positive(message = "precio > 0")
  Double precio,

  @NotNull @Positive(message = "duración > 0")
  Integer duracionMinutos
) {}