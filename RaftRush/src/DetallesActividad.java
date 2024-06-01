import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DetallesActividad extends JFrame{
    private JPanel jplGeneral;
    private JLabel lblBG;
    private JButton btnFecha;
    private JComboBox comboCantidad;
    private JButton btnReserva;
    private JPanel jplDatosReserva;
    private JLabel lblTitulo;
    private JLabel lblDescripcion;
    private JLabel lblPrecio;
    private JLabel lblFoto;

    public DetallesActividad(){
        setContentPane(jplGeneral);
        btnFecha.putClientProperty("JButton.buttonType", "roundRect");
        btnReserva.putClientProperty("JButton.buttonType", "roundRect");
        //comboCantidad.putClientProperty("JComponent.roundRect", true);

        background();
    }
    private void background(){
        ImageIcon background = new ImageIcon("resources/imagenes/Montanyas.png");
        lblBG.setIcon(background);
        ImageIcon roundAct = new ImageIcon("resources/imagenes/FotoActividad.png");
        lblFoto.setIcon(roundAct);
    }

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createWindow();
            }
        });
    }

    private static void createWindow(){
        JFrame frame = new DetallesActividad();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(660,1033);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
