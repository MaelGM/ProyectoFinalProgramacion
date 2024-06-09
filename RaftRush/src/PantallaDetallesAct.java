import Objetos.Actividad;
import Objetos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

/**
 * Clase encargada del estilo y funcionamiento de la pantalla de los detalles de la actividad.
 */
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

    /**
     * Constructor de la clase en la que inicializamos la clase definiendo algunas de sus propiedades y estilo.
     * @param cliente Le pasamos el cliente, ya que necesitaremos su información en caso de que decida reservas la actividad.
     * @param actividad Le pasamos la actividad de la cual queremos ver sus detalles.
     */
    public PantallaDetallesAct(Usuario cliente, Actividad actividad){
        super("Detalles de la actividad");
        init();
        setContentPane(jplGeneral);
        setDatos(actividad);
        btnReserva.putClientProperty("JButton.buttonType", "roundRect");
        //comboCantidad.putClientProperty("JComponent.roundRect", true);
        background();

        cargarListeners(cliente,actividad);
    }

    /**
     * Se cargan los detalles de la actividad.
     * @param actividad Actividad de la que cargaremos los detalles de la actividad.
     */
    private void setDatos(Actividad actividad) {
        lblTitulo.setText(actividad.getNombre());
        lblDescripcion.setText(actividad.getDescripcion());
        lblCurrentDate.setText("Fecha Reserva: " + LocalDate.now());
        lblPrecio.setText("Precio final: " + actividad.getPrecio() + "€");

    }

    /**
     * Se cargan los listeners del botón de reserva y el windowClosing
     * @param cliente Cliente con el que hemos iniciado sesión
     * @param actividad Actividad que estamos viendo sus detalles
     */
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
    }

    /**
     * Se intenta agregar una nueva reserva haciendo uso del cliente y la actividad, y en casi de conseguirlo, se muestra un mensaje.
     * @param cliente Cliente que realiza la reserva
     * @param actividad Actividad que se reserva
     */
    private void agregarReserva(Usuario cliente, Actividad actividad) {
        if (DataManager.agregarReserva(cliente,actividad)) {
            JOptionPane.showMessageDialog(null, "Reserva guardado", "Reserva", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Método encargado de la inicialización de la pantalla
     */
    private void init() {
        setSize(660,1033);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());
    }

    /**
     * Se carga el fondo de la pantalla.
     */
    private void background(){
        ImageIcon background = new ImageIcon("resources/imagenes/montanyas.png");
        lblBG.setIcon(background);
        ImageIcon roundAct = new ImageIcon("resources/imagenes/fotoActividad.png");
        lblFoto.setIcon(roundAct);
    }
}
