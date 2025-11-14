package co.edu.udec.ingsw.activity.application.usuario.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import co.edu.udec.ingsw.activity.application.usuario.dto.command.CreateUsuarioCommand;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.UsuarioResponse;
import co.edu.udec.ingsw.activity.application.usuario.mapper.UsuarioMapper;
import co.edu.udec.ingsw.activity.application.usuario.port.out.UsuarioRepositoryPort;
import co.edu.udec.ingsw.activity.domain.usuario.entity.Usuario;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests de CreateUsuarioService")
class CreateUsuarioServiceTest {

    @Mock
    private UsuarioRepositoryPort repositoryPort;

    @Mock
    private UsuarioMapper mapper;

    private CreateUsuarioService service;

    @BeforeEach
    void setUp() {
        service = new CreateUsuarioService(repositoryPort, mapper);
    }

    @Test
    @DisplayName("Debería crear un usuario exitosamente")
    void shouldCreateUsuarioSuccessfully() {
        // Given
        CreateUsuarioCommand command = new CreateUsuarioCommand(
            "Juan Pérez", "ADMIN", "password123"
        );
        UsuarioId generatedId = new UsuarioId(1);
        Usuario usuario = createMockUsuario(generatedId);
        UsuarioResponse expectedResponse = new UsuarioResponse(
            1, "Juan Pérez", "ADMIN", usuario.getFechaCreacion()
        );

        when(repositoryPort.nextId()).thenReturn(generatedId);
        when(mapper.toEntity(command, 1)).thenReturn(usuario);
        when(mapper.toResponse(usuario)).thenReturn(expectedResponse);

        // When
        UsuarioResponse response = service.execute(command);

        // Then - Solo verificar el comportamiento, no la implementación
        assertNotNull(response);
        assertEquals(expectedResponse.id(), response.id());
        assertEquals(expectedResponse.nombre(), response.nombre());
        assertEquals(expectedResponse.rol(), response.rol());
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

