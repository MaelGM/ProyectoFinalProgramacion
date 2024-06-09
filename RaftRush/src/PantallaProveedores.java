import Excepciones.ExceptionProveedor;
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
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Esta clase es la encargada de hacer funcionar y modificar estéticamente la pantalla de gestión de proveedores.
 */
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
    private Proveedor proveedorSeleccionado = null;

    private static final ImageIcon logo = new ImageIcon("resources/imagenes/logo.png");
    ImageIcon imgCorporativaCabecera= new ImageIcon("resources/imagenes/headerProveedores.png");

    /**
     * Constructor de la clase donde se llaman a los métodos necesarios para inicializar la pantalla. También se asigna un título
     * a la pantalla
     */
    public PantallaProveedores() {
        super("Lista de proveedores");
        init();
        cargarDatos();
        cargarListeners();
        estilo();
        rellenarTablaModificar(); //todo metodo cargarListeners
    }

    /**
     * Se cargan los Listeners de los botones.
     */
    private void cargarListeners() {
        Utils.cursorPointerBoton(btnAnyadir);
        Utils.cursorPointerBoton(btnEditar);
        btnAnyadir.addActionListener(addProveedor());
        btnEditar.addActionListener(editProveedor());
    }

    /**
     * ActionListener que obtiene el proveedor con los datos de la tabla inferior, comprueba que el proveedor es válido
     * y lo edita. Se mostrará un mensaje diciendo si ha habido éxito o no en la modificación.
     * @return Devuelve el propio código encargado de hacer la función.
     */
    private ActionListener editProveedor() {
        return e -> {
            proveedorSeleccionado = getSelectedProveedor();
            if (proveedorSeleccionado != null) {
                if (DataManager.editProveedor(proveedorSeleccionado))
                    JOptionPane.showMessageDialog(null, "Proveedor "+proveedorSeleccionado.getNombre()+" ha sido modificado con exito",
                            "Edit proveedor", JOptionPane.INFORMATION_MESSAGE);
                else JOptionPane.showMessageDialog(null, "No se ha podido modificar el proveedor", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                cargarDatos();
            } else JOptionPane.showMessageDialog(null, "No hay ningún proveedor seleccionado", "ERROR", JOptionPane.ERROR_MESSAGE);
        };
    }

    /**
     * ActionListener que obtiene el proveedor con los datos de la tabla inferior, comprueba que el proveedor es válido
     * y lo añade. Se mostrará un mensaje diciendo si ha habido éxito o no a la hora de añadir el proveedor.
     * @return Devuelve el propio código encargado de hacer la función.
     */
    private ActionListener addProveedor() {
        return e -> {
            proveedorSeleccionado = getSelectedProveedor();
           if (proveedorSeleccionado != null) {
               if (DataManager.addProveedor(proveedorSeleccionado))
                   JOptionPane.showMessageDialog(null, "Proveedor "+proveedorSeleccionado.getNombre()+" añadido",
                           "AddProveedor", JOptionPane.INFORMATION_MESSAGE);
               else JOptionPane.showMessageDialog(null, "No se ha podido añadir ningún proveedor", "ERROR",
                       JOptionPane.ERROR_MESSAGE);
               cargarDatos();
           } else JOptionPane.showMessageDialog(null, "No hay ningún proveedor seleccionado", "ERROR", JOptionPane.ERROR_MESSAGE);
        };
    }

    /**
     * Método encargado de la creación de un proveedor a partir de la tabla secundaria. Primero se comprueba si las celdas tienen datos.
     * Después se intenta crear un proveedor.
     * @return Si se consigue crear el proveedor, se devuelve el propio Proveedor, pero en caso contrario, se devolverá nulo
     */
    private Proveedor getSelectedProveedor() {
        String nombre = tblProvSeleccionado.getModel().getValueAt(0,0).toString();
        String telefono = tblProvSeleccionado.getModel().getValueAt(0,1).toString();
        String email = tblProvSeleccionado.getModel().getValueAt(0,2).toString();
        if (nombre.equals("") || telefono.equals("") || email.equals("")) return null;
        else {
            try {
                return new Proveedor(nombre, telefono, email);
            } catch (ExceptionProveedor e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * Método en el que se cargan algunas propiedades estéticas, como las imágenes.
     */
    private void estilo() {
        imgCorporativa.setIcon(imgCorporativaCabecera);
        JTableHeader headerActividades = tblProveedores.getTableHeader();
        headerActividades.setPreferredSize(new Dimension(headerActividades.getPreferredSize().width, 40));
        JTableHeader headerEliminarAct = tblProvSeleccionado.getTableHeader();
        headerEliminarAct.setPreferredSize(new Dimension(headerEliminarAct.getPreferredSize().width, 40));
        tablaProveedoresProperties();
        tablaProveedorSeleccionadoProperties();
        panelProveedorProperties();
    }

    /**
     * Se cargan los proveedores de la BD y posteriormente se inicializan ambas tablas.
     */
    public void cargarDatos(){
        if (DataManager.getProveedor()) {
            datosMainTable();
            datosProveedor();
        }
    }

    /**
     * Se crea el header y se rellena la tabla. Posteriormente, se asignan unas pocas propiedades a la tabla y se centra el texto de las celdas.
     */
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

    /**
     * Se inicializa la tabla secundaria con las celdas vacías, centrando el texto y modificando algunas propiedades.
     */
    public void datosProveedor(){
        String[]header = {"Nombre", "Telefono", "Email"};
        String[][]rows = new String[1][header.length];

        ///Todo De cara a tomar datos, propongo tomar la información de cada celda.
        rows[0][0] = "";
        rows[0][1] = "";
        rows[0][2] = "";

        tblProvSeleccionado.setModel(new DefaultTableModel(rows, header));
        tblProvSeleccionado.getTableHeader().setReorderingAllowed(false);

        asignarTamanyoColumnasProveedorSeleccionado();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblProvSeleccionado.getColumnCount(); i++) {
            tblProvSeleccionado.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    /**
     * Propiedades de la tabla principal
     */
    public void tablaProveedoresProperties(){
        tblProveedores.setShowGrid(true);
        tblProveedores.setGridColor(Color.BLACK);
        tblProveedores.getTableHeader().setOpaque(false);
        tblProveedores.getTableHeader().setBackground(new Color(47, 75, 89));
        tblProveedores.getTableHeader().setForeground(new Color(245, 159, 116));
        tblProveedores.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
    }

    /**
     * Propiedades de la tabla secundaria.
     */
    public void tablaProveedorSeleccionadoProperties(){
        tblProvSeleccionado.setShowGrid(true);
        tblProvSeleccionado.setGridColor(Color.BLACK);
        tblProvSeleccionado.getTableHeader().setOpaque(false);
        tblProvSeleccionado.getTableHeader().setBackground(new Color(47, 75, 89));
        tblProvSeleccionado.getTableHeader().setForeground(new Color(245, 159, 116));
        tblProvSeleccionado.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
    }

    /**
     * Propiedades del panel inferior.
     */
    public void panelProveedorProperties(){
        panelEliminarProveedor.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "PROVEEDOR", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        panelEliminarProveedor.setBorder(titleBorder);
    }

    /**
     * Asignación del tamaño de las columnas de la tabla principal
     */
    public void asignarTamanyoColumnasProveedores(){
        TableColumn column;
        for (int i = 0; i < tblProveedores.getColumnCount(); i++) {
            column = tblProveedores.getColumnModel().getColumn(i);
            switch (i){
                case 0-> column.setPreferredWidth(53);
                case 1, 3 -> column.setPreferredWidth(250);
                case 2 -> column.setPreferredWidth(150);
            }
        }
    }

    /**
     * Asignación del tamaño de las columnas de la tabla secundaria.
     */
    public void asignarTamanyoColumnasProveedorSeleccionado(){
        TableColumn column;
        for (int i = 0; i < tblProvSeleccionado.getColumnCount(); i++) {
            column = tblProvSeleccionado.getColumnModel().getColumn(i);
            switch (i){
                case 0, 2 -> column.setPreferredWidth(300);
                case 1 -> column.setPreferredWidth(20);
            }
        }
    }

    /**
     * Método encargado de inicializar la pantalla asignando algunos atributos a la misma, como lo son el tamaño.
     */
    public void init(){
        setContentPane(panelPrincipal);
        setSize(1480, 774);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(logo.getImage());
    }

    /**
     * Metodo para rellenar la tabla de modificar proveedores
     */
    public void rellenarTablaModificar() {
        tblProveedores.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = tblProveedores.getSelectedRow();
                if (e.getClickCount() == 2 && row != -1) {
                    proveedorSeleccionado = DataManager.getListProveedor().get(row);
                    tblProvSeleccionado.getModel().setValueAt(proveedorSeleccionado.getNombre(), 0, 0);
                    tblProvSeleccionado.getModel().setValueAt(proveedorSeleccionado.getTelefono(), 0, 1);
                    tblProvSeleccionado.getModel().setValueAt(proveedorSeleccionado.getEmail(), 0, 2);
                }
            }
        });
    }
}
