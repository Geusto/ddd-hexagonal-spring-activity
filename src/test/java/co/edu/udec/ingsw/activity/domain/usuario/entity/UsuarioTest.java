package co.edu.udec.ingsw.activity.domain.usuario.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import co.edu.udec.ingsw.activity.domain.usuario.valueobject.*;
import co.edu.udec.ingsw.activity.domain.usuario.event.UsuarioCreated;
import co.edu.udec.ingsw.activity.domain.usuario.event.UsuarioUpdated;
import co.edu.udec.ingsw.activity.domain.usuario.event.UsuarioDeleted;

@DisplayName("Tests de Entidad Usuario")
class UsuarioTest {

    @Test
    @DisplayName("Debería crear un usuario válido y publicar evento UsuarioCreated")
    void shouldCreateUsuarioAndPublishEvent() {
        // Given
        UsuarioId id = new UsuarioId(1);
        UsuarioNombre nombre = new UsuarioNombre("Juan Pérez");
        UsuarioRol rol = new UsuarioRol("ADMIN");
        UsuarioClave clave = new UsuarioClave("password123");
        
        // When
        Usuario usuario = new Usuario(id, nombre, rol, clave);
        
        // Then
        assertNotNull(usuario);
        assertEquals(id, usuario.getId());
        assertEquals(nombre, usuario.getNombre());
        assertEquals(rol, usuario.getRol());
        assertEquals(clave, usuario.getClave());
        assertNotNull(usuario.getFechaCreacion());
        
        // Verificar evento de dominio
        var events = usuario.getDomainEvents();
        assertEquals(1, events.size());
        assertInstanceOf(UsuarioCreated.class, events.getFirst());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el ID es nulo")
    void shouldThrowExceptionWhenIdIsNull() {
        // Given
        UsuarioNombre nombre = new UsuarioNombre("Juan Pérez");
        UsuarioRol rol = new UsuarioRol("ADMIN");
        UsuarioClave clave = new UsuarioClave("password123");
        
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Usuario(null, nombre, rol, clave));
        
        assertEquals("El ID no puede ser nulo", exception.getMessage());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el nombre es nulo")
    void shouldThrowExceptionWhenNombreIsNull() {
        // Given
        UsuarioId id = new UsuarioId(1);
        UsuarioRol rol = new UsuarioRol("ADMIN");
        UsuarioClave clave = new UsuarioClave("password123");
        
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Usuario(id, null, rol, clave));
        
        assertEquals("El nombre no puede ser nulo", exception.getMessage());
    }

    @Test
    @DisplayName("Debería publicar evento UsuarioUpdated al actualizar")
    void shouldPublishEventWhenUpdating() {
        // Given
        Usuario usuario = createValidUsuario();
        UsuarioNombre nuevoNombre = new UsuarioNombre("Pedro García");
        UsuarioRol nuevoRol = new UsuarioRol("USER");
        UsuarioClave nuevaClave = new UsuarioClave("newPassword");
        usuario.clearDomainEvents();
        
        // When
        usuario.actualizarUsuario(nuevoNombre, nuevoRol, nuevaClave);
        
        // Then - La entidad es inmutable, solo verifica el evento
        var events = usuario.getDomainEvents();
        assertEquals(1, events.size());
        assertInstanceOf(UsuarioUpdated.class, events.getFirst());
    }

    @Test
    @DisplayName("Debería lanzar excepción al actualizar con nombre nulo")
    void shouldThrowExceptionWhenUpdatingWithNullNombre() {
        // Given
        Usuario usuario = createValidUsuario();
        UsuarioRol nuevoRol = new UsuarioRol("USER");
        UsuarioClave nuevaClave = new UsuarioClave("newPassword");
        
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> usuario.actualizarUsuario(null, nuevoRol, nuevaClave));
        
        assertEquals("El nuevo nombre no puede ser nulo", exception.getMessage());
    }

    @Test
    @DisplayName("Debería marcar usuario para eliminar y publicar evento UsuarioDeleted")
    void shouldMarkUsuarioForDeletion() {
        // Given
        Usuario usuario = createValidUsuario();
        usuario.clearDomainEvents();
        
        // When
        usuario.marcarUsuarioParaEliminar();
        
        // Then
        var events = usuario.getDomainEvents();
        assertEquals(1, events.size());
        assertInstanceOf(UsuarioDeleted.class, events.getFirst());
    }

    @Test
    @DisplayName("Debería comparar correctamente dos usuarios iguales por ID")
    void shouldCompareEqualUsuarios() {
        // Given
        UsuarioId id = new UsuarioId(1);
        Usuario usuario1 = new Usuario(
            id,
            new UsuarioNombre("Juan"),
            new UsuarioRol("ADMIN"),
            new UsuarioClave("password1")
        );
        Usuario usuario2 = new Usuario(
            id,
            new UsuarioNombre("Pedro"),
            new UsuarioRol("USER"),
            new UsuarioClave("password2")
        );
        
        // When & Then - Compara por ID, no por todos los campos
        assertTrue(usuario1.equals(usuario2));
    }

    @Test
    @DisplayName("Debería retornar false al comparar con null")
    void shouldReturnFalseWhenComparingWithNull() {
        // Given
        Usuario usuario = createValidUsuario();
        
        // When & Then
        assertFalse(usuario.equals(null));
    }

    @Test
    @DisplayName("Debería limpiar eventos de dominio correctamente")
    void shouldClearDomainEvents() {
        // Given
        Usuario usuario = createValidUsuario();
        assertEquals(1, usuario.getDomainEvents().size());
        
        // When
        usuario.clearDomainEvents();
        
        // Then
        assertEquals(0, usuario.getDomainEvents().size());
    }

    @Test
    @DisplayName("Debería retornar una copia de los eventos, no la lista original")
    void shouldReturnCopyOfDomainEvents() {
        // Given
        Usuario usuario = createValidUsuario();
        var events1 = usuario.getDomainEvents();
        
        // When
        usuario.clearDomainEvents();
        var events2 = usuario.getDomainEvents();
        
        // Then
        assertEquals(1, events1.size()); // La copia original no se modifica
        assertEquals(0, events2.size()); // La nueva copia está vacía
    }

    private Usuario createValidUsuario() {
        return new Usuario(
            new UsuarioId(1),
            new UsuarioNombre("Test User"),
            new UsuarioRol("USER"),
            new UsuarioClave("password")
        );
    }
}

