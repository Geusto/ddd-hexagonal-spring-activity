package co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de CarreraTaxiKilometros Value Object")
class CarreraTaxiKilometrosTest {

    @Test
    @DisplayName("Debería crear un CarreraTaxiKilometros válido")
    void shouldCreateValidCarreraTaxiKilometros() {
        // Given
        Double validValue = 10.5;
        
        // When
        CarreraTaxiKilometros kilometros = new CarreraTaxiKilometros(validValue);
        
        // Then
        assertNotNull(kilometros);
        assertEquals(validValue, kilometros.value());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando los kilómetros son nulos")
    void shouldThrowExceptionWhenKilometrosIsNull() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CarreraTaxiKilometros(null));
        
        assertEquals("La distancia en kilómetros debe ser un número positivo mayor a 0", exception.getMessage());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando los kilómetros son cero o negativos")
    void shouldThrowExceptionWhenKilometrosIsZeroOrNegative() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new CarreraTaxiKilometros(0.0));
        
        assertThrows(IllegalArgumentException.class, () -> new CarreraTaxiKilometros(-5.0));
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando los kilómetros son mayores a 1000")
    void shouldThrowExceptionWhenKilometrosExceedsMaximum() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CarreraTaxiKilometros(1001.0));
        
        assertEquals("La distancia no puede ser mayor a 1000 kilómetros", exception.getMessage());
    }

    @Test
    @DisplayName("Debería aceptar kilómetros igual a 1000")
    void shouldAcceptKilometrosEqualToMaximum() {
        // Given
        Double maxKilometros = 1000.0;
        
        // When
        CarreraTaxiKilometros kilometros = new CarreraTaxiKilometros(maxKilometros);
        
        // Then
        assertNotNull(kilometros);
        assertEquals(maxKilometros, kilometros.value());
    }

    @Test
    @DisplayName("Debería formatear los kilómetros correctamente en toString")
    void shouldFormatKilometrosInToString() {
        // Given
        CarreraTaxiKilometros kilometros = new CarreraTaxiKilometros(10.5);
        
        // When
        String result = kilometros.toString();
        
        // Then
        assertNotNull(result);
        assertTrue(result.contains("km"));
        // El formato puede variar, solo verificamos que contiene "km"
    }
}

