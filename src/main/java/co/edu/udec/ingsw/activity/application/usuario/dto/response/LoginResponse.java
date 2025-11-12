package co.edu.udec.ingsw.activity.application.usuario.dto.response;

import java.time.LocalDateTime;

public record LoginResponse(
  String token,
  UsuarioResponse usuario,
  LocalDateTime fechaLogin
) {}

