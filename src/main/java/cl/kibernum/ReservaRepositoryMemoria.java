package cl.kibernum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaRepositoryMemoria implements ReservaRepository {
    private final List<Reserva> reservas = new ArrayList<>();

    @Override
    public void guardar(Reserva reserva) {
        reservas.add(reserva);
    }

    @Override
    public boolean eliminar(Reserva reserva) {
        return reservas.remove(reserva);
    }

    @Override
    public long contarPorFecha(LocalDate fecha) {
        return reservas.stream().filter(r -> r.getFecha().equals(fecha)).count();
    }

    public boolean yaExiste(Reserva reserva) {
        return reservas.stream().anyMatch(r ->
            r.getCancha().equals(reserva.getCancha()) &&
            r.getFecha().equals(reserva.getFecha()) &&
            r.getHora().equals(reserva.getHora())
        );
    }
}
