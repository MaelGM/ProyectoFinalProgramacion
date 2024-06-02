import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.ui.FlatLineBorder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;

public class PantallaAct extends JFrame {

    private JLabel imgCorporativa;
    private JPanel PanelPrincipal;
    private JPanel PanelContenido;
    private JPanel jpEliminarAct;
    private JScrollPane PanelDeTabla;
    private JTable tblActividades;
    private JScrollPane ScrollPanelRegAct;
    private JTable tblActSeleccionada;
    private JButton añadirActividadButton;
    private JButton eliminarButton;

    private static final ImageIcon logo = new ImageIcon("resources/imagenes/iconoMarino.png");
    ImageIcon imgCorporativaCabecera= new ImageIcon("resources/imagenes/cabeceraConTituloAct.png");

    public PantallaAct() { //Constructor
        super("Lista de actividades");
        setContentPane(PanelPrincipal);
        imgCorporativa.setIcon(imgCorporativaCabecera);
        JTableHeader headerActividades = tblActividades.getTableHeader();
        headerActividades.setPreferredSize(new Dimension(headerActividades.getPreferredSize().width, 40));
        JTableHeader headerEliminarAct = tblActSeleccionada.getTableHeader();
        headerEliminarAct.setPreferredSize(new Dimension(headerEliminarAct.getPreferredSize().width, 40));
        cargarDatos();
        init();
    }

    public void cargarDatos(){
        datosMainTable();
        datosActSeleccionada();
    }

    public void datosMainTable(){
        String[]header = {"ID", "Nombre", "Tipo", "Localidad", "Dificultad", "Precio"};
        String[][]rows = new String[3][header.length];

        //En lugar de las líneas de abajo, habra que recorrer con un bucle el List que nos devuelva DataManager
        rows[0][0] = "1";
        rows[0][1] = "Rafting en el rio";
        rows[0][2] = "Rafting";
        rows[0][3] = "Madrid";
        rows[0][4] = "Intermedio";
        rows[0][5] = "50.00€";


        rows[1][0] = "2";
        rows[1][1] = "Escalada en roca";
        rows[1][2] = "Escalada";
        rows[1][3] = "Barcelona";
        rows[1][4] = "Avanzado";
        rows[1][5] = "75.00€";

        rows[2][0] = "3";
        rows[2][1] = "Circuito de tirolinas";
        rows[2][2] = "Tirolina";
        rows[2][3] = "Sevilla";
        rows[2][4] = "Intermedio";
        rows[2][5] = "45.00€";


        tblActividades.setModel(new DefaultTableModel(rows, header));
        tblActividades.getTableHeader().setReorderingAllowed(false);
        tblActividades.setDefaultEditor(Override.class, null);

        asignarTamanyoColumnasAct();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblActividades.getColumnCount(); i++) {
            tblActividades.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void datosActSeleccionada(){
        String[]header = {"Nombre", "Tipo", "Localidad", "Dificultad", "Precio"};
        String[][]rows = new String[1][header.length];

        ///Todo De cara a tomar datos, propongo tomar la información de cada celda.
        rows[0][0] = "";
        rows[0][1] = "";
        rows[0][2] = "";
        rows[0][3] = "";
        rows[0][4] = "";

        tblActSeleccionada.setModel(new DefaultTableModel(rows, header));
        tblActSeleccionada.getTableHeader().setReorderingAllowed(false);
        tblActSeleccionada.setDefaultEditor(Override.class, null);

        asignarTamanyoColumnasActSeleccionada();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblActSeleccionada.getColumnCount(); i++) {
            tblActSeleccionada.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void tablaActProperties(){
        tblActividades.setShowGrid(true);
        tblActividades.setGridColor(Color.BLACK);
        tblActividades.getTableHeader().setOpaque(false);
        tblActividades.getTableHeader().setBackground(new Color(47, 75, 89));
        tblActividades.getTableHeader().setForeground(new Color(245, 159, 116));
        tblActividades.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
    }

    public void tablaActSeleccionadaProperties(){
        tblActSeleccionada.setShowGrid(true);
        tblActSeleccionada.setGridColor(Color.BLACK);
        tblActSeleccionada.getTableHeader().setOpaque(false);
        tblActSeleccionada.getTableHeader().setBackground(new Color(47, 75, 89));
        tblActSeleccionada.getTableHeader().setForeground(new Color(245, 159, 116));
        tblActSeleccionada.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
    }

    public void panelActSeleccionadaProperties(){
        jpEliminarAct.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "ACTIVIDAD", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        jpEliminarAct.setBorder(titleBorder);
    }

    public void asignarTamanyoColumnasAct(){
        TableColumn column;
        for (int i = 0; i < tblActividades.getColumnCount(); i++) {
            column = tblActividades.getColumnModel().getColumn(i);
            switch (i){
                case 0-> column.setPreferredWidth(53);
                case 1-> column.setPreferredWidth(200);
                case 2-> column.setPreferredWidth(250);
                case 3-> column.setPreferredWidth(250);
                case 4-> column.setPreferredWidth(100);
                case 5-> column.setPreferredWidth(150);
                case 6-> column.setPreferredWidth(200);
            }
        }
    }

    public void asignarTamanyoColumnasActSeleccionada(){
        TableColumn column;
        for (int i = 0; i < tblActSeleccionada.getColumnCount(); i++) {
            column = tblActSeleccionada.getColumnModel().getColumn(i);
            switch (i){
                case 0-> column.setPreferredWidth(200);
                case 1-> column.setPreferredWidth(250);
                case 2-> column.setPreferredWidth(250);
                case 3-> column.setPreferredWidth(100);
                case 4-> column.setPreferredWidth(150);
                case 5-> column.setPreferredWidth(200);
            }
        }
    }

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        inicio();
    }
    public void init(){
        tablaActProperties();
        tablaActSeleccionadaProperties();
        panelActSeleccionadaProperties();
    }

    private static void inicio() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new PantallaAct();
                frame.setSize(1480, 774);
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.setIconImage(logo.getImage());
            }
        });
    }
}
