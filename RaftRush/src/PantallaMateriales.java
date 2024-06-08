import Objetos.Centro;
import Objetos.Material;
import Objetos.Trabajador;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatLineBorder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class PantallaMateriales extends JFrame{
    private JPanel gestionMaterialesPane;
    private JButton btnVerEntregas;
    private JPanel panelModMaterial;
    private JScrollPane ScrollPanelRegAct;
    private JTable tblModMaterial;
    private JButton btnSolicitar;
    private JLabel lblHeader;
    private JButton btnModificar;
    private JTextField txtFldID;
    private JTextField txtFldCantidad;
    private JTable tblMat;
    private JPanel panelDatos;
    private JComboBox cmbCentro;
    private JPanel panelContenido;
    private JPanel panelDerecha;
    private JScrollPane PanelDeTabla;
    private JPanel jplTabla;
    private JComboBox cbxProveedor;
    private JPanel jplNuePedido;
    private static Material materialSeleccionado = null;

    ///ToDo, el tamaño del ComboBox
    public PantallaMateriales(){
        super("Gestión de Material");
        init();
        cargarDatos();
        cargaModTabla();//ESTO SERIA TEMPORAL, ES PARA COMPROBAR QUE FUNCIONE LA TABLA AUTHOR-->HAKEEM
        cargarListeners();
        panelMaterialesProperties();
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
        setLocationRelativeTo(null);

        tablaMaterialProperties();
        panelNuevoPedidoProperties();
        btnModificar.putClientProperty("JButton.buttonType", "roundRect");
        btnSolicitar.putClientProperty("JButton.buttonType", "roundRect");

    }

    private void cargarListeners() {
        btnVerEntregas.addActionListener(e -> new PantallaEntregas());
        Utils.cursorPointerBoton(btnModificar); // Te saldra para seleccionar un proveedor, en caso de que se haya aumentado la cantidad del stock
        Utils.cursorPointerBoton(btnSolicitar); // No me ha quedado claro que pasa si se le da a Solicitar --> Mael
        Utils.cursorPointerBoton(btnVerEntregas);
        btnModificar.addActionListener(asignarProveedor());
        cmbCentro.addActionListener(filtrar());
        rellenarTablaModificar();
    }

    private ActionListener filtrar() {
        return e -> {
            Centro centro = DataManager.getCentroByName(String.valueOf(cmbCentro.getSelectedItem()));
            List<Material> materiales = DataManager.getListMaterial();

            if (centro != null) materiales = materiales.stream().filter(material -> material.getCentro() == centro).toList();
            datosMaterialTable(materiales);
        };
    }

    private List<Material> filtrar(List<Material> lista) {
        Centro centro = DataManager.getCentroByName(String.valueOf(cmbCentro.getSelectedItem()));
        List<Material> materiales = DataManager.getListMaterial();

        if (centro != null) lista = materiales.stream().filter(material -> material.getCentro() == centro).toList();
        return lista;
    }

    private ActionListener asignarProveedor() {
        return e -> {
            // Crear los ComboBox con las opciones de contraseñas
            String[] passwordOptions = {"proveedor1", "proveedor2", "proveedor3"};
            JComboBox<String> passwordComboBox = new JComboBox<>(passwordOptions);

            // Crear el panel que contendrá los ComboBox
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Seleccione el proveedor de la entrega:"));
            panel.add(passwordComboBox);

            // Mostrar el cuadro de diálogo de entrada
            int option = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "Selección de proveedor",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );
        };
    }

    public void cargarDatos(){
        if (DataManager.getCentros() && DataManager.getMaterial()) {
            datosMaterialTable(DataManager.getListMaterial());
            cargarFiltro();
        }
    }

    private void cargarFiltro() {
        List<Centro> centros = DataManager.getListCentros();
        for (Centro c: centros) {
            cmbCentro.addItem(c.getNombre());
        }
    }

    public void datosMaterialTable(List<Material> materiales){
        String[]header = {"Código", "Nombre", "Cantidad", "Precio", "Nombre Centro"};
        Object[][]rows = new Object[materiales.size()][header.length];

        int j = 0;
        for (Material material: materiales) {
            rows[j][0] = material.getCodigo();
            rows[j][1] = material.getNombre();
            rows[j][2] = material.getCantidad();
            rows[j][3] = material.getPrecio();
            rows[j][4] = material.getCentro().getNombre();

            j++;
        }


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
        rows[0][0] = "";
        rows[0][1] = "";
        rows[0][2] = "";
        rows[0][3] = "";

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
        panelModMaterial.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "MATERIAL", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        panelModMaterial.setBorder(titleBorder);
    }

    public void panelMaterialesProperties(){
        panelDatos.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "FILTRO", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        panelDatos.setBorder(titleBorder);
    }

    public void asignarTamanyoColumnas(){
        TableColumn column;
        for (int i = 0; i < tblMat.getColumnCount(); i++) {
            column = tblMat.getColumnModel().getColumn(i);
            switch (i){
                case 0-> column.setPreferredWidth(50);
                case 1-> column.setPreferredWidth(150);
                case 2, 3 -> column.setPreferredWidth(70);
                case 4-> column.setPreferredWidth(300);
            }
        }
    }

    /**
     * Metodo para rellenar tabla de material seleccionado
     */
    public void rellenarTablaModificar() {
        tblMat.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = tblMat.getSelectedRow();
                if(e.getClickCount() == 2 && row != -1) {
                    materialSeleccionado = filtrar(DataManager.getListMaterial()).get(row);
                    tblModMaterial.getModel().setValueAt(materialSeleccionado.getNombre(), 0, 0);
                    tblModMaterial.getModel().setValueAt(materialSeleccionado.getCantidad(), 0, 1);
                    tblModMaterial.getModel().setValueAt(materialSeleccionado.getPrecio(), 0, 2);
                    tblModMaterial.getModel().setValueAt(DataManager.getNomCentro(materialSeleccionado.getCentro().getId()), 0, 3);
                }
            }
        });
    }
}
