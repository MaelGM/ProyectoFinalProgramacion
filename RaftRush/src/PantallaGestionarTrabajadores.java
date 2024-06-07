import Objetos.*;
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
import java.util.ArrayList;
import java.util.List;

public class PantallaGestionarTrabajadores extends JFrame {

    private JLabel lblHeader;
    private JTable tblTrabajadores;
    private JPanel panelPrincipal;
    private JPanel panelNuevoTrabajador;
    private JTable tblNuevoTrabajador;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnEdit;
    private JPanel panelHeader;
    private JPanel panelCentrado;
    private JPanel panelTabla;
    private JPanel panelTablaSelect;
    private JPanel panelBotones;
    private JPanel panelContenido;
    private JPanel panelSuperior;
    private JPanel panelFiltro;
    private JComboBox cmbCentro;
    private JLabel lblCentro;
    private DefaultTableModel model;
    private Trabajador trabajadorSeleccionado = null;


    public PantallaGestionarTrabajadores(){
        super("Gestión de trabajadores");
        init();
        estilo();
        cargarDatos();
        loadListeners();
    }

    public void init(){
        setContentPane(panelPrincipal);

        //Ventana
        setSize(1480, 774);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void estilo() {
        //Imágenes
        ImageIcon imgHeader = new ImageIcon("resources/imagenes/cabeceraConTituloTrabajadores.png");
        lblHeader.setIcon(imgHeader);
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());

        tablaTrabajadoresProperties();
        tablaNuevoTrabajadorProperties();
        panelNuevoTrabajadorProperties();
        panelFiltroBorder();
    }

    public void cargarDatos(){
        if (DataManager.getCentros() && DataManager.getTrabajador()) {
            cargarFiltro();
            datosMainTable(DataManager.getListTrabajador());
            datosNewTrabajador();
        }
    }

    private void cargarFiltro() {
        List<Centro> centros = DataManager.getListCentros();
        for (Centro c: centros) {
            cmbCentro.addItem(c.getNombre());
        }
    }

