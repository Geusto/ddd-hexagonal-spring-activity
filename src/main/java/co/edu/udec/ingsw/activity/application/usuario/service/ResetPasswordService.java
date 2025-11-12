package co.edu.udec.ingsw.activity.application.usuario.service;

import org.springframework.stereotype.Service;

import co.edu.udec.ingsw.activity.application.usuario.port.in.ResetPasswordUseCase;
import co.edu.udec.ingsw.activity.application.usuario.dto.command.ResetPasswordCommand;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.ResetPasswordResponse;
import co.edu.udec.ingsw.activity.application.usuario.port.out.UsuarioRepositoryPort;
import co.edu.udec.ingsw.activity.application.usuario.port.out.PasswordEncoderPort;
import co.edu.udec.ingsw.activity.application.usuario.port.out.TokenServicePort;
import co.edu.udec.ingsw.activity.domain.usuario.entity.Usuario;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioId;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioClave;

@Service
public class ResetPasswordService implements ResetPasswordUseCase {
  
  private final UsuarioRepositoryPort repository;
  private final PasswordEncoderPort passwordEncoder;
  private final TokenServicePort tokenService;
  
  public ResetPasswordService(
    UsuarioRepositoryPort repository,
    PasswordEncoderPort passwordEncoder,
    TokenServicePort tokenService
  ) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
    this.tokenService = tokenService;
  }
  
  @Override
  public ResetPasswordResponse execute(ResetPasswordCommand command) {
    // 1. Validar token de recuperación
    Integer usuarioId = tokenService.validateResetToken(command.token());
    if (usuarioId == null) {
      throw new IllegalArgumentException("Token inválido o expirado");
    }
    
    // 2. Buscar usuario
    Usuario usuario = repository.findById(new UsuarioId(usuarioId));
    if (usuario == null) {
      throw new IllegalArgumentException("Usuario no encontrado");
    }
    
    // 3. Codificar nueva contraseña
    String encodedPassword = passwordEncoder.encode(command.nuevaClave());
    
    // 4. Crear nueva instancia de usuario con la contraseña actualizada
    // Como la entidad es inmutable, debemos crear una nueva instancia
    UsuarioClave nuevaClave = new UsuarioClave(encodedPassword);
    Usuario usuarioActualizado = new Usuario(
      usuario.getId(),
      usuario.getNombre(),
      usuario.getRol(),
      nuevaClave
    );
    
    // 5. Persistir cambios (el repositorio reemplazará la instancia anterior)
    repository.update(usuarioActualizado);
    
    // 6. Invalidar el token usado
    tokenService.invalidateResetToken(command.token());
    
    return ResetPasswordResponse.success();
  }
}

