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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class PantallaReservas extends JFrame {

    private JLabel lblImgCorporativa;
    private JPanel panelPrincipal;
    private JPanel panelContenido;
    private JPanel panelEliminarRes;
    private JScrollPane PanelDeTabla;
    private JTable tblReservas;
    private JScrollPane ScrollPanelRegAct;
    private JTable tblResSeleccionada;
    private JButton btnAnularReserva;
    private List<Map<String, Object>> reservaSeleccionada = new ArrayList<>();

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
        datosMainTable();
        datosReserva();
    }

    public void datosMainTable(){
        String[]header = {"Fecha de reserva", "Id de la actividad", "NIF del cliente", "Precio de la actividad", "Centro"};
        String[][]rows = new String[3][header.length];

        //En lugar de las líneas de abajo, habra que recorrer con un bucle el List que nos devuelva DataManager
        rows[0][0] = "21-12-2024";
        rows[0][1] = "1";
        rows[0][2] = "48589632F";
        rows[0][3] = "75.00€";
        rows[0][4] = "Madrid";

        rows[1][0] = "14-02-2025";
        rows[1][1] = "2";
        rows[1][2] = "47858963R";
        rows[1][3] = "50.00€";
        rows[1][4] = "Barcelona";

        rows[2][0] = "20-08-2025";
        rows[2][1] = "3";
        rows[2][2] = "45685932V";
        rows[2][3] = "65.00€";
        rows[2][4] = "Sevilla";

        tblReservas.setModel(new DefaultTableModel(rows, header));
        tblReservas.getTableHeader().setReorderingAllowed(false);
        tblReservas.setDefaultEditor(Object.class, null);

        asignarTamanyoColumnasReservas();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblReservas.getColumnCount(); i++) {
            tblReservas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
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
                case 0-> column.setPreferredWidth(153);
                case 1, 4 -> column.setPreferredWidth(100);
                case 2, 3-> column.setPreferredWidth(250);
                case 5-> column.setPreferredWidth(150);
                case 6-> column.setPreferredWidth(200);
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
     * Metodo para la carga de lista de reservas
     */
    private void cargaReservasTable() {
        tblReservas.setShowGrid(true);
        Object[][] data = new Object[Objects.requireNonNull(DataManager.getListReservas()).size()][4];
        int i = 0;
        for (int j = 0; j < DataManager.getListReservas().size(); j++) {
            data[i][0] = DataManager.getListReservas().get(j).get("columna1");
            data[i][1] = DataManager.getListReservas().get(j).get("columna2");
            data[i][2] = DataManager.getListReservas().get(j).get("columna3");
            data[i][3] = DataManager.getListReservas().get(j).get("columna4");
            i++;
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
                    Map<String, Object> reservaSeleccionada = Objects.requireNonNull(DataManager.getListReservas()).get(row);
                    tblResSeleccionada.getModel().setValueAt(reservaSeleccionada.get("columna1"), 0, 0);
                    tblResSeleccionada.getModel().setValueAt(reservaSeleccionada.get("columna2"), 0, 1);
                    tblResSeleccionada.getModel().setValueAt(reservaSeleccionada.get("columna3"), 0, 2);
                    tblResSeleccionada.getModel().setValueAt(reservaSeleccionada.get("columna4"), 0, 3);
                    //tblResSeleccionada.getModel().setValueAt(reservaSeleccionada.get(Integer.parseInt("columna5")), 0, 4);
                }
            }
        });
    }
}
