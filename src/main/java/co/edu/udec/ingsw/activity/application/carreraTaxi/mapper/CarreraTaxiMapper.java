package co.edu.udec.ingsw.activity.application.carreraTaxi.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import co.edu.udec.ingsw.activity.domain.carreraTaxi.entity.CarreraTaxi;
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

import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.command.CreateCarreraTaxiCommand;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.command.UpdateCarreraTaxiCommand;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.response.CarreraTaxiResponse;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.response.CarreraTaxiListResponse;

@Component
public class CarreraTaxiMapper {
  
  /**
   * Mapear una carrera de taxi a un DTO de respuesta
   * @param carrera Entidad de carrera de taxi
   * @return DTO de respuesta de carrera de taxi
   */
  public CarreraTaxiResponse toResponse(CarreraTaxi carrera) {
    return new CarreraTaxiResponse(
      carrera.getId().value(),
      carrera.getCliente().value(),
      carrera.getTaxi().value(),
      carrera.getTaxista().value(),
      carrera.getKilometros().value(),
      carrera.getBarrioInicio().value(),
      carrera.getBarrioLlegada().value(),
      carrera.getCantidadPasajeros().value(),
      carrera.getPrecio().value(),
      carrera.getDuracionMinutos().value(),
      carrera.getFechaCreacion()
    );
  }
  
  /**
   * Mapear un listado de carreras de taxi a un DTO de lista
   * @param carreras Listado de carreras de taxi
   * @return DTO de lista de carreras de taxi
   */
  public CarreraTaxiListResponse toListResponse(List<CarreraTaxi> carreras) {
    List<CarreraTaxiResponse> responses = carreras.stream()
      .map(this::toResponse)
      .collect(Collectors.toList());
    
    return new CarreraTaxiListResponse(responses);
  }
  
  /**
   * Mapear un comando de creación a una entidad de carrera de taxi
   * @param command Comando de creación
   * @return Entidad de carrera de taxi
   */
  public CarreraTaxi toEntity(CreateCarreraTaxiCommand command, Integer generatedId) {
    return new CarreraTaxi(
      new CarreraTaxiId(generatedId),
      new CarreraTaxiCliente(command.cliente()),
      new CarreraTaxiTaxi(command.taxi()),
      new CarreraTaxiTaxista(command.taxista()),
      new CarreraTaxiKilometros(command.kilometros()),
      new CarreraTaxiBarrioInicio(command.barrioInicio()),
      new CarreraTaxiBarrioLlegada(command.barrioLlegada()),
      new CarreraTaxiCantidadPasajeros(command.cantidadPasajeros()),
      new CarreraTaxiPrecio(command.precio()),
      new CarreraTaxiDuracionMinutos(command.duracionMinutos())
    );
  }
  
  /**
   * Mapear un comando de actualización a una entidad de carrera de taxi
   * @param command Comando de actualización
   * @param carreraExistente Carrera existente a actualizar
   * @return Entidad de carrera de taxi actualizada
   */
  public CarreraTaxi toEntity(UpdateCarreraTaxiCommand command, CarreraTaxi carreraExistente) {
    return new CarreraTaxi(
      new CarreraTaxiId(command.id()),
      new CarreraTaxiCliente(command.cliente()),
      new CarreraTaxiTaxi(command.taxi()),
      new CarreraTaxiTaxista(command.taxista()),
      new CarreraTaxiKilometros(command.kilometros()),
      new CarreraTaxiBarrioInicio(command.barrioInicio()),
      new CarreraTaxiBarrioLlegada(command.barrioLlegada()),
      new CarreraTaxiCantidadPasajeros(command.cantidadPasajeros()),
      new CarreraTaxiPrecio(command.precio()),
      new CarreraTaxiDuracionMinutos(command.duracionMinutos())
    );
  }
}
