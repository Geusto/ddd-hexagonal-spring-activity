package co.edu.udec.ingsw.activity.application.usuario.port.in;

import co.edu.udec.ingsw.activity.application.usuario.dto.command.DeleteUsuarioCommand;

public interface DeleteUsuarioUseCase {
  void execute(DeleteUsuarioCommand command);
}
