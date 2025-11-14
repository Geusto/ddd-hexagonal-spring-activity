package co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de CarreraTaxiPrecio Value Object")
class CarreraTaxiPrecioTest {

    @Test
    @DisplayName("Debería crear un CarreraTaxiPrecio válido")
    void shouldCreateValidCarreraTaxiPrecio() {
        // Given
        Double validValue = 15000.0;
        
        // When
        CarreraTaxiPrecio precio = new CarreraTaxiPrecio(validValue);
        
        // Then
        assertNotNull(precio);
        assertEquals(validValue, precio.value());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el precio es nulo")
    void shouldThrowExceptionWhenPrecioIsNull() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CarreraTaxiPrecio(null));
        
        assertEquals("El precio debe ser un número positivo mayor a 0", exception.getMessage());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el precio es cero")
    void shouldThrowExceptionWhenPrecioIsZero() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CarreraTaxiPrecio(0.0));
        
        assertEquals("El precio debe ser un número positivo mayor a 0", exception.getMessage());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el precio es negativo")
    void shouldThrowExceptionWhenPrecioIsNegative() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CarreraTaxiPrecio(-100.0));
        
        assertEquals("El precio debe ser un número positivo mayor a 0", exception.getMessage());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el precio es mayor a 1,000,000")
    void shouldThrowExceptionWhenPrecioExceedsMaximum() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CarreraTaxiPrecio(1000001.0));
        
        assertEquals("El precio no puede ser mayor a 1,000,000", exception.getMessage());
    }

    @Test
    @DisplayName("Debería aceptar precio igual a 1,000,000")
    void shouldAcceptPrecioEqualToMaximum() {
        // Given
        Double maxPrecio = 1000000.0;
        
        // When
        CarreraTaxiPrecio precio = new CarreraTaxiPrecio(maxPrecio);
        
        // Then
        assertNotNull(precio);
        assertEquals(maxPrecio, precio.value());
    }

    @Test
    @DisplayName("Debería formatear el precio correctamente en toString")
    void shouldFormatPrecioInToString() {
        // Given
        CarreraTaxiPrecio precio = new CarreraTaxiPrecio(15000.50);
        
        // When
        String result = precio.toString();
        
        // Then
        assertNotNull(result);
        assertTrue(result.contains("$"));
        // El formato puede variar según la localización, solo verificamos que contiene el símbolo
    }
}

