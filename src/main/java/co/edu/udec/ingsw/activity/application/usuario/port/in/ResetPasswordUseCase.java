package co.edu.udec.ingsw.activity.application.usuario.port.in;

import co.edu.udec.ingsw.activity.application.usuario.dto.command.ResetPasswordCommand;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.ResetPasswordResponse;

public interface ResetPasswordUseCase {
  ResetPasswordResponse execute(ResetPasswordCommand command);
}