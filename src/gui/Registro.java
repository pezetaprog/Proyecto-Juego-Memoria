package gui;

import logica.GestorJugadores;

import javax.swing.*;
import java.awt.*;

public class Registro extends JFrame {

    private JTextField txnombre;
    private JLabel fondo;

    public Registro() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Registro de Jugador");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        // FONDO
        fondo = new JLabel();
        fondo.setBounds(0, 0, getWidth(), getHeight());
        java.net.URL imgUrl = getClass().getClassLoader().getResource("recursos/fondo.jpg");
        fondo.setIcon(imgUrl != null
            ? new ImageIcon(imgUrl)
            : new ImageIcon("recursos/fondo.jpg"));
        add(fondo);

        JLabel lblTitulo = new JLabel("Registro de Jugador");
        lblTitulo.setBounds(0, 40, 400, 40);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setForeground(Color.YELLOW);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblTitulo);

        JLabel lblNombre = new JLabel("Nickname:");
        lblNombre.setBounds(50, 120, 200, 30);
        lblNombre.setForeground(Color.YELLOW);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblNombre);

        txnombre = new JTextField();
        txnombre.setBounds(50, 155, 300, 35);
        txnombre.setFont(new Font("Arial", Font.PLAIN, 14));
        add(txnombre);

        // Mensaje de error visible en pantalla
        JLabel lblError = new JLabel("");
        lblError.setBounds(50, 195, 300, 25);
        lblError.setForeground(new Color(255, 80, 80));
        lblError.setFont(new Font("Arial", Font.ITALIC, 12));
        add(lblError);

        JButton btnGuardar = new JButton("Registrar");
        btnGuardar.setBounds(100, 260, 200, 50);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.setBackground(new Color(255, 215, 0));
        btnGuardar.setForeground(Color.BLACK);
        btnGuardar.setFocusPainted(false);
        add(btnGuardar);

        btnGuardar.addActionListener(e -> {
            String nombre = txnombre.getText().trim();
            lblError.setText("");

            if (nombre.isEmpty()) {
                lblError.setText("⚠ Ingresa un nickname.");
                return;
            }
            // ← Validación de unicidad con GestorJugadores
            if (GestorJugadores.get().nicknameExiste(nombre)) {
                lblError.setText("⚠ Ese nickname ya está en uso.");
                return;
            }

            GestorJugadores.get().registrarNickname(nombre);
            JOptionPane.showMessageDialog(this,
                "¡Bienvenido, " + nombre + "!\nYa puedes iniciar una partida.",
                "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        // También registrar al presionar Enter
        txnombre.addActionListener(e -> btnGuardar.doClick());

        setComponentZOrder(lblTitulo,  0);
        setComponentZOrder(lblNombre,  1);
        setComponentZOrder(txnombre,   2);
        setComponentZOrder(lblError,   3);
        setComponentZOrder(btnGuardar, 4);
        setComponentZOrder(fondo,      5);
    }
}