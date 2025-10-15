package co.edu.udec.ingsw.activity.application.carreraTaxi.port.in;

import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.command.DeleteCarreraTaxiCommand;

public interface DeleteCarreraTaxiUseCase {
  void execute(DeleteCarreraTaxiCommand command);
}