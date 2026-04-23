package gui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class IniciarJuego extends JFrame {

    private JLabel fondo;
    private JComboBox<String> cbselecUsuarios, dificultad;  // Recomendado usar generic

    public IniciarJuego() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Seleccion de usuario");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        // FONDO
        fondo = new JLabel();
        fondo.setBounds(0, 0, getWidth(), getHeight());
        URL imgUrl = getClass().getClassLoader().getResource("recursos/background2.jpg");
        if (imgUrl != null) {
            fondo.setIcon(new ImageIcon(imgUrl));
        } else {
            fondo.setIcon(new ImageIcon("recursos/background2.jpg"));
        }
        add(fondo);

        // COMBO BOX DE USUARIOS
        cbselecUsuarios = new JComboBox<>();
        cbselecUsuarios.setBounds(450, 200, 200, 25);
        cbselecUsuarios.setName("lista de usuarios");
        // Agregar elementos para que sea visible
        cbselecUsuarios.addItem("seleccione el usuario");
        cbselecUsuarios.addItem("Administrador");
        cbselecUsuarios.addItem("Juan Pérez");
        cbselecUsuarios.addItem("María García");
        cbselecUsuarios.addItem("Carlos López");
        add(cbselecUsuarios);

        //DIFICULTAD
        dificultad = new JComboBox<>();
        dificultad.setBounds(450, 250, 200, 25);
        dificultad.setName("dificultad");
        // Agregar elementos para que sea visible
        dificultad.addItem("seleccione la dificultad");
        dificultad.addItem("muy facil");
        dificultad.addItem("facil");
        dificultad.addItem("normal");
        dificultad.addItem("dificil");
        add(dificultad);

        // BOTÓN
        JButton btnGuardar = new JButton("Iniciar Juego");
        btnGuardar.setBounds(450, 350, 200, 50);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.setBackground(new Color(255, 215, 0));
        btnGuardar.setForeground(Color.BLACK);
        add(btnGuardar);

        // ORDEN DE CAPAS (IMPORTANTE: 0 = arriba, números más altos = atrás)
        setComponentZOrder(cbselecUsuarios, 0);
        setComponentZOrder(dificultad, 1);
        setComponentZOrder(btnGuardar, 2);       // Botón en medio
        setComponentZOrder(fondo, 3);            // Fondo al fondo

        // Opcional: Forzar repaint
        repaint();
    }
}
