package co.edu.udec.ingsw.activity.application.usuario.port.out;

public interface PasswordEncoderPort {
  
  boolean matches(String rawPassword, String encodedPassword);
  
  String encode(String rawPassword);
}

