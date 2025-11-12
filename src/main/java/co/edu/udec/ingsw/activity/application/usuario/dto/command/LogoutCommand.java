package co.edu.udec.ingsw.activity.application.usuario.dto.command;

public record LogoutCommand(
  String token
) {
  public LogoutCommand {
    if (token == null || token.trim().isEmpty()) {
      throw new IllegalArgumentException("El token no puede ser nulo o vacío");
    }
  }
}