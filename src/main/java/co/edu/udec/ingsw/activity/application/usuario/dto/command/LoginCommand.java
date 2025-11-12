package co.edu.udec.ingsw.activity.application.usuario.dto.command;

public record LoginCommand(
  String nombre,
  String clave
) {
  public LoginCommand {
    if (nombre == null || nombre.trim().isEmpty()) {
      throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
    }
    if (clave == null || clave.trim().isEmpty()) {
      throw new IllegalArgumentException("La clave no puede ser nula o vacía");
    }
  }
}