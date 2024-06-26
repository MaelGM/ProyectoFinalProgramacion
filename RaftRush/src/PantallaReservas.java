import Objetos.Actividad;
import Objetos.Cliente;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatLineBorder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Esta clase es la encargada de hacer funcionar y modificar estéticamente la pantalla de gestión de reservas.
 */
public class PantallaReservas extends JFrame {
    private JLabel lblImgCorporativa;
    private JPanel panelPrincipal;
    private JPanel panelEliminarRes;
    private JTable tblReservas;
    private JScrollPane ScrollPanelRegAct;
    private JTable tblResSeleccionada;
    private JButton btnAnularReserva;
    private JPanel panelTabla;
    private List<Map<String, Object>> reservaSeleccionada = new ArrayList<>();
    private DefaultTableModel model;
    public static int posicion = 0;

    private static final ImageIcon logo = new ImageIcon("resources/imagenes/logo.png");
    ImageIcon imgCorporativaCabecera= new ImageIcon("resources/imagenes/cabeceraConTituloRes.png");

    /**
     * Constructor de la clase donde se llaman a los métodos necesarios para inicializar la pantalla. También se asigna un título
     * a la pantalla
     */
    public PantallaReservas() {
        super("Lista de actividades");
        init();
        estilo();
        cargarListeners();
        cargarDatos();
        tablaReservasProperties();
        tablaReservaSeleccionadaProperties();
        panelReservasProperties();
    }

    /**
     * Método encargado de inicializar la pantalla asignando algunos atributos a la misma, como lo son el tamaño.
     */
    public void init(){
        setSize(1480, 774);
        setContentPane(panelPrincipal);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(logo.getImage());
    }

    /**
     * Método en el que se cargan algunas propiedades estéticas, como las imágenes.
     */
    private void estilo() {
        lblImgCorporativa.setIcon(imgCorporativaCabecera);
        JTableHeader headerActividades = tblReservas.getTableHeader();
        headerActividades.setPreferredSize(new Dimension(headerActividades.getPreferredSize().width, 40));
        JTableHeader headerEliminarAct = tblResSeleccionada.getTableHeader();
        headerEliminarAct.setPreferredSize(new Dimension(headerEliminarAct.getPreferredSize().width, 40));
    }

    /**
     * Se cargan todas las funciones de los botones y un mouseListener
     */
    private void cargarListeners() {
        Utils.cursorPointerBoton(btnAnularReserva);
        // TODO: Al clicar, se leen los datos de la tabla inferior y se elimina esa reserva de la BD
        btnAnularReserva.addActionListener(eliminarReserva());
        rellenaTablaModificar();
    }

    /**
     * Se cargan los datos de la BD (Centros, clientes, actividades y reservas) y después se cargan las listas y cada uno.
     */
    public void cargarDatos(){
        if (DataManager.getCentros() && DataManager.getClientes() && DataManager.getActividades() && DataManager.getReservas()){
            cargarListas();
            datosReserva();
        }
    }

    /**
     * Se cargan las tres listas, enviando nulo al método de DataManager, devolviendo asi la lista de todas las reservas. Se llama al método
     * encargado de cargar la tabla enviándole las 3 listas.
     */
    private void cargarListas() {
        List<Date> fechas = DataManager.getFechasReservas(null); // Si el usuario es nulo, devolvera la lista entera
        List<Actividad> actividades = DataManager.getActividadesReservas(null);
        List<Cliente> clientes = DataManager.getClientesReservas();
        datosMainTable(fechas, actividades, clientes);
    }


