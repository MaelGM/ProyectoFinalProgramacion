import Objetos.Actividad;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

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

    public PantallaActClientes(){
        super("Actividades Clientes");
        init();
        cargarListners();
        background();
        cargarDato();
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
        Utils.cursorPointerBoton(btnVerReservas);
        btnReservar.addActionListener(verDetalles());
        Utils.cursorPointerBoton(btnReservar);

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
        if (DataManager.getActividades()) {
            cargarActTabla();
        }
    }

    private void cargarActTabla(){
        Object[][] data = new Object[DataManager.getListActividades().size()][5];

        int i = 0;
        for (Actividad actividad:DataManager.getListActividades()) {
            data[i][0] = actividad.getNombre();
            data[i][1] = DataManager.getTipo(actividad.getTipo());
            data[i][2] = DataManager.getLocalidad(actividad.getIdCentro());
            data[i][3] = actividad.getDificultad();
            data[i][4] = actividad.getPrecio();

            i++;
        }

        model = new DefaultTableModel(data, new String[]{"Nombre", "Tipo", "Localidad", "Dificultad", "Precio"});
        tblActCli.setModel(model);

        //Color tableBG = new Color(110, 130, 141);

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


        tblActCli.getTableHeader().setReorderingAllowed(false);
        tblActCli.setDefaultEditor(Object.class,null);
        tblActCli.setEnabled(true);

    }

    private void background(){
        ImageIcon background = new ImageIcon("resources/imagenes/cabeceraActClientes.png");

        lblBG.setIcon(background);
    }
}
