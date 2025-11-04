package co.edu.udec.ingsw.activity.application.usuario.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import co.edu.udec.ingsw.activity.domain.usuario.entity.Usuario;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioId;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioNombre;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioRol;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioClave;

import co.edu.udec.ingsw.activity.application.usuario.dto.command.CreateUsuarioCommand;
import co.edu.udec.ingsw.activity.application.usuario.dto.command.UpdateUsuarioCommand;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.UsuarioResponse;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.UsuarioListResponse;

@Component
public class UsuarioMapper {
  
  /**
   * Mapear un usuario a un DTO de respuesta
   * @param usuario Entidad de usuario
   * @return DTO de respuesta de usuario
   */
  public UsuarioResponse toResponse(Usuario usuario) {
    return new UsuarioResponse(
      usuario.getId().value(),
      usuario.getNombre().value(),
      usuario.getRol().value(),
      usuario.getFechaCreacion()
    );
  }
  
  /**
   * Mapear un listado de usuarios a un DTO de lista
   * @param usuarios Listado de usuarios
   * @return DTO de lista de usuarios
   */
  public UsuarioListResponse toListResponse(List<Usuario> usuarios) {
    List<UsuarioResponse> responses = usuarios.stream()
      .map(this::toResponse)
      .collect(Collectors.toList());
    
    return new UsuarioListResponse(responses);
  }
  
  /**
   * Mapear un comando de creación a una entidad de usuario
   * @param command Comando de creación
   * @return Entidad de usuario
   */
  public Usuario toEntity(CreateUsuarioCommand command, Integer generatedId) {
    return new Usuario(
      new UsuarioId(generatedId),
      new UsuarioNombre(command.nombre()),
      new UsuarioRol(command.rol()),
      new UsuarioClave(command.clave())
    );
  }
  
  /**
   * Mapear un comando de actualización a una entidad de usuario
   * @param command Comando de actualización
   * @param usuarioExistente Usuario existente a actualizar
   * @return Entidad de usuario actualizada
   */
  public Usuario toEntity(UpdateUsuarioCommand command, Usuario usuarioExistente) {
    return new Usuario(
      new UsuarioId(command.id()),
      new UsuarioNombre(command.nombre()),
      new UsuarioRol(command.rol()),
      new UsuarioClave(command.clave())
    );
  }
}
