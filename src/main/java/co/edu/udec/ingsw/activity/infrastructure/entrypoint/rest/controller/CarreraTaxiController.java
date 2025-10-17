package co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.udec.ingsw.activity.application.carreraTaxi.port.in.*;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.command.*;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.query.*;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.response.*;
import co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.request.CarreraTaxiHttpRequest;
import co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.response.CarreraTaxiHttpResponse;
import co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.mapper.CarreraTaxiHttpMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/carreras-taxi")
public class CarreraTaxiController {
  private final CreateCarreraTaxiUseCase createUseCase;
  private final UpdateCarreraTaxiUseCase updateUseCase;
  private final DeleteCarreraTaxiUseCase deleteUseCase;
  private final GetByIdCarreraTaxiUseCase getByIdUseCase;
  private final ListCarreraTaxiUseCase listUseCase;
  private final CarreraTaxiHttpMapper httpMapper;

  public CarreraTaxiController(CreateCarreraTaxiUseCase createUseCase, UpdateCarreraTaxiUseCase updateUseCase, DeleteCarreraTaxiUseCase deleteUseCase, GetByIdCarreraTaxiUseCase getByIdUseCase, ListCarreraTaxiUseCase listUseCase, CarreraTaxiHttpMapper httpMapper) {
    this.createUseCase = createUseCase;
    this.updateUseCase = updateUseCase;
    this.deleteUseCase = deleteUseCase;
    this.getByIdUseCase = getByIdUseCase;
    this.listUseCase = listUseCase;
    this.httpMapper = httpMapper;
  }

  @PostMapping
  public ResponseEntity<CarreraTaxiHttpResponse> create(@Valid @RequestBody CarreraTaxiHttpRequest request) {
    CreateCarreraTaxiCommand command = httpMapper.toCreateCommand(request);
    CarreraTaxiResponse response = createUseCase.execute(command);
    return ResponseEntity.status(HttpStatus.CREATED).body(CarreraTaxiHttpResponse.created(response));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CarreraTaxiHttpResponse> update(
    @PathVariable Integer id,
    @Valid @RequestBody CarreraTaxiHttpRequest request
  ) {
    UpdateCarreraTaxiCommand command = httpMapper.toUpdateCommand(id, request);
    CarreraTaxiResponse response = updateUseCase.execute(command);
    return ResponseEntity.ok(CarreraTaxiHttpResponse.success(response));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CarreraTaxiHttpResponse> getById(@PathVariable Integer id) {
    GetCarreraTaxiByIdQuery query = new GetCarreraTaxiByIdQuery(id);
    CarreraTaxiResponse response = getByIdUseCase.execute(query);
    return ResponseEntity.ok(CarreraTaxiHttpResponse.success(response));
  }

  @GetMapping
  public ResponseEntity<CarreraTaxiHttpResponse> list() {
    ListCarreraTaxiQuery query = new ListCarreraTaxiQuery();
    CarreraTaxiListResponse response = listUseCase.execute(query);
    return ResponseEntity.ok(CarreraTaxiHttpResponse.success(response));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    DeleteCarreraTaxiCommand command = new DeleteCarreraTaxiCommand(id);
    deleteUseCase.execute(command);
    return ResponseEntity.noContent().build();
  }
}