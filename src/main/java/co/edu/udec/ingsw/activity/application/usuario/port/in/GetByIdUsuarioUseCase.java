package co.edu.udec.ingsw.activity.application.usuario.port.in;

import co.edu.udec.ingsw.activity.application.usuario.dto.query.GetUsuarioByIdQuery;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.UsuarioResponse;

public interface GetByIdUsuarioUseCase {
  UsuarioResponse execute(GetUsuarioByIdQuery query);
}
