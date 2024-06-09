import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clase encargada de todas las funciones y estilo de la primera pantalla, la pantalla inicial.
 */
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

    /**
     * Constructor de la clase PantallaInicial donde definimos el título de la página y llamamos a los metodos necesarios para
     * dejar funcionando la pantalla
     */
    public PantallaInicial() {
        super("Pantalla Inicial");
        init();
        loadListeners();
    }

    /**
     * Cargamos todos los listeners para que asi cada botón cumpla su función.
     */
    private void loadListeners() {
        btnIniciarSesion.addActionListener(iniciarSesion());
        btnRegistrar.addActionListener(registrar());
        Utils.cursorPointerBoton(btnIniciarSesion);
        Utils.cursorPointerBoton(btnRegistrar);
    }

    /**
     * Acción ejecutada por un botón que consiste en cerrar esta pantalla y avanzar a la pantalla de iniciar sesión
     * @return Devuelve la propia acción del botón
     */
    private ActionListener iniciarSesion(){
        return e -> {
            new PantallaInicioSesion();
            dispose();
        };
    }

    /**
     * Acción ejecutada por un botón que consiste en cerrar esta pantalla y avanzar a la pantalla de registrarse
     * @return Devuelve la propia acción del botón
     */
    private ActionListener registrar(){
        return e -> {
            new PantallaRegistro();
            dispose();
        };
    }

    /**
     * En este método se definen algunos valores de la página como sus propiedades, tamaño, icono etc...
     */
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
}
