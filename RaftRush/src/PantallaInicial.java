import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import java.awt.*;

public class PantallaInicial extends JFrame{
    private JPanel panelContenido;
    private JButton btnIniciarSesion;
    private JButton btnRegistrar;
    private JLabel lblAside;
    private JPanel panelPrincipal;
    private JLabel lblTitulo;
    private JPanel panelCentrado;
    private JLabel lblLogo;

    ImageIcon aside = new ImageIcon("resources/imagenes/asideSimple.png");
    ImageIcon logo = new ImageIcon("resources/imagenes/logoRaftRush.png");

    public PantallaInicial() {
        super("Pantalla Inicial");
        init();
    }

    private void init() {
        setContentPane(panelContenido);
        setIconImage(logo.getImage());
        lblAside.setIcon(aside);
        lblLogo.setIcon(logo);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1480, 900));
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame pantallaInicial = new PantallaInicial();
                pantallaInicial.setVisible(true);
            }
        });
    }
}
