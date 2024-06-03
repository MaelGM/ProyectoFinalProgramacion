import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ReservasClientes extends JFrame{
    private JPanel jplGeneral;
    private JTable tblReservas;
    private JLabel lblBG;
    private JPanel jplTabla;
    private DefaultTableModel model;

    public ReservasClientes(){
        setContentPane(jplGeneral);
        background();
        cargarDato();


        tblReservas.setShowGrid(true);//Mostrar grid color

        // Add window listener to handle the close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JFrame frame = new ActividadClientes();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1534,774);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);

                frame.setVisible(true);
            }
        });
    }

    private void cargarDato(){
        Object[][] data = new Object[2][5];

        data[0][0] = "2024-05-01";
        data[0][1] = "1";
        data[0][2] = "12345678A";
        data[0][3] = "50.00€";
        data[0][4] = "Parque Multiaventura Sierra Norte";

        data[1][0] = "2024-05-0";
        data[1][1] = "2";
        data[1][2] = "23456789B";
        data[1][3] = "75.00€";
        data[1][4] = "Parque de Aventura Montaña Mágica";



        model = new DefaultTableModel(data, new String[]{"Nombre", "Tipo", "Localidad", "Dificultad", "Precio"});
        tblReservas.setModel(model);
        //Color tableBG = new Color(110, 130, 141);

        tblReservas.setGridColor(Color.black);
        tblReservas.getTableHeader().setOpaque(false);
        tblReservas.getTableHeader().setBackground(new Color(47, 75, 89));
        tblReservas.getTableHeader().setForeground(new Color(245, 159, 116));
        tblReservas.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));

        TableColumnModel colums = tblReservas.getColumnModel();
        colums.getColumn(4).setMinWidth(150);

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