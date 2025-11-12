package co.edu.udec.ingsw.activity.infrastructure.adapter.security;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import co.edu.udec.ingsw.activity.application.usuario.port.out.TokenServicePort;

@Component
public class TokenServiceAdapter implements TokenServicePort {
  
  private final Map<String, Integer> authTokens = new HashMap<>();
  
  private final Map<String, TokenInfo> resetTokens = new HashMap<>();
  
  private static class TokenInfo {
    final Integer usuarioId;
    final long expirationTime;
    
    TokenInfo(Integer usuarioId, long expirationTime) {
      this.usuarioId = usuarioId;
      this.expirationTime = expirationTime;
    }
    
    boolean isExpired() {
      return System.currentTimeMillis() > expirationTime;
    }
  }

  @Override
  public String generateAuthToken(Integer usuarioId) {
    String token = UUID.randomUUID().toString();
    authTokens.put(token, usuarioId);
    return token;
  }

  @Override
  public String generateResetToken(Integer usuarioId) {
    String token = UUID.randomUUID().toString();
    long expirationTime = System.currentTimeMillis() + (60 * 60 * 1000); // 1 hora
    resetTokens.put(token, new TokenInfo(usuarioId, expirationTime));
    return token;
  }

  @Override
  public Integer validateAuthToken(String token) {
    return authTokens.get(token);
  }

  @Override
  public Integer validateResetToken(String token) {
    TokenInfo info = resetTokens.get(token);
    if (info == null || info.isExpired()) {
      resetTokens.remove(token);
      return null;
    }
    return info.usuarioId;
  }
  
  @Override
  public void invalidateResetToken(String token) {
    resetTokens.remove(token);
  }
}

