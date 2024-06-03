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
    private JPanel gestionCentrosPane;
    private JLabel imgCorporativa;
    private JTable tblCentros;
    private JPanel jplNueCentro;
    private JPanel panelPrincipal;
    private JPanel jpContenidoTabla;
    private JTextField txtFldNombre;
    private JTextField txtFldLocalidad;
    private JTextField txtFldPresupuesto;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnAnyadir;

    private static final ImageIcon logo = new ImageIcon("resources/imagenes/logo.png");
    ImageIcon imgCorporativaCabecera= new ImageIcon("resources/imagenes/headerCentros.png");

    public PantallaCentros() {
        super("Lista de centros");
        init();
        estilo();
        setContentPane(panelPrincipal);
        cargarDatos();
        panelProveedorProperties();
        tablaCentrosProperties();
    }

    private void estilo() {
        imgCorporativa.setIcon(imgCorporativaCabecera);
        JTableHeader headerActividades = tblCentros.getTableHeader();
        headerActividades.setPreferredSize(new Dimension(headerActividades.getPreferredSize().width, 40));
    }

    public void cargarDatos(){
        datosMainTable();
    }

    public void datosMainTable(){
        String[]header = {"ID", "Nombre", "Localidad", "Presupuesto"};
        String[][]rows = new String[3][header.length];

        //En lugar de las líneas de abajo, habra que recorrer con un bucle el List que nos devuelva DataManager
        rows[0][0] = "1";
        rows[0][1] = "RaftRush Barcelona";
        rows[0][2] = "Barcelona";
        rows[0][3] = "28000€";

        rows[1][0] = "2";
        rows[1][1] = "RaftRush Valencia";
        rows[1][2] = "Valencia";
        rows[1][3] = "15000€";

        rows[2][0] = "3";
        rows[2][1] = "RaftRush Madrid";
        rows[2][2] = "Madrid";
        rows[2][3] = "25000€";

        tblCentros.setModel(new DefaultTableModel(rows, header));
        tblCentros.getTableHeader().setReorderingAllowed(false);
        tblCentros.setDefaultEditor(Object.class, null);

        asignarTamanyoColumnasCentros();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblCentros.getColumnCount(); i++) {
            tblCentros.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void asignarTamanyoColumnasCentros(){
        TableColumn column;
        for (int i = 0; i < tblCentros.getColumnCount(); i++) {
            column = tblCentros.getColumnModel().getColumn(i);
            switch (i){
                case 0-> column.setPreferredWidth(100);
                case 1, 4 -> column.setPreferredWidth(250);
                case 2, 3-> column.setPreferredWidth(150);
                case 5-> column.setPreferredWidth(150);
                case 6-> column.setPreferredWidth(200);
            }
        }
    }

    public void panelProveedorProperties(){
        jplNueCentro.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "CENTRO", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        jplNueCentro.setBorder(titleBorder);
    }

    public void tablaCentrosProperties(){
        tblCentros.setShowGrid(true);
        tblCentros.setGridColor(Color.BLACK);
        tblCentros.getTableHeader().setOpaque(false);
        tblCentros.getTableHeader().setBackground(new Color(47, 75, 89));
        tblCentros.getTableHeader().setForeground(new Color(245, 159, 116));
        tblCentros.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
    }

    public void init(){
        setSize(1480, 774);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(logo.getImage());
    }
}
