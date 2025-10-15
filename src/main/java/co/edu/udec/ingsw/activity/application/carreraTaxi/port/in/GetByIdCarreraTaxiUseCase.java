package co.edu.udec.ingsw.activity.application.carreraTaxi.port.in;

import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.query.GetCarreraTaxiByIdQuery;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.response.CarreraTaxiResponse;

public interface GetByIdCarreraTaxiUseCase {
  CarreraTaxiResponse execute(GetCarreraTaxiByIdQuery query);
}