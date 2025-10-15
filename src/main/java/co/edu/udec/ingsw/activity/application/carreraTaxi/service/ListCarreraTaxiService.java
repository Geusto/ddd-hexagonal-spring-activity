package co.edu.udec.ingsw.activity.application.carreraTaxi.service;

import org.springframework.stereotype.Service;

import co.edu.udec.ingsw.activity.application.carreraTaxi.port.in.ListCarreraTaxiUseCase;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.query.ListCarreraTaxiQuery;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.response.CarreraTaxiListResponse;
import co.edu.udec.ingsw.activity.application.carreraTaxi.mapper.CarreraTaxiMapper;
import co.edu.udec.ingsw.activity.application.carreraTaxi.port.out.CarreraTaxiRepositoryPort;

@Service
public class ListCarreraTaxiService implements ListCarreraTaxiUseCase {
  
  private final CarreraTaxiRepositoryPort repository;
  private final CarreraTaxiMapper mapper;
  
  public ListCarreraTaxiService(CarreraTaxiRepositoryPort repository, CarreraTaxiMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }
  
  @Override
  public CarreraTaxiListResponse execute(ListCarreraTaxiQuery query) {
    // Obtener todas las carreras del repositorio
    var carreras = repository.findAll();
    
    // Retornar respuesta usando el mapper
    return mapper.toListResponse(carreras);
  }
}
