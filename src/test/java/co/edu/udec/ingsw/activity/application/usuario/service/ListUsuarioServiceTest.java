package co.edu.udec.ingsw.activity.application.usuario.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import co.edu.udec.ingsw.activity.application.usuario.dto.query.ListUsuarioQuery;
import co.edu.udec.ingsw.activity.application.usuario.dto.response.UsuarioListResponse;
import co.edu.udec.ingsw.activity.application.usuario.mapper.UsuarioMapper;
import co.edu.udec.ingsw.activity.application.usuario.port.out.UsuarioRepositoryPort;
import co.edu.udec.ingsw.activity.domain.usuario.entity.Usuario;
import co.edu.udec.ingsw.activity.domain.usuario.valueobject.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests de ListUsuarioService")
class ListUsuarioServiceTest {

    @Mock
    private UsuarioRepositoryPort repositoryPort;

    @Mock
    private UsuarioMapper mapper;

    private ListUsuarioService service;

    @BeforeEach
    void setUp() {
        service = new ListUsuarioService(repositoryPort, mapper);
    }

    @Test
    @DisplayName("Debería listar todos los usuarios exitosamente")
    void shouldListAllUsuariosSuccessfully() {
        // Given
        ListUsuarioQuery query = new ListUsuarioQuery();
        Usuario usuario1 = createMockUsuario(new UsuarioId(1), "Juan Pérez", "ADMIN");
        Usuario usuario2 = createMockUsuario(new UsuarioId(2), "María García", "USER");
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);
        UsuarioListResponse expectedResponse = new UsuarioListResponse(
            Arrays.asList(
                new co.edu.udec.ingsw.activity.application.usuario.dto.response.UsuarioResponse(1, "Juan Pérez", "ADMIN", null),
                new co.edu.udec.ingsw.activity.application.usuario.dto.response.UsuarioResponse(2, "María García", "USER", null)
            )
        );

        when(repositoryPort.findAll()).thenReturn(usuarios);
        when(mapper.toListResponse(usuarios)).thenReturn(expectedResponse);

        // When
        UsuarioListResponse response = service.execute(query);

        // Then - Solo verificar el comportamiento, no la implementación
        assertNotNull(response);
        assertEquals(2, response.usuarios().size());
        assertEquals("Juan Pérez", response.usuarios().get(0).nombre());
        assertEquals("María García", response.usuarios().get(1).nombre());
    }

    @Test
    @DisplayName("Debería retornar lista vacía cuando no hay usuarios")
    void shouldReturnEmptyListWhenNoUsuarios() {
        // Given
        ListUsuarioQuery query = new ListUsuarioQuery();
        List<Usuario> usuarios = List.of();
        UsuarioListResponse expectedResponse = new UsuarioListResponse(List.of());

        when(repositoryPort.findAll()).thenReturn(usuarios);
        when(mapper.toListResponse(usuarios)).thenReturn(expectedResponse);

        // When
        UsuarioListResponse response = service.execute(query);

        // Then - Solo verificar el comportamiento, no la implementación
        assertNotNull(response);
        assertTrue(response.usuarios().isEmpty());
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

