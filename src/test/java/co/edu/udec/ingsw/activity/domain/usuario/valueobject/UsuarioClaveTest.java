package co.edu.udec.ingsw.activity.domain.usuario.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de UsuarioClave Value Object")
class UsuarioClaveTest {

    @Test
    @DisplayName("Debería crear un UsuarioClave válido")
    void shouldCreateValidUsuarioClave() {
        // Given
        String validValue = "password123";
        
        // When
        UsuarioClave clave = new UsuarioClave(validValue);
        
        // Then
        assertNotNull(clave);
        assertEquals(validValue, clave.value());
    }

    @Test
    @DisplayName("Debería hacer trim automático de la clave")
    void shouldTrimClaveAutomatically() {
        // Given
        String valueWithSpaces = "  password123  ";
        
        // When
        UsuarioClave clave = new UsuarioClave(valueWithSpaces);
        
        // Then
        assertEquals("password123", clave.value());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando la clave es nula")
    void shouldThrowExceptionWhenClaveIsNull() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UsuarioClave(null));
        
        assertEquals("La clave no puede ser nula o vacía", exception.getMessage());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando la clave está vacía")
    void shouldThrowExceptionWhenClaveIsEmpty() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UsuarioClave(""));
        
        assertEquals("La clave no puede ser nula o vacía", exception.getMessage());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando la clave tiene menos de 6 caracteres")
    void shouldThrowExceptionWhenClaveIsTooShort() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UsuarioClave("12345"));
        
        assertEquals("La clave debe tener entre 6 y 100 caracteres", exception.getMessage());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando la clave tiene más de 100 caracteres")
    void shouldThrowExceptionWhenClaveIsTooLong() {
        // Given
        String longClave = "A".repeat(101);
        
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UsuarioClave(longClave));
        
        assertEquals("La clave debe tener entre 6 y 100 caracteres", exception.getMessage());
    }

    @Test
    @DisplayName("Debería aceptar clave con exactamente 6 caracteres")
    void shouldAcceptClaveWithExactly6Characters() {
        // Given
        String sixCharClave = "123456";
        
        // When
        UsuarioClave clave = new UsuarioClave(sixCharClave);
        
        // Then
        assertNotNull(clave);
        assertEquals(sixCharClave, clave.value());
    }

    @Test
    @DisplayName("Debería aceptar clave con exactamente 100 caracteres")
    void shouldAcceptClaveWithExactly100Characters() {
        // Given
        String hundredCharClave = "A".repeat(100);
        
        // When
        UsuarioClave clave = new UsuarioClave(hundredCharClave);
        
        // Then
        assertNotNull(clave);
        assertEquals(hundredCharClave, clave.value());
    }

    @Test
    @DisplayName("Debería ocultar la clave en toString por seguridad")
    void shouldHideClaveInToString() {
        // Given
        UsuarioClave clave = new UsuarioClave("password123");
        
        // When
        String result = clave.toString();
        
        // Then
        assertEquals("***", result);
    }
}

