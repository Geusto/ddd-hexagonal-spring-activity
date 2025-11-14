package co.edu.udec.ingsw.activity.domain.usuario.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de UsuarioNombre Value Object")
class UsuarioNombreTest {

    @Test
    @DisplayName("Debería crear un UsuarioNombre válido")
    void shouldCreateValidUsuarioNombre() {
        // Given
        String validValue = "Juan Pérez";
        
        // When
        UsuarioNombre nombre = new UsuarioNombre(validValue);
        
        // Then
        assertNotNull(nombre);
        assertEquals(validValue, nombre.value());
    }

    @Test
    @DisplayName("Debería hacer trim automático del nombre")
    void shouldTrimNombreAutomatically() {
        // Given
        String valueWithSpaces = "  Juan Pérez  ";
        
        // When
        UsuarioNombre nombre = new UsuarioNombre(valueWithSpaces);
        
        // Then
        assertEquals("Juan Pérez", nombre.value());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el nombre es nulo")
    void shouldThrowExceptionWhenNombreIsNull() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UsuarioNombre(null));
        
        assertEquals("El nombre no puede ser nulo o vacío", exception.getMessage());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el nombre está vacío")
    void shouldThrowExceptionWhenNombreIsEmpty() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UsuarioNombre(""));
        
        assertEquals("El nombre no puede ser nulo o vacío", exception.getMessage());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el nombre solo tiene espacios")
    void shouldThrowExceptionWhenNombreIsOnlySpaces() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UsuarioNombre("   "));
        
        assertEquals("El nombre no puede ser nulo o vacío", exception.getMessage());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el nombre tiene menos de 2 caracteres")
    void shouldThrowExceptionWhenNombreIsTooShort() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UsuarioNombre("A"));
        
        assertEquals("El nombre debe tener entre 2 y 50 caracteres", exception.getMessage());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el nombre tiene más de 50 caracteres")
    void shouldThrowExceptionWhenNombreIsTooLong() {
        // Given
        String longName = "A".repeat(51);
        
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UsuarioNombre(longName));
        
        assertEquals("El nombre debe tener entre 2 y 50 caracteres", exception.getMessage());
    }

    @Test
    @DisplayName("Debería aceptar nombre con exactamente 2 caracteres")
    void shouldAcceptNombreWithExactly2Characters() {
        // Given
        String twoCharName = "AB";
        
        // When
        UsuarioNombre nombre = new UsuarioNombre(twoCharName);
        
        // Then
        assertNotNull(nombre);
        assertEquals(twoCharName, nombre.value());
    }

    @Test
    @DisplayName("Debería aceptar nombre con exactamente 50 caracteres")
    void shouldAcceptNombreWithExactly50Characters() {
        // Given
        String fiftyCharName = "A".repeat(50);
        
        // When
        UsuarioNombre nombre = new UsuarioNombre(fiftyCharName);
        
        // Then
        assertNotNull(nombre);
        assertEquals(fiftyCharName, nombre.value());
    }

    @Test
    @DisplayName("Debería comparar correctamente dos UsuarioNombre iguales")
    void shouldCompareEqualUsuarioNombres() {
        // Given
        UsuarioNombre nombre1 = new UsuarioNombre("Juan Pérez");
        UsuarioNombre nombre2 = new UsuarioNombre("Juan Pérez");
        
        // When & Then
        assertTrue(nombre1.equals(nombre2));
    }
}

