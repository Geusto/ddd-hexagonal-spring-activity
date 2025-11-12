package co.edu.udec.ingsw.activity.application.usuario.dto.command;

public record ResetPasswordCommand(
  String token,
  String nuevaClave
) {
  public ResetPasswordCommand {
    if (token == null || token.trim().isEmpty()) {
      throw new IllegalArgumentException("El token no puede ser nulo o vacío");
    }
    if (nuevaClave == null || nuevaClave.trim().isEmpty()) {
      throw new IllegalArgumentException("La nueva clave no puede ser nula o vacía");
    }
    if (nuevaClave.trim().length() < 6) {
      throw new IllegalArgumentException("La nueva clave debe tener al menos 6 caracteres");
    }
  }
}