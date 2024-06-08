import Objetos.Actividad;
import Objetos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

public class PantallaDetallesAct extends JFrame{
    private JPanel jplGeneral;
    private JLabel lblBG;
    private JComboBox comboCantidad;
    private JButton btnReserva;
    private JPanel jplDatosReserva;
    private JLabel lblTitulo;
    private JLabel lblDescripcion;
    private JLabel lblPrecio;
    private JLabel lblFoto;
    private JLabel lblCurrentDate;

    public PantallaDetallesAct(Usuario cliente, Actividad actividad){
        init();
        setContentPane(jplGeneral);
        setDatos(actividad);
        btnReserva.putClientProperty("JButton.buttonType", "roundRect");
        //comboCantidad.putClientProperty("JComponent.roundRect", true);
        background();

        cargarListeners(cliente,actividad);
    }

    private void setDatos(Actividad actividad) {
        lblTitulo.setText(actividad.getNombre());
        lblDescripcion.setText(actividad.getDescripcion());
        lblCurrentDate.setText("Fecha Reserva: " + String.valueOf(LocalDate.now()));
    }

    private void cargarListeners(Usuario cliente, Actividad actividad) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new PantallaActClientes(cliente);
            }
        });
        btnReserva.addActionListener(e -> {
            agregarReserva(cliente,actividad);
            new PantallaActClientes(cliente);
            dispose();
        });
        comboCantidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cantidad = comboCantidad.getSelectedIndex();
                Double precioFinal = actividad.getPrecio() * cantidad;
                lblPrecio.setText("Precio final: " + String.valueOf(precioFinal) + "€");

            }
        });
    }

    private void agregarReserva(Usuario cliente, Actividad actividad) {
        if (DataManager.agregarReserva(cliente,actividad)) {
            JOptionPane.showMessageDialog(null, "Reserva guardado", "Reserva", JOptionPane.INFORMATION_MESSAGE);
        }
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
