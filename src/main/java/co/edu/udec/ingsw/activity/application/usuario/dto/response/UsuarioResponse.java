package co.edu.udec.ingsw.activity.application.usuario.dto.response;

import java.time.LocalDateTime;

public record UsuarioResponse(
  Integer id,
  String nombre,
  String rol,
  LocalDateTime fechaCreacion
) {}
