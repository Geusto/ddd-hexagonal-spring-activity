package co.edu.udec.ingsw.activity.application.usuario.dto.command;

public record ForgotPasswordCommand(
  String nombre
) {
  public ForgotPasswordCommand {
    if (nombre == null || nombre.trim().isEmpty()) {
      throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
    }
  }
}