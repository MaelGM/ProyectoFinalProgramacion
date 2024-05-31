import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ActividadClientes extends JFrame{
    private JPanel jplGeneral;
    private JLabel lblBG;
    private JPanel jplFill;
    private JButton verReservasButton;
    private JButton reservarButton;
    private JPanel jplDataAct;
    private JPanel jplTabla;
    private JPanel jplData;
    private JPanel jplFiltro;
    private JPanel jplUsuario;
    private JLabel lblNombreUsu;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTable tblActCli;
    private JLayeredPane jlpBackground;
    private DefaultTableModel model;

    public ActividadClientes(){
        setContentPane(jplGeneral);
        background();
        cargarDato();


        tblActCli.setShowGrid(true);//Mostrar grid color

        tblActCli.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });
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


    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new ActividadClientes();
                frame.setSize(1534,774);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);

                frame.setVisible(true);
            }
        });
    }
}
