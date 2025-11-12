package co.edu.udec.ingsw.activity.application.usuario.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import co.edu.udec.ingsw.activity.application.usuario.port.in.LoginUsuarioUseCase;
import co.edu.udec.ingsw.activity.application.usuario.dto.command.LoginCommand;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.LoginResponse;
import co.edu.udec.ingsw.activity.application.usuario.mapper.UsuarioMapper;
import co.edu.udec.ingsw.activity.application.usuario.port.out.UsuarioRepositoryPort;
import co.edu.udec.ingsw.activity.application.usuario.port.out.PasswordEncoderPort;
import co.edu.udec.ingsw.activity.application.usuario.port.out.TokenServicePort;
import co.edu.udec.ingsw.activity.application.usuario.port.out.SessionServicePort;
import co.edu.udec.ingsw.activity.domain.usuario.entity.Usuario;

@Service
public class LoginUsuarioService implements LoginUsuarioUseCase {
  
  private final UsuarioRepositoryPort repository;
  private final PasswordEncoderPort passwordEncoder;
  private final TokenServicePort tokenService;
  private final SessionServicePort sessionService;
  private final UsuarioMapper mapper;
  
  public LoginUsuarioService(
    UsuarioRepositoryPort repository,
    PasswordEncoderPort passwordEncoder,
    TokenServicePort tokenService,
    SessionServicePort sessionService,
    UsuarioMapper mapper
  ) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
    this.tokenService = tokenService;
    this.sessionService = sessionService;
    this.mapper = mapper;
  }
  
  @Override
  public LoginResponse execute(LoginCommand command) {
    // 1. Buscar usuario por nombre
    Usuario usuario = repository.findByNombre(command.nombre());
    if (usuario == null) {
      throw new IllegalArgumentException("Usuario o contraseña incorrectos");
    }
    
    // 2. Verificar contraseña
    String storedPassword = usuario.getClave().value();
    if (!passwordEncoder.matches(command.clave(), storedPassword)) {
      throw new IllegalArgumentException("Usuario o contraseña incorrectos");
    }
    
    // 3. Generar token de autenticación
    String token = tokenService.generateAuthToken(usuario.getId().value());
    
    // 4. Guardar sesión
    sessionService.saveSession(token, usuario.getId().value());
    
    // 5. Retornar respuesta
    return new LoginResponse(
      token,
      mapper.toResponse(usuario),
      LocalDateTime.now()
    );
  }
}

