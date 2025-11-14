package co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de CarreraTaxiCantidadPasajeros Value Object")
class CarreraTaxiCantidadPasajerosTest {

    @Test
    @DisplayName("Debería crear un CarreraTaxiCantidadPasajeros válido")
    void shouldCreateValidCantidadPasajeros() {
        // Given
        Integer validValue = 4;
        
        // When
        CarreraTaxiCantidadPasajeros cantidad = new CarreraTaxiCantidadPasajeros(validValue);
        
        // Then
        assertNotNull(cantidad);
        assertEquals(validValue, cantidad.value());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando la cantidad es nula")
    void shouldThrowExceptionWhenCantidadIsNull() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CarreraTaxiCantidadPasajeros(null));
        
        assertEquals("La cantidad de pasajeros debe ser un número positivo mayor a 0", exception.getMessage());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando la cantidad es cero o negativa")
    void shouldThrowExceptionWhenCantidadIsZeroOrNegative() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new CarreraTaxiCantidadPasajeros(0));
        
        assertThrows(IllegalArgumentException.class, () -> new CarreraTaxiCantidadPasajeros(-1));
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando la cantidad es mayor a 8")
    void shouldThrowExceptionWhenCantidadExceedsMaximum() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CarreraTaxiCantidadPasajeros(9));
        
        assertEquals("La cantidad de pasajeros no puede ser mayor a 8", exception.getMessage());
    }

    @Test
    @DisplayName("Debería aceptar cantidad igual a 8")
    void shouldAcceptCantidadEqualToMaximum() {
        // Given
        Integer maxCantidad = 8;
        
        // When
        CarreraTaxiCantidadPasajeros cantidad = new CarreraTaxiCantidadPasajeros(maxCantidad);
        
        // Then
        assertNotNull(cantidad);
        assertEquals(maxCantidad, cantidad.value());
    }

    @Test
    @DisplayName("Debería formatear correctamente en toString para un pasajero")
    void shouldFormatSinglePasajeroInToString() {
        // Given
        CarreraTaxiCantidadPasajeros cantidad = new CarreraTaxiCantidadPasajeros(1);
        
        // When
        String result = cantidad.toString();
        
        // Then
        assertTrue(result.contains("pasajero"));
        assertFalse(result.contains("pasajeros"));
    }

    @Test
    @DisplayName("Debería formatear correctamente en toString para múltiples pasajeros")
    void shouldFormatMultiplePasajerosInToString() {
        // Given
        CarreraTaxiCantidadPasajeros cantidad = new CarreraTaxiCantidadPasajeros(4);
        
        // When
        String result = cantidad.toString();
        
        // Then
        assertTrue(result.contains("pasajeros"));
    }
}

