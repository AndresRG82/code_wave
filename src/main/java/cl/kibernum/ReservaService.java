package cl.kibernum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class ReservaService {
    private final List<Reserva> reservas = new ArrayList<>();

    public Reserva reservar(Cancha cancha, LocalDate fecha, LocalTime hora, String usuario) {
        Reserva nueva = new Reserva(cancha, fecha, hora, usuario);
        if (existeReserva(nueva)) {
            throw new IllegalStateException("La cancha ya está reservada para esa fecha y hora");
        }
        reservas.add(nueva);
        return nueva;
    }

    public boolean cancelarReserva(Reserva reserva) {
        return reservas.remove(reserva);
    }

    public Reserva modificarReserva(Reserva original, String nuevoUsuario) {
        if (!reservas.remove(original)) {
            throw new IllegalStateException("La reserva original no existe");
        }
        Reserva modificada = new Reserva(original.getCancha(), original.getFecha(), original.getHora(), nuevoUsuario);
        reservas.add(modificada);
        return modificada;
    }

    public long contarReservasPorDia(LocalDate fecha) {
        return reservas.stream().filter(r -> r.getFecha().equals(fecha)).count();
    }

    private boolean existeReserva(Reserva reserva) {
        return reservas.stream().anyMatch(r -> r.esIgual(reserva));
    }
}