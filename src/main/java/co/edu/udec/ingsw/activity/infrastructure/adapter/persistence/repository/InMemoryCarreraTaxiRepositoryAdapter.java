package co.edu.udec.ingsw.activity.infrastructure.adapter.persistence.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.udec.ingsw.activity.application.carreraTaxi.port.out.CarreraTaxiRepositoryPort;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.entity.CarreraTaxi;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiId;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCarreraTaxiRepositoryAdapter implements CarreraTaxiRepositoryPort {
  private final Map<CarreraTaxiId, CarreraTaxi> storage = new HashMap<>();
  private int sequence = 1;

  @Override
  public CarreraTaxiId nextId() {
    return new CarreraTaxiId(sequence++);
  }

  @Override
  public void create(CarreraTaxi carrera) {
    storage.put(carrera.getId(), carrera);
  }

  @Override
  public void update(CarreraTaxi carrera) {
    storage.put(carrera.getId(), carrera);
  }

  @Override
  public void delete(CarreraTaxiId id) {
    storage.remove(id);
  }

  @Override
  public CarreraTaxi findById(CarreraTaxiId id) {
    return storage.get(id);
  }

  @Override
  public List<CarreraTaxi> findAll() {
    return new ArrayList<>(storage.values());
  }
}
