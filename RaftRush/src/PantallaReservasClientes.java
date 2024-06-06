import Objetos.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PantallaReservasClientes extends JFrame{
    private JPanel jplGeneral;
    private JTable tblReservas;
    private JLabel lblBG;
    private JPanel jplTabla;
    private JPanel panelCentrado;
    private JButton btnAnularReserva;
    private DefaultTableModel model;

    public PantallaReservasClientes(Usuario cliente){
        init();
        setContentPane(jplGeneral);
        background();
        cargarDato();
        cargarListeners(cliente);

        tblReservas.setShowGrid(true);//Mostrar grid color
    }

    private void cargarListeners(Usuario cliente) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new PantallaActClientes(cliente);
            }
        });
        Utils.cursorPointerBoton(btnAnularReserva); // TODO: Funcion que elimine una reserva seleccionada
    }

    private void init() {
        setSize(1480,774);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());
        setVisible(true);
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
