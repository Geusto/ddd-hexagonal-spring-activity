package co.edu.udec.ingsw.activity.application.usuario.dto.command;

public record CreateUsuarioCommand(
  String nombre,
  String rol,
  String clave
) {}
