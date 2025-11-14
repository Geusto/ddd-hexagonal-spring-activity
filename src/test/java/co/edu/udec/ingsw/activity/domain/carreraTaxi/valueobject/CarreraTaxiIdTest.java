package co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de CarreraTaxiId Value Object")
class CarreraTaxiIdTest {

    @Test
    @DisplayName("Debería crear un CarreraTaxiId válido")
    void shouldCreateValidCarreraTaxiId() {
        // Given
        Integer validValue = 1;
        
        // When
        CarreraTaxiId id = new CarreraTaxiId(validValue);
        
        // Then
        assertNotNull(id);
        assertEquals(validValue, id.value());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el ID es nulo")
    void shouldThrowExceptionWhenIdIsNull() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CarreraTaxiId(null));
        
        assertTrue(exception.getMessage().contains("ID") || exception.getMessage().contains("positivo"));
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el ID es cero o negativo")
    void shouldThrowExceptionWhenIdIsZeroOrNegative() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new CarreraTaxiId(0));
        
        assertThrows(IllegalArgumentException.class, () -> new CarreraTaxiId(-1));
    }
}

