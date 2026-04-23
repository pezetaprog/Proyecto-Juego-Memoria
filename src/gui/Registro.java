package gui;

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
        if (imgUrl != null) {
            fondo.setIcon(new ImageIcon(imgUrl));
        } else {
            fondo.setIcon(new ImageIcon("recursos/fondo.jpg"));
        }
        add(fondo);

        JLabel lblNombre = new JLabel("Nombre del Jugador:");
        lblNombre.setBounds(50, 100, 200, 30);
        lblNombre.setForeground(Color.YELLOW);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblNombre);

        txnombre = new JTextField();
        txnombre.setBounds(50, 140, 300, 30);
        add(txnombre);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(100, 250, 200, 50);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.setBackground(new Color(255, 215, 0));
        btnGuardar.setForeground(Color.BLACK);

        btnGuardar.addActionListener(e -> {
            String nombre = txnombre.getText().trim();
            if (!nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "¡Bienvenido " + nombre + "!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor ingresa tu nombre");
            }
        });

        add(btnGuardar);

        // ORDEN DE CAPAS
        setComponentZOrder(txnombre, 0);
        setComponentZOrder(btnGuardar, 1);
        setComponentZOrder(fondo, 2);
    }
}
