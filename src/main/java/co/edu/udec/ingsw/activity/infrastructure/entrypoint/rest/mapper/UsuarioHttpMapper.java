package co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.mapper;

import org.springframework.stereotype.Component;

import co.edu.udec.ingsw.activity.application.usuario.dto.command.CreateUsuarioCommand;
import co.edu.udec.ingsw.activity.application.usuario.dto.command.UpdateUsuarioCommand;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.UsuarioResponse;
import co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.request.UsuarioHttpRequest;

@Component
public class UsuarioHttpMapper {

  public CreateUsuarioCommand toCreateCommand(UsuarioHttpRequest req) {
    return new CreateUsuarioCommand(
      req.nombre(),
      req.rol(),
      req.clave()
    );
  }

  public UpdateUsuarioCommand toUpdateCommand(Integer id, UsuarioHttpRequest req) {
    return new UpdateUsuarioCommand(
      id,
      req.nombre(),
      req.rol(),
      req.clave()
    );
  }

  public UsuarioResponse toHttp(UsuarioResponse response) {
    return response;
  }
}
