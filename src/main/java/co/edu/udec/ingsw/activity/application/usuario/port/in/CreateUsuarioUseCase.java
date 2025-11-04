package co.edu.udec.ingsw.activity.application.usuario.port.in;

import co.edu.udec.ingsw.activity.application.usuario.dto.command.CreateUsuarioCommand;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.UsuarioResponse;

public interface CreateUsuarioUseCase {
  UsuarioResponse execute(CreateUsuarioCommand command);
}
