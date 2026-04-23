package gui;
import javax.swing.*;
import java.awt.*;

public class Interfaz extends JFrame {
    private JPanel panelPresentacion;
    private JLabel fondo;
    private JLabel titulo;
    private JButton jugar, salir, ranking, registro;

    public Interfaz() {
        this.InitComponents();
        this.setVisible(true);
    }

    private void InitComponents() {
        setTitle("Juego de Memoria por Cybertron");
        setSize(800, 460);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        //FUENTE GIN RAI

        titulo = new JLabel("Rescate autobot");
        titulo.setBounds(0, 50, 800, 50);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        titulo.setForeground(Color.YELLOW);
        titulo.setOpaque(false);
        add(titulo);


        jugar = new JButton("Jugar");
        jugar.setBounds(50, 130, 200, 50);
        jugar.setFont(new Font("Arial", Font.BOLD, 20));
        jugar.setForeground(Color.YELLOW);
        jugar.setBackground(new Color(67, 16, 116));
        jugar.setFocusPainted(false);
        jugar.addActionListener(e -> {
            new IniciarJuego();
        });
        add(jugar);


        salir = new JButton("Salir");
        salir.setBounds(50, 200, 200, 50);
        salir.setFont(new Font("Arial", Font.BOLD, 20));
        salir.setForeground(Color.YELLOW);
        salir.setBackground(new Color(67, 16, 116));
        salir.setFocusPainted(false);
        add(salir);

        ranking = new JButton("Ranking");
        ranking.setBounds(50, 270, 200, 50);
        ranking.setFont(new Font("Arial", Font.BOLD, 20));
        ranking.setForeground(Color.YELLOW);
        ranking.setBackground(new Color(67, 16, 116));
        ranking.setFocusPainted(false);
        add(ranking);

        registro = new JButton("Registro");
        registro.setBounds(50, 340, 200, 50);
        registro.setFont(new Font("Arial", Font.BOLD, 20));
        registro.setForeground(Color.YELLOW);
        registro.setBackground(new Color(67, 16, 116));
        registro.setFocusPainted(false);


        registro.addActionListener(e -> {
            new Registro();
        });

        add(registro);

        setComponentZOrder(titulo, 0);
        setComponentZOrder(jugar, 1);
        setComponentZOrder(ranking, 2);
        setComponentZOrder(salir, 3);
        setComponentZOrder(registro, 4);
        setComponentZOrder(fondo, 5);
    }
}
 //C:\Windows\Fonts