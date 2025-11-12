package co.edu.udec.ingsw.activity.application.usuario.port.in;

import co.edu.udec.ingsw.activity.application.usuario.dto.command.LogoutCommand;

public interface LogoutUsuarioUseCase {
  void execute(LogoutCommand command);
}