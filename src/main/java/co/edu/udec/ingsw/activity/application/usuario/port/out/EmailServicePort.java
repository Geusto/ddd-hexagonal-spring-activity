package co.edu.udec.ingsw.activity.application.usuario.port.out;

public interface EmailServicePort {
  
  void sendPasswordResetEmail(String destinatario, String token);
}