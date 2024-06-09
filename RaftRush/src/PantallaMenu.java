import Objetos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Esta clase es la encargada de hacer funcionar y modificar estéticamente la pantalla del menu de los trabajadores.
 */
public class PantallaMenu extends JFrame{

    private JPanel panelPrincipal;
    private JLabel lblHeader;
    private JButton btnGestionarTrabajadores;
    private JButton btnGestionarReservas;
    private JButton btnGestionarActividad;
    private JButton btnGestionarAlmacen;
    private JLabel lblNombre;
    private JLabel lblAvatar;
    private JPanel panelInferior;
    private JPanel panelCentral;
    private JPanel panelSuperior;
    private JPanel panelUsuario;
    private JLabel lblUsuario;
    private JButton btnGestionarProveedores;
    private JButton btnGestionarCentros;

    /**
     * Constructor de la clase, en la que le ponemos título a la pantalla y llamamos a los métodos necesarios para
     * hacer funcionar la pantalla.
     * @param trabajador Se le pasa el usuario con el que se inició sesión y se pone en el JLabel.
     */
    public PantallaMenu(Usuario trabajador){
        super("Menú Trabajadores");
        init();
        lblNombre.setText(trabajador.getNombre());
        loadListeners(trabajador);
    }

    /**
     * Carga varios listeners para hacer que los botones y JLabels funcionen correctamente. También hay otros Listeners
     * como por ejemplo el WindowListener, que se encarga de que si se cierra esta pantalla, se cierren todas las otras y se abra la
     * pantalla inicial.
     * @param trabajador Se le pasa el trabajador, para que en caso de que se clique en el perfil, se le pueda enviar la información del
     *                   trabajador.
     */
    private void loadListeners(Usuario trabajador){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Window window : Window.getWindows()) {
                    window.dispose();
                }
                new PantallaInicial();
            }
        });
        btnGestionarAlmacen.addActionListener(e -> new PantallaMateriales());
        btnGestionarTrabajadores.addActionListener(e -> new PantallaGestionarTrabajadores());
        btnGestionarReservas.addActionListener(e -> new PantallaReservas());
        btnGestionarActividad.addActionListener(e -> new PantallaActividades());
        btnGestionarCentros.addActionListener(e -> new PantallaCentros());
        btnGestionarProveedores.addActionListener(e -> new PantallaProveedores());
        Utils.cursorPointerBoton(btnGestionarAlmacen);
        Utils.cursorPointerBoton(btnGestionarTrabajadores);
        Utils.cursorPointerBoton(btnGestionarReservas);
        Utils.cursorPointerBoton(btnGestionarActividad);
        Utils.cursorPointerBoton(btnGestionarCentros);
        Utils.cursorPointerBoton(btnGestionarProveedores);


        panelUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PantallaPerfil(trabajador);
                dispose();
            }
        });
    }

    /**
     * Método inicializador en el que se definen las propiedades y estilo de la pantalla, como por ejemplo el icono, las
     * imágenes o el tamaño.
     */
    private void init(){
        setContentPane(panelPrincipal);
        ImageIcon imgHeader = new ImageIcon("resources/imagenes/cabeceraConTituloTrabajadores.png");
        ImageIcon imgAvatar = new ImageIcon("resources/imagenes/miniAvatarUser.png");
        lblHeader.setIcon(imgHeader);
        lblAvatar.setIcon(imgAvatar);
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());

        setSize(1480, 770);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
