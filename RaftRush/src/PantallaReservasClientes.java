import Objetos.Actividad;
import Objetos.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;

public class PantallaReservasClientes extends JFrame{
    private JPanel jplGeneral;
    private JTable tblReservas;
    private JLabel lblBG;
    private JPanel jplTabla;
    private JPanel panelCentrado;
    private JButton btnAnularReserva;
    private DefaultTableModel model;

    public PantallaReservasClientes(Usuario cliente, Actividad actividad){
        init();
        setContentPane(jplGeneral);
        background();
        //cargarDato(cliente,actividad);
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

    private void cargarDato(Usuario cliente, Actividad actividad){

        List<Actividad> listReservaClie = null; /*DataManager.getListActividades().stream()
                .filter(actividad1 -> actividad.getId() == actividad1.getId() && cliente.getNif().equals(DataManager.getListReservas().))
                .toList();*/
        Object[][] data = new Object[listReservaClie.size()][5];


        int i = 0;
        for (int j = 0; j < listReservaClie.size(); j++) {
            data[i][0] =
            data[i][1] =
            data[i][2] =
            data[i][3] =
            data[i][4] =

            i++;
        }


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
        for (int z = 0; i < tblReservas.getColumnCount(); z++) {
            tblReservas.getColumnModel().getColumn(z).setCellRenderer(centerRenderer);
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
