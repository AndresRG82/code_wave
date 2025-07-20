package cl.kibernum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class ReservaService {
    private final List<Reserva> reservas = new ArrayList<>();

    public Reserva reservar(Cancha cancha, LocalDate fecha, LocalTime hora, String usuario) {
       
        boolean yaReservada = reservas.stream().anyMatch(r ->
            r.getCancha().equals(cancha) &&
            r.getFecha().equals(fecha) &&
            r.getHora().equals(hora)
        );

        if (yaReservada) {
            throw new IllegalStateException("La cancha ya está reservada para esa fecha y hora");
        }

        Reserva nueva = new Reserva(cancha, fecha, hora, usuario);
        reservas.add(nueva);
        return nueva;
    }
    public boolean cancelarReserva(Reserva reserva) {
        return reservas.remove(reserva);
    }
    public Reserva modificarReserva(Reserva original, String nuevoUsuario) {
        reservas.remove(original);
        Reserva nueva = new Reserva(original.getCancha(), original.getFecha(), original.getHora(), nuevoUsuario);
        reservas.add(nueva);
        return nueva;
    }
    public long contarReservasPorDia(LocalDate fecha) {
        return reservas.stream().filter(r -> r.getFecha().equals(fecha)).count();
    }
}