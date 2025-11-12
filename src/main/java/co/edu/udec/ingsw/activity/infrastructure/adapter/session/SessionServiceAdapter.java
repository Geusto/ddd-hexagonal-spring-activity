package co.edu.udec.ingsw.activity.infrastructure.adapter.session;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import co.edu.udec.ingsw.activity.application.usuario.port.out.SessionServicePort;

@Component
public class SessionServiceAdapter implements SessionServicePort {
  
  private final Map<String, Integer> activeSessions = new HashMap<>();

  @Override
  public void saveSession(String token, Integer usuarioId) {
    activeSessions.put(token, usuarioId);
  }

  @Override
  public boolean isSessionActive(String token) {
    return activeSessions.containsKey(token);
  }

  @Override
  public void invalidateSession(String token) {
    activeSessions.remove(token);
  }

  @Override
  public Integer getUsuarioIdByToken(String token) {
    return activeSessions.get(token);
  }
}