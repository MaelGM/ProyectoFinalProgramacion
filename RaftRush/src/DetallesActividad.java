import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                activarWindows();
            }
        });
        btnReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activarWindows();
                dispose();
            }
        });
    }

    private void activarWindows(){
        JFrame frame = new ActividadClientes();
        frame.setSize(1534,774);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    private void background(){
        ImageIcon background = new ImageIcon("resources/imagenes/Montanyas.png");
        lblBG.setIcon(background);
        ImageIcon roundAct = new ImageIcon("resources/imagenes/FotoActividad.png");
        lblFoto.setIcon(roundAct);
    }
}
