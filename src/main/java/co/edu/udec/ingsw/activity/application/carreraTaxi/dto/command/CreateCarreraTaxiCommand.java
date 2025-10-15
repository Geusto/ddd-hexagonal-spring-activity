package co.edu.udec.ingsw.activity.application.carreraTaxi.dto.command;

public record CreateCarreraTaxiCommand(
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