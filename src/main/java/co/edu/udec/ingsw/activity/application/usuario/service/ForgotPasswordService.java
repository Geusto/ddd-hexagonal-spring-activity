package co.edu.udec.ingsw.activity.application.usuario.service;

import org.springframework.stereotype.Service;

import co.edu.udec.ingsw.activity.application.usuario.port.in.ForgotPasswordUseCase;
import co.edu.udec.ingsw.activity.application.usuario.dto.command.ForgotPasswordCommand;
import co.edu.udec.ingsw.activity.application.usuario.port.out.UsuarioRepositoryPort;
import co.edu.udec.ingsw.activity.application.usuario.port.out.TokenServicePort;
import co.edu.udec.ingsw.activity.application.usuario.port.out.EmailServicePort;
import co.edu.udec.ingsw.activity.domain.usuario.entity.Usuario;

@Service
public class ForgotPasswordService implements ForgotPasswordUseCase {
  
  private final UsuarioRepositoryPort repository;
  private final TokenServicePort tokenService;
  private final EmailServicePort emailService;
  
  public ForgotPasswordService(
    UsuarioRepositoryPort repository,
    TokenServicePort tokenService,
    EmailServicePort emailService
  ) {
    this.repository = repository;
    this.tokenService = tokenService;
    this.emailService = emailService;
  }
  
  @Override
  public void execute(ForgotPasswordCommand command) {
    // 1. Buscar usuario por nombre
    Usuario usuario = repository.findByNombre(command.nombre());
    
    // Por seguridad, no revelamos si el usuario existe o no
    if (usuario == null) {
      // Simulamos el envío aunque no exista (por seguridad)
      return;
    }
    
    // 2. Generar token de recuperación
    String resetToken = tokenService.generateResetToken(usuario.getId().value());
    
    // 3. Enviar email con el token (simulado)
    emailService.sendPasswordResetEmail(usuario.getNombre().value(), resetToken);
  }
}

