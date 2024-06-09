import Excepciones.ExceptionCentro;
import Excepciones.ExceptionProveedor;
import Objetos.Centro;
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
 * Esta clase es la encargada de hacer funcionar y modificar estéticamente la pantalla de gestión de centros.
 */
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
    private static Centro centroSeleccionado = null;
    public static int posicion = 0;

    private static final ImageIcon logo = new ImageIcon("resources/imagenes/logo.png");
    private static final ImageIcon imgCorporativaCabecera= new ImageIcon("resources/imagenes/headerCentros.png");

    /**
     * Constructor de la clase donde se llaman a los métodos necesarios para inicializar la pantalla. También se asigna un título
     * a la pantalla
     */
    public PantallaCentros() {
        super("Lista de centros");
        init();
        estilo();
        cargarListeners();
        cargarDatos();
        panelProveedorProperties();
        tablaCentrosProperties();
    }

    /**
     * Método encargado de inicializar la pantalla asignando algunos atributos a la misma, como lo son el tamaño.
     */
    public void init(){
        setSize(1480, 774);
        setContentPane(panelPrincipal);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(logo.getImage());
    }

    /**
     * Método en el que se cargan algunas propiedades estéticas, como las imágenes.
     */
    private void estilo() {
        imgCorporativa.setIcon(imgCorporativaCabecera);
        JTableHeader headerActividades = tblCentros.getTableHeader();
        headerActividades.setPreferredSize(new Dimension(headerActividades.getPreferredSize().width, 40));
    }

    /**
     * Se cargan todos los listeners de los botones y el mouseListenes de la tabla.
     */
    private void cargarListeners() {
        Utils.cursorPointerBoton(btnAnyadir);
        Utils.cursorPointerBoton(btnEditar);
        btnAnyadir.addActionListener(addCentro());
        btnEditar.addActionListener(editCentro());
        rellenarTxtField();
    }

    /**
     * Se cargan los datos de la BD (Centros) y posteriormente la tabla.
     */
    public void cargarDatos(){
        if (DataManager.getCentros()) {
            datosMainTable();
        }
    }

    /**
     * Se crea el header, se obtiene la lista de centros y se rellena la tabla. También se asignan algunas propiedades de la tabla
     * y se centra el texto de las celdas.
     */
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

    /**
     * Se asigna el tamaño de las columnas de la tabla
     */
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

    /**
     * Propiedades del panel principal
     */
    public void panelProveedorProperties(){
        panelDatos.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "CENTRO", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        panelDatos.setBorder(titleBorder);
    }

    /**
     * Propiedades de la tabla principal
     */
    public void tablaCentrosProperties(){
        tblCentros.setShowGrid(true);
        tblCentros.setGridColor(Color.BLACK);
        tblCentros.getTableHeader().setOpaque(false);
        tblCentros.getTableHeader().setBackground(new Color(47, 75, 89));
        tblCentros.getTableHeader().setForeground(new Color(245, 159, 116));
        tblCentros.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
    }

    /**
     * Metodo para chequear que los texfields no esten vacios.
     * @return true si estan completos, false si no.
     */
    private boolean checkTextFields() {
        if (txtFldNombre.getText() == null
                || txtFldLocalidad.getText() == null
                || txtFldPresupuesto.getText() == null) {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos", "Error en los datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }else return true;
    }

    /**
     * Metodo para el boton de añadir centro
     * @return ActionListener
     */
    private ActionListener addCentro() {
        return e -> {
            centroSeleccionado = getSelectedCentro();
            if (centroSeleccionado != null) {
                if (DataManager.addCentro(centroSeleccionado))
                    JOptionPane.showMessageDialog(null, "Centro "+ centroSeleccionado.getNombre()+" añadido",
                            "AddCentro", JOptionPane.INFORMATION_MESSAGE);
                else JOptionPane.showMessageDialog(null, "No se ha podido añadir ningún centro", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                cargarDatos();
            } else JOptionPane.showMessageDialog(null, "No hay ningún centro seleccionado", "ERROR", JOptionPane.ERROR_MESSAGE);
        };
    }

    /**
     * Metodo para el boton de editar centro
     * @return ActionListener
     */
    private ActionListener editCentro() {
        return e -> {
            centroSeleccionado = getSelectedCentro();
            if (centroSeleccionado != null) {
                if (DataManager.editCentro(centroSeleccionado))
                    JOptionPane.showMessageDialog(null, "Centro "+ centroSeleccionado.getNombre()+" ha sido modificado con exito",
                            "Edit centro", JOptionPane.INFORMATION_MESSAGE);
                else JOptionPane.showMessageDialog(null, "No se ha podido modificar el centro", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                cargarDatos();
            } else JOptionPane.showMessageDialog(null, "No hay ningún centro seleccionado", "ERROR", JOptionPane.ERROR_MESSAGE);
        };
    }

    /**
     * Metodo para devolver un centro de los textField
     * @return Centro centro
     */
    private Centro getSelectedCentro() {
        if (!checkTextFields()) return null;
        else {
            String nombre = txtFldNombre.getText();
            String localidad = txtFldLocalidad.getText();
            double presupuesto = Double.parseDouble(txtFldPresupuesto.getText());
            try {
                return new Centro(nombre, localidad, presupuesto);
            } catch (ExceptionCentro e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Metodo para rellenar los txtField al seleccionar un centro
     */
    public void rellenarTxtField() {
        tblCentros.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = tblCentros.getSelectedRow();
                if (e.getClickCount() == 2 && row != -1) {
                    centroSeleccionado = DataManager.getListCentros().get(row);
                    txtFldNombre.setText(centroSeleccionado.getNombre());
                    txtFldLocalidad.setText(centroSeleccionado.getLocalidad());
                    txtFldPresupuesto.setText(String.valueOf(centroSeleccionado.getPresupuesto()));
                }
            }
        });
    }
}
