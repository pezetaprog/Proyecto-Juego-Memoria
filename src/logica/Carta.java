package logica;

public class Carta {
    private int idPareja;
    private int imagenIndex;
    private boolean volteada;
    private boolean emparejada;

    public Carta(int idPareja) {
        this.idPareja = idPareja;
        this.imagenIndex = idPareja; 
        this.volteada = false;
        this.emparejada = false;
    }

    public Carta(int idPareja, int imagenIndex) {
        this.idPareja = idPareja;
        this.imagenIndex = imagenIndex;
        this.volteada = false;
        this.emparejada = false;
    }

    public int getIdPareja() { return idPareja; }
    public int getImagenIndex() { return imagenIndex; }
    public boolean estaVolteada() { return volteada; }
    public boolean estaEmparejada() { return emparejada; }

    public void voltear() { volteada = !volteada; }
    public void marcarComoEmparejada() { emparejada = true; }
    public void reiniciar() {
        volteada = false;
        emparejada = false;
    }
}
