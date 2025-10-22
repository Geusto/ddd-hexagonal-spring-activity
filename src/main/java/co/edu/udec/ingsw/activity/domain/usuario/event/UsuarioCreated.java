package co.edu.udec.ingsw.activity.domain.usuario.event;

import java.time.LocalDateTime;

import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioId;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioNombre;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioRol;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioClave;

public record UsuarioCreated(
  UsuarioId id,
  UsuarioNombre nombre,
  UsuarioRol rol,
  UsuarioClave clave,
  LocalDateTime fechaCreacion
) {
    
  public UsuarioCreated {
    if (id == null) {
      throw new IllegalArgumentException("El ID no puede ser nulo");
    }
    if (nombre == null) {
      throw new IllegalArgumentException("El nombre no puede ser nulo");
    }
    if (rol == null) {
      throw new IllegalArgumentException("El rol no puede ser nulo");
    }
    if (clave == null) {
      throw new IllegalArgumentException("La clave no puede ser nula");
    }
    if (fechaCreacion == null) {
      throw new IllegalArgumentException("La fecha de creación no puede ser nula");
    }
  }
  
  public UsuarioId getUsuarioId() {
    return id;
  }
  
  public UsuarioNombre getNombre() {
    return nombre;
  }
  
  public UsuarioRol getRol() {
    return rol;
  }
  
  public UsuarioClave getClave() {
    return clave;
  }
  
  public LocalDateTime getFechaCreacion() {
    return fechaCreacion;
  }
  
  @Override
  public String toString() {
    return String.format("UsuarioCreated{id=%s, nombre=%s, rol=%s, fechaCreacion=%s}", 
      id, nombre, rol, fechaCreacion);
  }
}
