package co.edu.udec.ingsw.activity.application.carreraTaxi.port.out;

import co.edu.udec.ingsw.activity.domain.carreraTaxi.entity.CarreraTaxi;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.CarreraTaxiId;
import java.util.List;

public interface CarreraTaxiRepositoryPort {
  CarreraTaxiId nextId();
  void create(CarreraTaxi carrera);
  void update(CarreraTaxi carrera);
  void delete(CarreraTaxiId id);
  CarreraTaxi findById(CarreraTaxiId id);
  List<CarreraTaxi> findAll();
}
