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

    public PantallaActClientes(Usuario cliente){
        super("Actividades Clientes");
        init();
        cargarListners(cliente);
        background();
        cargarDato();

        lblNombre.setText(cliente.getNombre());
    }


    private void init() {
        setSize(1534,774);
        setContentPane(panelGeneral);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());
    }

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
                    actividadSelected = DataManager.getListActividades().get(row);
                    JOptionPane.showMessageDialog(null, "Has selecionado la actividad " + actividadSelected.getNombre());
                }
            }
        });
        Utils.cursorPointerLabel(lblUsuario);
        Utils.cursorPointerLabel(lblNombre);
        Utils.cursorPointerBoton(btnReservar);
        Utils.cursorPointerBoton(btnVerReservas);
    }

    // AVISO: Lo tengo que hacer usando los centros, ya que la actividad tiene idCentro, pero en caso de que haya dos centros en la misma ciudad, no se diferenciaran.
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

    private ActionListener verReservas(Usuario cliente){
        return e -> {
            new PantallaReservasClientes(cliente, actividadSelected);
            dispose();
        };
    }
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

    public void cargarDato(){
        if (DataManager.getCentros() && DataManager.getTipos() && DataManager.getActividades()) {
            actualizaComboBox();
            cargarTabla(DataManager.getListActividades());
        }
    }

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

    /**
     * Metodo para que el combobox de localidad se actualice si hay nuevas localidades
     */
    private void actualizaComboBox() {
        for (int i = 0; i < DataManager.getLocalidadesCentro().size(); i++) {
            cmbCentro.addItem(DataManager.getLocalidadesCentro().get(i));
            cmbTipo.addItem(DataManager.getTiposActividadesCentro().get(i));
        }
    }

    /**
     * Metodo para rellenar la tabla con los datos de la fila seleccionada de la tabla actividades
     */
    public void getSelectedAct() {
        tblActividades.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = tblActividades.getSelectedRow();
                if (e.getClickCount() == 2 && row != -1) {
                    actSeleccionada = filtrar(DataManager.getListActividades()).get(row);
                    tblActSeleccionada.getModel().setValueAt(actSeleccionada.getNombre(), 0, 0);
                    tblActSeleccionada.getModel().setValueAt(actSeleccionada.getTipo().getNombre(), 0, 1);
                    tblActSeleccionada.getModel().setValueAt(DataManager.getLocalidad(actSeleccionada.getCentro().getId()), 0, 2);
                    tblActSeleccionada.getModel().setValueAt(actSeleccionada.getDificultad(), 0, 3);
                    tblActSeleccionada.getModel().setValueAt(actSeleccionada.getPrecio(), 0, 4);
                }
            }
        });
    }
}
