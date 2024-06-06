import Objetos.Actividad;
import Objetos.Centro;
import Objetos.Tipos;
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
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaActividades extends JFrame {

    private JLabel imgCorporativa;
    private JPanel panelPrincipal;
    private JPanel panelContenido;
    private JPanel panelEliminarAct;
    private JScrollPane jspTabla;
    private JTable tblActividades;
    private JScrollPane ScrollPanelRegAct;
    private JTable tblActSeleccionada;
    private JButton btnAddActividad;
    private JButton btnEliminarActividad;
    private JPanel panelDatos;
    private JComboBox cmbLocalidad;
    private JComboBox cmbTipo;
    private JPanel panelDerecho;
    private JPanel panelTabla;

    private static final ImageIcon logo = new ImageIcon("resources/imagenes/logo.png");
    ImageIcon imgCorporativaCabecera= new ImageIcon("resources/imagenes/cabeceraConTituloAct.png");

    public PantallaActividades() { //Constructor
        super("Lista de actividades");
        init();
        cargarDatos();
        estilo();
        cargarListeners();
        panelActividadesProperties();
        rellenarTablaModificar();
    }

    public void init(){
        setSize(1480, 774);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(panelPrincipal);
        setIconImage(logo.getImage());
        imgCorporativa.setIcon(imgCorporativaCabecera);
    }

    private void estilo() {
        tablaActProperties();
        panelActividadesProperties();
        tablaActSeleccionadaProperties();
        panelActSeleccionadaProperties();
    }

    public void cargarDatos(){
        if (DataManager.getActividades() && DataManager.getCentros() && DataManager.getTipos()) {
            actualizaComboBox();
            datosMainTable(DataManager.getListActividades());
            datosActSeleccionada();
        }
    }

    private void cargarListeners() {
        btnAddActividad.addActionListener(addActividad());
        cmbCentro.addActionListener(filtrar());
        cmbTipo.addActionListener(filtrar());
        Utils.cursorPointerBoton(btnAddActividad);
        Utils.cursorPointerBoton(btnEliminarActividad);
    }

    // AVISO: Lo tengo que hacer usando los centros, ya que la actividad tiene idCentro, pero en caso de que haya dos centros en la misma ciudad, no se diferenciaran.
    private ActionListener filtrar() {
        return e -> {
            Tipos tipo = DataManager.getTipo(String.valueOf(cmbTipo.getSelectedItem()));
            Centro centro = DataManager.getCentroByLocalidad(String.valueOf(cmbCentro.getSelectedItem()));
            List<Actividad> actividades = DataManager.getListActividades();

            if (tipo != null) actividades = actividades.stream().filter(actividad -> actividad.getTipo() == tipo.getId()).toList();
            if (centro != null) actividades = actividades.stream().filter(actividad -> actividad.getIdCentro() == centro.getId()).toList();
            datosMainTable(actividades);
        };
    }

    private ActionListener addActividad() {
        return e -> {
            new PantallaCrearAct();
            dispose(); // ¿La dejamos abierta para que vea las ya existentes o no?
        };
    }

    public void datosMainTable(List<Actividad> actividades){
        String[]header = {"ID", "Nombre", "Tipo", "Localidad", "Dificultad", "Precio"};
        Object[][]rows = new Object[actividades.size()][header.length];

        int i = 0;
        for (Actividad actividad: actividades) {
            rows[i][0] = actividad.getId();
            rows[i][1] = actividad.getNombre();
            rows[i][2] = DataManager.getTipo(actividad.getTipo());
            rows[i][3] = DataManager.getLocalidad(actividad.getIdCentro());
            rows[i][4] = actividad.getDificultad();
            rows[i][5] = actividad.getPrecio();

            i++;
        }

        tblActividades.setModel(new DefaultTableModel(rows, header));
        tblActividades.getTableHeader().setReorderingAllowed(false);
        tblActividades.setDefaultEditor(Object.class, null);

        asignarTamanyoColumnasAct();
        // Centrar los datos
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int j = 0; j < tblActividades.getColumnCount(); j++) {
            tblActividades.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }

    }
    public void datosActSeleccionada(){
        String[]header = {"Nombre", "Tipo", "Localidad", "Dificultad", "Precio"};
        String[][]rows = new String[1][header.length];

        ///Todo De cara a tomar datos, propongo tomar la información de cada celda.
        rows[0][0] = "";
        rows[0][1] = "";
        rows[0][2] = "";
        rows[0][3] = "";
        rows[0][4] = "";

        tblActSeleccionada.setModel(new DefaultTableModel(rows, header));
        tblActSeleccionada.getTableHeader().setReorderingAllowed(false);
        tblActSeleccionada.setDefaultEditor(Object.class, null);

        asignarTamanyoColumnasActSeleccionada();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblActSeleccionada.getColumnCount(); i++) {
            tblActSeleccionada.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void tablaActProperties(){
        tblActividades.setShowGrid(true);
        tblActividades.setGridColor(Color.BLACK);
        tblActividades.getTableHeader().setOpaque(false);
        tblActividades.getTableHeader().setBackground(new Color(47, 75, 89));
        tblActividades.getTableHeader().setForeground(new Color(245, 159, 116));
        tblActividades.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));

        JTableHeader headerActividades = tblActividades.getTableHeader();
        headerActividades.setPreferredSize(new Dimension(headerActividades.getPreferredSize().width, 40));
    }
    public void tablaActSeleccionadaProperties(){
        tblActSeleccionada.setShowGrid(true);
        tblActSeleccionada.setGridColor(Color.BLACK);
        tblActSeleccionada.getTableHeader().setOpaque(false);
        tblActSeleccionada.getTableHeader().setBackground(new Color(47, 75, 89));
        tblActSeleccionada.getTableHeader().setForeground(new Color(245, 159, 116));
        tblActSeleccionada.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
        JTableHeader headerEliminarAct = tblActSeleccionada.getTableHeader();
        headerEliminarAct.setPreferredSize(new Dimension(headerEliminarAct.getPreferredSize().width, 40));
    }
    public void panelActSeleccionadaProperties(){
        panelEliminarAct.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "ACTIVIDAD", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        panelEliminarAct.setBorder(titleBorder);
    }

    public void panelActividadesProperties(){
        panelDatos.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "FILTRO", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        panelDatos.setBorder(titleBorder);
    }

    public void asignarTamanyoColumnasAct(){
        TableColumn column;
        for (int i = 0; i < tblActividades.getColumnCount(); i++) {
            column = tblActividades.getColumnModel().getColumn(i);
            switch (i){
                case 0, 6 -> column.setPreferredWidth(50);
                case 1, 2 -> column.setPreferredWidth(200);
                case 3, 5, 4 -> column.setPreferredWidth(150);
            }
        }
    }
    public void asignarTamanyoColumnasActSeleccionada(){
        TableColumn column;
        for (int i = 0; i < tblActSeleccionada.getColumnCount(); i++) {
            column = tblActSeleccionada.getColumnModel().getColumn(i);
            switch (i){
                case 0,5-> column.setPreferredWidth(200);
                case 1,2-> column.setPreferredWidth(250);
                case 3-> column.setPreferredWidth(100);
                case 4-> column.setPreferredWidth(150);
            }
        }
    }

    /**
     * Metodo para rellenar la tabla con los datos de la fila seleccionada de la tabla actividades
     */
    public void rellenarTablaModificar() {
        tblActividades.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = tblActividades.getSelectedRow();
                if (e.getClickCount() == 2 && row != -1) {
                    Actividad act = DataManager.getListActividades().get(row);
                    tblActSeleccionada.getModel().setValueAt(act.getNombre(), 0, 0);
                    tblActSeleccionada.getModel().setValueAt(act.getTipo(), 0, 1);
                    tblActSeleccionada.getModel().setValueAt(DataManager.getLocalidad(act.getIdCentro()), 0, 2);
                    tblActSeleccionada.getModel().setValueAt(act.getDificultad(), 0, 3);
                    tblActSeleccionada.getModel().setValueAt(act.getPrecio(), 0, 4);
                }
            }
        });
    }

    /**
     * Metodo para que el combobox de localidad se actualice si hay nuevas localidades
     */
    private void actualizaComboBox() {
        for (int i = 0; i < DataManager.getLocalidadesCentro().size(); i++) {
            cmbLocalidad.addItem(DataManager.getLocalidadesCentro().get(i));
            cmbTipo.addItem(DataManager.getTiposActividadesCentro().get(i));
        }
    }
}
