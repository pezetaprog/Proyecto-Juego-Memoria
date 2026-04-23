package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GestorImagenes {

    private static GestorImagenes instancia;
    private final List<String> rutas = new ArrayList<>();

    private GestorImagenes() {}

    public static GestorImagenes get() {
        if (instancia == null) instancia = new GestorImagenes();
        return instancia;
    }

    // ── Administración ───────────────────────────────────────────────

    public void agregarImagen(String ruta) {
        if (ruta != null && !ruta.isBlank() && !rutas.contains(ruta))
            rutas.add(ruta);
    }

    public void eliminarImagen(String ruta) {
        rutas.remove(ruta);
    }

    public List<String> getRutas() {
        return Collections.unmodifiableList(rutas);
    }

    public int totalImagenes() { return rutas.size(); }

    // ── Uso en juego ─────────────────────────────────────────────────

    /**
     * Devuelve 'cantidad' índices al azar (sin repetición) de las imágenes
     * disponibles. La GUI los usa para construir el Tablero.
     *
     * @throws IllegalStateException si no hay suficientes imágenes cargadas
     */
    public int[] seleccionarAleatorios(int cantidad) {
        if (rutas.size() < cantidad)
            throw new IllegalStateException(
                "Se necesitan al menos " + cantidad + " imágenes. " +
                "Hay " + rutas.size() + " cargadas.");

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < rutas.size(); i++) indices.add(i);
        Collections.shuffle(indices);

        int[] resultado = new int[cantidad];
        for (int i = 0; i < cantidad; i++) resultado[i] = indices.get(i);
        return resultado;
    }

    /**
     * Devuelve la ruta de una imagen por su índice.
     */
    public String getRuta(int indice) {
        return rutas.get(indice);
    }
}