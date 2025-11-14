package co.edu.udec.ingsw.activity.application.usuario.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import co.edu.udec.ingsw.activity.application.usuario.dto.query.GetUsuarioByIdQuery;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.UsuarioResponse;
import co.edu.udec.ingsw.activity.application.usuario.mapper.UsuarioMapper;
import co.edu.udec.ingsw.activity.application.usuario.port.out.UsuarioRepositoryPort;
import co.edu.udec.ingsw.activity.domain.usuario.entity.Usuario;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests de GetByIdUsuarioService")
class GetByIdUsuarioServiceTest {

    @Mock
    private UsuarioRepositoryPort repositoryPort;

    @Mock
    private UsuarioMapper mapper;

    private GetByIdUsuarioService service;

    @BeforeEach
    void setUp() {
        service = new GetByIdUsuarioService(repositoryPort, mapper);
    }

    @Test
    @DisplayName("Debería obtener un usuario por ID exitosamente")
    void shouldGetUsuarioByIdSuccessfully() {
        // Given
        GetUsuarioByIdQuery query = new GetUsuarioByIdQuery(1);
        UsuarioId id = new UsuarioId(1);
        Usuario usuario = createMockUsuario(id);
        UsuarioResponse expectedResponse = new UsuarioResponse(
            1, "Juan Pérez", "ADMIN", usuario.getFechaCreacion()
        );

        when(repositoryPort.findById(id)).thenReturn(usuario);
        when(mapper.toResponse(usuario)).thenReturn(expectedResponse);

        // When
        UsuarioResponse response = service.execute(query);

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
        GetUsuarioByIdQuery query = new GetUsuarioByIdQuery(999);
        UsuarioId id = new UsuarioId(999);

        when(repositoryPort.findById(id)).thenReturn(null);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.execute(query));

        assertEquals("Usuario no encontrado con ID: 999", exception.getMessage());
    }

    private Usuario createMockUsuario(UsuarioId id) {
        return new Usuario(
            id,
            new UsuarioNombre("Juan Pérez"),
            new UsuarioRol("ADMIN"),
            new UsuarioClave("password")
        );
    }
}

