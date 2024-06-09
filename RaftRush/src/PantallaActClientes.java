import Objetos.Actividad;
import Objetos.Centro;
import Objetos.Tipo;
import com.formdev.flatlaf.ui.FlatLineBorder;
import Objetos.Usuario;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Clase destinada al funcionamiento y presentación de la pantalla encargada de listar las actividades de cara a los clientes.
 */
public class PantallaActClientes extends JFrame{
    private JPanel panelGeneral;
    private JLabel lblBG;
    private JPanel panelFill;
    private JButton btnVerReservas;
    private JButton btnReservar;
    private JPanel panelDataAct;
    private JPanel panelTabla;
    private JPanel panelData;
    private JPanel jplFiltro;
    private JPanel panelUsuario;
    private JLabel lblNombreUsu;
    private JComboBox cmbCentro;
    private JComboBox cmbTipo;
    private JTable tblActCli;
    private JPanel panelSuperior;
    private JPanel panelInferior;
    private JLabel lblCentro;
    private JLabel lblTipo;
    private JLabel lblUsuario;
    private JLabel lblNombre;
    private JLayeredPane jlpBackground;
    private DefaultTableModel model;
    private Actividad actividadSelected = null;

    /**
     * Constructor de la clase en donde se le asigna un título a la pantalla y se inicializa esta misma.
     * @param cliente Cliente que ha iniciado sesión
     */
    public PantallaActClientes(Usuario cliente){
        super("Actividades Clientes");
        init();
        cargarListners(cliente);
        estilo();
        background();
        cargarDato();

        lblNombre.setText(cliente.getNombre());
    }

