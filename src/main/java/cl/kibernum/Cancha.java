package cl.kibernum;

import java.util.Objects;

public class Cancha {
    private final String nombre;
    private final String deporte;
    private final String horarioDisponible;

    public Cancha(String nombre, String deporte, String horarioDisponible) {
        this.nombre = nombre;
        this.deporte = deporte;
        this.horarioDisponible = horarioDisponible;
    }

    public String getNombre() { return nombre; }
    public String getDeporte() { return deporte; }
    public String getHorarioDisponible() { return horarioDisponible; }

    public boolean esMismaCancha(Cancha otra) {
        return this.nombre.equals(otra.nombre) && this.deporte.equals(otra.deporte);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cancha cancha)) return false;
        return esMismaCancha(cancha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, deporte);
    }
}
