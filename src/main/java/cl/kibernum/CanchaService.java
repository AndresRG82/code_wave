package cl.kibernum;

public class CanchaService {
    public Cancha registrar(String nombre, String deporte, String horarioDisponible) {
        if (nombre == null || deporte == null || horarioDisponible == null) {
            throw new IllegalArgumentException("Datos inválidos para registrar cancha");
        }
        return new Cancha(nombre, deporte, horarioDisponible);
    }
}