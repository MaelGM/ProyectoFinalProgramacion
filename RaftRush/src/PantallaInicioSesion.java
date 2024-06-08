import Excepciones.ExceptionUsuario;
import Objetos.Cliente;
import Objetos.Trabajador;
import Objetos.Usuario;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Arrays;

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

    public static String nifGuardar;
    public Usuario user;

    ImageIcon aside = new ImageIcon("resources/imagenes/asideSimple.png");

    /**
     * Constructor de la clase PantallaInicioSesion en el que se llaman a los métodos necesarios para hacer funcionar la pantalla.
     */
    public PantallaInicioSesion() {
        super("Inicia sesión");
        init();
        cargarEstilo();
        cargarMascaras();
        cargarListeners();
        cargarImagenes();
    }

    /**
     * Método en el que se marcan las propiedades del frame esenciales, como lo son el tamaño, el panel principal, etc.
     */
    private void init() {
        setContentPane(panelPrincipal);
        setResizable(false);
        setVisible(true);
        setSize(new Dimension(1480, 900));
        setLocationRelativeTo(null);
    }

    /**
     * Método destinado a cargar todas las propiedades destinadas a la presentación estética de algunos elementos.
     */
    private void cargarEstilo() {
        panelDatos.putClientProperty(FlatClientProperties.STYLE, "arc: 40");
    }

    /**
     * Aquí se cargan todas las imágenes de la pantalla.
     */
    private void cargarImagenes() {
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());
        lblAside.setIcon(aside);
        lblImgUsuario.setIcon(new ImageIcon("resources/imagenes/user.png"));
        lblPassword.setIcon(new ImageIcon("resources/imagenes/password.png"));
    }

    /**
     * Método en el que se definen las máscaras de los form text fields (Nif)
     */
    private void cargarMascaras() {
        try {
            MaskFormatter maskNif = new MaskFormatter("########U");
            formTxtFldNif.setFormatterFactory(new DefaultFormatterFactory(maskNif));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Se cargan los listeners encargados de dar funcionalidades a algunos elementos como los botones o label.
     */
    private void cargarListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new PantallaInicial();
            }
        });
        btnIniciarSesion.addActionListener(iniciarSesion());
        Utils.cursorPointerBoton(btnIniciarSesion);
        Utils.cursorPointerLabel(lblCrearCuenta);
        listenerLabel();
    }

    /**
     * Listener encargado de hacer que cuando se clique cierto JLabel, te lleve a otra pantalla.
     */
    private void listenerLabel(){
        lblCrearCuenta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PantallaRegistro();
                dispose();
            }
        });
    }

    /**
     * Código ejecutado cuando se pulsa el botón iniciar sesión, en el que se validan los text fields, la existencia del usuario
     * y que la contraseña coincida
     * @return Devuelve la acción que ejecutara el programa en el momento en el que se pulse el botón.
     */
    private ActionListener iniciarSesion() {
        return e -> {
            if (checkTextFields() && DataManager.getUsuarios()){
                //try {
                    char[] passwordChars = passwdField.getPassword();
                    String password = new String(passwordChars);
                    //if (PasswordUtils.checkPassword(password, DataManager.getHashPassword(formTxtFldNif.getText()))) {
                        user = DataManager.findUsuario(formTxtFldNif.getText());

                        if (user instanceof Trabajador) new PantallaMenu(user); // TODO: Tendriamos que enviarle el usuario (Constructor nuevo) para asi que cambie la pestaña perfil
                        else if (user instanceof Cliente) new PantallaActClientes(user);
                        dispose();

                    /*}else{
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Contraseña",JOptionPane.ERROR_MESSAGE);
                    }*/
                //} catch (NoSuchAlgorithmException ex) {
                //    ex.printStackTrace();
                //}
            }
        };
    }

    /**
     * Revisa si los campos de texto están vacíos o no. En el caso del form text, comprueba si tiene un espacio,
     * ya que si lo tiene, es que no ha rellenado el campo correctamente.
     * @return True si no están vacíos, o false en caso de estarlo.
     */
    private boolean checkTextFields() {
        if (formTxtFldNif.getText().contains(" ") || new String(passwdField.getPassword()).equals("")) {
            JOptionPane.showMessageDialog(null, "Rellene los campos", "Error en los datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }else return true;
    }
}
