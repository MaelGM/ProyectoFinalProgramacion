import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

public class PantallaInicioSesion extends JFrame{
    private JPanel panelContenido;
    private JPanel panelPrincipal;
    private JPanel panelCentrado;
    private JPanel panelDatos;
    private JPanel panelButton;
    private JPanel panelSuperior;
    private JPanel panelLabel;
    private JPanel panelInferior;
    private JPanel panelPasswd;
    private JPanel panelNif;
    private JPanel panelLabelNif;
    private JPanel panelLabelPasswd;
    private JFormattedTextField formTxtFldNif;
    private JButton btnIniciarSesion;
    private JLabel lblAside;
    private JLabel lblCrearCuenta;
    private JLabel lblTitulo;
    private JLabel lblImgUsuario;
    private JLabel lblUsuario;
    private JLabel lblPassword;
    private JLabel lblNif;
    private JPasswordField passwdField;

    ImageIcon aside = new ImageIcon("resources/imagenes/asideSimple.png");

    public PantallaInicioSesion() {
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
        btnIniciarSesion.addActionListener(iniciarSesion());
        Utils.cursorPointerBoton(btnIniciarSesion);
        listenerLabel();
    }

    private ActionListener iniciarSesion() {
        return e -> {
            new PantallaMenu();
            dispose();
        };
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
