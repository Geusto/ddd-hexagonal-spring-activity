package co.edu.udec.ingsw.activity.application.usuario.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import co.edu.udec.ingsw.activity.application.usuario.dto.command.UpdateUsuarioCommand;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.UsuarioResponse;
import co.edu.udec.ingsw.activity.application.usuario.mapper.UsuarioMapper;
import co.edu.udec.ingsw.activity.application.usuario.port.out.UsuarioRepositoryPort;
import co.edu.udec.ingsw.activity.domain.usuario.entity.Usuario;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests de UpdateUsuarioService")
class UpdateUsuarioServiceTest {

    @Mock
    private UsuarioRepositoryPort repositoryPort;

    @Mock
    private UsuarioMapper mapper;

    private UpdateUsuarioService service;

    @BeforeEach
    void setUp() {
        service = new UpdateUsuarioService(repositoryPort, mapper);
    }

    @Test
    @DisplayName("Debería actualizar un usuario exitosamente")
    void shouldUpdateUsuarioSuccessfully() {
        // Given
        UpdateUsuarioCommand command = new UpdateUsuarioCommand(
            1, "Pedro García", "USER", "newPassword"
        );
        UsuarioId id = new UsuarioId(1);
        Usuario usuarioExistente = createMockUsuario(id);
        Usuario usuarioActualizado = createMockUsuario(id, "Pedro García", "USER");
        UsuarioResponse expectedResponse = new UsuarioResponse(
            1, "Pedro García", "USER", usuarioActualizado.getFechaCreacion()
        );

        when(repositoryPort.findById(id)).thenReturn(usuarioExistente);
        when(mapper.toEntity(command, usuarioExistente)).thenReturn(usuarioActualizado);
        when(mapper.toResponse(usuarioActualizado)).thenReturn(expectedResponse);

        // When
        UsuarioResponse response = service.execute(command);

        // Then - Solo verificar el comportamiento, no la implementación
        assertNotNull(response);
        assertEquals(expectedResponse.id(), response.id());
        assertEquals(expectedResponse.nombre(), response.nombre());
        assertEquals(expectedResponse.rol(), response.rol());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el usuario no existe")
    void shouldThrowExceptionWhenUsuarioNotFound() {
        // Given
        UpdateUsuarioCommand command = new UpdateUsuarioCommand(
            1, "Pedro García", "USER", "newPassword"
        );
        UsuarioId id = new UsuarioId(1);

        when(repositoryPort.findById(id)).thenReturn(null);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.execute(command));

        assertEquals("Usuario no encontrado con ID: 1", exception.getMessage());
    }

    private Usuario createMockUsuario(UsuarioId id) {
        return new Usuario(
            id,
            new UsuarioNombre("Test User"),
            new UsuarioRol("USER"),
            new UsuarioClave("password")
        );
    }

    private Usuario createMockUsuario(UsuarioId id, String nombre, String rol) {
        return new Usuario(
            id,
            new UsuarioNombre(nombre),
            new UsuarioRol(rol),
            new UsuarioClave("password")
        );
    }
}

