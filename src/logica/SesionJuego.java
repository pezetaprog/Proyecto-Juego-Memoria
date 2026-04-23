package logica;
import datos.Dificultad;

public class SesionJuego {

    private final String    nickname;
    private Dificultad      dificultad;
    private int             puntuacionAcumulada;
    private int             partidasConsecutivas;


    private long            ultimoTiempo;

    public SesionJuego(String nickname, Dificultad dificultad) {
        this.nickname             = nickname;
        this.dificultad           = dificultad;
        this.puntuacionAcumulada  = 0;
        this.partidasConsecutivas = 0;
        this.ultimoTiempo         = 0;
    }


    public int completarPartida(int parejas, long segundos) {
        int puntosPartida = parejas * dificultad.getPuntosPorPareja();
        puntuacionAcumulada  += puntosPartida;
        partidasConsecutivas += 1;
        ultimoTiempo          = segundos;

        
        GestorJugadores.get().registrarPartida(
            nickname, puntuacionAcumulada, segundos, dificultad.getNombre()
        );

        return puntosPartida;
    }

    
    public void reiniciar(Dificultad nuevaDificultad) {
        this.dificultad           = nuevaDificultad;
        this.puntuacionAcumulada  = 0;
        this.partidasConsecutivas = 0;
        this.ultimoTiempo         = 0;
    }

  
    public void continuar() {
       
    }

    

    public String    getNickname()            { return nickname; }
    public Dificultad getDificultad()         { return dificultad; }
    public int       getPuntuacionAcumulada() { return puntuacionAcumulada; }
    public int       getPartidasConsecutivas(){ return partidasConsecutivas; }
    public long      getUltimoTiempo()        { return ultimoTiempo; }
}