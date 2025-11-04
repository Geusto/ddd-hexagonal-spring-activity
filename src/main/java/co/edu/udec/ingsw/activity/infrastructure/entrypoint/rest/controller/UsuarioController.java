package co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.udec.ingsw.activity.application.usuario.port.in.*;
import co.edu.udec.ingsw.activity.application.usuario.dto.command.*;
import co.edu.udec.ingsw.activity.application.usuario.dto.query.*;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.*;
import co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.request.UsuarioHttpRequest;
import co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.response.UsuarioHttpResponse;
import co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.mapper.UsuarioHttpMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
  private final CreateUsuarioUseCase createUseCase;
  private final UpdateUsuarioUseCase updateUseCase;
  private final DeleteUsuarioUseCase deleteUseCase;
  private final GetByIdUsuarioUseCase getByIdUseCase;
  private final ListUsuarioUseCase listUseCase;
  private final UsuarioHttpMapper httpMapper;

  public UsuarioController(CreateUsuarioUseCase createUseCase, UpdateUsuarioUseCase updateUseCase, DeleteUsuarioUseCase deleteUseCase, GetByIdUsuarioUseCase getByIdUseCase, ListUsuarioUseCase listUseCase, UsuarioHttpMapper httpMapper) {
    this.createUseCase = createUseCase;
    this.updateUseCase = updateUseCase;
    this.deleteUseCase = deleteUseCase;
    this.getByIdUseCase = getByIdUseCase;
    this.listUseCase = listUseCase;
    this.httpMapper = httpMapper;
  }

  @PostMapping
  public ResponseEntity<UsuarioHttpResponse> create(@Valid @RequestBody UsuarioHttpRequest request) {
    CreateUsuarioCommand command = httpMapper.toCreateCommand(request);
    UsuarioResponse response = createUseCase.execute(command);
    return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioHttpResponse.created(response));
  }

  @PutMapping("/{id}")
  public ResponseEntity<UsuarioHttpResponse> update(
    @PathVariable Integer id,
    @Valid @RequestBody UsuarioHttpRequest request
  ) {
    UpdateUsuarioCommand command = httpMapper.toUpdateCommand(id, request);
    UsuarioResponse response = updateUseCase.execute(command);
    return ResponseEntity.ok(UsuarioHttpResponse.success(response));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UsuarioHttpResponse> getById(@PathVariable Integer id) {
    GetUsuarioByIdQuery query = new GetUsuarioByIdQuery(id);
    UsuarioResponse response = getByIdUseCase.execute(query);
    return ResponseEntity.ok(UsuarioHttpResponse.success(response));
  }

  @GetMapping
  public ResponseEntity<UsuarioHttpResponse> list() {
    ListUsuarioQuery query = new ListUsuarioQuery();
    UsuarioListResponse response = listUseCase.execute(query);
    return ResponseEntity.ok(UsuarioHttpResponse.success(response));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<UsuarioHttpResponse> delete(@PathVariable Integer id) {
    DeleteUsuarioCommand command = new DeleteUsuarioCommand(id);
    deleteUseCase.execute(command);
    return ResponseEntity.ok(UsuarioHttpResponse.deleted("Usuario eliminado exitosamente"));
  }
}
