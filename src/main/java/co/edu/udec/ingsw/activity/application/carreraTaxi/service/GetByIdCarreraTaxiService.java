package co.edu.udec.ingsw.activity.application.carreraTaxi.service;

import org.springframework.stereotype.Service;

import co.edu.udec.ingsw.activity.application.carreraTaxi.port.in.GetByIdCarreraTaxiUseCase;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.query.GetCarreraTaxiByIdQuery;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.response.CarreraTaxiResponse;
import co.edu.udec.ingsw.activity.application.carreraTaxi.mapper.CarreraTaxiMapper;
import co.edu.udec.ingsw.activity.application.carreraTaxi.port.out.CarreraTaxiRepositoryPort;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.entity.CarreraTaxi;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiId;

@Service
public class GetByIdCarreraTaxiService implements GetByIdCarreraTaxiUseCase {
  
  private final CarreraTaxiRepositoryPort repository;
  private final CarreraTaxiMapper mapper;
  
  public GetByIdCarreraTaxiService(CarreraTaxiRepositoryPort repository, CarreraTaxiMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }
  
  @Override
  public CarreraTaxiResponse execute(GetCarreraTaxiByIdQuery query) {
    // Buscar carrera por ID
    CarreraTaxiId id = new CarreraTaxiId(query.id());
    CarreraTaxi carrera = repository.findById(id);
    
    if (carrera == null) {
      throw new IllegalArgumentException("Carrera de taxi no encontrada con ID: " + query.id());
    }
    
    // Retornar respuesta usando el mapper
    return mapper.toResponse(carrera);
  }
}
