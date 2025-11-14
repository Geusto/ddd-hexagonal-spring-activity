package co.edu.udec.ingsw.activity.domain.usuario.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de UsuarioRol Value Object")
class UsuarioRolTest {

    @Test
    @DisplayName("Debería crear un UsuarioRol válido")
    void shouldCreateValidUsuarioRol() {
        // Given
        String validValue = "ADMIN";
        
        // When
        UsuarioRol rol = new UsuarioRol(validValue);
        
        // Then
        assertNotNull(rol);
        assertEquals(validValue, rol.value());
    }

    @Test
    @DisplayName("Debería hacer trim automático del rol")
    void shouldTrimRolAutomatically() {
        // Given
        String valueWithSpaces = "  ADMIN  ";
        
        // When
        UsuarioRol rol = new UsuarioRol(valueWithSpaces);
        
        // Then
        assertEquals("ADMIN", rol.value());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el rol es nulo")
    void shouldThrowExceptionWhenRolIsNull() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UsuarioRol(null));
        
        assertEquals("El rol no puede ser nulo o vacío", exception.getMessage());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el rol está vacío")
    void shouldThrowExceptionWhenRolIsEmpty() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UsuarioRol(""));
        
        assertEquals("El rol no puede ser nulo o vacío", exception.getMessage());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el rol tiene menos de 2 caracteres")
    void shouldThrowExceptionWhenRolIsTooShort() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UsuarioRol("A"));
        
        assertEquals("El rol debe tener entre 2 y 20 caracteres", exception.getMessage());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el rol tiene más de 20 caracteres")
    void shouldThrowExceptionWhenRolIsTooLong() {
        // Given
        String longRol = "A".repeat(21);
        
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UsuarioRol(longRol));
        
        assertEquals("El rol debe tener entre 2 y 20 caracteres", exception.getMessage());
    }

    @Test
    @DisplayName("Debería aceptar rol con exactamente 2 caracteres")
    void shouldAcceptRolWithExactly2Characters() {
        // Given
        String twoCharRol = "AD";
        
        // When
        UsuarioRol rol = new UsuarioRol(twoCharRol);
        
        // Then
        assertNotNull(rol);
        assertEquals(twoCharRol, rol.value());
    }

    @Test
    @DisplayName("Debería aceptar rol con exactamente 20 caracteres")
    void shouldAcceptRolWithExactly20Characters() {
        // Given
        String twentyCharRol = "A".repeat(20);
        
        // When
        UsuarioRol rol = new UsuarioRol(twentyCharRol);
        
        // Then
        assertNotNull(rol);
        assertEquals(twentyCharRol, rol.value());
    }
}

