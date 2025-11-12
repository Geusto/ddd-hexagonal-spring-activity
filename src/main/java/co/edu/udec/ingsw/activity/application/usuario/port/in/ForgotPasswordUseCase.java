package co.edu.udec.ingsw.activity.application.usuario.port.in;

import co.edu.udec.ingsw.activity.application.usuario.dto.command.ForgotPasswordCommand;

public interface ForgotPasswordUseCase {
  void execute(ForgotPasswordCommand command);
}