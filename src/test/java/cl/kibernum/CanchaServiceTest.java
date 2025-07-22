package cl.kibernum;  // o el paquete que uses

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class CanchaServiceTest {
    @Test
    void deberiaRegistrarCancha() {
        CanchaService service = new CanchaService();
        Cancha cancha = service.registrar("Cancha 1", "Fútbol", "08:00-20:00");

        assertEquals("Cancha 1", cancha.getNombre());
        assertEquals("Fútbol", cancha.getDeporte());
        assertEquals("08:00-20:00", cancha.getHorarioDisponible());
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



}
