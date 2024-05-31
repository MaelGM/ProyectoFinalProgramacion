import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;

public class InicioSesion extends JFrame{
    private JLabel lblAside;
    private JPanel panelContenido;
    private JPanel panelPrincipal;
    private JPanel panelCentrado;

    ImageIcon aside = new ImageIcon("resources/imagenes/asideSimple.png");

    public InicioSesion() {
        super("Inicia sesión");
        init();
    }

    private void init() {
        setContentPane(panelContenido);
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());
        lblAside.setIcon(aside);

        panelCentrado.putClientProperty(FlatClientProperties.STYLE, "arc: 40");

        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1480, 900));
        setLocationRelativeTo(null);
    }
}
