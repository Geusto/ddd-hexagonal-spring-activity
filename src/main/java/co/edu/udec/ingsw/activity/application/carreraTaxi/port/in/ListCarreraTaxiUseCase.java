package co.edu.udec.ingsw.activity.application.carreraTaxi.port.in;

import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.query.ListCarreraTaxiQuery;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.response.CarreraTaxiListResponse;

public interface ListCarreraTaxiUseCase {
  CarreraTaxiListResponse execute(ListCarreraTaxiQuery query);
}