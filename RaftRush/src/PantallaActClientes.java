import Objetos.Actividad;
import Objetos.Centro;
import Objetos.Tipos;
import com.formdev.flatlaf.ui.FlatLineBorder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private JLayeredPane jlpBackground;
    private DefaultTableModel model;

    public PantallaActClientes() {
        super("Actividades Clientes");
        init();
        cargarListners();
        cargarDato();
        estilo();

    }

    private void init() {
        setSize(1534,774);
        setContentPane(panelGeneral);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());
    }

    private void cargarListners() {
        btnVerReservas.addActionListener(verReservas());
        btnReservar.addActionListener(verDetalles());
        cmbCentro.addActionListener(filtrar());
        cmbTipo.addActionListener(filtrar());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new PantallaInicial();
            }
        });
        lblNombreUsu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PantallaPerfil();
            }
        });
        Utils.cursorPointerLabel(lblNombreUsu);
        Utils.cursorPointerBoton(btnReservar);
        Utils.cursorPointerBoton(btnVerReservas);
    }

    // AVISO: Lo tengo que hacer usando los centros, ya que la actividad tiene idCentro, pero en caso de que haya dos centros en la misma ciudad, no se diferenciaran.
    private ActionListener filtrar() {
        return e -> {
            Tipos tipo = DataManager.getTipo(String.valueOf(cmbTipo.getSelectedItem()));
            Centro centro = DataManager.getCentroByLocalidad(String.valueOf(cmbCentro.getSelectedItem()));
            List<Actividad> actividades = DataManager.getListActividades();

            if (tipo != null) actividades = actividades.stream().filter(actividad -> actividad.getTipo() == tipo.getId()).toList();
            if (centro != null) actividades = actividades.stream().filter(actividad -> actividad.getIdCentro() == centro.getId()).toList();
            cargarTabla(actividades);
        };
    }

    private ActionListener verReservas(){
        return e -> {
            new PantallaReservasClientes();
            dispose();
        };
    }
    private ActionListener verDetalles(){
        return e -> {
            new PantallaDetallesAct();
            dispose();
        };
    }

    public void cargarDato(){
        if (DataManager.getActividades() && DataManager.getCentros() && DataManager.getTipos()) {
            cargarFiltro();
            cargarTabla(DataManager.getListActividades());
        }
    }

    private void cargarFiltro() {
        rellenarFiltroLocalidad();
        rellenarFiltroTipo();
    }

    private void rellenarFiltroLocalidad() {
        List<Centro> centros = DataManager.getListCentros();
        for (Centro c: centros) {
            cmbCentro.addItem(c.getLocalidad());
        }
    }
    private void rellenarFiltroTipo() {
        List<Tipos> tipos = DataManager.getListTipos();
        for (Tipos tipo: tipos) {
            cmbTipo.addItem(tipo.getNombre());
        }
    }

    private void cargarTabla(List<Actividad> actividades){
        Object[][] data = new Object[actividades.size()][5];

        int i = 0;
        for (Actividad actividad : actividades) {
            data[i][0] = actividad.getNombre();
            data[i][1] = DataManager.getTipo(actividad.getTipo());
            data[i][2] = DataManager.getLocalidad(actividad.getIdCentro());
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

    private void setBorderPanel() {
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "FILTRO", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        jplFiltro.setBorder(titleBorder);
    }

    private void background(){
        ImageIcon background = new ImageIcon("resources/imagenes/cabeceraActClientes.png");

        lblBG.setIcon(background);
    }
}
