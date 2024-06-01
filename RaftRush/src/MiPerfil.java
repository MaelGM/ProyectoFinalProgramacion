import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;

public class MiPerfil extends JFrame{
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

    public MiPerfil(){
        setContentPane(jplGeneral);
        //btnFecha.putClientProperty("JButton.buttonType", "roundRect");
        //btnReserva.putClientProperty("JButton.buttonType", "roundRect");
        jplDatos.putClientProperty(FlatClientProperties.STYLE, "arc: 20");

        background();
    }
    private void background(){
        ImageIcon background = new ImageIcon("resources/imagenes/Montanyas.png");
        lblBG.setIcon(background);
        ImageIcon roundAct = new ImageIcon("resources/imagenes/Avatar.png");
        lblAvatar.setIcon(roundAct);
        ImageIcon user = new ImageIcon("resources/imagenes/User.png");
        lblIcon1.setIcon(user);
        ImageIcon phone = new ImageIcon("resources/imagenes/Phone.png");
        lblIcon2.setIcon(phone);
        ImageIcon key = new ImageIcon("resources/imagenes/Key_alt.png");
        lblIcon3.setIcon(key);
    }


    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new MiPerfil();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(660,1033);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);

                frame.setVisible(true);
            }
        });
    }
}
