import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatLineBorder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PantallaEntregas extends JFrame{
    private JPanel panelGeneral;
    private JLabel lblBG;
    private JPanel panelTabla;
    private JTable tblReservas;
    private JPanel panelCentrado;
    private JPanel panelFiltro;
    private JLabel lblCentro;
    private JComboBox cmbCentro;
    private JComboBox cmbProveedor;
    private JLabel lblProveedor;
    private DefaultTableModel model;
    public PantallaEntregas(){
        super("Entregas");
        init();
        background();
        estilo();
        cargarDato();
        tblReservas.setShowGrid(true);//Mostrar grid color
    }



    private void init() {
        setSize(1480,774);
        setContentPane(panelGeneral);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());
        setVisible(true);
    }

    private void estilo() {
        panelFiltro.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "FILTRO", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        panelFiltro.setBorder(titleBorder);
    }

    public void cargarDato(){
        if (DataManager.getProveedor() && DataManager.getMaterial() && DataManager.getCentros()) {
            cargarEntregaTable();
        }
    }

    private void cargarEntregaTable(){

        tblReservas.setShowGrid(true);//Mostrar grid color

        Object[][] data = new Object[DataManager.getListEntregas().size()][4];

        int i = 0;
        for (int j = 0; j < DataManager.getListEntregas().size(); j++) {
            data[i][0] = DataManager.getListEntregas().get(j).get("column1");
            data[i][1] = DataManager.getListEntregas().get(j).get("column2");
            data[i][2] = DataManager.getListEntregas().get(j).get("column3");
            data[i][3] = DataManager.getListEntregas().get(j).get("column4");

            i++;
        }

        model = new DefaultTableModel(data, new String[]{"Fecha de entrega", "Proveedor", "Material", "Centro"});
        tblReservas.setModel(model);
        //Color tableBG = new Color(110, 130, 141);

        tblReservas.setGridColor(Color.black);
        tblReservas.getTableHeader().setOpaque(false);
        tblReservas.getTableHeader().setBackground(new Color(47, 75, 89));
        tblReservas.getTableHeader().setForeground(new Color(245, 159, 116));
        tblReservas.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int x = 0; i < tblReservas.getColumnCount(); x++) {
            tblReservas.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }

        tblReservas.getTableHeader().setReorderingAllowed(false);
        tblReservas.setDefaultEditor(Object.class,null);
        tblReservas.setEnabled(true);

    }

    private void background(){
        ImageIcon background = new ImageIcon("resources/imagenes/cabeceraConTituloEntregas.png");

        lblBG.setIcon(background);
    }
}
