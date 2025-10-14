package co.edu.udec.ingsw.activity.domain.carreraTaxi.event;

import java.time.LocalDateTime;

import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiId;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiPrecio;

public record CarreraTaxiUpdated(
  CarreraTaxiId id,
  CarreraTaxiPrecio precioAnterior,
  CarreraTaxiPrecio precioNuevo,
  LocalDateTime fechaActualizacion
) {
  
  public CarreraTaxiUpdated {
    if (id == null) {
      throw new IllegalArgumentException("El ID no puede ser nulo");
    }
    if (precioAnterior == null) {
      throw new IllegalArgumentException("El precio anterior no puede ser nulo");
    }
    if (precioNuevo == null) {
      throw new IllegalArgumentException("El precio nuevo no puede ser nulo");
    }
    if (fechaActualizacion == null) {
      throw new IllegalArgumentException("La fecha de actualización no puede ser nula");
    }
  }
  
  public CarreraTaxiId getCarreraId() {
    return id;
  }
  
  public CarreraTaxiPrecio getPrecioAnterior() {
    return precioAnterior;
  }
  
  public CarreraTaxiPrecio getPrecioNuevo() {
    return precioNuevo;
  }
  
  public LocalDateTime getFechaActualizacion() {
    return fechaActualizacion;
  }
  
  @Override
  public String toString() {
    return String.format("CarreraTaxiUpdated{id=%s, precioAnterior=%s, precioNuevo=%s, fechaActualizacion=%s}", id, precioAnterior, precioNuevo, fechaActualizacion);
  }
}