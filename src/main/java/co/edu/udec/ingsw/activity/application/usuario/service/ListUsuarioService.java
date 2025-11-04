package co.edu.udec.ingsw.activity.application.usuario.service;

import org.springframework.stereotype.Service;

import co.edu.udec.ingsw.activity.application.usuario.port.in.ListUsuarioUseCase;
import co.edu.udec.ingsw.activity.application.usuario.dto.query.ListUsuarioQuery;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.UsuarioListResponse;
import co.edu.udec.ingsw.activity.application.usuario.mapper.UsuarioMapper;
import co.edu.udec.ingsw.activity.application.usuario.port.out.UsuarioRepositoryPort;

/**
 * Servicio para listar todos los usuarios
 * 
 * Este servicio se encarga de listar todos los usuarios implementando el caso de uso.
 * Es el encargado de orquestar el caso de uso, y recibe el query de entrada y el response de salida.
 */
@Service
public class ListUsuarioService implements ListUsuarioUseCase {
  
  private final UsuarioRepositoryPort repository;
  private final UsuarioMapper mapper;
  
  public ListUsuarioService(UsuarioRepositoryPort repository, UsuarioMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }
  
  @Override
  public UsuarioListResponse execute(ListUsuarioQuery query) {
    // Obtener todos los usuarios
    var usuarios = repository.findAll();
    
    // Retornar respuesta usando el mapper
    return mapper.toListResponse(usuarios);
  }
}
