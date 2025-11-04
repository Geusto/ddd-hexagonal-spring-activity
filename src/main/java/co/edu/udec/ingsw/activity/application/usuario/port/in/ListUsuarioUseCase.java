package co.edu.udec.ingsw.activity.application.usuario.port.in;

import co.edu.udec.ingsw.activity.application.usuario.dto.query.ListUsuarioQuery;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.UsuarioListResponse;

public interface ListUsuarioUseCase {
  UsuarioListResponse execute(ListUsuarioQuery query);
}
