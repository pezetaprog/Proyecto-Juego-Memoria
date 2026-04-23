package logica;

import  administrador.UtilidadArchivos;

import java.util.*;
import java.util.stream.Collectors;

public class GestorJugadores {

    private static final int TOP_POR_DIFICULTAD = 10;

    private static GestorJugadores instancia;

    private final Set<String>          nicknames = new HashSet<>();
    private final List<EntradaRanking> ranking   = new ArrayList<>();

    private GestorJugadores() {
        cargarDatos(); // 🔥 carga al iniciar
    }

    public static GestorJugadores get() {
        if (instancia == null) instancia = new GestorJugadores();
        return instancia;
    }

    // ==============================
    // CARGA INICIAL
    // ==============================
    private void cargarDatos() {
        try {
            ranking.addAll(UtilidadArchivos.cargarRanking());
        } catch (Exception ignored) {}

        try {
            nicknames.addAll(UtilidadArchivos.cargarUsuarios());
        } catch (Exception ignored) {}
    }

    // ==============================
    // USUARIOS
    // ==============================
    public boolean registrarNickname(String nickname) {
        if (nickname == null || nickname.isBlank()) return false;

        boolean agregado = nicknames.add(nickname.trim());

        if (agregado) {
            UtilidadArchivos.guardarUsuarios(new ArrayList<>(nicknames)); // 🔥 guarda
        }

        return agregado;
    }

    public boolean nicknameExiste(String nickname) {
        return nicknames.contains(nickname == null ? "" : nickname.trim());
    }

    public Set<String> getNicknames() {
        return Collections.unmodifiableSet(nicknames);
    }

    // ==============================
    // RANKING
    // ==============================
    public void registrarPartida(String nickname, int puntuacion,
                                  long tiempoSegundos, String nombreDificultad) {

        ranking.add(new EntradaRanking(nickname, puntuacion,
                                       tiempoSegundos, nombreDificultad));

        Collections.sort(ranking); // 🔥 ordena

        UtilidadArchivos.guardarRanking(ranking); // 🔥 guarda
    }

    public List<EntradaRanking> getRankingPorDificultad(String nombreDificultad) {
        return ranking.stream()
            .filter(e -> e.getNombreDificultad().equalsIgnoreCase(nombreDificultad))
            .sorted()
            .limit(TOP_POR_DIFICULTAD)
            .collect(Collectors.toList());
    }

    public List<String> getDificultadesConEntradas() {
        return ranking.stream()
            .map(EntradaRanking::getNombreDificultad)
            .distinct()
            .collect(Collectors.toList());
    }
}