import Excepciones.ExceptionUsuario;
import Objetos.Cliente;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.event.*;
import java.security.NoSuchAlgorithmException;
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
    private JPasswordField passwordField;
    private JLabel lblContrasenya;
    private JPanel panelLabelContrasenya;
    private JLabel lblIconoContrasenya;
    private JPanel panelContrasenya;

    ImageIcon aside = new ImageIcon("resources/imagenes/asideSimple.png");

    /**
     * Constructor de la clase PantallaRegistro en el que se llaman a los métodos necesarios para hacer funcionar la pantalla.
     */
    public PantallaRegistro() {
        super("Pantalla Inicial");
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
        setContentPane(panelContenido);

        setResizable(false);
        setVisible(true);
        setSize(new Dimension(1480, 900));
        setLocationRelativeTo(null);
    }

    /**
     * Método en el que se definen las máscaras de los form text fields (Teléfono, edad y nif)
     */
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

    /**
     * Aquí se cargan todas las imágenes de la pantalla.
     */
    private void cargarImagenes() {
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());
        lblAside.setIcon(aside);
        lblImgUsuario.setIcon(new ImageIcon("resources/imagenes/user.png"));
        lblImgEdad.setIcon(new ImageIcon("resources/imagenes/edad.png"));
        lblImgTel.setIcon(new ImageIcon("resources/imagenes/telefono.png"));
        lblImgPassword.setIcon(new ImageIcon("resources/imagenes/password.png"));
        lblIconoContrasenya.setIcon(new ImageIcon("resources/imagenes/password.png"));
    }

    /**
     * Método destinado a cargar todas las propiedades destinadas a la presentación estética de algunos elementos.
     */
    private void cargarEstilo() {
        panelCentral.putClientProperty(FlatClientProperties.STYLE, "arc: 40");
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
        btnRegistrar.addActionListener(registrarse());
        listenerLabel();
        Utils.cursorPointerBoton(btnRegistrar);
        Utils.cursorPointerLabel(lblIniciarSesion);
    }
    /**
     * Listener encargado de hacer que cuando se clique cierto JLabel, te lleve a otra pantalla.
     */
    private void listenerLabel(){
        lblIniciarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PantallaInicioSesion();
                dispose();
            }
        });
    }

    /**
     * En este método, lo primero es que cargamos los usuarios, para después comprobar si los campos de texto están correctamente
     * rellenados, y por último, si es posible crear un cliente con esos datos. Después de estas validaciones, se intenta
     * añadir el cliente a la Base de datos, pero si ya hay un cliente con el mismo nif, rechaza el proceso mostrando un mensaje,
     * si no, avanzara a la siguiente pantalla
     * @return Devuelve la propia acción del botón
     */
    private ActionListener registrarse(){
        return e -> {
            Cliente cliente;
            if (DataManager.getUsuarios() && checkTextFields() && (cliente = readTextFields()) != null){
                if (DataManager.addCliente(cliente)) {
                    new PantallaActClientes(cliente); // TODO: Hay que pasarle a la siguiente pantalla el cliente para asi poder tener los datos del mismo.
                    dispose();
                }else
                    JOptionPane.showMessageDialog(null, "Ya hay una cuenta con este nif",
                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        };
    }

    /**
     * En este metodo se lee todos los campos de texto, y con todos esos datos, se crea un cliente.
     * @return El cliente, caso de haber podido crear el cliente. En caso contrario, nulo.
     */
    private Cliente readTextFields(){

        String nombre = txtFldNombre.getText();
        int edad = Integer.parseInt(formTxtFldEdad.getText().trim());
        String telefono = formTxtFldTel.getText();
        String nif = formTxtFldNif.getText();
        String password = null;
        try {
            password = PasswordUtils.hashPassword(new String(passwordField.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        try {
            return new Cliente(nif, password, telefono, nombre, edad);
        } catch (ExceptionUsuario e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en los datos", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Revisa si los campos de texto están vacíos o no. En el caso del form text, comprueba si tiene un espacio (dos en caso de la edad),
     * ya que si lo tiene, es que no ha rellenado el campo correctamente.
     * @return True si no están vacíos, o false en caso de estarlo.
     */
    private boolean checkTextFields() {
        if (txtFldNombre.getText().equals("")
                || formTxtFldEdad.getText().contains("  ")
                || formTxtFldTel.getText().contains(" ")
                || formTxtFldNif.getText().contains(" ")
                || new String(passwordField.getPassword()).equals("")) {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos", "Error en los datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }else return true;
    }




}
