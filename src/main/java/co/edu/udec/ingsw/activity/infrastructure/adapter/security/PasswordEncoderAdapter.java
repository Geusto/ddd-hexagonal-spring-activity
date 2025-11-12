package co.edu.udec.ingsw.activity.infrastructure.adapter.security;

import org.springframework.stereotype.Component;

import co.edu.udec.ingsw.activity.application.usuario.port.out.PasswordEncoderPort;

@Component
public class PasswordEncoderAdapter implements PasswordEncoderPort {

  @Override
  public boolean matches(String rawPassword, String encodedPassword) {
    return rawPassword != null && rawPassword.equals(encodedPassword);
  }

  @Override
  public String encode(String rawPassword) {
    return rawPassword;
  }
}

