package co.edu.udec.ingsw.activity.application.usuario.dto.response;

import java.util.List;

public record UsuarioListResponse(
  List<UsuarioResponse> usuarios
) {}
