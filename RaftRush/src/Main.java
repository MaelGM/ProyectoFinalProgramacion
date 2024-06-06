import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        if (DBManager.loadDriver() && DBManager.connect()) {
            DBManager.close();
            SwingUtilities.invokeLater(() -> new PantallaMenu().setVisible(true));
        }
    }
}
