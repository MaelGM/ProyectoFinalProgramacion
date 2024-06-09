import Excepciones.ExceptionUsuario;
import Objetos.*;
import Excepciones.ExceptionUsuario;
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
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Esta clase es la encargada de hacer funcionar y modificar estéticamente la pantalla de gestión de trabajadores.
 */
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
    public static int posicion = 0;

    /**
     * Constructor de la clase donde se llaman a los métodos necesarios para inicializar la pantalla. También se
     * define el título de la pantalla
     */
    public PantallaGestionarTrabajadores(){
        super("Gestión de trabajadores");
        init();
        estilo();
        cargarDatos();
        loadListeners();
    }

    /**
     * Inicialización de la pantalla con algunas de sus propiedades
     */
    public void init(){
        setContentPane(panelPrincipal);

        //Ventana
        setSize(1480, 774);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * Estilo de la pantalla, como lo son las imágenes o las propiedades de las tablas
     */
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

    /**
     * Se cargan los datos necesarios (centros y trabajadores) y se carga la tabla superior, inferior y los valores del comboBox.
     */
    public void cargarDatos(){
        if (DataManager.getCentros() && DataManager.getTrabajador()) {
            cargarFiltro();
            datosMainTable(DataManager.getListTrabajador());
            datosNewTrabajador();
        }
    }

    /**
     * Se rellenan las opciones del comboBox
     */
    private void cargarFiltro() {
        List<Centro> centros = DataManager.getListCentros();
        for (Centro c: centros) {
            cmbCentro.addItem(c.getNombre());
        }
    }

    /**
     * Se cargan todos los listeners de los botones o algunos de las tablas
     */
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
        btnEdit.addActionListener(editarTrabajador());
        btnDelete.addActionListener(eliminarTrabajador());
    }
    /**
     * ActionListener encargado de eliminar al trabajador seleccionado
     * @return Devuelve la acción de eliminar el trabajador
     */
    private ActionListener eliminarTrabajador(){
        return e -> {
            if (!checkTextFields()) {
                JOptionPane.showMessageDialog(null,"No has elegido a un paciente");
            }else{
                int opcion = JOptionPane.showConfirmDialog(null,"Estas seguro que deseas eliminar a ese trabajador : " + trabajadorSeleccionado.getNif());
                System.out.println(opcion);
                if (opcion == 0) {
                    JOptionPane.showMessageDialog(null, "Eliminando al paciente: " + trabajadorSeleccionado.getNif());
                    int trabajadorBorrar = DataManager.borrarTrabajador(trabajadorSeleccionado.getNif());
                    if (trabajadorBorrar == 0) {
                        JOptionPane.showMessageDialog(null, "ERROR. No se puedo borrar el trabajador null.");
                    }else{
                        JOptionPane.showMessageDialog(null, "Trabajador borrado correctamente");
                    }
                    datosMainTable(DataManager.getListTrabajador());
                }
            }
        };
    }
    /**
     * ActionListener encargado de editar al trabajador seleccionado
     * @return Devuelve la acción de editar el trabajador
     */
    private ActionListener editarTrabajador(){
        return e -> {
            if (checkTextFields()) {
                String nif = (String) tblNuevoTrabajador.getModel().getValueAt(0, 0);
                String nombre = (String) tblNuevoTrabajador.getModel().getValueAt(0, 1);
                String apellido = (String) tblNuevoTrabajador.getModel().getValueAt(0, 2);
                Object edadO = tblNuevoTrabajador.getModel().getValueAt(0, 3);
                int edad = Integer.parseInt(edadO.toString());
                Object salarioO = tblNuevoTrabajador.getModel().getValueAt(0, 4);
                double salario = Double.parseDouble(salarioO.toString());
                Centro centro = DataManager.getCentroByName((String) tblNuevoTrabajador.getModel().getValueAt(0, 5));
                try {
                    Trabajador tempTrabajador = new Trabajador(nif, trabajadorSeleccionado.getContrasenya(), nombre, apellido, salario,edad,centro);
                    if(DataManager.editarTrabajador(tempTrabajador)){

                        DataManager.getListTrabajador().get(posicion).setNombre(tempTrabajador.getNombre());
                        DataManager.getListTrabajador().get(posicion).setApellido(tempTrabajador.getApellido());
                        DataManager.getListTrabajador().get(posicion).setSalario(tempTrabajador.getSalario());
                        DataManager.getListTrabajador().get(posicion).setEdad(tempTrabajador.getEdad());
                        DataManager.getListTrabajador().get(posicion).setCentro(tempTrabajador.getCentro());

                        JOptionPane.showMessageDialog(null, "Se han actualizado los datos del trabajador",
                                "Actualización BBDD", JOptionPane.INFORMATION_MESSAGE);

                        datosMainTable(DataManager.getListTrabajador());
                    }
                } catch (ExceptionUsuario ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }

    /**
     * ActionListener encargado de filtrar cuando se cambie el valor del comboBox
     * @return Devuelve la acción que ejecutara el comboBox
     */
    private ActionListener filtrar() {
        return e -> {
            Centro centro = DataManager.getCentroByName(String.valueOf(cmbCentro.getSelectedItem()));
            List<Trabajador> trabajadores = DataManager.getListTrabajador();

            if (centro != null) trabajadores = trabajadores.stream().filter(trabajador -> trabajador.getCentro() == centro).toList();
            datosMainTable(trabajadores);
        };
    }

    /**
     * Otro método de filtración en el que se le pasa una lista y se devuelve esa propia lista pero filtrada.
     * @param lista Lista que se quiere filtrar
     * @return Lista de trabajadores ya filtrada
     */
    private List<Trabajador> filtrar(List<Trabajador> lista) {
        Centro centro = DataManager.getCentroByName(String.valueOf(cmbCentro.getSelectedItem()));
        if (centro != null) lista = DataManager.getListTrabajador().stream().filter(trabajador -> trabajador.getCentro() == centro).toList();

        return lista;
    }

    /**
     * ActionListener que se encarga de que cuando se quiera añadir un trabajador, aparezca una pantalla solicitando que se asigne una
     * contraseña a ese nuevo trabajador. Se comprueba que ambos passwordField son iguales y se leen todos los datos para
     * posteriormente crear una nueva cuenta trabajador.
     * @return Devuelve la propia acción
     */
    private ActionListener pedirPassword() {
        return e -> {
            if (checkTextFields()) {
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

                if (option == 0 && new String(passwordField.getPassword()).equals(new String(confirmPasswordField.getPassword()))) {
                    String nif = (String) tblNuevoTrabajador.getModel().getValueAt(0, 0);
                    String nombre = (String) tblNuevoTrabajador.getModel().getValueAt(0, 1);
                    String apellido = (String) tblNuevoTrabajador.getModel().getValueAt(0, 2);
                    Object edadO = tblNuevoTrabajador.getModel().getValueAt(0, 3);
                    int edad = Integer.parseInt((String) edadO);
                    Object salarioO = tblNuevoTrabajador.getModel().getValueAt(0, 4);
                    double salario = Double.parseDouble((String) salarioO);
                    int idCentro = DataManager.getIdCentroByName((String) tblNuevoTrabajador.getModel().getValueAt(0, 5));
                    String contrasenya = "";
                    try {
                        contrasenya = PasswordUtils.hashPassword(new String(passwordField.getPassword()));
                    } catch (NoSuchAlgorithmException ex) {
                        throw new RuntimeException(ex);
                    }


                    if (DataManager.agregarTrabajador(nif, nombre, apellido, edad, salario, idCentro, contrasenya)) {
                        JOptionPane.showMessageDialog(null, "Nuevo trabajador agregado", "Trabajador", JOptionPane.INFORMATION_MESSAGE);
                        datosMainTable(DataManager.getListTrabajador());
                    }
                }
        }
        };
    }

    /**
     * Se revisa si hay alguna celda vacía para asi evitar errores. Muestra un mensaje en caso de detectar un error.
     * @return True en caso de no haber ninguna celda vacía, false en caso contrario.
     */
    private boolean checkTextFields() {
        if (tblNuevoTrabajador.getModel().getValueAt(0,0).equals("")
                || tblNuevoTrabajador.getModel().getValueAt(0,1).equals("")
                || tblNuevoTrabajador.getModel().getValueAt(0,2).equals("")
                || tblNuevoTrabajador.getModel().getValueAt(0,3).equals("")
                || tblNuevoTrabajador.getModel().getValueAt(0,4).equals("")
                || tblNuevoTrabajador.getModel().getValueAt(0,5).equals("")) {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos", "Error en los datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }else return true;
    }

    /**
     * Método encargado de cargar los trabajadores en la tabla, cargar algunas propiedades de la tabla, hacerla visible,
     * centrar el texto de las celdas...
     * @param trabajadores Lista con la información de los trabajadores que se plasmarán en la tabla.
     */
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

    /**
     * Propiedades, header y texto centrado de la tabla inferior.
     */
    public void datosNewTrabajador(){
        String[]header = {"NIF","Nombre", "Apellidos", "Edad", "Salario", "Centro"};
        String[][]rows = new String[1][header.length];

        rows[0][0] = "";
        rows[0][1] = "";
        rows[0][2] = "";
        rows[0][3] = "";
        rows[0][4] = "";

        tblNuevoTrabajador.setModel(new DefaultTableModel(rows, header));
        tblNuevoTrabajador.getTableHeader().setReorderingAllowed(false);

        asignarTamanyoColumnasNuevoTrabajador();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblNuevoTrabajador.getColumnCount(); i++) {
            tblNuevoTrabajador.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    /**
     * Propiedades de la tabla principal, como lo son su color y otras propiedades.
     */
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

    /**
     * Propiedades del panel del filtro. Principalmente, es la creación del borde
     */
    private void panelFiltroBorder() {
        panelFiltro.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "FILTRO", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        panelFiltro.setBorder(titleBorder);
    }

    /**
     * Propiedades de la tabla inferior
     */
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

    /**
     * Propiedades del panel inferior. (Color, borde, titulo...)
     */
    public void panelNuevoTrabajadorProperties(){
        panelNuevoTrabajador.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "TRABAJADOR", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        panelNuevoTrabajador.setBorder(titleBorder);
    }

    /**
     * Asignación del tamaño de las columnas de la tabla principal
     */
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

    /**
     * Asignación del tamaño de las columnas de la tabla secundaria.
     */
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
                Centro centro = DataManager.getCentroByName(String.valueOf(cmbCentro.getSelectedItem()));
                if (e.getClickCount() == 2 && row != -1) {
                    trabajadorSeleccionado = filtrar(DataManager.getListTrabajador()).get(row);
                    tblNuevoTrabajador.getModel().setValueAt(trabajadorSeleccionado.getNif(), 0, 0);
                    tblNuevoTrabajador.getModel().setValueAt(trabajadorSeleccionado.getNombre(), 0, 1);
                    tblNuevoTrabajador.getModel().setValueAt(trabajadorSeleccionado.getApellido(), 0, 2);
                    tblNuevoTrabajador.getModel().setValueAt(trabajadorSeleccionado.getEdad(), 0, 3);
                    tblNuevoTrabajador.getModel().setValueAt(trabajadorSeleccionado.getSalario(), 0, 4);
                    tblNuevoTrabajador.getModel().setValueAt(DataManager.getNomCentro(trabajadorSeleccionado.getCentro().getId()), 0, 5);

                    posicion = tblTrabajadores.getSelectedRow();
                }
            }
        });
    }
}
