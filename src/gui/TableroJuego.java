package gui;

import logica.Carta;
import logica.GestorImagenes;
import logica.SesionJuego;
import logica.Tablero;

import javax.swing.*;
import java.awt.*;

public class TableroJuego extends JFrame {

    private SesionJuego sesion;
    private Tablero     tablero;
    private JButton[]   botones;

    private JLabel lblPuntaje, lblTiempo, lblPartidas;
    private javax.swing.Timer cronometro;
    private long segundosTranscurridos = 0;

    private static final Color COLOR_REVERSO    = new Color(44, 62, 80);
    private static final Color COLOR_EMPAREJADA = new Color(80, 80, 80);
    private static final Color[] COLORES = {
        new Color(231, 76, 60),  new Color(52, 152, 219),
        new Color(46, 204, 113), new Color(241, 196, 15),
        new Color(155, 89, 182), new Color(230, 126, 34),
        new Color(26, 188, 156), new Color(52, 73, 94),
        new Color(192, 57, 43),  new Color(41, 128, 185),
        new Color(39, 174, 96),  new Color(243, 156, 18),
        new Color(142, 68, 173), new Color(211, 84, 0),
        new Color(22, 160, 133), new Color(44, 62, 80),
        new Color(231, 76, 60),  new Color(52, 152, 219),
    };

    public TableroJuego(SesionJuego sesion) {
        this.sesion = sesion;
        construirTablero();
        construirUI();
        iniciarCronometro();
        setVisible(true);
    }

    private void construirTablero() {
        int filas    = sesion.getDificultad().getFilas();
        int columnas = sesion.getDificultad().getColumnas();
        int parejas  = (filas * columnas) / 2;

        try {
            int[] indices = GestorImagenes.get().seleccionarAleatorios(parejas);
            tablero = new Tablero(filas, columnas, indices);
        } catch (IllegalStateException ex) {
            // No hay imágenes cargadas: modo color
            tablero = new Tablero(filas, columnas);
        }

        tablero.setOnActualizarVista(this::actualizarVista);
    }

