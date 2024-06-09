import Objetos.Centro;
import Objetos.Material;
import Objetos.Proveedor;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatLineBorder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Clase encargada de hacer funcionar correctamente la pantalla de gestión de materiales.
 */
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

    /**
     * Constructor de la clase en la que se inicializa la pantalla y se le pone título a la misma.
     */
    public PantallaMateriales(){
        super("Gestión de Material");
        init();
        cargarDatos();
        cargaModTabla();
        cargarListeners();
        panelMaterialesProperties();
    }

    /**
     * Método encargado de inicializar la pantalla, con su respectivo tamaño, imágenes, etc...
     */
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

    /**
     * En este método se cargan todas las funciones de los botones o comboBoxes.
     */
    private void cargarListeners() {
        btnVerEntregas.addActionListener(e -> new PantallaEntregas());
        Utils.cursorPointerBoton(btnModificar); // Te saldra para seleccionar un proveedor, en caso de que se haya aumentado la cantidad del stock
        Utils.cursorPointerBoton(btnSolicitar); // No me ha quedado claro que pasa si se le da a Solicitar --> Mael
        Utils.cursorPointerBoton(btnVerEntregas);
        btnSolicitar.addActionListener(asignarProveedor());
        btnModificar.addActionListener(modificarMat());
        cmbCentro.addActionListener(filtrar());
        rellenarTablaModificar();
    }

    /**
     * Este método se encarga de que en cualquier momento de que se modifique el comboBox, se comprobara el valor del mismo y
     * se filtrará la lista de materiales según este valor. Posteriormente, se llama al método encargado de mostrar los materiales
     * en la tabla pero enviándole la lista filtrada.
     * @return Devuelve la propia acción de filtrado
     */
    private ActionListener filtrar() {
        return e -> {
            Centro centro = DataManager.getCentroByName(String.valueOf(cmbCentro.getSelectedItem()));
            List<Material> materiales = DataManager.getListMaterial();

            if (centro != null) materiales = materiales.stream().filter(material -> material.getCentro() == centro).toList();
            datosMaterialTable(materiales);
        };
    }

    /**
     * Este método es similar al anterior, pero no se ejecutará cuando se accione con el comboBox. Este método
     * modifica una lista.
     * @param lista Lista que se le envía con el propósito de ser modificada.
     * @return Lista que se le ha enviado pero modificada
     */
    private List<Material> filtrar(List<Material> lista) {
        Centro centro = DataManager.getCentroByName(String.valueOf(cmbCentro.getSelectedItem()));
        List<Material> materiales = DataManager.getListMaterial();

        if (centro != null) lista = materiales.stream().filter(material -> material.getCentro() == centro).toList();
        return lista;
    }


    private ActionListener modificarMat(){
        return e -> {

        };
    }
    /**
     * Este método se encarga de la aparición de una pequeña pantalla en la que el usuario elegirá a que proveedor le solicitamos la entrega
     * @return La acción del botón
     */
    private ActionListener asignarProveedor() {
        return e -> {
            List<String> proveedoresOptions = DataManager.getListProveedor().stream().map(Proveedor::getNombre).toList();
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(proveedoresOptions.toArray(new String[0]));
            JComboBox<String> comboBox = new JComboBox<>(comboBoxModel);

            for (int i = 0; i < (proveedoresOptions.size()/2)-5; i++) {
                comboBox.addItem(proveedoresOptions.get(i));
            }

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Seleccione el proveedor de la entrega:"));
            panel.add(comboBox);

            int option = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "Selección de proveedor",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );
        };
    }

    /**
     * Se cargan los datos de centros y materiales, y posteriormente, se carga la tabla y los valores del comboBox.
     */
    public void cargarDatos(){
        if (DataManager.getCentros() && DataManager.getMaterial() && DataManager.getProveedor()) {
            datosMaterialTable(DataManager.getListMaterial());
            cargarFiltro();
        }
    }

    /**
     * Se cargan las diferentes opciones del comboBox.
     */
    private void cargarFiltro() {
        List<Centro> centros = DataManager.getListCentros();
        for (Centro c: centros) {
            cmbCentro.addItem(c.getNombre());
        }
    }

    /**
     * Se le pasa una lista, y con esta lista, se define un header y se rellena la tabla de datos. Después se centra el
     * texto de las celdas y se asignan unas pocas propiedades de la tabla
     * @param materiales Lista con los materiales que se mostraran en la tabla.
     */
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

    /**
     * Se cargan las propiedades de la tabla inferior
     */
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

    /**
     * Aquí se carga el estilo de la tabla, como por ejemplo el color de esta.
     */
    public void tablaMaterialProperties(){
        tblMat.setShowGrid(true);
        tblMat.setGridColor(Color.BLACK);
        tblMat.getTableHeader().setOpaque(false);
        tblMat.getTableHeader().setBackground(new Color(47, 75, 89));
        tblMat.getTableHeader().setForeground(new Color(245, 159, 116));
        tblMat.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
    }

    /**
     * Otro método destinado al estilo del panel inferior de la pantalla
     */
    public void panelNuevoPedidoProperties(){
        panelModMaterial.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "MATERIAL", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        panelModMaterial.setBorder(titleBorder);
    }

    /**
     * Método con más estilo de otro panel
     */
    public void panelMaterialesProperties(){
        panelDatos.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "FILTRO", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        panelDatos.setBorder(titleBorder);
    }

    /**
     * Se asignan los tamaños de las columnas.
     */
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
     * Método para rellenar tabla de material seleccionado
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
