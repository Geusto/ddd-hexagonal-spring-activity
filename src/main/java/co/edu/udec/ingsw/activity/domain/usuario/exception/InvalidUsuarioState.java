package co.edu.udec.ingsw.activity.domain.usuario.exception;

public class InvalidUsuarioState extends UsuarioDomainException {
    
    private static final long serialVersionUID = 1L;
    
    public InvalidUsuarioState(String message) {
        super(message);
    }
    
    public InvalidUsuarioState(String message, Throwable cause) {
        super(message, cause);
    }
    
    @Override
    public String getExceptionType() {
        return "InvalidUsuarioState";
    }
}
