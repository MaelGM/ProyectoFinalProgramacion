import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;

public class GestionarMateriales extends JFrame{
    private JPanel gestionMaterialesPane;
    private JLabel lblHeader;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton solicitarEntregaButton;

    public GestionarMateriales(){
        super("Gestión de Material");
        init();
        loadListeners();
    }

    public void loadListeners(){
        //ActionListeners
    }

    public void init(){
        setContentPane(gestionMaterialesPane);
        ImageIcon imgHeader = new ImageIcon("resources/imagenes/headerTrabajadores.png");
        ImageIcon favicon = new ImageIcon("resources/imagenes/logoRaftRush.png");
        lblHeader.setIcon(imgHeader);
        setIconImage(favicon.getImage());

        setSize(1480, 770);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        SwingUtilities.invokeLater(() -> new GestionarMateriales().setVisible(true));
    }
}
