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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaGestionarTrabajadores extends JFrame {

    private JLabel lblHeader;
    private JTable tblTrabajadores;
    private JPanel panelPrincipal;
    private JPanel jplNuevoTrabajador;
    private JTable tblNuevoTrabajador;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnEdit;
    private JPanel panelHeader;
    private JPanel panelContenido;
    private JPanel panelTabla;
    private JPanel panelTablaSelect;
    private JPanel panelBotones;


    public PantallaGestionarTrabajadores(){
        super("Gestión de trabajadores");
        init();
        cargarDatos();
        loadListeners();
    }

    public void init(){
        setContentPane(panelPrincipal);

        //Imágenes
        ImageIcon imgHeader = new ImageIcon("resources/imagenes/cabeceraConTituloTrabajadores.png");
        lblHeader.setIcon(imgHeader);
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());

        //Ventana
        setSize(1480, 770);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        tablaTrabajadoresProperties();
        tablaNuevoTrabajadorProperties();
        panelNuevoTrabajadorProperties();
    }

    public void cargarDatos(){
        datosMainTable();
        datosNewTrabajador();
    }

    public void loadListeners(){
        Utils.cursorPointerBoton(btnAdd);
        Utils.cursorPointerBoton(btnDelete);
        Utils.cursorPointerBoton(btnEdit);
        tblTrabajadores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        tblNuevoTrabajador.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });

        btnAdd.addActionListener(pedirPassword());
    }

    private ActionListener pedirPassword() {
        return e -> {
            JPasswordField passwordField = new JPasswordField();
            JPasswordField confirmPasswordField = new JPasswordField();

            // Crear el panel que contendrá los campos
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Introduzca su contraseña:"));
            panel.add(passwordField);
            panel.add(new JLabel("Repita la contraseña:"));
            panel.add(confirmPasswordField);

            // Mostrar el cuadro de diálogo de entrada
            int option = JOptionPane.showConfirmDialog(null, panel, "Creé la nueva contraseña",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            //TODO: Revisar si le ha dado a ok, y si le ha dado, revisar la contraseña es repetida
        };
    }

    public void datosMainTable(){
        String[]header = {"ID", "Nombre", "Apellidos", "Edad", "Salario", "Localidad"};
        String[][]rows = new String[2][header.length];

        //En lugar de las líneas de abajo, habra que recorrer con un bucle el List que nos devuelva DataManager
        rows[0][0] = "1";
        rows[0][1] = "Juan";
        rows[0][2] = "García";
        rows[0][3] = "35";
        rows[0][4] = "2500.00€";
        rows[0][5] = "Madrid";

        rows[1][0] = "2";
        rows[1][1] = "María";
        rows[1][2] = "López";
        rows[1][3] = "28";
        rows[1][4] = "2800.00€";
        rows[1][5] = "Barcelona";

        tblTrabajadores.setModel(new DefaultTableModel(rows, header));
        tblTrabajadores.getTableHeader().setReorderingAllowed(false);
        tblTrabajadores.setDefaultEditor(Object.class, null);

        asignarTamanyoColumnasTrabajadores();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblTrabajadores.getColumnCount(); i++) {
            tblTrabajadores.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void datosNewTrabajador(){
        String[]header = {"Nombre", "Apellidos", "Edad", "Salario", "Localidad"};
        String[][]rows = new String[1][header.length];

        ///Todo De cara a tomar datos, propongo tomar la información de cada celda.
        rows[0][0] = "";
        rows[0][1] = "";
        rows[0][2] = "";
        rows[0][3] = "";
        rows[0][4] = "";

        tblNuevoTrabajador.setModel(new DefaultTableModel(rows, header));
        tblNuevoTrabajador.getTableHeader().setReorderingAllowed(false);
        tblNuevoTrabajador.setDefaultEditor(Override.class, null);

        asignarTamanyoColumnasNuevoTrabajador();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblNuevoTrabajador.getColumnCount(); i++) {
            tblNuevoTrabajador.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void tablaTrabajadoresProperties(){
        tblTrabajadores.setShowGrid(true);
        tblTrabajadores.setGridColor(Color.BLACK);
        tblTrabajadores.getTableHeader().setOpaque(false);
        tblTrabajadores.getTableHeader().setBackground(new Color(47, 75, 89));
        tblTrabajadores.getTableHeader().setForeground(new Color(245, 159, 116));
        tblTrabajadores.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
    }

    public void tablaNuevoTrabajadorProperties(){
        tblNuevoTrabajador.setShowGrid(true);
        tblNuevoTrabajador.setGridColor(Color.BLACK);
        tblNuevoTrabajador.getTableHeader().setOpaque(false);
        tblNuevoTrabajador.getTableHeader().setBackground(new Color(47, 75, 89));
        tblNuevoTrabajador.getTableHeader().setForeground(new Color(245, 159, 116));
        tblNuevoTrabajador.getTableHeader().setFont(new Font("Inter", Font.BOLD,16));
    }

    public void panelNuevoTrabajadorProperties(){
        jplNuevoTrabajador.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        Border lineBorder = new FlatLineBorder(new Insets(16, 16, 16, 16), Color.cyan, 1, 8);

        Font titleFont = new Font("Inter", Font.BOLD, 16);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(lineBorder, "Nuevo Pedido", TitledBorder.LEADING, TitledBorder.TOP, titleFont, Color.cyan);
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);

        jplNuevoTrabajador.setBorder(titleBorder);
    }

    public void asignarTamanyoColumnasTrabajadores(){
        TableColumn column;
        for (int i = 0; i < tblTrabajadores.getColumnCount(); i++) {
            column = tblTrabajadores.getColumnModel().getColumn(i);
            switch (i){
                case 0-> column.setPreferredWidth(53);
                case 1,2, 5 -> column.setPreferredWidth(200);
                case 3,4-> column.setPreferredWidth(100);
            }
        }
    }

    public void asignarTamanyoColumnasNuevoTrabajador(){
        TableColumn column;
        for (int i = 0; i < tblNuevoTrabajador.getColumnCount(); i++) {
            column = tblNuevoTrabajador.getColumnModel().getColumn(i);
            switch (i){
                case 0,1, 4 -> column.setPreferredWidth(200);
                case 2,3-> column.setPreferredWidth(100);
            }
        }
    }
}
