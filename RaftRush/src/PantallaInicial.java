import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        loadListeners();
    }

    private void loadListeners() {
        btnIniciarSesion.addActionListener(iniciarSesion());
    }

    private ActionListener iniciarSesion(){
        return e -> {
            new InicioSesion();
            dispose();
        };
    }

    private void init() {
        setContentPane(panelContenido);
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());
        lblAside.setIcon(aside);
        lblLogo.setIcon(logo);

        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1480, 900));
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        SwingUtilities.invokeLater(() -> new PantallaInicial().setVisible(true));
    }
}
