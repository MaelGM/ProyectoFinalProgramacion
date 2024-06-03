import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.ui.FlatLineBorder;

import javax.swing.*;
import java.awt.*;

public class PantallaCrearAct extends JFrame{

    private JPanel panelPrincipal;
    private JLabel imgCorporativaDer;
    private JTextField txtFldNombre;
    private JComboBox cBoxTipo;
    private JComboBox cBoxLocalidad;
    private JTextField txtFldPrecio;
    private JComboBox cBoxDificultad;
    private JButton registrarActividadButton;
    private JPanel datosActPanel;
    private JPanel panelCentrado;
    private JPanel panelContenido;
    private JPanel panelTextfields;
    private JTextArea txtAreaDesc;
    private JScrollPane envolturaDescArea;
    private static final ImageIcon logo = new ImageIcon("resources/imagenes/logo.png");
    ImageIcon imgCorporativa = new ImageIcon("resources/imagenes/asideSimple.png");


    public PantallaCrearAct() { //Constructor
        super("Crear actividad");
        init();
        setContentPane(panelPrincipal);
        imgCorporativaDer.setIcon(imgCorporativa);
        cargarTextFields();
    }

    private void init() {
        setSize(1480, 900);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(logo.getImage());
    }

    private void cargarTextFields() {
        datosActPanel.putClientProperty(FlatClientProperties.STYLE, "arc: 40");
        txtFldNombre.putClientProperty(FlatClientProperties.STYLE, "arc: 10");
        txtFldPrecio.putClientProperty(FlatClientProperties.STYLE, "arc: 10");
        envolturaDescArea.putClientProperty(FlatClientProperties.STYLE,"arc: 10");
        txtAreaDesc.setLineWrap(true);
    }
}
