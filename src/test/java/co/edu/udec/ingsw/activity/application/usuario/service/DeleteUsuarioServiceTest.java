package co.edu.udec.ingsw.activity.application.usuario.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import co.edu.udec.ingsw.activity.application.usuario.dto.command.DeleteUsuarioCommand;
import co.edu.udec.ingsw.activity.application.usuario.port.out.UsuarioRepositoryPort;
import co.edu.udec.ingsw.activity.domain.usuario.entity.Usuario;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests de DeleteUsuarioService")
class DeleteUsuarioServiceTest {

    @Mock
    private UsuarioRepositoryPort repositoryPort;

    private DeleteUsuarioService service;

    @BeforeEach
    void setUp() {
        service = new DeleteUsuarioService(repositoryPort);
    }

    @Test
    @DisplayName("Debería eliminar un usuario exitosamente")
    void shouldDeleteUsuarioSuccessfully() {
        // Given
        DeleteUsuarioCommand command = new DeleteUsuarioCommand(1);
        UsuarioId id = new UsuarioId(1);
        Usuario usuario = createMockUsuario(id);

        when(repositoryPort.findById(id)).thenReturn(usuario);

        // When
        service.execute(command);

        // Then - Solo verificar el comportamiento (evento de dominio), no la implementación
        assertFalse(usuario.getDomainEvents().isEmpty());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el usuario no existe")
    void shouldThrowExceptionWhenUsuarioNotFound() {
        // Given
        DeleteUsuarioCommand command = new DeleteUsuarioCommand(999);
        UsuarioId id = new UsuarioId(999);

        when(repositoryPort.findById(id)).thenReturn(null);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.execute(command));

        assertEquals("Usuario no encontrado con ID: 999", exception.getMessage());
    }

    private Usuario createMockUsuario(UsuarioId id) {
        return new Usuario(
            id,
            new UsuarioNombre("Test User"),
            new UsuarioRol("USER"),
            new UsuarioClave("password")
        );
    }
}

