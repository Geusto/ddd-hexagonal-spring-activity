package co.edu.udec.ingsw.activity.application.carreraTaxi.service;

import org.springframework.stereotype.Service;

import co.edu.udec.ingsw.activity.application.carreraTaxi.port.in.CreateCarreraTaxiUseCase;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.command.CreateCarreraTaxiCommand;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.response.CarreraTaxiResponse;
import co.edu.udec.ingsw.activity.application.carreraTaxi.mapper.CarreraTaxiMapper;
import co.edu.udec.ingsw.activity.application.carreraTaxi.port.out.CarreraTaxiRepositoryPort;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.entity.CarreraTaxi;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiId;

/**
 * Servicio para crear una carrera de taxi
 * 
 * Nota: este servicio es el que se encarga de crear una carrera de taxi pero implementando el caso de uso, no el comando.
 * En la definición del servicio, es el encargado de orquestar el caso de uso, y recibe el command/query de entrada y el response de salida.
 */
@Service
public class CreateCarreraTaxiService implements CreateCarreraTaxiUseCase {
  
  private final CarreraTaxiRepositoryPort repository;
  private final CarreraTaxiMapper mapper;
  
  public CreateCarreraTaxiService(CarreraTaxiRepositoryPort repository, CarreraTaxiMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }
  
  @Override
  public CarreraTaxiResponse execute(CreateCarreraTaxiCommand command) {
    // Generar ID único
    CarreraTaxiId id = repository.nextId();
    
    // Crear entidad usando el mapper
    CarreraTaxi carrera = mapper.toEntity(command, id.value());
    
    // Persistir en el repositorio
    repository.create(carrera);
    
    // Retornar respuesta usando el mapper
    return mapper.toResponse(carrera);
  }
}
