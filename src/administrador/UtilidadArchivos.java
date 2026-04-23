package administrador;
import logica.EntradaRanking;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UtilidadArchivos {

    private static final String CARPETA = "datos/";
    private static final String ARCHIVO_RANKING = CARPETA + "ranking.dat";
    private static final String ARCHIVO_USUARIOS = CARPETA + "usuarios.dat";

    static {
        File dir = new File(CARPETA);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static <T> void guardar(T objeto, String ruta) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(objeto);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T leer(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return (T) ois.readObject();
        }
    }

  
    public static void guardarRanking(List<EntradaRanking> ranking) {
        try {
            guardar(ranking, ARCHIVO_RANKING);
        } catch (IOException e) {
            System.out.println("Error guardando ranking: " + e.getMessage());
        }
    }

    public static List<EntradaRanking> cargarRanking() {
        try {
            return leer(ARCHIVO_RANKING);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    
    public static void guardarUsuarios(List<String> usuarios) {
        try {
            guardar(usuarios, ARCHIVO_USUARIOS);
        } catch (IOException e) {
            System.out.println("Error guardando usuarios: " + e.getMessage());
        }
    }

    public static List<String> cargarUsuarios() {
        try {
            return leer(ARCHIVO_USUARIOS);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}