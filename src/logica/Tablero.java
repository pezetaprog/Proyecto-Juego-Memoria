package logica;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Tablero {
    private Carta[] cartas;
    private int filas, columnas;
    private int primeraSeleccion = -1;
    private int segundaSeleccion = -1;
    private boolean esperando = false;

    
    private int[] imagenesAsignadas;
    private Runnable onActualizarVista;

    public Tablero(int filas, int columnas, int[] indicesImagenes) {
        this.filas = filas;
        this.columnas = columnas;
        int totalCartas = filas * columnas;
        if (totalCartas % 2 != 0)
            throw new IllegalArgumentException("El número total de cartas debe ser par");
        if (indicesImagenes.length < totalCartas / 2)
            throw new IllegalArgumentException("No hay suficientes imágenes para las parejas");

        cartas = new Carta[totalCartas];
        this.imagenesAsignadas = indicesImagenes;
        inicializarCartas();
        barajar();
    }

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        int totalCartas = filas * columnas;
        if (totalCartas % 2 != 0) {
            throw new IllegalArgumentException("El número total de cartas debe ser par");
        }
        cartas = new Carta[totalCartas];
        int[] indices = new int[totalCartas / 2];
        for (int i = 0; i < indices.length; i++) indices[i] = i;
        this.imagenesAsignadas = indices;
        inicializarCartas();
        barajar();
    }

  
    public void setOnActualizarVista(Runnable listener) {
        this.onActualizarVista = listener;
    }

    private void notificarVista() {
        if (onActualizarVista != null) {
            onActualizarVista.run();
        }
    }

    private void inicializarCartas() {
        int index = 0;
        for (int id = 0; id < cartas.length / 2; id++) {
            cartas[index++] = new Carta(id, imagenesAsignadas[id]);
            cartas[index++] = new Carta(id, imagenesAsignadas[id]);
        }
    }

    public void barajar() {
        
        for (Carta carta : cartas) {
            carta.reiniciar();
        }
        List<Carta> lista = new ArrayList<>();
        Collections.addAll(lista, cartas);
        Collections.shuffle(lista);
        lista.toArray(cartas);
        primeraSeleccion = -1;
        segundaSeleccion = -1;
        esperando = false;
    }

    public void seleccionarCarta(int indice) {
        if (esperando) return;
        if (indice < 0 || indice >= cartas.length) return;

        Carta carta = cartas[indice];
        if (carta.estaEmparejada() || carta.estaVolteada()) return;

        carta.voltear();
        notificarVista();

        if (primeraSeleccion == -1) {
            primeraSeleccion = indice;
        } else if (segundaSeleccion == -1) {
            segundaSeleccion = indice;
            esperando = true;   
            verificarPareja();
        }
    }

    private void verificarPareja() {
        Carta c1 = cartas[primeraSeleccion];
        Carta c2 = cartas[segundaSeleccion];

        if (c1.getIdPareja() == c2.getIdPareja()) {
            c1.marcarComoEmparejada();
            c2.marcarComoEmparejada();
            limpiarSelecciones();
            esperando = false;
            notificarVista();
        } else {
            
            int idx1 = primeraSeleccion;
            int idx2 = segundaSeleccion;
            limpiarSelecciones();

            javax.swing.Timer timer = new javax.swing.Timer(1000, e -> {
                cartas[idx1].voltear();
                cartas[idx2].voltear();
                esperando = false;  
                notificarVista();
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void limpiarSelecciones() {
        primeraSeleccion = -1;
        segundaSeleccion = -1;
    }

    public boolean juegoTerminado() {
        for (Carta c : cartas) {
            if (!c.estaEmparejada()) return false;
        }
        return true;
    }

   
    public Carta getCarta(int indice) { return cartas[indice]; }
    public int getTotalCartas() { return cartas.length; }
    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }
}