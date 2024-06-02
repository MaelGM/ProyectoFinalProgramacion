import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Utils {
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
