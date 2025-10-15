package co.edu.udec.ingsw.activity.application.carreraTaxi.service;

import org.springframework.stereotype.Service;

import co.edu.udec.ingsw.activity.application.carreraTaxi.port.in.DeleteCarreraTaxiUseCase;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.command.DeleteCarreraTaxiCommand;
import co.edu.udec.ingsw.activity.application.carreraTaxi.port.out.CarreraTaxiRepositoryPort;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.entity.CarreraTaxi;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiId;

@Service
public class DeleteCarreraTaxiService implements DeleteCarreraTaxiUseCase {
  
  private final CarreraTaxiRepositoryPort repository;
  
  public DeleteCarreraTaxiService(CarreraTaxiRepositoryPort repository) {
    this.repository = repository;
  }
  
  @Override
  public void execute(DeleteCarreraTaxiCommand command) {
    // Buscar carrera existente
    CarreraTaxiId id = new CarreraTaxiId(command.id());
    CarreraTaxi carrera = repository.findById(id);
    
    if (carrera == null) {
      throw new IllegalArgumentException("Carrera de taxi no encontrada con ID: " + command.id());
    }
    
    // Marcar para eliminación (publica evento de dominio)
    carrera.marcarCarreraParaEliminar();
    
    // Eliminar del repositorio
    repository.delete(id);
  }
}
