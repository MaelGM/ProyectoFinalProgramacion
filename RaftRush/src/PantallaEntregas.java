import Objetos.*;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatLineBorder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class PantallaEntregas extends JFrame{
    private JPanel panelGeneral;
    private JLabel lblBG;
    private JPanel panelTabla;
    private JTable tblEntregas;
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
        cargarListeners();
        cargarDato();
        estilo();
        tblEntregas.setShowGrid(true);//Mostrar grid color
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

        tblEntregas.setGridColor(Color.black);
        tblEntregas.getTableHeader().setOpaque(false);
        tblEntregas.getTableHeader().setBackground(new Color(47, 75, 89));
        tblEntregas.getTableHeader().setForeground(new Color(245, 159, 116));
        tblEntregas.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
        tblEntregas.setShowGrid(true);//Mostrar grid color

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "FILTRO", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        panelFiltro.setBorder(titleBorder);
    }

    private void cargarListeners() {
        cmbProveedor.addActionListener(filtrar());
        cmbCentro.addActionListener(filtrar());
    }

    private ActionListener filtrar() {
        return e -> {
            Proveedor proveedor = DataManager.getProveedorByName(String.valueOf(cmbProveedor.getSelectedItem()));
            Centro centro = DataManager.getCentroByLocalidad(String.valueOf(cmbCentro.getSelectedItem()));

            List<Date> fechas = DataManager.getFechasEntregas();
            List<Material> materiales = DataManager.getMaterialEntregas();
            List<Proveedor> proveedores = DataManager.getProveedoresEntregas();

            if (proveedor != null && proveedores != null) proveedores = proveedores.stream().filter(proveedor1 -> proveedor1 == proveedor).toList();
            if (centro != null && materiales != null) materiales = materiales.stream().filter(material -> material.getCentro() == centro).toList();
            cargarEntregaTable(fechas, materiales, proveedores);
        };
    }

    public void cargarDato(){
        if (DataManager.getCentros() && DataManager.getProveedor() && DataManager.getMaterial() && DataManager.getEntregas()) {
            actualizaComboBox();
            cargarEntregaTable(DataManager.getFechasEntregas(), DataManager.getMaterialEntregas(), DataManager.getProveedoresEntregas());
        }
    }

    private void cargarEntregaTable(List<Date> fechas, List<Material> materiales, List<Proveedor> proveedores){
        if (fechas != null && materiales != null && proveedores != null){
            String[]header = {"Fecha de entrega", "Proveedor", "Material", "Centro"};

            Object[][] data = new Object[getLongitud(materiales, proveedores)][4];

            int i = 0;
            for (Material material: materiales) {
                data[i][0] = fechas.get(i).toString();
                data[i][1] = proveedores.get(i).getNombre();
                data[i][2] = material.getNombre();
                data[i][3] = material.getCentro().getNombre();

                i++;
            }

            tblEntregas.setModel(new DefaultTableModel(data, header));

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (i = 0; i < tblEntregas.getColumnCount(); i++) {
                tblEntregas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            asignarTamanyoColumnasReservas();

            tblEntregas.getTableHeader().setReorderingAllowed(false);
            tblEntregas.setDefaultEditor(Object.class,null);
            tblEntregas.setEnabled(true);
        }
    }

    private int getLongitud(List<Material> materiales, List<Proveedor> proveedores) {
        int longitud;
        if (materiales.size() > proveedores.size()) {
            longitud = proveedores.size();
        }else if (materiales.size() < proveedores.size()){
            longitud = materiales.size();
        }else {
            longitud = materiales.size();
        }
        return longitud;
    }

    public void asignarTamanyoColumnasReservas(){
        TableColumn column;
        for (int i = 0; i < tblEntregas.getColumnCount(); i++) {
            column = tblEntregas.getColumnModel().getColumn(i);
            switch (i){
                case 0 -> column.setPreferredWidth(100);
                case 1, 3 -> column.setPreferredWidth(400);
                case 2 -> column.setPreferredWidth(250);
            }
        }
    }

    private void background(){
        ImageIcon background = new ImageIcon("resources/imagenes/cabeceraConTituloEntregas.png");

        lblBG.setIcon(background);
    }

    private void actualizaComboBox() {
        for (int i = 0; i < DataManager.getLocalidadesCentro().size(); i++) {
            cmbCentro.addItem(DataManager.getListCentros().get(i).getNombre());
            cmbProveedor.addItem(DataManager.getListProveedor().get(i).getNombre());
        }
    }
}
