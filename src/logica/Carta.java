package logica;

public class Carta {
    private final int idPareja;
    private boolean volteada;
    private boolean emparejada;

    public Carta(int idPareja) {
        this.idPareja = idPareja;
        this.volteada = false;
        this.emparejada = false;
    }

    public int getIdPareja() { return idPareja; }
    public boolean estaVolteada() { return volteada; }
    public boolean estaEmparejada() { return emparejada; }

    public void voltear() {
        if (!emparejada) {
            volteada = !volteada;
        }
    }

    public void marcarComoEmparejada() {
        emparejada = true;
        volteada = true;
    }

    public void reiniciar() {
        volteada = false;
        emparejada = false;
    }
}