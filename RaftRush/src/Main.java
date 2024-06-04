import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        SwingUtilities.invokeLater(() -> new PantallaEntregas().setVisible(true));
    }
}
