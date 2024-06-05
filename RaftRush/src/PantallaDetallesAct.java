import Objetos.Usuario;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PantallaDetallesAct extends JFrame{
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

    public PantallaDetallesAct(Usuario cliente){
        init();
        setContentPane(jplGeneral);
        btnFecha.putClientProperty("JButton.buttonType", "roundRect");
        btnReserva.putClientProperty("JButton.buttonType", "roundRect");
        //comboCantidad.putClientProperty("JComponent.roundRect", true);
        background();

        cargarListeners(cliente);
    }

    private void cargarListeners(Usuario cliente) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new PantallaActClientes(cliente);
            }
        });
        btnReserva.addActionListener(e -> {
            new PantallaActClientes(cliente);
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
        ImageIcon roundAct = new ImageIcon("resources/imagenes/fotoActividad.png");
        lblFoto.setIcon(roundAct);
    }
}
