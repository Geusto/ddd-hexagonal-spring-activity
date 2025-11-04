package co.edu.udec.ingsw.activity.application.usuario.service;

import org.springframework.stereotype.Service;

import co.edu.udec.ingsw.activity.application.usuario.port.in.GetByIdUsuarioUseCase;
import co.edu.udec.ingsw.activity.application.usuario.dto.query.GetUsuarioByIdQuery;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.UsuarioResponse;
import co.edu.udec.ingsw.activity.application.usuario.mapper.UsuarioMapper;
import co.edu.udec.ingsw.activity.application.usuario.port.out.UsuarioRepositoryPort;
import co.edu.udec.ingsw.activity.domain.usuario.entity.Usuario;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioId;

/**
 * Servicio para obtener un usuario por ID
 * 
 * Este servicio se encarga de obtener un usuario por su ID implementando el caso de uso.
 * Es el encargado de orquestar el caso de uso, y recibe el query de entrada y el response de salida.
 */
@Service
public class GetByIdUsuarioService implements GetByIdUsuarioUseCase {
  
  private final UsuarioRepositoryPort repository;
  private final UsuarioMapper mapper;
  
  public GetByIdUsuarioService(UsuarioRepositoryPort repository, UsuarioMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }
  
  @Override
  public UsuarioResponse execute(GetUsuarioByIdQuery query) {
    // Buscar usuario por ID
    Usuario usuario = repository.findById(new UsuarioId(query.id()));
    if (usuario == null) {
      throw new IllegalArgumentException("Usuario no encontrado con ID: " + query.id());
    }
    
    // Retornar respuesta usando el mapper
    return mapper.toResponse(usuario);
  }
}
