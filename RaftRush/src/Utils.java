import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Clase Utils usada para evitar la repetición de código
 */
public class Utils {
    /**
     * Con este método le damos la propiedad al ratón de que se ponga en tipo "HAND_CURSOR" cuando este encima del botón.
     * @param boton Botón al que le queremos añadir esta propiedad
     */
    public static void cursorPointerBoton(JButton boton){
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                boton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    /**
     * Con este método le damos la propiedad al ratón de que se ponga en tipo "HAND_CURSOR" cuando este encima del label.
     * @param label JLabel al que le queremos añadir esta propiedad
     */
    public static void cursorPointerLabel(JLabel label){
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }
}
