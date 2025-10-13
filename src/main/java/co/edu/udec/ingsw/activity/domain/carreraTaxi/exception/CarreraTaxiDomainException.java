package co.edu.udec.ingsw.activity.domain.carreraTaxi.exception;

/**
 * Excepción base para todas las excepciones del dominio CarreraTaxi.
 * 
 * Esta excepción representa errores que ocurren cuando se violan
 * las reglas de negocio o el estado del dominio es inválido.
 * 
 * Todas las excepciones específicas del dominio CarreraTaxi deben
 * extender de esta clase para mantener la consistencia y permitir
 * el manejo centralizado de errores del dominio.
 * 
 */
public class CarreraTaxiDomainException extends RuntimeException {
    
    protected static final int DEFAULT_ERROR_CODE = 400;

    public CarreraTaxiDomainException(String message) {
        super(message);
    }
    
    public CarreraTaxiDomainException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public String getExceptionType() {
        return this.getClass().getSimpleName();
    }
    
    public boolean isCarreraTaxiDomainException() {
        return true;
    }
    
    public int getDefaultErrorCode() {
        return DEFAULT_ERROR_CODE;
    }
}
