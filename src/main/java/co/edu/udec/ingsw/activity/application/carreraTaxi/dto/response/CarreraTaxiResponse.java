package co.edu.udec.ingsw.activity.application.carreraTaxi.dto.response;

import java.time.LocalDateTime;

public record CarreraTaxiResponse (
  Integer id,
  String cliente,
  String taxi,
  String taxista,
  Double kilometros,
  String barrioInicio,
  String barrioLlegada,
  Integer cantidadPasajeros,
  Double precio,
  Integer duracionMinutos,
  LocalDateTime fechaCreacion
) {}
