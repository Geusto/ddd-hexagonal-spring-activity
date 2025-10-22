package co.edu.udec.ingsw.activity.domain.usuario.event;

import java.time.LocalDateTime;

import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioId;

public record UsuarioDeleted(
  UsuarioId id,
  LocalDateTime fechaEliminacion
) {
    
  public UsuarioDeleted {
    if (id == null) {
      throw new IllegalArgumentException("El ID no puede ser nulo");
    }
    if (fechaEliminacion == null) {
      throw new IllegalArgumentException("La fecha de eliminación no puede ser nula");
    }
  }
  
  public UsuarioId getUsuarioId() {
    return id;
  }
  
  public LocalDateTime getFechaEliminacion() {
    return fechaEliminacion;
  }
  
  @Override
  public String toString() {
    return String.format("UsuarioDeleted{id=%s, fechaEliminacion=%s}", 
      id, fechaEliminacion);
  }
}
