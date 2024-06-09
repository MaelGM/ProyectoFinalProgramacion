import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;

/**
 * Clase desde la cual ejecutamos el proyecto.
 */
public class Main {
    /**
     * Método main desde el cual ejecutamos el proyecto. Antes de nada, instalamos la temática MacDark haciendo uso
     * de la librería y posteriormente, intentamos conectarnos a la BD, y si no hay problema, nos lleva a la pantalla inicial.
     */
    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        if (DBManager.loadDriver() && DBManager.connect()) {
            DBManager.close();
            SwingUtilities.invokeLater(() -> new PantallaInicial().setVisible(true));
        }
    }
}
