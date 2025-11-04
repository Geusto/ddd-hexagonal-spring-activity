package co.edu.udec.ingsw.activity.application.usuario.service;

import org.springframework.stereotype.Service;

import co.edu.udec.ingsw.activity.application.usuario.port.in.DeleteUsuarioUseCase;
import co.edu.udec.ingsw.activity.application.usuario.dto.command.DeleteUsuarioCommand;
import co.edu.udec.ingsw.activity.application.usuario.port.out.UsuarioRepositoryPort;
import co.edu.udec.ingsw.activity.domain.usuario.entity.Usuario;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioId;

/**
 * Servicio para eliminar un usuario
 * 
 * Este servicio se encarga de eliminar un usuario implementando el caso de uso.
 * Es el encargado de orquestar el caso de uso, y recibe el command de entrada.
 */
@Service
public class DeleteUsuarioService implements DeleteUsuarioUseCase {
  
  private final UsuarioRepositoryPort repository;
  
  public DeleteUsuarioService(UsuarioRepositoryPort repository) {
    this.repository = repository;
  }
  
  @Override
  public void execute(DeleteUsuarioCommand command) {
    // Buscar usuario existente
    Usuario usuario = repository.findById(new UsuarioId(command.id()));
    if (usuario == null) {
      throw new IllegalArgumentException("Usuario no encontrado con ID: " + command.id());
    }
    
    // Marcar usuario para eliminación (publica evento de dominio)
    usuario.marcarUsuarioParaEliminar();
    
    // Eliminar del repositorio
    repository.delete(new UsuarioId(command.id()));
  }
}
