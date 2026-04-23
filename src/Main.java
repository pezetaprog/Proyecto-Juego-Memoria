import gui.Interfaz;
import logica.GestorImagenes;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        // Cargar imágenes antes de abrir la interfaz
        String[] imagenes = {
            "astrotrain.png",
            "blitzwing.png",
            "bluestrike.png",
            "braun.png",
            "bumbulbe.png",
            "grimlock.png",
            "hotRod.png",
            "hound.png",
            "ironhide.png",
            "jazz.png",
            "Megatron.png",
            "mirage.png",
            "omegaSupreme.png",
            "optimusprime.png",
            "prowl.png",
            "shockwave.png",
            "soundstreaker.png",
            "soundwave.png",
            "starscream.png",
            "ultramagnus.png",
            "wheeljack.png"
        };

        for (String nombre : imagenes) {
            GestorImagenes.get().agregarImagen("/recursos/" + nombre);
        }

        SwingUtilities.invokeLater(() -> {
            new Interfaz().setVisible(true);
        });
    }
}