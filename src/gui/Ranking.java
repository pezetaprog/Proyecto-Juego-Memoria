package gui;

import logica.EntradaRanking;
import logica.GestorDificultades;
import logica.GestorJugadores;
import datos.Dificultad;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Ranking extends JFrame {

    public Ranking() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("🏆 Ranking");
        setSize(650, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setResizable(false);
        setLocationRelativeTo(null);

        // ── Norte: título ────────────────────────────────────────────
        JLabel titulo = new JLabel("🏆 Top 10 por Dificultad", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(Color.YELLOW);
        titulo.setOpaque(true);
        titulo.setBackground(new Color(67, 16, 116));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(titulo, BorderLayout.NORTH);

        // ── Centro: pestañas por dificultad ─────────────────────────
        JTabbedPane pestanas = new JTabbedPane();
        pestanas.setFont(new Font("Arial", Font.BOLD, 13));

        for (Dificultad dif : GestorDificultades.get().getDificultades()) {
            pestanas.addTab(dif.getNombre(), crearTabla(dif.getNombre()));
        }
        add(pestanas, BorderLayout.CENTER);

        // ── Sur: cerrar ──────────────────────────────────────────────
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrar.setBackground(new Color(67, 16, 116));
        btnCerrar.setForeground(Color.YELLOW);
        btnCerrar.setFocusPainted(false);
        btnCerrar.addActionListener(e -> dispose());

        JPanel sur = new JPanel();
        sur.setBackground(new Color(40, 10, 80));
        sur.add(btnCerrar);
        add(sur, BorderLayout.SOUTH);

        getContentPane().setBackground(new Color(40, 10, 80));
    }

    private JScrollPane crearTabla(String nombreDificultad) {
        String[] columnas = {"#", "Nickname", "Puntuación", "Tiempo"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        List<EntradaRanking> entradas =
            GestorJugadores.get().getRankingPorDificultad(nombreDificultad);

        if (entradas.isEmpty()) {
            modelo.addRow(new Object[]{"—", "Sin partidas aún", "—", "—"});
        } else {
            for (int i = 0; i < entradas.size(); i++) {
                EntradaRanking e = entradas.get(i);
                String medalla = switch (i) {
                    case 0 -> "🥇";
                    case 1 -> "🥈";
                    case 2 -> "🥉";
                    default -> String.valueOf(i + 1);
                };
                modelo.addRow(new Object[]{
                    medalla,
                    e.getNickname(),
                    e.getPuntuacion() + " pts",
                    e.getTiempoFormateado()
                });
            }
        }

        JTable tabla = new JTable(modelo);
        tabla.setFont(new Font("Arial", Font.PLAIN, 14));
        tabla.setRowHeight(32);
        tabla.setBackground(new Color(30, 10, 60));
        tabla.setForeground(Color.WHITE);
        tabla.setGridColor(new Color(100, 50, 150));
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tabla.getTableHeader().setBackground(new Color(67, 16, 116));
        tabla.getTableHeader().setForeground(Color.YELLOW);

        // Centrar columnas
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);
        centrado.setBackground(new Color(30, 10, 60));
        centrado.setForeground(Color.WHITE);
        for (int i = 0; i < columnas.length; i++)
            tabla.getColumnModel().getColumn(i).setCellRenderer(centrado);

        // Anchos
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);

        return new JScrollPane(tabla);
    }
}