package co.edu.udec.ingsw.activity.domain.carreraTaxi.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import co.edu.udec.ingsw.activity.domain.carreraTaxi.valueobject.*;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.event.CarreraTaxiCreated;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.event.CarreraTaxiUpdated;
import co.edu.udec.ingsw.activity.domain.carreraTaxi.event.CarreraTaxiDeleted;

@DisplayName("Tests de Entidad CarreraTaxi")
class CarreraTaxiTest {

    @Test
    @DisplayName("Debería crear una carrera de taxi válida y publicar evento CarreraTaxiCreated")
    void shouldCreateCarreraTaxiAndPublishEvent() {
        // Given
        CarreraTaxi carrera = createValidCarreraTaxi();
        
        // Then
        assertNotNull(carrera);
        assertNotNull(carrera.getId());
        assertNotNull(carrera.getCliente());
        assertNotNull(carrera.getTaxi());
        assertNotNull(carrera.getTaxista());
        assertNotNull(carrera.getFechaCreacion());
        
        // Verificar evento de dominio
        var events = carrera.getDomainEvents();
        assertEquals(1, events.size());
        assertInstanceOf(CarreraTaxiCreated.class, events.getFirst());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el ID es nulo")
    void shouldThrowExceptionWhenIdIsNull() {
        // Given
        var validValues = createValidValueObjects();
        
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CarreraTaxi(
            null,
            validValues.cliente(),
            validValues.taxi(),
            validValues.taxista(),
            validValues.kilometros(),
            validValues.barrioInicio(),
            validValues.barrioLlegada(),
            validValues.cantidadPasajeros(),
            validValues.precio(),
            validValues.duracionMinutos()
        ));
        
        assertEquals("El ID no puede ser nulo", exception.getMessage());
    }

    @Test
    @DisplayName("Debería permitir crear carrera con barrios diferentes")
    void shouldAllowCreatingCarreraWithDifferentBarrios() {
        // Given
        var validValues = createValidValueObjects();
        
        // When - Crear con barrios diferentes (esto debería funcionar)
        CarreraTaxi carrera = new CarreraTaxi(
            validValues.id(),
            validValues.cliente(),
            validValues.taxi(),
            validValues.taxista(),
            validValues.kilometros(),
            validValues.barrioInicio(),
            validValues.barrioLlegada(),
            validValues.cantidadPasajeros(),
            validValues.precio(),
            validValues.duracionMinutos()
        );
        
        // Then
        assertNotNull(carrera);
        assertNotEquals(validValues.barrioInicio().value(), validValues.barrioLlegada().value());
    }

    @Test
    @DisplayName("Debería actualizar precio y publicar evento CarreraTaxiUpdated")
    void shouldUpdatePrecioAndPublishEvent() {
        // Given
        CarreraTaxi carrera = createValidCarreraTaxi();
        CarreraTaxiPrecio nuevoPrecio = new CarreraTaxiPrecio(20000.0);
        carrera.clearDomainEvents();
        
        // When
        carrera.actualizarPrecio(nuevoPrecio);
        
        // Then
        assertEquals(nuevoPrecio, carrera.getPrecio());
        
        var events = carrera.getDomainEvents();
        assertEquals(1, events.size());
        assertInstanceOf(CarreraTaxiUpdated.class, events.getFirst());
    }

    @Test
    @DisplayName("Debería lanzar excepción al actualizar con precio nulo")
    void shouldThrowExceptionWhenUpdatingWithNullPrecio() {
        // Given
        CarreraTaxi carrera = createValidCarreraTaxi();
        
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> carrera.actualizarPrecio(null));
        
        assertEquals("El nuevo precio no puede ser nulo", exception.getMessage());
    }

    @Test
    @DisplayName("Debería marcar carrera para eliminar y publicar evento CarreraTaxiDeleted")
    void shouldMarkCarreraForDeletion() {
        // Given
        CarreraTaxi carrera = createValidCarreraTaxi();
        carrera.clearDomainEvents();
        
        // When
        carrera.marcarCarreraParaEliminar();
        
        // Then
        var events = carrera.getDomainEvents();
        assertEquals(1, events.size());
        assertInstanceOf(CarreraTaxiDeleted.class, events.getFirst());
    }

    @Test
    @DisplayName("Debería comparar correctamente dos carreras iguales")
    void shouldCompareEqualCarreras() {
        // Given
        CarreraTaxiId id = new CarreraTaxiId(1);
        CarreraTaxi carrera1 = createCarreraTaxiWithId(id);
        CarreraTaxi carrera2 = createCarreraTaxiWithId(id);
        
        // When & Then
        assertTrue(carrera1.equals(carrera2));
    }

    @Test
    @DisplayName("Debería retornar false al comparar con null")
    void shouldReturnFalseWhenComparingWithNull() {
        // Given
        CarreraTaxi carrera = createValidCarreraTaxi();
        
        // When & Then
        assertFalse(carrera.equals(null));
    }

    @Test
    @DisplayName("Debería limpiar eventos de dominio correctamente")
    void shouldClearDomainEvents() {
        // Given
        CarreraTaxi carrera = createValidCarreraTaxi();
        assertEquals(1, carrera.getDomainEvents().size());
        
        // When
        carrera.clearDomainEvents();
        
        // Then
        assertEquals(0, carrera.getDomainEvents().size());
    }

    private CarreraTaxi createValidCarreraTaxi() {
        var values = createValidValueObjects();
        return new CarreraTaxi(
            values.id(),
            values.cliente(),
            values.taxi(),
            values.taxista(),
            values.kilometros(),
            values.barrioInicio(),
            values.barrioLlegada(),
            values.cantidadPasajeros(),
            values.precio(),
            values.duracionMinutos()
        );
    }

    private CarreraTaxi createCarreraTaxiWithId(CarreraTaxiId id) {
        var values = createValidValueObjects();
        return new CarreraTaxi(
            id,
            values.cliente(),
            values.taxi(),
            values.taxista(),
            values.kilometros(),
            values.barrioInicio(),
            values.barrioLlegada(),
            values.cantidadPasajeros(),
            values.precio(),
            values.duracionMinutos()
        );
    }

    private ValidValueObjects createValidValueObjects() {
        return new ValidValueObjects(
            new CarreraTaxiId(1),
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

    private record ValidValueObjects(
        CarreraTaxiId id,
        CarreraTaxiCliente cliente,
        CarreraTaxiTaxi taxi,
        CarreraTaxiTaxista taxista,
        CarreraTaxiKilometros kilometros,
        CarreraTaxiBarrioInicio barrioInicio,
        CarreraTaxiBarrioLlegada barrioLlegada,
        CarreraTaxiCantidadPasajeros cantidadPasajeros,
        CarreraTaxiPrecio precio,
        CarreraTaxiDuracionMinutos duracionMinutos
    ) {}
}

