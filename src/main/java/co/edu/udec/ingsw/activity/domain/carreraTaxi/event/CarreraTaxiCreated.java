package co.edu.udec.ingsw.activity.domain.carreraTaxi.event;

import java.time.LocalDateTime;

import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiId;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiCliente;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiTaxi;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiTaxista;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiKilometros;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiPrecio;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiDuracionMinutos;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiBarrioInicio;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiBarrioLlegada;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiCantidadPasajeros;

public record CarreraTaxiCreated(
  CarreraTaxiId id,
  CarreraTaxiCliente cliente,
  CarreraTaxiTaxi taxi,
  CarreraTaxiTaxista taxista,
  CarreraTaxiKilometros kilometros,
  CarreraTaxiPrecio precio,
  CarreraTaxiDuracionMinutos duracionMinutos,
  CarreraTaxiBarrioInicio barrioInicio,
  CarreraTaxiBarrioLlegada barrioLlegada,
  CarreraTaxiCantidadPasajeros cantidadPasajeros,
  LocalDateTime fechaCreacionCarrera
) {
    
  /**
   * validaciones de parámetros nulos
   */
  public CarreraTaxiCreated {
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
    if (precio == null) {
      throw new IllegalArgumentException("El precio no puede ser nulo");
    }
    if (duracionMinutos == null) {
      throw new IllegalArgumentException("La duración no puede ser nula");
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
    if (fechaCreacionCarrera == null) {
      throw new IllegalArgumentException("La fecha de creación no puede ser nula");
    }
  }
  
  public CarreraTaxiId getCarreraId() {
    return id;
  }
  
  public CarreraTaxiCliente getCliente() {
    return cliente;
  }
  
  public CarreraTaxiTaxi getTaxi() {
    return taxi;
  }
  
  public CarreraTaxiTaxista getTaxista() {
    return taxista;
  }
  
  public CarreraTaxiKilometros getKilometros() {
    return kilometros;
  }
  
  public CarreraTaxiPrecio getPrecio() {
    return precio;
  }
  
  public CarreraTaxiDuracionMinutos getDuracionMinutos() {
    return duracionMinutos;
  }
  
  public CarreraTaxiBarrioInicio getBarrioInicio() {
    return barrioInicio;
  }
  
  public CarreraTaxiBarrioLlegada getBarrioLlegada() {
    return barrioLlegada;
  }
  
  public CarreraTaxiCantidadPasajeros getCantidadPasajeros() {
    return cantidadPasajeros;
  }
  
  public LocalDateTime getFechaCreacionCarrera() {
    return fechaCreacionCarrera;
  }
  
  @Override
  public String toString() {
    return String.format("CarreraTaxiCreated{id=%s, cliente=%s, taxi=%s, taxista=%s, " + "kilometros=%s, precio=%s, duracionMinutos=%s, barrioInicio=%s, " + "barrioLlegada=%s, cantidadPasajeros=%s, fechaCreacion=%s}", id, cliente, taxi, taxista, kilometros, precio, duracionMinutos, barrioInicio, barrioLlegada, cantidadPasajeros, fechaCreacionCarrera);
  }
}