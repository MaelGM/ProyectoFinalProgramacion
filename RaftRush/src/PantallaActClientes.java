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
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTable tblActCli;
    private JPanel panelSuperior;
    private JPanel panelInferior;
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

    private void cargarDato(){
        Object[][] data = new Object[2][5];

        data[0][0] = "Rafting en el Río";
        data[0][1] = "Rafting";
        data[0][2] = "Madrid";
        data[0][3] = "Intermedio";
        data[0][4] = "50.00€";

        data[1][0] = "Escalada en Roca";
        data[1][1] = "Escalada";
        data[1][2] = "Barcelona";
        data[1][3] = "Avanzado";
        data[1][4] = "75.00€";



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
        for (int i = 0; i < tblActCli.getColumnCount(); i++) {
            tblActCli.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
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
