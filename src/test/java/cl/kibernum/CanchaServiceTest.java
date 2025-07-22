package cl.kibernum;  // o el paquete que uses

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.LocalTime;

public class CanchaServiceTest {
   
   @Test
    void deberiaRegistrarYGuardarCancha() {
        // Arrange
        CanchaRepository mockRepo = mock(CanchaRepository.class);
        CanchaService service = new CanchaService(mockRepo);

        // Act
        Cancha cancha = service.registrar("Cancha 1", "Fútbol", "08:00-20:00");

        // Assert
        ArgumentCaptor<Cancha> captor = ArgumentCaptor.forClass(Cancha.class);
        verify(mockRepo).guardar(captor.capture());

        Cancha capturada = captor.getValue();
        assertEquals("Cancha 1", capturada.getNombre());
        assertEquals("Fútbol", capturada.getDeporte());
        assertEquals("08:00-20:00", capturada.getHorarioDisponible());
        assertEquals(cancha, capturada); // verifica que sea la misma instancia
    }
    @Test
    void deberiaCrearReservaParaUnaCanchaDisponible() {
        Cancha cancha = new Cancha("Cancha 1", "Fútbol", "08:00-20:00");
        ReservaService service = new ReservaService();

        LocalDate fecha = LocalDate.of(2025, 7, 19);
        LocalTime hora = LocalTime.of(10, 0);

        Reserva reserva = service.reservar(cancha, fecha, hora, "Pedro");

        assertEquals("Pedro", reserva.getUsuario());
        assertEquals(cancha, reserva.getCancha());
        assertEquals(fecha, reserva.getFecha());
        assertEquals(hora, reserva.getHora());
    }
    @Test
    void deberiaCancelarReservaExistente() {
        Cancha cancha = new Cancha("Cancha 1", "Fútbol", "08:00-20:00");
        ReservaService service = new ReservaService();
        LocalDate fecha = LocalDate.of(2025, 7, 19);
        LocalTime hora = LocalTime.of(10, 0);

        Reserva reserva = service.reservar(cancha, fecha, hora, "Pedro");
        boolean resultado = service.cancelarReserva(reserva);

        assertTrue(resultado);

    }
    @Test
    void deberiaModificarUsuarioDeReserva() {
        Cancha cancha = new Cancha("Cancha 1", "Fútbol", "08:00-20:00");
        ReservaService service = new ReservaService();
        LocalDate fecha = LocalDate.of(2025, 7, 19);
        LocalTime hora = LocalTime.of(10, 0);

        Reserva original = service.reservar(cancha, fecha, hora, "Pedro");
        Reserva modificada = service.modificarReserva(original, "Juan");

        assertEquals("Juan", modificada.getUsuario());
    }    
    @Test
    void deberiaContarReservasPorDia() {
        Cancha cancha = new Cancha("Cancha 1", "Fútbol", "08:00-20:00");
        ReservaService service = new ReservaService();
        LocalDate fecha = LocalDate.of(2025, 7, 19);

        service.reservar(cancha, fecha, LocalTime.of(9, 0), "A");
        service.reservar(cancha, fecha, LocalTime.of(10, 0), "B");

        assertEquals(2, service.contarReservasPorDia(fecha));
    }
    @Test
    void deberiaValidarDuplicadoDeReserva() {
        Cancha cancha = new Cancha("Cancha 1", "Fútbol", "08:00-20:00");
        ReservaService service = new ReservaService();
        LocalDate fecha = LocalDate.of(2025, 7, 19);
        LocalTime hora = LocalTime.of(10, 0);

        service.reservar(cancha, fecha, hora, "Pedro");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.reservar(cancha, fecha, hora, "Juan");
        });

        assertEquals("La cancha ya está reservada para esa fecha y hora", exception.getMessage());
    }

}
