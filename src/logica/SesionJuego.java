package logica;
import datos.Dificultad;
/**
 * Controla el estado de una sesión de juego completa de un jugador:
 * acumula puntuación entre partidas consecutivas y lleva el contador
 * de partidas jugadas en la misma dificultad.
 *
 * Reglas del enunciado:
 *  - Reiniciar → puntaje vuelve a 0, se puede cambiar dificultad.
 *  - Continuar → puntaje se suma al anterior, misma dificultad,
 *                se incrementa el contador de partidas consecutivas.
 */
public class SesionJuego {

    private final String    nickname;
    private Dificultad      dificultad;
    private int             puntuacionAcumulada;
    private int             partidasConsecutivas;

    // Tiempo de la última partida completada (segundos)
    private long            ultimoTiempo;

    public SesionJuego(String nickname, Dificultad dificultad) {
        this.nickname             = nickname;
        this.dificultad           = dificultad;
        this.puntuacionAcumulada  = 0;
        this.partidasConsecutivas = 0;
        this.ultimoTiempo         = 0;
    }

    // ── Acciones ─────────────────────────────────────────────────────

    /**
     * Llama al terminar cada tablero.
     * @param parejas   número de parejas encontradas en esa partida
     * @param segundos  tiempo empleado en esa partida
     * @return puntos ganados en esta partida
     */
    public int completarPartida(int parejas, long segundos) {
        int puntosPartida = parejas * dificultad.getPuntosPorPareja();
        puntuacionAcumulada  += puntosPartida;
        partidasConsecutivas += 1;
        ultimoTiempo          = segundos;

        // Guardar en el ranking global
        GestorJugadores.get().registrarPartida(
            nickname, puntuacionAcumulada, segundos, dificultad.getNombre()
        );

        return puntosPartida;
    }

    /**
     * Reinicia la sesión (puntaje = 0) pero conserva el nickname.
     * Permite cambiar de dificultad.
     */
    public void reiniciar(Dificultad nuevaDificultad) {
        this.dificultad           = nuevaDificultad;
        this.puntuacionAcumulada  = 0;
        this.partidasConsecutivas = 0;
        this.ultimoTiempo         = 0;
    }

    /**
     * Continuar: solo reinicia el tablero, el puntaje se acumula.
     * La dificultad no cambia.
     */
    public void continuar() {
        // El tablero se reinicia desde fuera; aquí solo confirmamos
        // que la dificultad permanece igual (no hace falta código extra,
        // pero el método existe para que la GUI lo llame explícitamente).
    }

    // ── Getters ──────────────────────────────────────────────────────

    public String    getNickname()            { return nickname; }
    public Dificultad getDificultad()         { return dificultad; }
    public int       getPuntuacionAcumulada() { return puntuacionAcumulada; }
    public int       getPartidasConsecutivas(){ return partidasConsecutivas; }
    public long      getUltimoTiempo()        { return ultimoTiempo; }
}