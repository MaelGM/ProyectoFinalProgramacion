import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PantallaMenu extends JFrame{

    private JPanel menuTrabajadoresPane;
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

    public PantallaMenu(){
        super("Menú Trabajadores");
        init();
        loadListeners();
    }

    private void loadListeners(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new PantallaInicial();
            }
        });
        btnGestionarAlmacen.addActionListener(gestMaterial());
        btnGestionarTrabajadores.addActionListener(gestTrabajadores());
        btnGestionarReservas.addActionListener(gestReservas());
        btnGestionarActividad.addActionListener(gestAct());
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
            new PantallaAct();
        };
    }

    private ActionListener gestReservas(){
        return e -> {
            new PantallaReservas();
        };
    }

    private void init(){
        setContentPane(menuTrabajadoresPane);
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
