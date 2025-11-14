package co.edu.udec.ingsw.activity.application.carreraTaxi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.command.CreateCarreraTaxiCommand;
import co.edu.udec.ingsw.activity.application.carreraTaxi.dto.response.CarreraTaxiResponse;
import co.edu.udec.ingsw.activity.application.carreraTaxi.mapper.CarreraTaxiMapper;
import co.edu.udec.ingsw.activity.application.carreraTaxi.port.out.CarreraTaxiRepositoryPort;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.entity.CarreraTaxi;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests de CreateCarreraTaxiService")
class CreateCarreraTaxiServiceTest {

    @Mock
    private CarreraTaxiRepositoryPort repositoryPort;

    @Mock
    private CarreraTaxiMapper mapper;

    private CreateCarreraTaxiService service;

    @BeforeEach
    void setUp() {
        service = new CreateCarreraTaxiService(repositoryPort, mapper);
    }

    @Test
    @DisplayName("Debería crear una carrera de taxi exitosamente")
    void shouldCreateCarreraTaxiSuccessfully() {
        // Given
        CreateCarreraTaxiCommand command = createValidCommand();
        CarreraTaxiId generatedId = new CarreraTaxiId(1);
        CarreraTaxi carrera = createMockCarreraTaxi(generatedId);
        CarreraTaxiResponse expectedResponse = new CarreraTaxiResponse(
            1, "Juan Pérez", "ABC123", "Carlos López", 10.5, 
            "Centro", "Norte", 2, 15000.0, 30, carrera.getFechaCreacion()
        );

        when(repositoryPort.nextId()).thenReturn(generatedId);
        when(mapper.toEntity(command, 1)).thenReturn(carrera);
        when(mapper.toResponse(carrera)).thenReturn(expectedResponse);

        // When
        CarreraTaxiResponse response = service.execute(command);

        // Then - Solo verificar el comportamiento, no la implementación
        assertNotNull(response);
        assertEquals(expectedResponse.id(), response.id());
        assertEquals(expectedResponse.cliente(), response.cliente());
        assertEquals(expectedResponse.taxi(), response.taxi());
        assertEquals(expectedResponse.precio(), response.precio());
    }

    private CreateCarreraTaxiCommand createValidCommand() {
        return new CreateCarreraTaxiCommand(
            "Juan Pérez", "ABC123", "Carlos López", 10.5,
            "Centro", "Norte", 2, 15000.0, 30
        );
    }

    private CarreraTaxi createMockCarreraTaxi(CarreraTaxiId id) {
        return new CarreraTaxi(
            id,
            new CarreraTaxiCliente("Juan Pérez"),
            new CarreraTaxiTaxi("ABC123"),
            new CarreraTaxiTaxista("Carlos López"),
            new CarreraTaxiKilometros(10.5),
            new CarreraTaxiBarrioInicio("Centro"),
            new CarreraTaxiBarrioLlegada("Norte"),
            new CarreraTaxiCantidadPasajeros(2),
            new CarreraTaxiPrecio(15000.0),
            new CarreraTaxiDuracionMinutos(30)
        );
    }

}

