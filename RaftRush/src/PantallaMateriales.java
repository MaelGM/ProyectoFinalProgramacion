import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatLineBorder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaMateriales extends JFrame{
    private JPanel gestionMaterialesPane;
    private JLabel lblHeader;
    private JTextField txtFldID;
    private JTextField txtFldCantidad;
    private JButton btnSolicitarEntrega;
    private JTable tblMat;
    private JComboBox cbxProveedor;
    private JPanel jplNuePedido;

    ///ToDo, el tamaño del ComboBox
    public PantallaMateriales(){
        super("Gestión de Material");
        init();
        cargarDatos();
        loadListeners();
    }

    public void loadListeners(){
        tblMat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

    public void init(){
        setContentPane(gestionMaterialesPane);
        //Imágenes
        ImageIcon imgHeader = new ImageIcon("resources/imagenes/cabeceraConTituloMaterial.png");
        lblHeader.setIcon(imgHeader);
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());

        //Ventana
        setSize(1480, 770);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        tablaMaterialProperties();
        panelNuevoPedidoProperties();
        txtFldID.putClientProperty(FlatClientProperties.STYLE, "arc: 10");
        txtFldCantidad.putClientProperty(FlatClientProperties.STYLE, "arc: 10");
        cbxProveedor.putClientProperty(FlatClientProperties.STYLE, "arc: 10");

    }

    public void cargarDatos(){

        String[]header = {"Código", "Nombre", "Cantidad", "Precio"};
        String[][]rows = new String[3][header.length];

        //En lugar de las líneas de abajo, habra que recorrer con un bucle el List que nos devuelva DataManager
        rows[0][0] = "1";
        rows[0][1] = "Mosquetón tipo D";
        rows[0][2] = "40";
        rows[0][3] = "8";

        rows[1][0] = "2";
        rows[1][1] = "Arnés espeleo";
        rows[1][2] = "15";
        rows[1][3] = "45";

        rows[2][0] = "3";
        rows[2][1] = "Cuerda dinámica 20mts";
        rows[2][2] = "10";
        rows[2][3] = "40";

        tblMat.setModel(new DefaultTableModel(rows, header));
        tblMat.getTableHeader().setReorderingAllowed(false);
        tblMat.setDefaultEditor(Object.class, null);

        asignarTamanyoColumnas();
    }

    public void tablaMaterialProperties(){
        tblMat.setShowGrid(true);
        tblMat.setGridColor(Color.BLACK);
        tblMat.getTableHeader().setOpaque(false);
        tblMat.getTableHeader().setBackground(new Color(47, 75, 89));
        tblMat.getTableHeader().setForeground(new Color(245, 159, 116));
        tblMat.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
    }

    public void panelNuevoPedidoProperties(){
        jplNuePedido.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "Nuevo Pedido", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        jplNuePedido.setBorder(titleBorder);
    }

    public void asignarTamanyoColumnas(){
        TableColumn column;
        for (int i = 0; i < tblMat.getColumnCount(); i++) {
            column = tblMat.getColumnModel().getColumn(i);
            switch (i){
                case 0-> column.setPreferredWidth(90);
                case 1-> column.setPreferredWidth(400);
                case 2-> column.setPreferredWidth(150);
                case 3-> column.setPreferredWidth(150);
            }
        }
    }
}
