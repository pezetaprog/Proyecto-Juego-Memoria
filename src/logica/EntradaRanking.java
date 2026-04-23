package logica;

public class EntradaRanking implements Comparable<EntradaRanking> {
    private final String nickname;
    private final int    puntuacion;
    private final long   tiempoSegundos;
    private final String nombreDificultad;

    public EntradaRanking(String nickname, int puntuacion,
                          long tiempoSegundos, String nombreDificultad) {
        this.nickname          = nickname;
        this.puntuacion        = puntuacion;
        this.tiempoSegundos    = tiempoSegundos;
        this.nombreDificultad  = nombreDificultad;
    }

    public String getNickname()         { return nickname; }
    public int    getPuntuacion()        { return puntuacion; }
    public long   getTiempoSegundos()    { return tiempoSegundos; }
    public String getNombreDificultad()  { return nombreDificultad; }


    public String getTiempoFormateado() {
        long min = tiempoSegundos / 60;
        long seg = tiempoSegundos % 60;
        return String.format("%02d:%02d", min, seg);
    }

  
    @Override
    public int compareTo(EntradaRanking otro) {
        if (this.puntuacion != otro.puntuacion)
            return Integer.compare(otro.puntuacion, this.puntuacion);
        return Long.compare(this.tiempoSegundos, otro.tiempoSegundos);
    }
}