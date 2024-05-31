import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuTrabajadores extends JFrame{

    private JPanel menuTrabajadoresPane;
    private JLabel lblHeader;
    private JButton gestionarTrabajadoresButton;
    private JButton gestionarReservasButton;
    private JButton gestionarActividadButton;
    private JButton btnGestAlmacen;
    private JLabel lblNombre;
    private JLabel lblAvatar;

    public MenuTrabajadores(){
        super("Menú Trabajadores");
        init();
        loadListeners();
    }

    private void loadListeners(){
        btnGestAlmacen.addActionListener(gestMaterial());
    }

    private ActionListener gestMaterial(){
        return e -> {
            new GestionarMateriales();
        };
    }

    private void init(){
        setContentPane(menuTrabajadoresPane);
        ImageIcon imgHeader = new ImageIcon("resources/imagenes/headerTrabajadores.png");
        ImageIcon imgAvatar = new ImageIcon("resources/imagenes/miniAvatarUser.png");
        ImageIcon favicon = new ImageIcon("resources/imagenes/logoRaftRush.png");
        lblHeader.setIcon(imgHeader);
        lblAvatar.setIcon(imgAvatar);
        setIconImage(favicon.getImage());

        setSize(1480, 770);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


    }

    public static void main(String[] args){
        FlatMacDarkLaf.setup();
        SwingUtilities.invokeLater(() -> new MenuTrabajadores().setVisible(true));
    }
}
