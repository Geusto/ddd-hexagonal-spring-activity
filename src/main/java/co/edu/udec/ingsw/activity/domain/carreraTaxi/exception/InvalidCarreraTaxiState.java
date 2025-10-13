package co.edu.udec.ingsw.activity.domain.carreraTaxi.exception;

/**
 * Excepción que se lanza cuando el estado de una carrera de taxi es inválido.
 * 
 * Esta excepción se utiliza cuando se intenta realizar una operación
 * que no es válida para el estado actual de la carrera.
 * 
 */
public class InvalidCarreraTaxiState extends CarreraTaxiDomainException {
    
    public InvalidCarreraTaxiState(String message) {
        super(message);
    }
    
    public InvalidCarreraTaxiState(String message, Throwable cause) {
        super(message, cause);
    }
    
    public InvalidCarreraTaxiState(String operation, String currentState) {
        super(String.format("No se puede %s en el estado actual: %s", operation, currentState));
    }
    
    public InvalidCarreraTaxiState(String fromState, String toState, boolean isTransition) {
        super(String.format("Transición de estado inválida: %s -> %s", fromState, toState));
    }
}