    public void loadListeners(){
        Utils.cursorPointerBoton(btnAdd);
        Utils.cursorPointerBoton(btnDelete);
        Utils.cursorPointerBoton(btnEdit);
        tblTrabajadores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        tblNuevoTrabajador.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {super.mouseClicked(e);
            }
        });
        cmbCentro.addActionListener(filtrar());
        btnAdd.addActionListener(pedirPassword());
        rellenarTablaModificar();
    }

    private ActionListener filtrar() {
        return e -> {
            Centro centro = DataManager.getCentroByName(String.valueOf(cmbCentro.getSelectedItem()));
            List<Trabajador> trabajadores = DataManager.getListTrabajador();

            if (centro != null) trabajadores = trabajadores.stream().filter(trabajador -> trabajador.getCentro() == centro).toList();
            datosMainTable(trabajadores);
        };
    }

    private ActionListener pedirPassword() {
        return e -> {
            JPasswordField passwordField = new JPasswordField();
            JPasswordField confirmPasswordField = new JPasswordField();

            // Crear el panel que contendrá los campos
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Introduzca su contraseña:"));
            panel.add(passwordField);
            panel.add(new JLabel("Repita la contraseña:"));
            panel.add(confirmPasswordField);

            // Mostrar el cuadro de diálogo de entrada
            int option = JOptionPane.showConfirmDialog(null, panel, "Creé la nueva contraseña",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            //TODO: Revisar si le ha dado a ok, y si le ha dado, revisar la contraseña es repetida
        };
    }

    public void datosMainTable(List<Trabajador> trabajadores){
        Object[][] data = new Object[trabajadores.size()][7];

        int j = 0;
        for (Trabajador trabajador:trabajadores) {
            data[j][0] = trabajador.getNif();
            data[j][1] = trabajador.getNombre();
            data[j][2] = trabajador.getApellido();
            data[j][3] = trabajador.getEdad();
            data[j][4] = trabajador.getSalario();
            data[j][5] = trabajador.getCentro().getNombre();

            j++;
        }

        model = new DefaultTableModel(data, new String[]{"NIF", "Nombre", "Apellidos", "Edad", "Salario", "Centro"});

        tblTrabajadores.setModel(model);
        tblTrabajadores.getTableHeader().setReorderingAllowed(false);
        tblTrabajadores.setDefaultEditor(Object.class, null);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblTrabajadores.getColumnCount(); i++) {
            tblTrabajadores.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }


        asignarTamanyoColumnasTrabajadores();
    }

    public void datosNewTrabajador(){
        String[]header = {"NIF","Nombre", "Apellidos", "Edad", "Salario", "Centro"};
        String[][]rows = new String[1][header.length];

        ///Todo De cara a tomar datos, propongo tomar la información de cada celda.
        rows[0][0] = "";
        rows[0][1] = "";
        rows[0][2] = "";
        rows[0][3] = "";
        rows[0][4] = "";

        tblNuevoTrabajador.setModel(new DefaultTableModel(rows, header));
        tblNuevoTrabajador.getTableHeader().setReorderingAllowed(false);
        tblNuevoTrabajador.setDefaultEditor(Override.class, null);

        asignarTamanyoColumnasNuevoTrabajador();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblNuevoTrabajador.getColumnCount(); i++) {
            tblNuevoTrabajador.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void tablaTrabajadoresProperties(){
        tblTrabajadores.setShowGrid(true);
        tblTrabajadores.setGridColor(Color.BLACK);
        tblTrabajadores.getTableHeader().setOpaque(false);
        tblTrabajadores.getTableHeader().setBackground(new Color(47, 75, 89));
        tblTrabajadores.getTableHeader().setForeground(new Color(245, 159, 116));
        tblTrabajadores.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));

        JTableHeader headerActividades = tblTrabajadores.getTableHeader();
        headerActividades.setPreferredSize(new Dimension(headerActividades.getPreferredSize().width, 40));
    }

    private void panelFiltroBorder() {
        panelFiltro.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "FILTRO", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        panelFiltro.setBorder(titleBorder);
    }

    public void tablaNuevoTrabajadorProperties(){
        tblNuevoTrabajador.setShowGrid(true);
        tblNuevoTrabajador.setGridColor(Color.BLACK);
        tblNuevoTrabajador.getTableHeader().setOpaque(false);
        tblNuevoTrabajador.getTableHeader().setBackground(new Color(47, 75, 89));
        tblNuevoTrabajador.getTableHeader().setForeground(new Color(245, 159, 116));
        tblNuevoTrabajador.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));

        JTableHeader headerActividades = tblNuevoTrabajador.getTableHeader();
        headerActividades.setPreferredSize(new Dimension(headerActividades.getPreferredSize().width, 40));
    }

    public void panelNuevoTrabajadorProperties(){
        panelNuevoTrabajador.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "TRABAJADOR", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        panelNuevoTrabajador.setBorder(titleBorder);
    }

    public void asignarTamanyoColumnasTrabajadores(){
        TableColumn column;
        for (int i = 0; i < tblTrabajadores.getColumnCount(); i++) {
            column = tblTrabajadores.getColumnModel().getColumn(i);
            switch (i){
                case 0, 4, 3, 1, 2 -> column.setPreferredWidth(40);
                case 5 -> column.setPreferredWidth(200);
            }
        }
    }

    public void asignarTamanyoColumnasNuevoTrabajador(){
        TableColumn column;
        for (int i = 0; i < tblNuevoTrabajador.getColumnCount(); i++) {
            column = tblNuevoTrabajador.getColumnModel().getColumn(i);
            switch (i){
                case 0,4 -> column.setPreferredWidth(150);
                case 1,2 -> column.setPreferredWidth(250);
                case 3 -> column.setPreferredWidth(100);
                case 5 -> column.setPreferredWidth(200);
            }
        }
    }

    /**
     * Metodo para rellenar la tabla con un trabajador seleccionado
     */
    public void rellenarTablaModificar() {
        tblTrabajadores.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = tblTrabajadores.getSelectedRow();
                if (e.getClickCount() == 2 && row != -1) {
                    trabajadorSeleccionado = DataManager.getListTrabajador().get(row);
                    tblNuevoTrabajador.getModel().setValueAt(trabajadorSeleccionado.getNif(), 0, 0);
                    tblNuevoTrabajador.getModel().setValueAt(trabajadorSeleccionado.getNombre(), 0, 1);
                    tblNuevoTrabajador.getModel().setValueAt(trabajadorSeleccionado.getApellido(), 0, 2);
                    tblNuevoTrabajador.getModel().setValueAt(trabajadorSeleccionado.getEdad(), 0, 3);
                    tblNuevoTrabajador.getModel().setValueAt(trabajadorSeleccionado.getSalario(), 0, 4);
                    tblNuevoTrabajador.getModel().setValueAt(DataManager.getNomCentro(trabajadorSeleccionado.getIdCentro()), 0, 5);
                }
            }
        });
    }
}
