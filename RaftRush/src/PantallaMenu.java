import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    public PantallaMenu(){
        super("Menú Trabajadores");
        init();
        loadListeners();
    }

    private void loadListeners(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Window window : Window.getWindows()) {
                    window.dispose();
                }
                new PantallaInicial();
            }
        });
        btnGestionarAlmacen.addActionListener(gestMaterial());
        btnGestionarTrabajadores.addActionListener(gestTrabajadores());
        btnGestionarReservas.addActionListener(gestReservas());
        btnGestionarActividad.addActionListener(gestAct());
        btnGestionarCentros.addActionListener(gestCentros());
        btnGestionarProveedores.addActionListener(gestProveedores());
        Utils.cursorPointerBoton(btnGestionarAlmacen);
        Utils.cursorPointerBoton(btnGestionarTrabajadores);
        Utils.cursorPointerBoton(btnGestionarReservas);
        Utils.cursorPointerBoton(btnGestionarActividad);
        Utils.cursorPointerBoton(btnGestionarCentros);
        Utils.cursorPointerBoton(btnGestionarProveedores);


        panelUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PantallaPerfil();
            }
        });
    }

    private ActionListener gestMaterial(){
        return e -> {
            new PantallaMateriales();
        };
    }
    private ActionListener gestTrabajadores(){
        return e -> {
            new PantallaGestionarTrabajadores();
        };
    }
    private ActionListener gestAct(){
        return e -> {
            new PantallaActividades();
        };
    }
    private ActionListener gestReservas(){
        return e -> {
            new PantallaReservas();
        };
    }
    private ActionListener gestProveedores() {
        return e -> {
            new PantallaProveedores();
        };
    }
    private ActionListener gestCentros() {
        return e -> {
            new PantallaCentros();
        };
    }

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
