package co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.response;

public record CarreraTaxiHttpResponse(
  boolean ok,
  Object data
) {
  public static CarreraTaxiHttpResponse created(Object data) {
    return new CarreraTaxiHttpResponse(true, data);
  }

  public static CarreraTaxiHttpResponse success(Object data) {
    return new CarreraTaxiHttpResponse(true, data);
  }
}
