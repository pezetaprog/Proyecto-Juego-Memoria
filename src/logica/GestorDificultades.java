package logica;
import datos.Dificultad;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GestorDificultades {

    private static GestorDificultades instancia;
    private final List<Dificultad> dificultades = new ArrayList<>();

    private GestorDificultades() {
       
        dificultades.add(new Dificultad("Fácil",       2, 4, 10));
        dificultades.add(new Dificultad("Medio",       4, 4, 20));
        dificultades.add(new Dificultad("Difícil",     4, 6, 30));
        dificultades.add(new Dificultad("Experto",     6, 6, 50));
    }

    public static GestorDificultades get() {
        if (instancia == null) instancia = new GestorDificultades();
        return instancia;
    }

    
    public List<Dificultad> getDificultades() {
        return Collections.unmodifiableList(dificultades);
    }

   
    public void agregarDificultad(Dificultad d) {
        dificultades.add(d);
    }

   
    public boolean eliminarDificultad(String nombre) {
        if (dificultades.size() <= 4) return false;
        return dificultades.removeIf(d -> d.getNombre().equalsIgnoreCase(nombre));
    }

   
    public Dificultad getDificultadMinima() {
        return dificultades.get(0);
    }
}