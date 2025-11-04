package co.edu.udec.ingsw.activity.application.usuario.port.in;

import co.edu.udec.ingsw.activity.application.usuario.dto.command.UpdateUsuarioCommand;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.UsuarioResponse;

public interface UpdateUsuarioUseCase {
  UsuarioResponse execute(UpdateUsuarioCommand command);
}
