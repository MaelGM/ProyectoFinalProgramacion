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
import java.util.Date;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;


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

    private static final ImageIcon logo = new ImageIcon("resources/imagenes/logo.png");
    ImageIcon imgCorporativaCabecera= new ImageIcon("resources/imagenes/cabeceraConTituloRes.png");

    public PantallaReservas() { //Constructor
        super("Lista de actividades");
        init();
        estilo();
        cargarListeners();
        cargarDatos();
        tablaReservasProperties();
        tablaReservaSeleccionadaProperties();
        panelReservasProperties();
    }

    public void init(){
        setSize(1480, 774);
        setContentPane(panelPrincipal);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(logo.getImage());
    }

    private void estilo() {
        lblImgCorporativa.setIcon(imgCorporativaCabecera);
        JTableHeader headerActividades = tblReservas.getTableHeader();
        headerActividades.setPreferredSize(new Dimension(headerActividades.getPreferredSize().width, 40));
        JTableHeader headerEliminarAct = tblResSeleccionada.getTableHeader();
        headerEliminarAct.setPreferredSize(new Dimension(headerEliminarAct.getPreferredSize().width, 40));
    }

    private void cargarListeners() {
        Utils.cursorPointerBoton(btnAnularReserva);
        // TODO: Al clicar, se leen los datos de la tabla inferior y se elimina esa reserva de la BD
        rellenaTablaModificar();
    }

    public void cargarDatos(){
        if (DataManager.getCentros() && DataManager.getClientes() && DataManager.getActividades() && DataManager.getReservas()){
            cargarListas();
            datosReserva();
        }
    }

    private void cargarListas() {
        List<Date> fechas = DataManager.getFechasReservas();
        List<Actividad> actividades = DataManager.getActividadesReservas();
        List<Cliente> clientes = DataManager.getClientesReservas();
        datosMainTable(fechas, actividades, clientes);
    }

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

    public void datosReserva(){
        String[]header = {"Fecha de reserva", "Id de la actividad", "NIF del cliente", "Precio de la actividad", "Centro"};
        String[][]rows = new String[1][header.length];

        ///Todo De cara a tomar datos, propongo tomar la información de cada celda.
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

    public void tablaReservasProperties(){
        tblReservas.setShowGrid(true);
        tblReservas.setGridColor(Color.BLACK);
        tblReservas.getTableHeader().setOpaque(false);
        tblReservas.getTableHeader().setBackground(new Color(47, 75, 89));
        tblReservas.getTableHeader().setForeground(new Color(245, 159, 116));
        tblReservas.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
    }

    public void tablaReservaSeleccionadaProperties(){
        tblResSeleccionada.setShowGrid(true);
        tblResSeleccionada.setGridColor(Color.BLACK);
        tblResSeleccionada.getTableHeader().setOpaque(false);
        tblResSeleccionada.getTableHeader().setBackground(new Color(47, 75, 89));
        tblResSeleccionada.getTableHeader().setForeground(new Color(245, 159, 116));
        tblResSeleccionada.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
    }

    public void panelReservasProperties(){
        panelEliminarRes.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "RESERVA", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        panelEliminarRes.setBorder(titleBorder);
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
                }
            }
        });
    }
}
