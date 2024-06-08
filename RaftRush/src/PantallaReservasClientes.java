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

public class PantallaReservasClientes extends JFrame{
    private JPanel jplGeneral;
    private JTable tblReservas;
    private JLabel lblBG;
    private JPanel jplTabla;
    private JPanel panelCentrado;
    private JButton btnAnularReserva;
    private DefaultTableModel model;

    public PantallaReservasClientes(Usuario cliente, Actividad actividadSelected){
        super("Reservas");
        init();
        setContentPane(jplGeneral);
        background();
        cargarListas(cliente);
        cargarListeners(cliente);
        estilo();

        tblReservas.setShowGrid(true);//Mostrar grid color
    }

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

    private void cargarListeners(Usuario cliente) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new PantallaActClientes(cliente);
            }
        });
        Utils.cursorPointerBoton(btnAnularReserva); // TODO: Funcion que elimine una reserva seleccionada
    }

    private void cargarListas(Usuario cliente) {
        List<Date> fechas = DataManager.getFechasReservas();
        List<Actividad> actividades = DataManager.getActividadesReservas();
        List<Cliente> clientes = DataManager.getClientesReservas().stream().filter(cliente1 -> cliente1.getNif().equalsIgnoreCase(cliente.getNif())).toList();
        if (clientes.size() >= 1) cargarDatos(fechas, actividades, clientes);
        else JOptionPane.showMessageDialog(null, "No tienes reservas", "WARNING", JOptionPane.WARNING_MESSAGE);

    }

    private void init() {
        setSize(1480,774);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());
        setVisible(true);
    }

    private void cargarDatos(List<Date> fechas, List<Actividad> actividades, List<Cliente> clientes){
        if (clientes != null && fechas != null && actividades != null) {
            String[] header = {"Fecha de reserva", "NIF del cliente", "Actividad", "Precio", "Localidad"};
            Object[][] data = new Object[clientes.size()][header.length];
            int i = 0;
            for (Cliente cliente : clientes) {
                data[i][0] = fechas.get(i).toString();
                data[i][1] = clientes.get(i).getNif();
                data[i][2] = actividades.get(i).getNombre();
                data[i][3] = actividades.get(i).getPrecio();
                data[i][4] = actividades.get(i).getCentro().getLocalidad();

                i++;
            }

            tblReservas.setModel(new DefaultTableModel(data, header));

            asignarTamanyoColumnasReservas();
            tblReservas.getTableHeader().setReorderingAllowed(false);
            tblReservas.setDefaultEditor(Object.class, null);
            tblReservas.setEnabled(true);
        }
    }

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

    private void background(){
        ImageIcon background = new ImageIcon("resources/imagenes/cabeceraResClientes.png");

        lblBG.setIcon(background);
    }
}
