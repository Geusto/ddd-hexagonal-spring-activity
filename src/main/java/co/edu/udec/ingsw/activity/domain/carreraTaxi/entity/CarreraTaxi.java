package co.edu.udec.ingsw.activity.domain.carreraTaxi.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiId;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiCliente;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiTaxi;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiTaxista;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiKilometros;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiBarrioInicio;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiBarrioLlegada;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiCantidadPasajeros;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiPrecio;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiDuracionMinutos;

// Imports para eventos de dominio
import co.edu.udec.ingsw.activity.domain.carreraTaxi.event.CarreraTaxiCreated;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.event.CarreraTaxiUpdated;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.event.CarreraTaxiDeleted;

public class CarreraTaxi {
  // Campos inmutables (final)
  private final CarreraTaxiId id;
  private final CarreraTaxiCliente cliente;
  private final CarreraTaxiTaxi taxi;
  private final CarreraTaxiTaxista taxista;
  private final CarreraTaxiKilometros kilometros;
  private final CarreraTaxiBarrioInicio barrioInicio;
  private final CarreraTaxiBarrioLlegada barrioLlegada;
  private final CarreraTaxiCantidadPasajeros cantidadPasajeros;
  private final CarreraTaxiDuracionMinutos duracionMinutos;
  private final LocalDateTime fechaCreacion;

  // Campos mutables
  private CarreraTaxiPrecio precio;
    
  private final List<Object> domainEvents = new ArrayList<>();
    
  /**
   * Constructor principal de la entidad CarreraTaxi
   * 
   * @param id Identificador único de la carrera
   * @param cliente Nombre del cliente
   * @param taxi Placa del vehículo
   * @param taxista Nombre del taxista
   * @param kilometros Distancia recorrida en kilómetros
   * @param barrioInicio Barrio de inicio de la carrera
   * @param barrioLlegada Barrio de llegada de la carrera
   * @param cantidadPasajeros Número de pasajeros
   * @param precio Precio de la carrera
   * @param duracionMinutos Duración en minutos
   */
  public CarreraTaxi(CarreraTaxiId id, CarreraTaxiCliente cliente, CarreraTaxiTaxi taxi, CarreraTaxiTaxista taxista, CarreraTaxiKilometros kilometros, CarreraTaxiBarrioInicio barrioInicio, CarreraTaxiBarrioLlegada barrioLlegada, CarreraTaxiCantidadPasajeros cantidadPasajeros, CarreraTaxiPrecio precio, CarreraTaxiDuracionMinutos duracionMinutos) {

  // Validaciones de negocio
  validateBusinessRules(id, cliente, taxi, taxista, kilometros, barrioInicio, barrioLlegada, cantidadPasajeros, precio, duracionMinutos);
    this.id = id;
    this.cliente = cliente;
    this.taxi = taxi;
    this.taxista = taxista;
    this.kilometros = kilometros;
    this.barrioInicio = barrioInicio;
    this.barrioLlegada = barrioLlegada;
    this.cantidadPasajeros = cantidadPasajeros;
    this.precio = precio;
    this.duracionMinutos = duracionMinutos;
    this.fechaCreacion = LocalDateTime.now();
  
  // Publicar evento de dominio
  addDomainEvent(new CarreraTaxiCreated(
    this.id, this.cliente, this.taxi, this.taxista, 
    this.kilometros, this.precio, this.duracionMinutos, 
    this.barrioInicio, this.barrioLlegada, this.cantidadPasajeros, 
    this.fechaCreacion
  ));
  }
  
  // Métodos de acceso (getters)
  public CarreraTaxiId getId() { return id; }
  public CarreraTaxiCliente getCliente() { return cliente; }
  public CarreraTaxiTaxi getTaxi() { return taxi; }
  public CarreraTaxiTaxista getTaxista() { return taxista; }
  public CarreraTaxiKilometros getKilometros() { return kilometros; }
  public CarreraTaxiBarrioInicio getBarrioInicio() { return barrioInicio; }
  public CarreraTaxiBarrioLlegada getBarrioLlegada() { return barrioLlegada; }
  public CarreraTaxiCantidadPasajeros getCantidadPasajeros() { return cantidadPasajeros; }
  public CarreraTaxiPrecio getPrecio() { return precio; }
  public CarreraTaxiDuracionMinutos getDuracionMinutos() { return duracionMinutos; }
  public LocalDateTime getFechaCreacion() { return fechaCreacion; }

