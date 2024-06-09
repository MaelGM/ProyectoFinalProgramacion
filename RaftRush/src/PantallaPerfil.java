import Excepciones.ExceptionUsuario;
import Objetos.Cliente;
import Objetos.Trabajador;
import Objetos.Usuario;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.NoSuchAlgorithmException;

/**
 * Esta clase es la encargada de hacer funcionar y modificar estéticamente la pantalla de la visualización del perfil.
 */
public class PantallaPerfil extends JFrame{
    private JPanel panelGeneral;
    private JPanel panelDatos;
    private JTextField txtContra;
    private JTextField txtNif;
    private JTextField txtNombre;
    private JButton btnAct;
    private JButton btnLogOut;
    private JLabel lblMiPerfil;
    private JPanel panelPerfilDatos;
    private JLabel lblAvatar;
    private JLabel lblBG;
    private JLabel lblNombre;
    private JLabel lblContra;
    private JLabel lblNif;
    private JLabel lblIcon1;
    private JLabel lblIcon2;
    private JLabel lblIcon3;

    /**
     * Constructor de la clase en la que se inicializa la pantalla.
     * @param usu Usuario del cliente/trabajador que usamos para reflejar sus datos en os txtFields.
     */
    public PantallaPerfil(Usuario usu){
        init();
        setContentPane(panelGeneral);
        panelDatos.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
        background();
        txtNif.setText(usu.getNif());
        txtNombre.setText(usu.getNombre());
        cargarListeners(usu);
    }

    /**
     * Se cargan los listeners encargados de realizar las funciones del WindowClosing o de los botones.
     * @param usu Le pasamos el usuario porque será necesario en métodos posteriores.
     */
    private void cargarListeners(Usuario usu) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Window window : Window.getWindows()) {
                    window.dispose();
                }
                if (usu instanceof Trabajador) {
                    new PantallaMenu(usu);
                }else if (usu instanceof Cliente){
                    new  PantallaActClientes(usu);
                }
            }
        });

        btnLogOut.addActionListener(e -> {
            for (Window window : Window.getWindows()) {
                window.dispose();
            }
            new PantallaInicial();
        });

        btnAct.addActionListener(e -> {
            actualizarUsu(usu);
            dispose();
        });
    }

    /**
     * En este metodo, comprobamos la validez de los datos de los textFields y si son correctos, creamos un usuario temporal.
     * Modificamos el usuario temporal y con esos datos, modificamos el usuario como tal. Una vez terminado, comprobamos
     * si el usuario es un trabajador o es un cliente y le enviamos a una pantalla y otra dependiendo de ello.
     * @param usu Usuario que se modificará
     */
    private void actualizarUsu(Usuario usu){
        String nombre = txtNombre.getText();
        String contrasenya = txtContra.getText();

        if (!txtNombre.getText().isEmpty() || !txtContra.getText().isEmpty()) {
            if (txtNombre.getText().isEmpty()) {
                nombre = usu.getNombre();
            }
            if (txtContra.getText().isEmpty()) {
                contrasenya = usu.getContrasenya();
            }else{
                try {
                    contrasenya = PasswordUtils.hashPassword(txtContra.getText());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }

            try{
                usu.setNombre(nombre);
                usu.setContrasenya(contrasenya);
                if (DataManager.editarUsuario(usu, nombre, contrasenya, txtNif.getText()) > 0) {
                    //usu.setNombre(nombre);
                    //usu.setContrasenya(contrasenya);
                    JOptionPane.showMessageDialog(null, "Perfil de " + usu.getNombre() +" actualizado correctamente", "Actualizar Perfil", JOptionPane.INFORMATION_MESSAGE);

                    if (usu instanceof Trabajador) {
                        new PantallaMenu(usu);
                    }else if (usu instanceof Cliente){
                        new  PantallaActClientes(usu);
                    }
                }
            }catch (ExceptionUsuario e) {
                JOptionPane.showMessageDialog(null, "Los datos de tu perfil no se puede actualizar debido de un error", "Actualizar Perfil", JOptionPane.ERROR_MESSAGE);
                if (usu instanceof Trabajador) {
                    new PantallaMenu(usu);
                }else if (usu instanceof Cliente){
                    new  PantallaActClientes(usu);
                }
                e.printStackTrace();
            }
        }
    }

    /**
     * Método en el que inicializamos la pantalla con el icono, el tamaño...
     */
    private void init() {
        setSize(660,1033);
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());
    }

    /**
     * Creamos el background de la pantalla y asiganmos algunos iconos.
     */
    private void background(){
        ImageIcon background = new ImageIcon("resources/imagenes/montanyas.png");
        lblBG.setIcon(background);
        ImageIcon roundAct = new ImageIcon("resources/imagenes/avatar.png");
        lblAvatar.setIcon(roundAct);
        ImageIcon user = new ImageIcon("resources/imagenes/user.png");
        lblIcon1.setIcon(user);
        ImageIcon phone = new ImageIcon("resources/imagenes/phone.png");
        lblIcon2.setIcon(phone);
        ImageIcon key = new ImageIcon("resources/imagenes/password.png");
        lblIcon3.setIcon(key);
    }
}
