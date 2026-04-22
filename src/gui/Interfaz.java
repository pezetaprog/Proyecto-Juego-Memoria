package gui;
import javax.swing.*;
import java.awt.*;

public class Interfaz extends JFrame {
    private JPanel panelPresentacion;
    private JLabel fondo;
    private JLabel titulo;

    public Interfaz() {
        this.InitComponents();
    }

    private void InitComponents() {
        setTitle("Juego de Memoria por Cybertron");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

       
        fondo = new JLabel();
        fondo.setBounds(0, 0, getWidth(), getHeight());
        java.net.URL imgUrl = getClass().getClassLoader().getResource("recursos/fondo");
        if (imgUrl != null) {
            fondo.setIcon(new ImageIcon(imgUrl));
        } else {
            // Fallback: try absolute path (for development)
            fondo.setIcon(new ImageIcon("recursos/fondo"));
        }
        add(fondo);

        titulo = new JLabel("Rescatanos encontrandonos a todos ");
        titulo.setBounds(0, 20, 800, 30);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titulo.setOpaque(false);
        add(titulo);

      
        setComponentZOrder(titulo, 0);
        setComponentZOrder(fondo, 1);
    }
}
