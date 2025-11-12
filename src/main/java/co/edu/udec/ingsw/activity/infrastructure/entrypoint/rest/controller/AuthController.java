package co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.udec.ingsw.activity.application.usuario.port.in.*;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.LoginResponse;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.ResetPasswordResponse;
import co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.request.*;
import co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.dto.response.AuthHttpResponse;
import co.edu.udec.ingsw.activity.infrastructure.entrypoint.rest.mapper.AuthHttpMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  
  private final LoginUsuarioUseCase loginUseCase;
  private final ForgotPasswordUseCase forgotPasswordUseCase;
  private final ResetPasswordUseCase resetPasswordUseCase;
  private final LogoutUsuarioUseCase logoutUseCase;
  private final AuthHttpMapper httpMapper;
  
  public AuthController(
    LoginUsuarioUseCase loginUseCase,
    ForgotPasswordUseCase forgotPasswordUseCase,
    ResetPasswordUseCase resetPasswordUseCase,
    LogoutUsuarioUseCase logoutUseCase,
    AuthHttpMapper httpMapper
  ) {
    this.loginUseCase = loginUseCase;
    this.forgotPasswordUseCase = forgotPasswordUseCase;
    this.resetPasswordUseCase = resetPasswordUseCase;
    this.logoutUseCase = logoutUseCase;
    this.httpMapper = httpMapper;
  }
  
  @PostMapping("/login")
  public ResponseEntity<AuthHttpResponse> login(@Valid @RequestBody LoginHttpRequest request) {
    try {
      LoginResponse response = loginUseCase.execute(httpMapper.toLoginCommand(request));
      return ResponseEntity.ok(AuthHttpResponse.loginSuccess(response));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(AuthHttpResponse.error(e.getMessage()));
    }
  }
  
  @PostMapping("/forgot-password")
  public ResponseEntity<AuthHttpResponse> forgotPassword(
    @Valid @RequestBody ForgotPasswordHttpRequest request
  ) {
    try {
      forgotPasswordUseCase.execute(httpMapper.toForgotPasswordCommand(request));
      return ResponseEntity.ok(AuthHttpResponse.forgotPasswordSuccess());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(AuthHttpResponse.error("Error al procesar la solicitud: " + e.getMessage()));
    }
  }
  
  @PostMapping("/reset-password")
  public ResponseEntity<AuthHttpResponse> resetPassword(
    @Valid @RequestBody ResetPasswordHttpRequest request
  ) {
    try {
      ResetPasswordResponse response = resetPasswordUseCase.execute(
        httpMapper.toResetPasswordCommand(request)
      );
      return ResponseEntity.ok(AuthHttpResponse.resetPasswordSuccess(response));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(AuthHttpResponse.error(e.getMessage()));
    }
  }
  
  @PostMapping("/logout")
  public ResponseEntity<AuthHttpResponse> logout(@Valid @RequestBody LogoutHttpRequest request) {
    try {
      logoutUseCase.execute(httpMapper.toLogoutCommand(request));
      return ResponseEntity.ok(AuthHttpResponse.logoutSuccess());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(AuthHttpResponse.error(e.getMessage()));
    }
  }
}