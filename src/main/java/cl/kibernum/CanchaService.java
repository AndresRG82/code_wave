package cl.kibernum;

public class CanchaService {

    public Cancha registrar(String nombre, String deporte, String horario) {
        return new Cancha(nombre, deporte, horario);
    }

}
