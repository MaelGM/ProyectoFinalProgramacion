import Objetos.Actividad;
import Objetos.Proveedor;
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

public class PantallaProveedores extends JFrame{
    private JPanel panelPrincipal;
    private JPanel PanelPrincipal;
    private JLabel imgCorporativa;
    private JTable tblProveedores;
    private JPanel panelEliminarProveedor;
    private JScrollPane ScrollPanelRegAct;
    private JTable tblProvSeleccionado;
    private JButton btnAnyadir;
    private JButton btnEditar;
    private JScrollPane PanelDeTabla;
    private JPanel PanelContenido;

    private static final ImageIcon logo = new ImageIcon("resources/imagenes/logo.png");
    ImageIcon imgCorporativaCabecera= new ImageIcon("resources/imagenes/headerProveedores.png");


    public PantallaProveedores() {
        super("Lista de proveedores");
        init();
        estilo();
        cargarDatos();
        tablaProveedoresProperties();
        tablaProveedorSeleccionadoProperties();
        panelProveedorProperties();
    }

    private void estilo() {
        imgCorporativa.setIcon(imgCorporativaCabecera);
        JTableHeader headerActividades = tblProveedores.getTableHeader();
        headerActividades.setPreferredSize(new Dimension(headerActividades.getPreferredSize().width, 40));
        JTableHeader headerEliminarAct = tblProvSeleccionado.getTableHeader();
        headerEliminarAct.setPreferredSize(new Dimension(headerEliminarAct.getPreferredSize().width, 40));
    }

    public void cargarDatos(){
        if (DataManager.getProveedor()) {
            datosMainTable();
            datosProveedor();
        }
    }

    public void datosMainTable(){
        String[]header = {"Codigo", "Nombre", "Telefono", "Email"};
        Object[][]rows = new Object[DataManager.getListProveedor().size()][header.length];

        int j = 0;
        for (Proveedor proveedor:DataManager.getListProveedor()) {
            rows[j][0] = proveedor.getId();
            rows[j][1] = proveedor.getNombre();
            rows[j][2] = proveedor.getTelefono();
            rows[j][3] = proveedor.getEmail();

            j++;
        }

        tblProveedores.setModel(new DefaultTableModel(rows, header));
        tblProveedores.getTableHeader().setReorderingAllowed(false);
        tblProveedores.setDefaultEditor(Object.class, null);

        asignarTamanyoColumnasProveedores();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblProveedores.getColumnCount(); i++) {
            tblProveedores.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void datosProveedor(){
        String[]header = {"Codigo", "Nombre", "Telefono", "Email"};
        String[][]rows = new String[1][header.length];

        ///Todo De cara a tomar datos, propongo tomar la información de cada celda.
        rows[0][0] = "";
        rows[0][1] = "";
        rows[0][2] = "";
        rows[0][3] = "";

        tblProvSeleccionado.setModel(new DefaultTableModel(rows, header));
        tblProvSeleccionado.getTableHeader().setReorderingAllowed(false);
        tblProvSeleccionado.setDefaultEditor(Object.class, null);

        asignarTamanyoColumnasProveedorSeleccionado();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblProvSeleccionado.getColumnCount(); i++) {
            tblProvSeleccionado.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void tablaProveedoresProperties(){
        tblProveedores.setShowGrid(true);
        tblProveedores.setGridColor(Color.BLACK);
        tblProveedores.getTableHeader().setOpaque(false);
        tblProveedores.getTableHeader().setBackground(new Color(47, 75, 89));
        tblProveedores.getTableHeader().setForeground(new Color(245, 159, 116));
        tblProveedores.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
    }

    public void tablaProveedorSeleccionadoProperties(){
        tblProvSeleccionado.setShowGrid(true);
        tblProvSeleccionado.setGridColor(Color.BLACK);
        tblProvSeleccionado.getTableHeader().setOpaque(false);
        tblProvSeleccionado.getTableHeader().setBackground(new Color(47, 75, 89));
        tblProvSeleccionado.getTableHeader().setForeground(new Color(245, 159, 116));
        tblProvSeleccionado.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
    }

    public void panelProveedorProperties(){
        panelEliminarProveedor.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "PROVEEDOR", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        panelEliminarProveedor.setBorder(titleBorder);
    }

    public void asignarTamanyoColumnasProveedores(){
        TableColumn column;
        for (int i = 0; i < tblProveedores.getColumnCount(); i++) {
            column = tblProveedores.getColumnModel().getColumn(i);
            switch (i){
                case 0-> column.setPreferredWidth(153);
                case 1, 4 -> column.setPreferredWidth(100);
                case 2, 3-> column.setPreferredWidth(250);
                case 5-> column.setPreferredWidth(150);
                case 6-> column.setPreferredWidth(200);
            }
        }
    }

    public void asignarTamanyoColumnasProveedorSeleccionado(){
        TableColumn column;
        for (int i = 0; i < tblProvSeleccionado.getColumnCount(); i++) {
            column = tblProvSeleccionado.getColumnModel().getColumn(i);
            switch (i){
                case 0, 3, 5 -> column.setPreferredWidth(200);
                case 1-> column.setPreferredWidth(250);
                case 2, 4 -> column.setPreferredWidth(150);
            }
        }
    }

    public void init(){
        setContentPane(panelPrincipal);
        setSize(1480, 774);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(logo.getImage());
    }
}
