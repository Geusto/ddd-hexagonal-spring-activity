package co.edu.udec.ingsw.activity.application.carreraTaxi.port.in;

import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.command.CreateCarreraTaxiCommand;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.response.CarreraTaxiResponse;

public interface CreateCarreraTaxiUseCase {
  CarreraTaxiResponse execute(CreateCarreraTaxiCommand command);
}