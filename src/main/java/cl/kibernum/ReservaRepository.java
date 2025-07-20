package cl.kibernum;

import java.time.LocalDate;

public interface ReservaRepository {
    void guardar(Reserva reserva);
    boolean eliminar(Reserva reserva);
    long contarPorFecha(LocalDate fecha);
}