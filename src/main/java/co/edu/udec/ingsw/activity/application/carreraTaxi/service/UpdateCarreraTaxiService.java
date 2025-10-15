package co.edu.udec.ingsw.activity.application.carreraTaxi.service;

import org.springframework.stereotype.Service;

import co.edu.udec.ingsw.activity.application.carreraTaxi.port.in.UpdateCarreraTaxiUseCase;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.command.UpdateCarreraTaxiCommand;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.response.CarreraTaxiResponse;
import co.edu.udec.ingsw.activity.application.carreraTaxi.mapper.CarreraTaxiMapper;
import co.edu.udec.ingsw.activity.application.carreraTaxi.port.out.CarreraTaxiRepositoryPort;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.entity.CarreraTaxi;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiId;

@Service
public class UpdateCarreraTaxiService implements UpdateCarreraTaxiUseCase {
  
  private final CarreraTaxiRepositoryPort repository;
  private final CarreraTaxiMapper mapper;
  
  public UpdateCarreraTaxiService(CarreraTaxiRepositoryPort repository, CarreraTaxiMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }
  
  @Override
  public CarreraTaxiResponse execute(UpdateCarreraTaxiCommand command) {
    // Buscar carrera existente
    CarreraTaxiId id = new CarreraTaxiId(command.id());
    CarreraTaxi carreraExistente = repository.findById(id);
    
    if (carreraExistente == null) {
      throw new IllegalArgumentException("Carrera de taxi no encontrada con ID: " + command.id());
    }
    
    // Crear entidad actualizada usando el mapper
    CarreraTaxi carreraActualizada = mapper.toEntity(command, carreraExistente);
    
    // Actualizar en el repositorio
    repository.update(carreraActualizada);
    
    // Retornar respuesta usando el mapper
    return mapper.toResponse(carreraActualizada);
  }
}
