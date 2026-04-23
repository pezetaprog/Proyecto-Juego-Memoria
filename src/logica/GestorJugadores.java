package logica;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Singleton que vive durante toda la sesión
public class GestorJugadores {

    public static class Entrada {
        public final String nombre;
        public final int puntuacion;

        public Entrada(String nombre, int puntuacion) {
            this.nombre = nombre;
            this.puntuacion = puntuacion;
        }
    }

    private static GestorJugadores instancia;
    private final List<Entrada> ranking = new ArrayList<>();

    private GestorJugadores() {}

    public static GestorJugadores get() {
        if (instancia == null) instancia = new GestorJugadores();
        return instancia;
    }

    // Registrar resultado al terminar una partida
    public void registrar(String nombre, int puntuacion) {
        ranking.add(new Entrada(nombre, puntuacion));
    }

    // Devuelve el ranking ordenado de mayor a menor puntuación
    public List<Entrada> getRanking() {
        List<Entrada> ordenado = new ArrayList<>(ranking);
        ordenado.sort(Comparator.comparingInt((Entrada e) -> e.puntuacion).reversed());
        return ordenado;
    }
}