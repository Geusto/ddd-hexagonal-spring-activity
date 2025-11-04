package co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.response;

public record UsuarioHttpResponse(
  boolean ok,
  Object data
) {
  public static UsuarioHttpResponse created(Object data) {
    return new UsuarioHttpResponse(true, data);
  }

  public static UsuarioHttpResponse success(Object data) {
    return new UsuarioHttpResponse(true, data);
  }

  public static UsuarioHttpResponse deleted(String message) {
    return new UsuarioHttpResponse(true, message);
  }
}
