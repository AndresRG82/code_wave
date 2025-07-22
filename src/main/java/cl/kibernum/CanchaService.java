package cl.kibernum;

public class CanchaService {
    private final CanchaRepository repository;

    public CanchaService(CanchaRepository repository) {
        this.repository = repository;
    }

    public Cancha registrar(String nombre, String deporte, String horarioDisponible) {
        if (nombre == null || deporte == null || horarioDisponible == null) {
            throw new IllegalArgumentException("Datos inválidos para registrar cancha");
        }

        Cancha cancha = new Cancha(nombre, deporte, horarioDisponible);
        repository.guardar(cancha);
        return cancha;
    }
}