  public void actualizarPrecio(CarreraTaxiPrecio nuevoPrecio) {
    if (nuevoPrecio == null) {
      throw new IllegalArgumentException("El nuevo precio no puede ser nulo");
    }
    
    // Guardar el precio anterior para el evento
    CarreraTaxiPrecio precioAnterior = this.precio;
    this.precio = nuevoPrecio;
    
    // Publicar evento de dominio con información de la actualización
    addDomainEvent(new CarreraTaxiUpdated(
      this.id, precioAnterior, nuevoPrecio, LocalDateTime.now()
    ));
  }
  
  /**
   * Método de negocio: Marcar la carrera para eliminación
   */
  public void marcarCarreraParaEliminar() {
    addDomainEvent(new CarreraTaxiDeleted(
      this.id, LocalDateTime.now()
    ));
  }
  
  /**
   * Verificar si dos carreras son iguales
   */
  public boolean equals(CarreraTaxi other) {
    return other != null && this.id.equals(other.getId());
  }
  
  /**
   * Representación en cadena de la carrera
   */
  @Override
  public String toString() {
    return String.format("CarreraTaxi{id=%s, cliente=%s, taxi=%s, taxista=%s, " + "kilometros=%s, barrioInicio=%s, barrioLlegada=%s, " + "cantidadPasajeros=%s, precio=%s, duracionMinutos=%s, fechaCreacion=%s}", id, cliente, taxi, taxista, kilometros, barrioInicio, barrioLlegada, cantidadPasajeros, precio, duracionMinutos, fechaCreacion);
  }
  
  // Métodos de eventos de dominio
  public List<Object> getDomainEvents() {
    return new ArrayList<>(domainEvents);
  }
  
  public void clearDomainEvents() {
    domainEvents.clear();
  }
  
  private void addDomainEvent(Object event) {
    domainEvents.add(event);
  }
  
  // Métodos de validación privados
  private void validateBusinessRules(CarreraTaxiId id, CarreraTaxiCliente cliente, CarreraTaxiTaxi taxi, CarreraTaxiTaxista taxista, CarreraTaxiKilometros kilometros, CarreraTaxiBarrioInicio barrioInicio, CarreraTaxiBarrioLlegada barrioLlegada, CarreraTaxiCantidadPasajeros cantidadPasajeros, CarreraTaxiPrecio precio, CarreraTaxiDuracionMinutos duracionMinutos) {
      
    if (id == null) {
      throw new IllegalArgumentException("El ID no puede ser nulo");
    }
    if (cliente == null) {
      throw new IllegalArgumentException("El cliente no puede ser nulo");
    }
    if (taxi == null) {
      throw new IllegalArgumentException("El taxi no puede ser nulo");
    }
    if (taxista == null) {
      throw new IllegalArgumentException("El taxista no puede ser nulo");
    }
    if (kilometros == null) {
      throw new IllegalArgumentException("Los kilómetros no pueden ser nulos");
    }
    if (barrioInicio == null) {
      throw new IllegalArgumentException("El barrio de inicio no puede ser nulo");
    }
    if (barrioLlegada == null) {
      throw new IllegalArgumentException("El barrio de llegada no puede ser nulo");
    }
    if (cantidadPasajeros == null) {
      throw new IllegalArgumentException("La cantidad de pasajeros no puede ser nula");
    }
    if (precio == null) {
      throw new IllegalArgumentException("El precio no puede ser nulo");
    }
    if (duracionMinutos == null) {
      throw new IllegalArgumentException("La duración no puede ser nula");
    }
    
    // Validación de negocio: El barrio de inicio y llegada no pueden ser iguales
    if (barrioInicio.equals(barrioLlegada)) {
      throw new IllegalArgumentException("El barrio de inicio y llegada no pueden ser iguales");
    }
  }
}