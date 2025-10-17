package co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.mapper;

import org.springframework.stereotype.Component;

import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.command.CreateCarreraTaxiCommand;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.command.UpdateCarreraTaxiCommand;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.response.CarreraTaxiResponse;
import co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.request.CarreraTaxiHttpRequest;

@Component
public class CarreraTaxiHttpMapper {

  public CreateCarreraTaxiCommand toCreateCommand(CarreraTaxiHttpRequest req) {
    return new CreateCarreraTaxiCommand(
      req.cliente(),
      req.taxi(),
      req.taxista(),
      req.kilometros(),
      req.barrioInicio(),
      req.barrioLlegada(),
      req.cantidadPasajeros(),
      req.precio(),
      req.duracionMinutos()
    );
  }

  public UpdateCarreraTaxiCommand toUpdateCommand(Integer id, CarreraTaxiHttpRequest req) {
    return new UpdateCarreraTaxiCommand(
      id,
      req.cliente(),
      req.taxi(),
      req.taxista(),
      req.kilometros(),
      req.barrioInicio(),
      req.barrioLlegada(),
      req.cantidadPasajeros(),
      req.precio(),
      req.duracionMinutos()
    );
  }

  public CarreraTaxiResponse toHttp(CarreraTaxiResponse response) {
    return response;
  }
}