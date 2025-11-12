package co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.mapper;

import org.springframework.stereotype.Component;

import co.edu.udec.ingsw.activity.application.usuario.dto.command.LoginCommand;
import co.edu.udec.ingsw.activity.application.usuario.dto.command.ForgotPasswordCommand;
import co.edu.udec.ingsw.activity.application.usuario.dto.command.ResetPasswordCommand;
import co.edu.udec.ingsw.activity.application.usuario.dto.command.LogoutCommand;
import co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.request.LoginHttpRequest;
import co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.request.ForgotPasswordHttpRequest;
import co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.request.ResetPasswordHttpRequest;
import co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.request.LogoutHttpRequest;

@Component
public class AuthHttpMapper {
  
  public LoginCommand toLoginCommand(LoginHttpRequest request) {
    return new LoginCommand(
      request.nombre(),
      request.clave()
    );
  }
  
  public ForgotPasswordCommand toForgotPasswordCommand(ForgotPasswordHttpRequest request) {
    return new ForgotPasswordCommand(
      request.nombre()
    );
  }
  
  public ResetPasswordCommand toResetPasswordCommand(ResetPasswordHttpRequest request) {
    return new ResetPasswordCommand(
      request.token(),
      request.nuevaClave()
    );
  }
  
  public LogoutCommand toLogoutCommand(LogoutHttpRequest request) {
    return new LogoutCommand(
      request.token()
    );
  }
}