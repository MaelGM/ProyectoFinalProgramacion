import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

public class PantallaRegistro extends JFrame{
    private JLabel lblAside;
    private JPanel panelContenido;
    private JPanel panelPrincipal;
    private JPanel panelCentrado;
    private JPanel panelSuperior;
    private JPanel panelCentral;
    private JLabel lblTitulo;
    private JButton btnRegistrar;
    private JPanel panelInferior;
    private JPanel panelBoton;
    private JPanel panelIniciarSesion;
    private JLabel lblIniciarSesion;
    private JPanel panelUsuario;
    private JPanel panelEdad;
    private JPanel panelTel;
    private JPanel panelNif;
    private JLabel lblImgUsuario;
    private JLabel lblImgEdad;
    private JLabel lblImgTel;
    private JLabel lblImgPassword;
    private JTextField txtFldNombre;
    private JFormattedTextField formTxtFldEdad;
    private JFormattedTextField formTxtFldTel;
    private JFormattedTextField formTxtFldNif;
    private JPanel panelLabelEdad;
    private JLabel lblEdad;
    private JLabel lblTelefono;
    private JPanel panelLabelTel;
    private JPanel panelLabelNombre;
    private JLabel lblNombre;
    private JPanel panelLabelNif;
    private JLabel lblNif;

    ImageIcon aside = new ImageIcon("resources/imagenes/asideSimple.png");

    public PantallaRegistro() {
        super("Pantalla Inicial");
        init();
        cargarEstilo();
        cargarMascaras();
        cargarListeners();
        cargarImagenes();
    }

    private void init() {
        setContentPane(panelContenido);

        setResizable(false);
        setVisible(true);
        setSize(new Dimension(1480, 900));
        setLocationRelativeTo(null);
    }

    private void cargarMascaras() {
        try {
            MaskFormatter maskNif = new MaskFormatter("########U");
            MaskFormatter maskTel = new MaskFormatter("#########");
            MaskFormatter maskEdad = new MaskFormatter("##");
            formTxtFldNif.setFormatterFactory(new DefaultFormatterFactory(maskNif));
            formTxtFldEdad.setFormatterFactory(new DefaultFormatterFactory(maskEdad));
            formTxtFldTel.setFormatterFactory(new DefaultFormatterFactory(maskTel));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    private void cargarImagenes() {
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());
        lblAside.setIcon(aside);
        lblImgUsuario.setIcon(new ImageIcon("resources/imagenes/user.png"));
        lblImgEdad.setIcon(new ImageIcon("resources/imagenes/edad.png"));
        lblImgTel.setIcon(new ImageIcon("resources/imagenes/telefono.png"));
        lblImgPassword.setIcon(new ImageIcon("resources/imagenes/password.png"));
    }
    private void cargarEstilo() {
        panelCentral.putClientProperty(FlatClientProperties.STYLE, "arc: 40");
    }
    private void cargarListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new PantallaInicial();
            }
        });
        btnRegistrar.addActionListener(registrarse());
        listenerLabel();
        Utils.cursorPointerBoton(btnRegistrar);
    }

    private ActionListener registrarse(){
        return e -> {
            new PantallaMenu();
            dispose();
        };
    }
    private void listenerLabel(){
        lblIniciarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PantallaInicioSesion();
                dispose();
            }
        });
        Utils.cursorPointerLabel(lblIniciarSesion);
    }


}
