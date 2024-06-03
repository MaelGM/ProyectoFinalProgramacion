import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

public class InicioSesion extends JFrame{
    private JPanel panelContenido;
    private JPanel panelPrincipal;
    private JPanel panelCentrado;
    private JPanel panelDatos;
    private JPanel panelButton;
    private JPanel panelSuperior;
    private JPanel panelLabel;
    private JPanel panelInferior;
    private JPanel panelNif;
    private JPanel panelUsuario;
    private JPanel panelLabelUsuario;
    private JPanel panelLabelNif;
    private JTextField txtFldUsuario;
    private JFormattedTextField formTxtFldNif;
    private JButton btnIniciarSesion;
    private JLabel lblAside;
    private JLabel lblCrearCuenta;
    private JLabel lblTitulo;
    private JLabel lblImgUsuario;
    private JLabel lblUsuario;
    private JLabel lblPassword;
    private JLabel lblNif;

    ImageIcon aside = new ImageIcon("resources/imagenes/asideSimple.png");

    public InicioSesion() {
        super("Inicia sesión");
        init();
        cargarEstilo();
        cargarMascaras();
        cargarListeners();
        cargarImagenes();
    }

    private void init() {
        setContentPane(panelPrincipal);
        setResizable(false);
        setVisible(true);
        setSize(new Dimension(1480, 900));
        setLocationRelativeTo(null);
    }

    private void cargarEstilo() {
        panelDatos.putClientProperty(FlatClientProperties.STYLE, "arc: 40");
    }

    private void cargarImagenes() {
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());
        lblAside.setIcon(aside);
        lblImgUsuario.setIcon(new ImageIcon("resources/imagenes/user.png"));
        lblPassword.setIcon(new ImageIcon("resources/imagenes/password.png"));
    }

    private void cargarMascaras() {
        try {
            MaskFormatter maskNif = new MaskFormatter("########U");
            formTxtFldNif.setFormatterFactory(new DefaultFormatterFactory(maskNif));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void cargarListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new PantallaInicial();
            }
        });
        Utils.cursorPointerBoton(btnIniciarSesion);
        listenerLabel();
    }

    private void listenerLabel(){
        lblCrearCuenta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PantallaRegistro();
                dispose();
            }
        });
        Utils.cursorPointerLabel(lblCrearCuenta);
    }


}
