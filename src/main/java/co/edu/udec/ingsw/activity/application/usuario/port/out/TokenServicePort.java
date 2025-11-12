package co.edu.udec.ingsw.activity.application.usuario.port.out;

public interface TokenServicePort {
  
  String generateAuthToken(Integer usuarioId);
  
  String generateResetToken(Integer usuarioId);
  
  Integer validateAuthToken(String token);
  
  Integer validateResetToken(String token);
  
  void invalidateResetToken(String token);
}