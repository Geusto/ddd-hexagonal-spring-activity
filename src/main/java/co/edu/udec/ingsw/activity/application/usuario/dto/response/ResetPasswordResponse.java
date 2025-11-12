package co.edu.udec.ingsw.activity.application.usuario.dto.response;

public record ResetPasswordResponse(
  String mensaje
) {
  public static ResetPasswordResponse success() {
    return new ResetPasswordResponse("Contraseña restablecida exitosamente");
  }
}