    private ActionListener eliminarReserva(){
        return e -> {
            if (!checkTextFields()) {
                JOptionPane.showMessageDialog(null,"No has elegido una reserva");
            }else{
                int opcion = JOptionPane.showConfirmDialog(null,"Estas seguro que deseas eliminar " +
                        "la reserva del cliente : " + tblResSeleccionada.getModel().getValueAt(0, 2));
                System.out.println(opcion);
                if (opcion == 0) {
                    JOptionPane.showMessageDialog(null, "Eliminando la reserva");
                    Object dateO = tblResSeleccionada.getModel().getValueAt(0,0);
                    String date = dateO.toString();
                    String nif = (String) tblResSeleccionada.getModel().getValueAt(0, 2);
                    //Object idActO = tblResSeleccionada.getModel().getValueAt(0, 3);

                    Object actividadO = tblResSeleccionada.getModel().getValueAt(0, 1);
                    int idAct = (Integer) actividadO;

                    int reservaBorrar = DataManager.borrarReserva(date, nif, idAct);
                    if (reservaBorrar == 0) {
                        JOptionPane.showMessageDialog(null, "ERROR. No se puedo borrar la reserva.");
                    }else{
                        JOptionPane.showMessageDialog(null, "Reserva borrado correctamente");
                        List<Date> fechas = DataManager.getFechasReservas(null); // Si el usuario es nulo, devolvera la lista entera
                        List<Actividad> actividades = DataManager.getActividadesReservas(null);
                        List<Cliente> clientes = DataManager.getClientesReservas();
                        datosMainTable(fechas,actividades,clientes);
                    }
                }
            }
        };
    }
    /**
     * Primero comprueba que las listas no son nulas, y en caso de no serlo, define el header y empieza a rellenar la tabla.
     * Posteriormente, define algunas propiedades de la tabla y centra el texto de las celdas.
     * @param fechas Lista con todas las fechas de todas las reservas
     * @param actividades Lista con todas las actividades de todas las reservas
     * @param clientes Lista con todas los clientes con reservas.
     */
    public void datosMainTable(List<Date> fechas, List<Actividad> actividades, List<Cliente> clientes){
        if (clientes != null && fechas != null && actividades != null){
            String[]header = {"Fecha de reserva", "NIF del cliente", "Actividad", "Precio", "Centro"};
            Object[][]rows = new Object[fechas.size()][header.length];
            int i = 0;
            for (Date fecha: fechas) {
                rows[i][0] = fecha.toString();
                rows[i][1] = clientes.get(i).getNif();
                rows[i][2] = actividades.get(i).getNombre();
                rows[i][3] = actividades.get(i).getPrecio();
                rows[i][4] = actividades.get(i).getCentro().getNombre();

                i++;
            }

            tblReservas.setModel(new DefaultTableModel(rows, header));
            tblReservas.getTableHeader().setReorderingAllowed(false);
            tblReservas.setDefaultEditor(Object.class, null);

            asignarTamanyoColumnasReservas();

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (i = 0; i < tblReservas.getColumnCount(); i++) {
                tblReservas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
    }

    /**
     * Se carga el header y algunas propiedades de la tabla inferior. También se inicializan las celdas, dejándolas vacías.
     */
    public void datosReserva(){
        String[]header = {"Fecha de reserva", "Id de la actividad", "NIF del cliente", "Precio de la actividad", "Centro"};
        String[][]rows = new String[1][header.length];

        rows[0][0] = "";
        rows[0][1] = "";
        rows[0][2] = "";
        rows[0][3] = "";
        rows[0][4] = "";

        tblResSeleccionada.setModel(new DefaultTableModel(rows, header));
        tblResSeleccionada.getTableHeader().setReorderingAllowed(false);
        tblResSeleccionada.setDefaultEditor(Object.class, null);

        asignarTamanyoColumnasReservaSeleccionada();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblResSeleccionada.getColumnCount(); i++) {
            tblResSeleccionada.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    /**
     * Propiedades de la tabla principal.
     */
    public void tablaReservasProperties(){
        tblReservas.setShowGrid(true);
        tblReservas.setGridColor(Color.BLACK);
        tblReservas.getTableHeader().setOpaque(false);
        tblReservas.getTableHeader().setBackground(new Color(47, 75, 89));
        tblReservas.getTableHeader().setForeground(new Color(245, 159, 116));
        tblReservas.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
    }

    /**
     * Propiedades de la tabla secundaria.
     */
    public void tablaReservaSeleccionadaProperties(){
        tblResSeleccionada.setShowGrid(true);
        tblResSeleccionada.setGridColor(Color.BLACK);
        tblResSeleccionada.getTableHeader().setOpaque(false);
        tblResSeleccionada.getTableHeader().setBackground(new Color(47, 75, 89));
        tblResSeleccionada.getTableHeader().setForeground(new Color(245, 159, 116));
        tblResSeleccionada.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
    }

    /**
     * Propiedades del panel inferior (Borde)
     */
    public void panelReservasProperties(){
        panelEliminarRes.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "RESERVA", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        panelEliminarRes.setBorder(titleBorder);
    }

    /**
     * Tamaño de las columnas de la tabla principal
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
     * Tamaño de las columnas de la tabla secundaria.
     */
    public void asignarTamanyoColumnasReservaSeleccionada(){
        TableColumn column;
        for (int i = 0; i < tblResSeleccionada.getColumnCount(); i++) {
            column = tblResSeleccionada.getColumnModel().getColumn(i);
            switch (i){
                case 0, 3, 5 -> column.setPreferredWidth(200);
                case 1-> column.setPreferredWidth(250);
                case 2, 4 -> column.setPreferredWidth(150);
            }
        }
    }

    /**
     * Metodo para modificar la reserva seleccionada
     */
    public void rellenaTablaModificar() {
        tblReservas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                    int row = tblReservas.getSelectedRow();
                    if (e.getClickCount() == 2 && row != -1) {
                        tblResSeleccionada.getModel().setValueAt(DataManager.getListReservas().get(row).get("columna1"), 0, 0);
                        tblResSeleccionada.getModel().setValueAt(DataManager.getListReservas().get(row).get("columna2"), 0, 2);
                        tblResSeleccionada.getModel().setValueAt(DataManager.getListReservas().get(row).get("columna3"), 0, 1);
                        tblResSeleccionada.getModel().setValueAt(DataManager.getPrecioAct((Integer) DataManager.getListReservas().get(row).get("columna3")), 0, 3);
                        tblResSeleccionada.getModel().setValueAt(DataManager.getNomCentro((Integer) DataManager.getListReservas().get(row).get("columna4")), 0, 4);

                        posicion = tblReservas.getSelectedRow();
                    }
            }
        });
    }

    private boolean checkTextFields() {
        if (tblResSeleccionada.getModel().getValueAt(0,0).equals("")
                || tblResSeleccionada.getModel().getValueAt(0,1).equals("")
                || tblResSeleccionada.getModel().getValueAt(0,2).equals("")
                || tblResSeleccionada.getModel().getValueAt(0,3).equals("")
                || tblResSeleccionada.getModel().getValueAt(0,4).equals("")) {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos", "Error en los datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }else return true;
    }
}
