package co.edu.udec.ingsw.activity.application.usuario.port.out;

import co.edu.udec.ingsw.activity.domain.usuario.entity.Usuario;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioId;
import java.util.List;

public interface UsuarioRepositoryPort {
  UsuarioId nextId();
  void create(Usuario usuario);
  void update(Usuario usuario);
  void delete(UsuarioId id);
  Usuario findById(UsuarioId id);
  Usuario findByNombre(String nombre);
  List<Usuario> findAll();
}