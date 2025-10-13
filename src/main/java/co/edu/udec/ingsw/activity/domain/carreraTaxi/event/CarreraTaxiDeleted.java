package co.edu.udec.ingsw.activity.domain.carreraTaxi.event;

import java.time.LocalDateTime;

import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiId;

public record CarreraTaxiDeleted(
  CarreraTaxiId id,
  LocalDateTime fechaEliminacion
) {
    
  public CarreraTaxiDeleted {
    if (id == null) {
      throw new IllegalArgumentException("El ID no puede ser nulo");
    }
    if (fechaEliminacion == null) {
      throw new IllegalArgumentException("La fecha de eliminación no puede ser nula");
    }
  }
    
  public CarreraTaxiId getCarreraId() {
    return id;
  }
  
  public LocalDateTime getFechaEliminacion() {
    return fechaEliminacion;
  }
  
  @Override
  public String toString() {
    return String.format("CarreraTaxiDeleted{id=%s, fechaEliminacion=%s}", id, fechaEliminacion);
  }
}