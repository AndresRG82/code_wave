package cl.kibernum;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
    private final Cancha cancha;
    private final LocalDate fecha;
    private final LocalTime hora;
    private final String usuario;

    public Reserva(Cancha cancha, LocalDate fecha, LocalTime hora, String usuario) {
        this.cancha = cancha;
        this.fecha = fecha;
        this.hora = hora;
        this.usuario = usuario;
    }

    public Cancha getCancha() { return cancha; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }
    public String getUsuario() { return usuario; }
}