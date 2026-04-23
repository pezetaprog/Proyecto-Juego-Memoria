package datos;

public class Dificultad {
    private final String nombre;
    private final int filas;
    private final int columnas;
    private final int puntosPorPareja;

    public Dificultad(String nombre, int filas, int columnas, int puntosPorPareja) {
        if ((filas * columnas) % 2 != 0)
            throw new IllegalArgumentException("El tablero debe tener un número par de cartas");
        this.nombre = nombre;
        this.filas = filas;
        this.columnas = columnas;
        this.puntosPorPareja = puntosPorPareja;
    }

    public String getNombre()        { return nombre; }
    public int getFilas()            { return filas; }
    public int getColumnas()         { return columnas; }
    public int getPuntosPorPareja()  { return puntosPorPareja; }
    public int getTotalParejas()     { return (filas * columnas) / 2; }

    @Override
    public String toString() { return nombre; }
}