package co.edu.udec.ingsw.activity.infrastructure.adapter.persistence.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.udec.ingsw.activity.application.usuario.port.out.UsuarioRepositoryPort;
import co.edu.udec.ingsw.activity.domain.usuario.entity.Usuario;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.UsuarioId;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUsuarioRepositoryAdapter implements UsuarioRepositoryPort {
  private final Map<UsuarioId, Usuario> storage = new HashMap<>();
  private int sequence = 1;

  @Override
  public UsuarioId nextId() {
    return new UsuarioId(sequence++);
  }

  @Override
  public void create(Usuario usuario) {
    storage.put(usuario.getId(), usuario);
  }

  @Override
  public void update(Usuario usuario) {
    storage.put(usuario.getId(), usuario);
  }

  @Override
  public void delete(UsuarioId id) {
    storage.remove(id);
  }

  @Override
  public Usuario findById(UsuarioId id) {
    return storage.get(id);
  }

  @Override
  public List<Usuario> findAll() {
    return new ArrayList<>(storage.values());
  }
}
