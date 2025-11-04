package co.edu.udec.ingsw.activity.application.usuario.service;

import org.springframework.stereotype.Service;

import co.edu.udec.ingsw.activity.application.usuario.port.in.UpdateUsuarioUseCase;
import co.edu.udec.ingsw.activity.application.usuario.dto.command.UpdateUsuarioCommand;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.UsuarioResponse;
import co.edu.udec.ingsw.activity.application.usuario.mapper.UsuarioMapper;
import co.edu.udec.ingsw.activity.application.usuario.port.out.UsuarioRepositoryPort;
import co.edu.udec.ingsw.activity.domain.usuario.entity.Usuario;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioId;

/**
 * Servicio para actualizar un usuario
 * 
 * Este servicio se encarga de actualizar un usuario implementando el caso de uso.
 * Es el encargado de orquestar el caso de uso, y recibe el command de entrada y el response de salida.
 */
@Service
public class UpdateUsuarioService implements UpdateUsuarioUseCase {
  
  private final UsuarioRepositoryPort repository;
  private final UsuarioMapper mapper;
  
  public UpdateUsuarioService(UsuarioRepositoryPort repository, UsuarioMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }
  
  @Override
  public UsuarioResponse execute(UpdateUsuarioCommand command) {
    // Buscar usuario existente
    Usuario usuarioExistente = repository.findById(new UsuarioId(command.id()));
    if (usuarioExistente == null) {
      throw new IllegalArgumentException("Usuario no encontrado con ID: " + command.id());
    }
    
    // Crear entidad actualizada usando el mapper
    Usuario usuarioActualizado = mapper.toEntity(command, usuarioExistente);
    
    // Actualizar en el repositorio
    repository.update(usuarioActualizado);
    
    // Retornar respuesta usando el mapper
    return mapper.toResponse(usuarioActualizado);
  }
}