    private void construirUI() {
        int filas    = sesion.getDificultad().getFilas();
        int columnas = sesion.getDificultad().getColumnas();

        setTitle("Juego — " + sesion.getNickname()
                 + " | " + sesion.getDificultad().getNombre());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));
        setResizable(false);

        int anchoVentana = columnas * 110 + 40;
        int altoVentana  = filas   * 110 + 160;
        setSize(anchoVentana, altoVentana);
        setLocationRelativeTo(null);

        JPanel norte = new JPanel(new GridLayout(1, 3));
        norte.setBackground(new Color(30, 10, 60));
        norte.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        lblPuntaje  = infoLabel("Puntos: 0");
        lblTiempo   = infoLabel("Tiempo: 00:00");
        lblPartidas = infoLabel("Partida: " + (sesion.getPartidasConsecutivas() + 1));

        norte.add(lblPuntaje);
        norte.add(lblTiempo);
        norte.add(lblPartidas);
        add(norte, BorderLayout.NORTH);

        JPanel panelTablero = new JPanel(new GridLayout(filas, columnas, 6, 6));
        panelTablero.setBackground(new Color(20, 5, 40));
        panelTablero.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        botones = new JButton[tablero.getTotalCartas()];
        for (int i = 0; i < botones.length; i++) {
            final int idx = i;
            botones[i] = crearBoton();
            botones[i].addActionListener(e -> tablero.seleccionarCarta(idx));
            panelTablero.add(botones[i]);
        }
        add(panelTablero, BorderLayout.CENTER);

        JPanel sur = new JPanel();
        sur.setBackground(new Color(30, 10, 60));

        JButton btnRanking = botonSur("Ver Ranking");
        btnRanking.addActionListener(e -> new Ranking());

        add(sur, BorderLayout.SOUTH);
        sur.add(btnRanking);

        actualizarVista();
    }

    private JLabel infoLabel(String texto) {
        JLabel lbl = new JLabel(texto, SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        lbl.setForeground(Color.YELLOW);
        return lbl;
    }

    private JButton botonSur(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBackground(new Color(67, 16, 116));
        btn.setForeground(Color.YELLOW);
        btn.setFocusPainted(false);
        return btn;
    }

    private JButton crearBoton() {
        JButton btn = new JButton("?");
        btn.setFont(new Font("Arial", Font.BOLD, 24));
        btn.setBackground(COLOR_REVERSO);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void iniciarCronometro() {
        cronometro = new javax.swing.Timer(1000, e -> {
            segundosTranscurridos++;
            long min = segundosTranscurridos / 60;
            long seg = segundosTranscurridos % 60;
            lblTiempo.setText(String.format("Tiempo: %02d:%02d", min, seg));
        });
        cronometro.start();
    }

    private void actualizarVista() {
        SwingUtilities.invokeLater(() -> {
            int parejasEncontradas = 0;

            for (int i = 0; i < botones.length; i++) {
                Carta carta = tablero.getCarta(i);
                JButton btn = botones[i];

                if (carta.estaEmparejada()) {
                    parejasEncontradas++;
                    btn.setBackground(COLOR_EMPAREJADA);
                    btn.setForeground(Color.LIGHT_GRAY);
                    btn.setText("✓");
                    btn.setEnabled(false);

                } else if (carta.estaVolteada()) {
                    btn.setBackground(COLORES[carta.getIdPareja() % COLORES.length]);
                    btn.setForeground(Color.WHITE);
                    btn.setText(String.valueOf(carta.getIdPareja() + 1));
                    btn.setEnabled(false);

                } else {
                    btn.setBackground(COLOR_REVERSO);
                    btn.setForeground(Color.WHITE);
                    btn.setText("?");
                    btn.setEnabled(true);
                }
            }

            // parejasEncontradas viene en unidades de carta, dividir entre 2
            int parejas = parejasEncontradas / 2;
            int puntosActuales = sesion.getPuntuacionAcumulada()
                + parejas * sesion.getDificultad().getPuntosPorPareja();
            lblPuntaje.setText("Puntos: " + puntosActuales);

            if (tablero.juegoTerminado()) terminarPartida(parejas);
        });
    }

    private void terminarPartida(int parejas) {
        cronometro.stop();

        int puntosGanados = sesion.completarPartida(parejas, segundosTranscurridos);

        String msg = String.format(
            "¡Completaste el tablero!\n\n" +
            "Puntos esta partida : %d\n" +
            "Puntuación total    : %d\n" +
            "Tiempo              : %02d:%02d",
            puntosGanados,
            sesion.getPuntuacionAcumulada(),
            segundosTranscurridos / 60,
            segundosTranscurridos % 60
        );

        Object[] opciones = {"Continuar (misma dificultad)", "Reiniciar (puntaje en 0)", "Salir"};
        int resp = JOptionPane.showOptionDialog(this, msg, "Partida completada",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
            null, opciones, opciones[0]);

        if (resp == 0) {
            sesion.continuar();
            segundosTranscurridos = 0;
            construirTablero();
            lblPartidas.setText("Partida: " + (sesion.getPartidasConsecutivas() + 1));
            construirBotonesTablero();
            iniciarCronometro();

        } else if (resp == 1) {
            dispose();
            new IniciarJuego().setVisible(true);

        } else {
            dispose();
        }
    }

    /** Reconstruye solo los botones cuando se continúa */
    private void construirBotonesTablero() {
        JPanel panelTablero = (JPanel) ((BorderLayout) getContentPane()
            .getLayout()).getLayoutComponent(BorderLayout.CENTER);
        panelTablero.removeAll();

        botones = new JButton[tablero.getTotalCartas()];
        for (int i = 0; i < botones.length; i++) {
            final int idx = i;
            botones[i] = crearBoton();
            botones[i].addActionListener(e -> tablero.seleccionarCarta(idx));
            panelTablero.add(botones[i]);
        }
        panelTablero.revalidate();
        panelTablero.repaint();
        actualizarVista();
    }
}