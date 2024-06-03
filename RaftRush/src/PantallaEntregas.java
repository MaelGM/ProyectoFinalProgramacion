import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PantallaEntregas extends JFrame{
    private JPanel jplGeneral;
    private JLabel lblBG;
    private JPanel panelCentrado;
    private JPanel jplTabla;
    private JTable tblReservas;
    private DefaultTableModel model;
    public PantallaEntregas(){
        init();
        setContentPane(jplGeneral);
        background();
        cargarDato();


        tblReservas.setShowGrid(true);//Mostrar grid color
    }

    private void init() {
        setSize(1480,774);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());
        setVisible(true);
    }

    private void cargarDato(){
        tblReservas.setShowGrid(true);//Mostrar grid color

        Object[][] data = new Object[2][4];

        data[0][0] = "2024-05-01";
        data[0][1] = "Prov 1";
        data[0][2] = "1";
        data[0][3] = "Centro 1";

        data[1][0] = "2024-05-0";
        data[1][1] = "Prov 2";
        data[1][2] = "2";
        data[1][3] = "centro 2";



        model = new DefaultTableModel(data, new String[]{"Fecha de entrega", "Nombre Prov", "Codigo Material", "Nombre Centro"});
        tblReservas.setModel(model);
        //Color tableBG = new Color(110, 130, 141);

        tblReservas.setGridColor(Color.black);
        tblReservas.getTableHeader().setOpaque(false);
        tblReservas.getTableHeader().setBackground(new Color(47, 75, 89));
        tblReservas.getTableHeader().setForeground(new Color(245, 159, 116));
        tblReservas.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblReservas.getColumnCount(); i++) {
            tblReservas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tblReservas.getTableHeader().setReorderingAllowed(false);
        tblReservas.setDefaultEditor(Object.class,null);
        tblReservas.setEnabled(true);

    }

    private void background(){
        ImageIcon background = new ImageIcon("resources/imagenes/cabeceraResClientes.png");

        lblBG.setIcon(background);
    }
}
