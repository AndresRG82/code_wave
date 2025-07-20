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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cancha cancha = (Cancha) o;
        return Objects.equals(nombre, cancha.nombre) &&
               Objects.equals(deporte, cancha.deporte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, deporte);
    }


}
