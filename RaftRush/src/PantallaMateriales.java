import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;

public class PantallaMateriales extends JFrame{
    private JPanel gestionMaterialesPane;
    private JPanel jplTabla;
    private JScrollPane PanelDeTabla;
    private JButton btnVerEntregas;
    private JPanel jplModMaterial;
    private JScrollPane ScrollPanelRegAct;
    private JTable tblModMaterial;
    private JButton btnSolicitar;
    private JLabel lblHeader;
    private JButton btnModificar;
    private JTextField txtFldID;
    private JTextField txtFldCantidad;
    private JTable tblMat;
    private JComboBox cbxProveedor;
    private JPanel jplNuePedido;

    ///ToDo, el tamaño del ComboBox
    public PantallaMateriales(){
        super("Gestión de Material");
        init();
        cargarDatos();
        cargaModTabla();//ESTO SERIA TEMPORAL, ES PARA COMPROBAR QUE FUNCIONE LA TABLA AUTHOR-->HAKEEM
        loadListeners();
        cargarListeners();
    }

    private void cargarListeners() {
        btnVerEntregas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaEntregas();
            }
        });
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
        ImageIcon imgHeader = new ImageIcon("resources/imagenes/cabeceraConTituloEntregas.png");
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
        btnModificar.putClientProperty("JButton.buttonType", "roundRect");
        btnSolicitar.putClientProperty("JButton.buttonType", "roundRect");

    }

    public void cargarDatos(){

        String[]header = {"Código", "Nombre", "Cantidad", "Precio", "Nombre Centro"};
        String[][]rows = new String[3][header.length];

        //En lugar de las líneas de abajo, habra que recorrer con un bucle el List que nos devuelva DataManager
        rows[0][0] = "1";
        rows[0][1] = "Mosquetón tipo D";
        rows[0][2] = "40";
        rows[0][3] = "8";
        rows[0][4] = "Centro 1";

        rows[1][0] = "2";
        rows[1][1] = "Arnés espeleo";
        rows[1][2] = "15";
        rows[1][3] = "45";
        rows[1][4] = "centro 2";

        rows[2][0] = "3";
        rows[2][1] = "Cuerda dinámica 20mts";
        rows[2][2] = "10";
        rows[2][3] = "40";
        rows[2][4] = "centro 3";

        tblMat.setModel(new DefaultTableModel(rows, header));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblMat.getColumnCount(); i++) {
            tblMat.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tblMat.getTableHeader().setReorderingAllowed(false);
        tblMat.setDefaultEditor(Object.class, null);

        asignarTamanyoColumnas();
    }

    private void cargaModTabla(){
        tblModMaterial.setShowGrid(true);//Mostrar grid color
        String[]header = {"Nombre", "Cantidad", "Precio", "Nombre Centro"};
        String[][]rows = new String[1][header.length];

        //En lugar de las líneas de abajo, habra que recorrer con un bucle el List que nos devuelva DataManager
        rows[0][0] = "Mosquetón tipo D";
        rows[0][1] = "10";
        rows[0][2] = "25.00€";
        rows[0][3] = "centro 1";

        tblModMaterial.setModel(new DefaultTableModel(rows, header));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblModMaterial.getColumnCount(); i++) {
            tblModMaterial.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }


        tblModMaterial.getTableHeader().setOpaque(false);
        tblModMaterial.getTableHeader().setBackground(new Color(47, 75, 89));
        tblModMaterial.getTableHeader().setForeground(new Color(245, 159, 116));
        tblModMaterial.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));

        tblModMaterial.getTableHeader().setReorderingAllowed(false);
        tblModMaterial.setDefaultEditor(Object.class, null);

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
        jplModMaterial.putClientProperty(FlatClientProperties.STYLE, "arc: 8");

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
