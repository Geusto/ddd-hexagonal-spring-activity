package co.edu.udec.ingsw.activity.domain.usuario.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioId;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioNombre;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioRol;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioClave;


import co.edu.udec.ingsw.activity.domain.usuario.event.UsuarioCreated;
import co.edu.udec.ingsw.activity.domain.usuario.event.UsuarioUpdated;
import co.edu.udec.ingsw.activity.domain.usuario.event.UsuarioDeleted;

public class Usuario {

  private final UsuarioId id;
  private final UsuarioNombre nombre;
  private final UsuarioRol rol;
  private final UsuarioClave clave;
  private final LocalDateTime fechaCreacion;
    
  private final List<Object> domainEvents = new ArrayList<>();
    
  /**
   * Constructor principal de la entidad Usuario
   * 
   * @param id Identificador único del usuario
   * @param nombre Nombre del usuario
   * @param rol Rol del usuario
   * @param clave Clave del usuario
   */
  public Usuario(UsuarioId id, UsuarioNombre nombre, UsuarioRol rol, UsuarioClave clave) {

    // Validaciones de negocio
    validateBusinessRules(id, nombre, rol, clave);
    this.id = id;
    this.nombre = nombre;
    this.rol = rol;
    this.clave = clave;
    this.fechaCreacion = LocalDateTime.now();
  
    // Publicar evento de dominio
    addDomainEvent(new UsuarioCreated(
      this.id, this.nombre, this.rol, this.clave, this.fechaCreacion
    ));
  }
  
  
  public UsuarioId getId() { return id; }
  public UsuarioNombre getNombre() { return nombre; }
  public UsuarioRol getRol() { return rol; }
  public UsuarioClave getClave() { return clave; }
  public LocalDateTime getFechaCreacion() { return fechaCreacion; }

  public void actualizarUsuario(UsuarioNombre nuevoNombre, UsuarioRol nuevoRol, UsuarioClave nuevaClave) {
    if (nuevoNombre == null) {
      throw new IllegalArgumentException("El nuevo nombre no puede ser nulo");
    }
    if (nuevoRol == null) {
      throw new IllegalArgumentException("El nuevo rol no puede ser nulo");
    }
    if (nuevaClave == null) {
      throw new IllegalArgumentException("La nueva clave no puede ser nula");
    }
    
    
    UsuarioNombre nombreAnterior = this.nombre;
    UsuarioRol rolAnterior = this.rol;
    UsuarioClave claveAnterior = this.clave;
    
    
    addDomainEvent(new UsuarioUpdated(
      this.id, nombreAnterior, nuevoNombre, rolAnterior, nuevoRol, 
      claveAnterior, nuevaClave, LocalDateTime.now()
    ));
  }
  
    public void marcarUsuarioParaEliminar() {
    addDomainEvent(new UsuarioDeleted(
      this.id, LocalDateTime.now()
    ));
  }
  
  public boolean equals(Usuario other) {
    return other != null && this.id.equals(other.getId());
  }
  
  @Override
  public String toString() {
    return String.format("Usuario{id=%s, nombre=%s, rol=%s, fechaCreacion=%s}", 
      id, nombre, rol, fechaCreacion);
  }
  
  public List<Object> getDomainEvents() {
    return new ArrayList<>(domainEvents);
  }
  
  public void clearDomainEvents() {
    domainEvents.clear();
  }
  
  private void addDomainEvent(Object event) {
    domainEvents.add(event);
  }
  
  private void validateBusinessRules(UsuarioId id, UsuarioNombre nombre, UsuarioRol rol, UsuarioClave clave) {
      
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
  }
}
