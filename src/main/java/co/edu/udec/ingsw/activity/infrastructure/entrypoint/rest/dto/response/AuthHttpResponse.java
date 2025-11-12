package co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.response;

import co.edu.udec.ingsw.activity.application.usuario.dto.response.LoginResponse;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.ResetPasswordResponse;

public record AuthHttpResponse(
  boolean success,
  String message,
  Object data
) {
  public static AuthHttpResponse loginSuccess(LoginResponse loginResponse) {
    return new AuthHttpResponse(
      true,
      "Login exitoso",
      loginResponse
    );
  }
  
  public static AuthHttpResponse resetPasswordSuccess(ResetPasswordResponse response) {
    return new AuthHttpResponse(
      true,
      response.mensaje(),
      null
    );
  }
  
  public static AuthHttpResponse logoutSuccess() {
    return new AuthHttpResponse(
      true,
      "Sesión cerrada exitosamente",
      null
    );
  }
  
  public static AuthHttpResponse forgotPasswordSuccess() {
    return new AuthHttpResponse(
      true,
      "Si el usuario existe, se ha enviado un email con las instrucciones",
      null
    );
  }
  
  public static AuthHttpResponse error(String message) {
    return new AuthHttpResponse(
      false,
      message,
      null
    );
  }
}