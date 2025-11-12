package co.edu.udec.ingsw.activity.application.usuario.port.in;

import co.edu.udec.ingsw.activity.application.usuario.dto.command.LoginCommand;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.LoginResponse;

public interface LoginUsuarioUseCase {
  LoginResponse execute(LoginCommand command);
}