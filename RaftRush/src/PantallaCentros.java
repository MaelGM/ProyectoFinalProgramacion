import Objetos.Centro;
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

public class PantallaCentros extends JFrame {
    private JPanel panelContenido;
    private JLabel imgCorporativa;
    private JTable tblCentros;
    private JPanel panelDatos;
    private JPanel panelPrincipal;
    private JTextField txtFldNombre;
    private JTextField txtFldLocalidad;
    private JTextField txtFldPresupuesto;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnAnyadir;
    private JPanel panelCentrado;
    private JScrollPane PanelDeTabla;
    private JPanel panelTabla;

    private static final ImageIcon logo = new ImageIcon("resources/imagenes/logo.png");
    private static final ImageIcon imgCorporativaCabecera= new ImageIcon("resources/imagenes/headerCentros.png");

    public PantallaCentros() {
        super("Lista de centros");
        init();
        estilo();
        cargarListeners();
        cargarDatos();
        panelProveedorProperties();
        tablaCentrosProperties();
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
        imgCorporativa.setIcon(imgCorporativaCabecera);
        JTableHeader headerActividades = tblCentros.getTableHeader();
        headerActividades.setPreferredSize(new Dimension(headerActividades.getPreferredSize().width, 40));
    }

    private void cargarListeners() {
        Utils.cursorPointerBoton(btnAnyadir);
        Utils.cursorPointerBoton(btnEditar);
        Utils.cursorPointerBoton(btnEliminar);
    }

    public void cargarDatos(){
        if (DataManager.getCentros()) {
            datosMainTable();
        }
    }

    public void datosMainTable(){
        String[]header = {"ID", "Nombre", "Localidad", "Presupuesto"};
        Object[][]rows = new Object[DataManager.getListCentros().size()][header.length];

        int i = 0;
        for (Centro centro:DataManager.getListCentros()) {
            rows[i][0] = centro.getId();
            rows[i][1] = centro.getNombre();
            rows[i][2] = centro.getLocalidad();
            rows[i][3] = centro.getPresupuesto();
            i++;
        }

        tblCentros.setModel(new DefaultTableModel(rows, header));
        tblCentros.getTableHeader().setReorderingAllowed(false);
        tblCentros.setDefaultEditor(Object.class, null);

        asignarTamanyoColumnasCentros();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int j = 0; j < tblCentros.getColumnCount(); j++) {
            tblCentros.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }
    }

    public void asignarTamanyoColumnasCentros(){
        TableColumn column;
        for (int i = 0; i < tblCentros.getColumnCount(); i++) {
            column = tblCentros.getColumnModel().getColumn(i);
            switch (i){
                case 0-> column.setPreferredWidth(100);
                case 1, 4 -> column.setPreferredWidth(250);
                case 2, 3, 5-> column.setPreferredWidth(150);
                case 6-> column.setPreferredWidth(200);
            }
        }
    }

    public void panelProveedorProperties(){
        panelDatos.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "CENTRO", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        panelDatos.setBorder(titleBorder);
    }

    public void tablaCentrosProperties(){
        tblCentros.setShowGrid(true);
        tblCentros.setGridColor(Color.BLACK);
        tblCentros.getTableHeader().setOpaque(false);
        tblCentros.getTableHeader().setBackground(new Color(47, 75, 89));
        tblCentros.getTableHeader().setForeground(new Color(245, 159, 116));
        tblCentros.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
    }


}
