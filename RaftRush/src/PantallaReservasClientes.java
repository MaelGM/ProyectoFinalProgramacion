import Objetos.Actividad;
import Objetos.Cliente;
import Objetos.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;

/**
 * Clase encargada del funcionamiento y estética de la pantalla encargada de la visualización de las reservas del cliente.
 */
public class PantallaReservasClientes extends JFrame{
    private JPanel jplGeneral;
    private JTable tblReservas;
    private JLabel lblBG;
    private JPanel jplTabla;
    private JPanel panelCentrado;
    private JButton btnAnularReserva;
    private DefaultTableModel model;

    /**
     * Constructor de la clase donde se le asigna un título a la pantalla y se inicializa a través de ciertos métodos.
     */
    public PantallaReservasClientes(Usuario cliente){
        super("Reservas");
        init();
        setContentPane(jplGeneral);
        background();
        cargarListas(cliente);
        cargarListeners(cliente);
        estilo();

        tblReservas.setShowGrid(true);//Mostrar grid color
    }

    /**
     * Método encargado del estilo de la pantalla (Color, fuente, centrar el texto...)
     */
    private void estilo() {
        tblReservas.setGridColor(Color.black);
        tblReservas.getTableHeader().setOpaque(false);
        tblReservas.getTableHeader().setBackground(new Color(47, 75, 89));
        tblReservas.getTableHeader().setForeground(new Color(245, 159, 116));
        tblReservas.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));

        TableColumnModel colums = tblReservas.getColumnModel();
        colums.getColumn(4).setMinWidth(150);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblReservas.getColumnCount(); i++) {
            tblReservas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    /**
     * Se cargan los listeners del botón anular reserva y del windowClosing
     * @param cliente Cliente para poder volver a la pantalla anterior enviándole el cliente en el que se ha iniciado sesión.
     */
    private void cargarListeners(Usuario cliente) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new PantallaActClientes(cliente);
            }
        });
        Utils.cursorPointerBoton(btnAnularReserva); // TODO: Funcion que elimine una reserva seleccionada
    }

    /**
     * Se cargan las listas con la información de las reservas y, si el cliente tiene reservas, se llama al método
     * que cargara la tabla junto con el cliente, si no, se muestra un mensaje de aviso.
     * @param cliente Cliente del cual queremos saber sus reservas.
     */
    private void cargarListas(Usuario cliente) {
        try{
            List<Date> fechas = DataManager.getFechasReservas(cliente);
            List<Actividad> actividades = DataManager.getActividadesReservas(cliente);
            if (DataManager.getClientesReservas().contains(cliente)) cargarDatos(fechas, actividades, DataManager.getCliente(cliente.getNif()));
            else JOptionPane.showMessageDialog(null, "No tienes reservas", "WARNING", JOptionPane.WARNING_MESSAGE);
        }catch (NullPointerException ex){
            JOptionPane.showMessageDialog(null, "No tienes reservas", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Método de inicialización de la pantalla.
     */
    private void init() {
        setSize(1480,774);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());
        setVisible(true);
    }

    /**
     * Se carga la tabla con la information de las listas que se le pasa.
     * @param fechas Lista con las fechas de cuando se realizaron las reservas
     * @param actividades Lista con las actividades de las reservas
     * @param cliente Cliente que ha realizado las reservas.
     */
    private void cargarDatos(List<Date> fechas, List<Actividad> actividades, Cliente cliente){
        if (cliente != null && fechas != null && actividades != null) {
            String[] header = {"Fecha de reserva", "NIF del cliente", "Actividad", "Precio", "Localidad"};
            Object[][] data = new Object[actividades.size()][header.length];
            int i = 0;
            for (Actividad actividad : actividades) {
                data[i][0] = fechas.get(i).toString();
                data[i][1] = cliente.getNif();
                data[i][2] = actividad.getNombre();
                data[i][3] = actividad.getPrecio();
                data[i][4] = actividad.getCentro().getLocalidad();

                i++;
            }

            tblReservas.setModel(new DefaultTableModel(data, header));

            asignarTamanyoColumnasReservas();
            tblReservas.getTableHeader().setReorderingAllowed(false);
            tblReservas.setDefaultEditor(Object.class, null);
            tblReservas.setEnabled(true);
        }
    }

    /**
     * Asignación del tamaño de las columnas.
     */
    public void asignarTamanyoColumnasReservas(){
        TableColumn column;
        for (int i = 0; i < tblReservas.getColumnCount(); i++) {
            column = tblReservas.getColumnModel().getColumn(i);
            switch (i){
                case 0 -> column.setPreferredWidth(153);
                case 1 -> column.setPreferredWidth(100);
                case 2, 4 -> column.setPreferredWidth(300);
                case 3 -> column.setPreferredWidth(50);
            }
        }
    }

    /**
     * Se carga el fondo de la pantalla.
     */
    private void background(){
        ImageIcon background = new ImageIcon("resources/imagenes/cabeceraResClientes.png");

        lblBG.setIcon(background);
    }
}
