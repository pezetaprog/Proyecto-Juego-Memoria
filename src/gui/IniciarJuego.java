package gui;

import datos.Dificultad;
import logica.GestorDificultades;
import logica.GestorJugadores;
import logica.SesionJuego;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class IniciarJuego extends JFrame {

    private JComboBox<String> cbUsuarios;
    private JComboBox<Dificultad> cbDificultad;
    private JLabel lblError;

    public IniciarJuego() {
        initComponents();
        setVisible(true);
    }

    private void cargarUsuarios() {
        String seleccionActual = (String) cbUsuarios.getSelectedItem();

        cbUsuarios.removeAllItems();

        List<String> nicks = new ArrayList<>(GestorJugadores.get().getNicknames());
        nicks.sort(String::compareToIgnoreCase);

        if (nicks.isEmpty()) {
            cbUsuarios.addItem("-- Sin jugadores registrados --");
            cbUsuarios.setEnabled(false);
        } else {
            cbUsuarios.setEnabled(true);
            cbUsuarios.addItem("-- Seleccione jugador --");
            for (String nick : nicks) {
                cbUsuarios.addItem(nick);
            }

            if (seleccionActual != null && nicks.contains(seleccionActual)) {
                cbUsuarios.setSelectedItem(seleccionActual);
            }
        }
    }

    private void initComponents() {
        setTitle("Selección de usuario y dificultad");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // ✅ FONDO COMO CONTENT PANE
        URL imgUrl = getClass().getClassLoader().getResource("recursos/background2.jpg");
        JLabel fondo = new JLabel(
                imgUrl != null
                        ? new ImageIcon(imgUrl)
                        : new ImageIcon("recursos/background2.jpg")
        );
        fondo.setLayout(null);
        setContentPane(fondo);

        // ── Etiquetas ────────────────────────────────────────────────
        JLabel lblUsuario = new JLabel("Jugador:");
        lblUsuario.setBounds(450, 170, 200, 25);
        lblUsuario.setForeground(Color.YELLOW);
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        fondo.add(lblUsuario);

        JLabel lblDif = new JLabel("Dificultad:");
        lblDif.setBounds(450, 235, 200, 25);
        lblDif.setForeground(Color.YELLOW);
        lblDif.setFont(new Font("Arial", Font.BOLD, 14));
        fondo.add(lblDif);

        lblError = new JLabel("");
        lblError.setBounds(400, 310, 280, 25);
        lblError.setForeground(new Color(255, 80, 80));
        lblError.setFont(new Font("Arial", Font.ITALIC, 12));
        fondo.add(lblError);

        // ── ComboBox usuarios ────────────────────────────────────────
        cbUsuarios = new JComboBox<>();
        cbUsuarios.setBounds(450, 200, 150, 28);
        cbUsuarios.setFont(new Font("Arial", Font.PLAIN, 13));
        cargarUsuarios();
        fondo.add(cbUsuarios);

        // Botón "+"
        JButton btnNuevoUsuario = new JButton("+");
        btnNuevoUsuario.setBounds(605, 200, 45, 28);
        btnNuevoUsuario.setToolTipText("Registrar nuevo jugador");
        btnNuevoUsuario.setFocusPainted(false);
        btnNuevoUsuario.addActionListener(e -> {
            Registro r = new Registro();
            r.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent we) {
                    cargarUsuarios();
                }
            });
        });
        fondo.add(btnNuevoUsuario);

        // ── ComboBox dificultad ──────────────────────────────────────
        cbDificultad = new JComboBox<>();
        cbDificultad.setBounds(450, 260, 200, 28);
        cbDificultad.setFont(new Font("Arial", Font.PLAIN, 13));

        for (Dificultad d : GestorDificultades.get().getDificultades()) {
            cbDificultad.addItem(d);
        }
        cbDificultad.setSelectedIndex(0);
        fondo.add(cbDificultad);

        // ── Botón iniciar ────────────────────────────────────────────
        JButton btnIniciar = new JButton("Iniciar Juego");
        btnIniciar.setBounds(450, 350, 200, 50);
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 16));
        btnIniciar.setBackground(new Color(255, 215, 0));
        btnIniciar.setForeground(Color.BLACK);
        btnIniciar.setFocusPainted(false);
        fondo.add(btnIniciar);

        btnIniciar.addActionListener(e -> {
            lblError.setText("");

            String nickname = (String) cbUsuarios.getSelectedItem();

            if (nickname == null || nickname.startsWith("--")) {
                lblError.setText("Selecciona un jugador.");
                return;
            }

            Dificultad dif = (Dificultad) cbDificultad.getSelectedItem();
            if (dif == null) dif = GestorDificultades.get().getDificultadMinima();

            SesionJuego sesion = new SesionJuego(nickname, dif);
            dispose();
            new TableroJuego(sesion).setVisible(true);
        });
    }
}