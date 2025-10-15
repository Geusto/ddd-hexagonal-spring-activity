package co.edu.udec.ingsw.activity.application.carreraTaxi.dto.command;

public record UpdateCarreraTaxiCommand(
  Integer id,
  String cliente,
  String taxi,
  String taxista,
  Double kilometros,
  String barrioInicio,
  String barrioLlegada,
  Integer cantidadPasajeros,
  Double precio,
  Integer duracionMinutos
) {}