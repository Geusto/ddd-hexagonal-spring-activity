package co.edu.udec.ingsw.activity.application.usuario.service;

import org.springframework.stereotype.Service;

import co.edu.udec.ingsw.activity.application.usuario.port.in.CreateUsuarioUseCase;
import co.edu.udec.ingsw.activity.application.usuario.dto.command.CreateUsuarioCommand;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.UsuarioResponse;
import co.edu.udec.ingsw.activity.application.usuario.mapper.UsuarioMapper;
import co.edu.udec.ingsw.activity.application.usuario.port.out.UsuarioRepositoryPort;
import co.edu.udec.ingsw.activity.domain.usuario.entity.Usuario;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioId;

/**
 * Servicio para crear un usuario
 * 
 * Este servicio se encarga de crear un usuario implementando el caso de uso.
 * Es el encargado de orquestar el caso de uso, y recibe el command de entrada y el response de salida.
 */
@Service
public class CreateUsuarioService implements CreateUsuarioUseCase {
  
  private final UsuarioRepositoryPort repository;
  private final UsuarioMapper mapper;
  
  public CreateUsuarioService(UsuarioRepositoryPort repository, UsuarioMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }
  
  @Override
  public UsuarioResponse execute(CreateUsuarioCommand command) {
    // Generar ID único
    UsuarioId id = repository.nextId();
    
    // Crear entidad usando el mapper
    Usuario usuario = mapper.toEntity(command, id.value());
    
    // Persistir en el repositorio
    repository.create(usuario);
    
    // Retornar respuesta usando el mapper
    return mapper.toResponse(usuario);
  }
}