    /**
     * Método encargado de la inicialización de la pantalla.
     */
    private void init() {
        setSize(1534,774);
        setContentPane(panelGeneral);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());
    }

    /**
     * Se cargan todos los listeners de todos los botones, mouseListener, windowClosing, comboBoxes...
     * @param cliente Le pasamos el cliente, ya que será necesario para algunos métodos posteriores.
     */
    private void cargarListners(Usuario cliente) {
        btnVerReservas.addActionListener(verReservas(cliente));
        btnReservar.addActionListener(verDetalles(cliente));

        cmbCentro.addActionListener(filtrar());
        cmbTipo.addActionListener(filtrar());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new PantallaInicial();
            }
        });
        lblUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PantallaPerfil(cliente);
                dispose();
            }
        });
        lblNombre.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PantallaPerfil(cliente);
                dispose();
            }
        });

        tblActCli.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblActCli.getSelectedRow();
                if (e.getClickCount() == 1 && row != -1) {
                    tblActCli.setSelectionBackground(new Color(102,206,195));
                } else if (e.getClickCount() == 2 && row != -1) {
                    tblActCli.setSelectionBackground(new Color(47,84,96));
                    actividadSelected = getListFiltrada().get(row);
                    JOptionPane.showMessageDialog(null, "Has selecionado la actividad " + actividadSelected.getNombre());
                }
            }
        });
        Utils.cursorPointerLabel(lblUsuario);
        Utils.cursorPointerLabel(lblNombre);
        Utils.cursorPointerBoton(btnReservar);
        Utils.cursorPointerBoton(btnVerReservas);
    }

    /**
     * Se comprueba el valor de los comboBoxes y se filtra la lista de actividades según estos valores. Después se llama
     * al método encargado de cargar la tabla pero pasándole la lista filtrada.
     * @return Se devuelve la propia acción del código
     */
    private ActionListener filtrar() {
        return e -> {
            Tipo tipo = DataManager.getTipoByName(String.valueOf(cmbTipo.getSelectedItem()));
            //Tipo tipo = DataManager.getTipo(String.valueOf(cmbTipo.getSelectedItem()));
            Centro centro = DataManager.getCentroByLocalidad(String.valueOf(cmbCentro.getSelectedItem()));
            List<Actividad> actividades = DataManager.getListActividades();

            if (tipo != null) actividades = actividades.stream().filter(actividad -> actividad.getTipo() == tipo).toList();
            if (centro != null) actividades = actividades.stream().filter(actividad -> actividad.getCentro() == centro).toList();
            cargarTabla(actividades);
        };
    }

    /**
     * Este método se usa para obtener la lista de actividades que se está mostrando en este momento. Obtiene los valores
     * por los que se está filtrando, filtra, y devuelve la lista de actividades ya filtrada.
     * @return Lista de actividades filtrada
     */
    private List<Actividad> getListFiltrada() {
        Tipo tipo = DataManager.getTipoByName(String.valueOf(cmbTipo.getSelectedItem()));
        //Tipo tipo = DataManager.getTipo(String.valueOf(cmbTipo.getSelectedItem()));
        Centro centro = DataManager.getCentroByLocalidad(String.valueOf(cmbCentro.getSelectedItem()));
        List<Actividad> actividades = DataManager.getListActividades();

        if (tipo != null) actividades = actividades.stream().filter(actividad -> actividad.getTipo() == tipo).toList();
        if (centro != null) actividades = actividades.stream().filter(actividad -> actividad.getCentro() == centro).toList();
        return actividades;
    }

    /**
     * Se cierra la pantalla actual y se abre otra con las reservas del cliente en el que estamos iniciados.
     * @param cliente Cliente con el que hemos iniciado sesión y lo usamos para ver solamente las reservas del cliente
     * @return Se devuelve la propia acción del código
     */
    private ActionListener verReservas(Usuario cliente){
        return e -> {
            new PantallaReservasClientes(cliente);
            dispose();
        };
    }

    /**
     * En este método comprobamos si hay alguna actividad seleccionada, y si la hay, nos lleva a otra pantalla, pero en caso
     * de no haber ninguna seleccionada, se mostrará un mensaje.
     * @param cliente Le pasamos el cliente, ya que en la pantalla posterior, nos hará falta en caso de querer hacer una reserva.
     * @return Se devuelve la propia acción del código
     */
    private ActionListener verDetalles(Usuario cliente){
            return e -> {
                if (actividadSelected != null) {
                    new PantallaDetallesAct(cliente, actividadSelected);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Selecciona una actividad primero", "Actividad", JOptionPane.INFORMATION_MESSAGE);
                }
            };
    }

    /**
     * Se cargan los datos de la BD junto con los comboBoxes y la tabla
     */
    public void cargarDato(){
        if (DataManager.getCentros() && DataManager.getTipos() && DataManager.getActividades()) {
            actualizaComboBox();
            cargarTabla(DataManager.getListActividades());
        }
    }

    /**
     * Se carga la tabla con la información de la lista que se le pasa y se definen algunas propiedades
     * @param actividades Lista de actividades con la información que se plasmará en la tabla
     */
    private void cargarTabla(List<Actividad> actividades){
        Object[][] data = new Object[actividades.size()][5];

        int i = 0;
        for (Actividad actividad : actividades) {
            data[i][0] = actividad.getNombre();
            data[i][1] = actividad.getTipo().getNombre();
            data[i][2] = actividad.getCentro().getLocalidad();
            data[i][3] = actividad.getDificultad();
            data[i][4] = actividad.getPrecio();

            i++;
        }

        model = new DefaultTableModel(data, new String[]{"Nombre", "Tipo", "Localidad", "Dificultad", "Precio"});
        tblActCli.setModel(model);

        tblActCli.getTableHeader().setReorderingAllowed(false);
        tblActCli.setDefaultEditor(Object.class,null);
        tblActCli.setEnabled(true);

    }

    /**
     * En este método se carga el estilo de la pantalla (Color, borde, fuente, etc)
     */
    private void estilo() {
        setBorderPanel();
        background();
        // Estilo de la tabla
        tblActCli.setShowGrid(true);//Mostrar grid color
        tblActCli.setGridColor(Color.black);
        tblActCli.getTableHeader().setOpaque(false);
        tblActCli.getTableHeader().setBackground(new Color(47, 75, 89));
        tblActCli.getTableHeader().setForeground(new Color(245, 159, 116));
        tblActCli.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int j = 0; j < tblActCli.getColumnCount(); j++) {
            tblActCli.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }
    }

    /**
     * Se define el borde del panel del filtro.
     */
    private void setBorderPanel() {
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "FILTRO", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        jplFiltro.setBorder(titleBorder);
    }

    /**
     * Se define el fondo de la pantalla.
     */
    private void background(){
        ImageIcon background = new ImageIcon("resources/imagenes/cabeceraActClientes.png");

        lblBG.setIcon(background);
    }

    /**
     * Metodo para que el combobox de localidad se actualice si hay nuevas localidades
     */
    private void actualizaComboBox() {
        for (int i = 0; i < DataManager.getLocalidadesCentro().size(); i++) {
            cmbCentro.addItem(DataManager.getLocalidadesCentro().get(i));
            cmbTipo.addItem(DataManager.getTiposActividadesCentro().get(i));
        }
    }
}
