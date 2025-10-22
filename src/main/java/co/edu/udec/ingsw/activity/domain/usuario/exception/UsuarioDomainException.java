package co.edu.udec.ingsw.activity.domain.usuario.exception;

public class UsuarioDomainException extends RuntimeException {
    
    protected static final int DEFAULT_ERROR_CODE = 400;

    public UsuarioDomainException(String message) {
        super(message);
    }
    
    public UsuarioDomainException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public String getExceptionType() {
        return this.getClass().getSimpleName();
    }
    
    public boolean isUsuarioDomainException() {
        return true;
    }
    
    public int getDefaultErrorCode() {
        return DEFAULT_ERROR_CODE;
    }
}
