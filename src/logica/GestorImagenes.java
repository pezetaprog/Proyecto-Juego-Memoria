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

   
    public String getRuta(int indice) {
        return rutas.get(indice);
    }
    public void cargarDesdeRecursos(String paquete) {
    // paquete ejemplo: "recursos" (sin slashes)
    String ruta = "/" + paquete;
    try {
        // Leer el contenido del package como stream de texto
        java.io.InputStream is = getClass().getResourceAsStream(ruta);
        if (is == null) {
            System.err.println("Package no encontrado: " + ruta);
            return;
        }

        // El stream contiene los nombres de archivos separados por línea
        java.io.BufferedReader br = new java.io.BufferedReader(
            new java.io.InputStreamReader(is)
        );

        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.toLowerCase().endsWith(".png") ||
                linea.toLowerCase().endsWith(".jpg")) {
                agregarImagen(ruta + "/" + linea);
            }
        }
        System.out.println("Imágenes cargadas: " + rutas);

    } catch (Exception e) {
        System.err.println("Error: " + e.getMessage());
    }
}
}