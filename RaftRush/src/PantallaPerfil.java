import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PantallaPerfil extends JFrame{
    private JPanel jplGeneral;
    private JPanel jplDatos;
    private JTextField txtTelefono;
    private JTextField txtNif;
    private JTextField txtNombre;
    private JButton btnAct;
    private JButton btnLogOut;
    private JLabel lblMiPerfil;
    private JPanel jplPerfilDatos;
    private JLabel lblAvatar;
    private JLabel lblBG;
    private JLabel lblNombre;
    private JLabel lblTelefono;
    private JLabel lblNif;
    private JLabel lblIcon1;
    private JLabel lblIcon2;
    private JLabel lblIcon3;

    public PantallaPerfil(){
        init();
        setContentPane(jplGeneral);
        jplDatos.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
        background();

        cargarListeners();
    }

    private void cargarListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new PantallaActClientes();
            }
        });
        btnAct.addActionListener(e -> {
            new PantallaActClientes();
            dispose();
        });
    }

    private void init() {
        setSize(660,1033);
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());
    }

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
