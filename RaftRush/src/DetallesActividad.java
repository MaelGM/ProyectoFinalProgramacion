import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import java.awt.*;

public class DetallesActividad extends JFrame{
    private JPanel jplGeneral;
    private JLabel lblBG;
    private JButton btnFecha;
    private JComboBox comboCantidad;
    private JButton btnReserva;
    private JPanel jplDatosReserva;
    private JLabel lblTitulo;
    private JLabel lblFoto;
    private JLabel lblDescripcion;
    private JLabel lblPrecio;
    private JLabel lblGradient;

    public DetallesActividad(){
        setContentPane(jplGeneral);

        background();
    }
    private void background(){
        ImageIcon background = new ImageIcon("resources/imagenes/Montanyas.png");
        lblBG.setIcon(background);
    }

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new DetallesActividad();
                frame.setSize(660,1033);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
