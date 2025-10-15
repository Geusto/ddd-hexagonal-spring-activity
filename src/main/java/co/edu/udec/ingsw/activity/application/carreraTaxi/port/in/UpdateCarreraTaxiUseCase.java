package co.edu.udec.ingsw.activity.application.carreraTaxi.port.in;

import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.command.UpdateCarreraTaxiCommand;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.response.CarreraTaxiResponse;

public interface UpdateCarreraTaxiUseCase {
  CarreraTaxiResponse execute(UpdateCarreraTaxiCommand command);
}