package co.edu.udec.ingsw.activity.application.usuario.dto.command;

public record UpdateUsuarioCommand(
  Integer id,
  String nombre,
  String rol,
  String clave
) {}
