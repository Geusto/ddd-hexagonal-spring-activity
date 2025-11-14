package co.edu.udec.ingsw.activity.domain.usuario.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de UsuarioId Value Object")
class UsuarioIdTest {

    @Test
    @DisplayName("Debería crear un UsuarioId válido")
    void shouldCreateValidUsuarioId() {
        // Given
        Integer validValue = 1;
        
        // When
        UsuarioId usuarioId = new UsuarioId(validValue);
        
        // Then
        assertNotNull(usuarioId);
        assertEquals(validValue, usuarioId.value());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el ID es nulo")
    void shouldThrowExceptionWhenIdIsNull() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UsuarioId(null));
        
        assertEquals("El ID del usuario debe ser un número positivo mayor a 0", exception.getMessage());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el ID es cero")
    void shouldThrowExceptionWhenIdIsZero() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UsuarioId(0));
        
        assertEquals("El ID del usuario debe ser un número positivo mayor a 0", exception.getMessage());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el ID es negativo")
    void shouldThrowExceptionWhenIdIsNegative() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UsuarioId(-1));
        
        assertEquals("El ID del usuario debe ser un número positivo mayor a 0", exception.getMessage());
    }

    @Test
    @DisplayName("Debería comparar correctamente dos UsuarioId iguales")
    void shouldCompareEqualUsuarioIds() {
        // Given
        UsuarioId id1 = new UsuarioId(1);
        UsuarioId id2 = new UsuarioId(1);
        
        // When & Then
        assertTrue(id1.equals(id2));
    }

    @Test
    @DisplayName("Debería comparar correctamente dos UsuarioId diferentes")
    void shouldCompareDifferentUsuarioIds() {
        // Given
        UsuarioId id1 = new UsuarioId(1);
        UsuarioId id2 = new UsuarioId(2);
        
        // When & Then
        assertFalse(id1.equals(id2));
    }

    @Test
    @DisplayName("Debería retornar el valor como String en toString")
    void shouldReturnStringValueInToString() {
        // Given
        UsuarioId id = new UsuarioId(123);
        
        // When
        String result = id.toString();
        
        // Then
        assertEquals("123", result);
    }
}

