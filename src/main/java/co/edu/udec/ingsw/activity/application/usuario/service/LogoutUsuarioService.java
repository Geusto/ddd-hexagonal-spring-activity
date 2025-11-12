package co.edu.udec.ingsw.activity.application.usuario.service;

import org.springframework.stereotype.Service;

import co.edu.udec.ingsw.activity.application.usuario.port.in.LogoutUsuarioUseCase;
import co.edu.udec.ingsw.activity.application.usuario.dto.command.LogoutCommand;
import co.edu.udec.ingsw.activity.application.usuario.port.out.SessionServicePort;

@Service
public class LogoutUsuarioService implements LogoutUsuarioUseCase {
  
  private final SessionServicePort sessionService;
  
  public LogoutUsuarioService(SessionServicePort sessionService) {
    this.sessionService = sessionService;
  }
  
  @Override
  public void execute(LogoutCommand command) {
    // Verificar que la sesión existe
    if (!sessionService.isSessionActive(command.token())) {
      throw new IllegalArgumentException("Sesión no encontrada o ya cerrada");
    }
    
    // Invalidar la sesión
    sessionService.invalidateSession(command.token());
  }
}

