import Objetos.*;
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
import java.util.Date;
import java.util.List;

/**
 * Esta es una clase con el propósito de hacer funcionar la pantalla de visualización de entregas.
 */
public class PantallaEntregas extends JFrame{
    private JPanel panelGeneral;
    private JLabel lblBG;
    private JPanel panelTabla;
    private JTable tblEntregas;
    private JPanel panelCentrado;
    private JPanel panelFiltro;
    private JLabel lblCentro;
    private JComboBox cmbCentro;
    private JComboBox cmbProveedor;
    private DefaultTableModel model;

    /**
     * Constructor de la clase en la que se le pone un título a la pantalla y se llaman a los métodos necesarios para inicializar la pantalla.
     */
    public PantallaEntregas(){
        super("Entregas");
        init();
        background();
        cargarListeners();
        cargarDato();
        estilo();
        tblEntregas.setShowGrid(true);//Mostrar grid color
    }

    /**
     * Se inicializa la pantalla asignándole el tamaño, icono, y más propiedades.
     */
    private void init() {
        setSize(1480,774);
        setContentPane(panelGeneral);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());
        setVisible(true);
    }

    /**
     * Estilo de la pantalla (Color, tamaño, fuente...)
     */
    private void estilo() {
        panelFiltro.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        tblEntregas.setGridColor(Color.black);
        tblEntregas.getTableHeader().setOpaque(false);
        tblEntregas.getTableHeader().setBackground(new Color(47, 75, 89));
        tblEntregas.getTableHeader().setForeground(new Color(245, 159, 116));
        tblEntregas.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
        tblEntregas.setShowGrid(true);//Mostrar grid color

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "FILTRO", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        panelFiltro.setBorder(titleBorder);
    }

    /**
     * Se cargan los listeners de los comboBox
     */
    private void cargarListeners() {
        //cmbProveedor.addActionListener(filtrar());
        cmbCentro.addActionListener(filtrar());
    }

    /**
     * Método de filtración en el que se obtienen todos los datos en tres listas diferentes y se filtran en caso de que el comboBox
     * sí que esté asignado. Después de ello, llama al método cargar tabla enviándole las listas filtradas.
     * @return Devuelve la propia acción que realiza el código
     */
    private ActionListener filtrar() {
        return e -> {
            String nombreCentroSeleccionado = (String) cmbCentro.getSelectedItem();
            Centro centro = DataManager.getCentroByName(nombreCentroSeleccionado);

            List<Date> fechas = DataManager.getFechasEntregas();
            List<Material> materiales = DataManager.getMaterialEntregas();
            List<Proveedor> proveedores = DataManager.getProveedoresEntregas();

            if (centro != null && materiales != null) {
                materiales = materiales.stream().filter(material -> material.getCentro().equals(centro)).toList();
            }
            cargarEntregaTable(fechas, materiales, proveedores);
        };
    }

    /**
     * Se cargan los datos necesarios de la BD, las opciones de los comboBoxes, y la tabla.
     */
    public void cargarDato(){
        if (DataManager.getCentros() && DataManager.getProveedor() && DataManager.getMaterial() && DataManager.getEntregas()) {
            actualizaComboBox();
            cargarEntregaTable(DataManager.getFechasEntregas(), DataManager.getMaterialEntregas(), DataManager.getProveedoresEntregas());
        }
    }

    /**
     * Primero se comprueba que las listas no son nulas ni están vacías. Después se mira cuál de las tres listas es más
     * corta para darle ese tamaño a la matriz, y posteriormente se rellena la tabla usando los datos de las 3 listas.
     * Por último, se centra el texto de las celdas y se definen algunas propiedades.
     * @param fechas Lista con todas las listas de las entregas.
     * @param materiales Lista con todos los materiales de las entregas.
     * @param proveedores Lista con todos los proveedores de las entregas.
     */
    private void cargarEntregaTable(List<Date> fechas, List<Material> materiales, List<Proveedor> proveedores) {
        if (fechas != null && materiales != null && proveedores != null) {
            // Verifico que todas las listas tengan al menos un elemento
            if (fechas.isEmpty() || materiales.isEmpty() || proveedores.isEmpty()) {
                return;
            }

            String[] header = {"Fecha de entrega", "Proveedor", "Material", "Centro"};

            // Obtengo la longitud mínima para evitar ArrayIndexOutOfBoundsException
            int longitud = Math.min(fechas.size(), Math.min(materiales.size(), proveedores.size()));
            Object[][] data = new Object[longitud][4];

            for (int i = 0; i < longitud; i++) {
                data[i][0] = fechas.get(i).toString();
                data[i][1] = proveedores.get(i).getNombre();
                data[i][2] = materiales.get(i).getNombre();
                data[i][3] = materiales.get(i).getCentro().getNombre();
            }

            tblEntregas.setModel(new DefaultTableModel(data, header));

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < tblEntregas.getColumnCount(); i++) {
                tblEntregas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            asignarTamanyoColumnasReservas();

            tblEntregas.getTableHeader().setReorderingAllowed(false);
            tblEntregas.setDefaultEditor(Object.class, null);
            tblEntregas.setEnabled(true);
        }
    }

    /**
     * Asignación del tamaño de las columnas de la tabla
     */
    public void asignarTamanyoColumnasReservas(){
        TableColumn column;
        for (int i = 0; i < tblEntregas.getColumnCount(); i++) {
            column = tblEntregas.getColumnModel().getColumn(i);
            switch (i){
                case 0 -> column.setPreferredWidth(100);
                case 1, 3 -> column.setPreferredWidth(400);
                case 2 -> column.setPreferredWidth(250);
            }
        }
    }

    /**
     * Se carga el fondo de la pantalla
     */
    private void background(){
        ImageIcon background = new ImageIcon("resources/imagenes/cabeceraConTituloEntregas.png");

        lblBG.setIcon(background);
    }

    /**
     * Se rellenan las opciones de los comboBoxes.
     */
    private void actualizaComboBox() {
        for (Centro centro : DataManager.getListCentros()) {
            cmbCentro.addItem(centro.getNombre());
        }
    }
}
