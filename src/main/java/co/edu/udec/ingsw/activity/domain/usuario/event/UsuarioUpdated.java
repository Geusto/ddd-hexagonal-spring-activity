package co.edu.udec.ingsw.activity.domain.usuario.event;

import java.time.LocalDateTime;

import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioId;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioNombre;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioRol;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioClave;

public record UsuarioUpdated(
  UsuarioId id,
  UsuarioNombre nombreAnterior,
  UsuarioNombre nombreNuevo,
  UsuarioRol rolAnterior,
  UsuarioRol rolNuevo,
  UsuarioClave claveAnterior,
  UsuarioClave claveNueva,
  LocalDateTime fechaActualizacion
) {
    
  public UsuarioUpdated {
    if (id == null) {
      throw new IllegalArgumentException("El ID no puede ser nulo");
    }
    if (nombreAnterior == null) {
      throw new IllegalArgumentException("El nombre anterior no puede ser nulo");
    }
    if (nombreNuevo == null) {
      throw new IllegalArgumentException("El nombre nuevo no puede ser nulo");
    }
    if (rolAnterior == null) {
      throw new IllegalArgumentException("El rol anterior no puede ser nulo");
    }
    if (rolNuevo == null) {
      throw new IllegalArgumentException("El rol nuevo no puede ser nulo");
    }
    if (claveAnterior == null) {
      throw new IllegalArgumentException("La clave anterior no puede ser nula");
    }
    if (claveNueva == null) {
      throw new IllegalArgumentException("La clave nueva no puede ser nula");
    }
    if (fechaActualizacion == null) {
      throw new IllegalArgumentException("La fecha de actualización no puede ser nula");
    }
  }
  
  public UsuarioId getUsuarioId() {
    return id;
  }
  
  public UsuarioNombre getNombreAnterior() {
    return nombreAnterior;
  }
  
  public UsuarioNombre getNombreNuevo() {
    return nombreNuevo;
  }
  
  public UsuarioRol getRolAnterior() {
    return rolAnterior;
  }
  
  public UsuarioRol getRolNuevo() {
    return rolNuevo;
  }
  
  public UsuarioClave getClaveAnterior() {
    return claveAnterior;
  }
  
  public UsuarioClave getClaveNueva() {
    return claveNueva;
  }
  
  public LocalDateTime getFechaActualizacion() {
    return fechaActualizacion;
  }
  
  @Override
  public String toString() {
    return String.format("UsuarioUpdated{id=%s, nombreAnterior=%s, nombreNuevo=%s, " + 
      "rolAnterior=%s, rolNuevo=%s, fechaActualizacion=%s}", 
      id, nombreAnterior, nombreNuevo, rolAnterior, rolNuevo, fechaActualizacion);
  }
}
