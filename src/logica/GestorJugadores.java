package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class GestorJugadores {

    private static final int TOP_POR_DIFICULTAD = 10;

    private static GestorJugadores instancia;

    private final Set<String>          nicknames = new HashSet<>();
    private final List<EntradaRanking> ranking   = new ArrayList<>();

    private GestorJugadores() {}

    public static GestorJugadores get() {
        if (instancia == null) instancia = new GestorJugadores();
        return instancia;
    }

   
    public boolean registrarNickname(String nickname) {
        if (nickname == null || nickname.isBlank()) return false;
        return nicknames.add(nickname.trim());
    }

    public boolean nicknameExiste(String nickname) {
        return nicknames.contains(nickname == null ? "" : nickname.trim());
    }

    public void registrarPartida(String nickname, int puntuacion,
                                  long tiempoSegundos, String nombreDificultad) {
        ranking.add(new EntradaRanking(nickname, puntuacion,
                                       tiempoSegundos, nombreDificultad));
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

public Set<String> getNicknames() {
    return Collections.unmodifiableSet(nicknames);
}
}