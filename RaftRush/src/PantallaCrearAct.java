import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PantallaCrearAct extends JFrame{

    private JPanel panelPrincipal;
    private JLabel imgCorporativaDer;
    private JTextField txtFldNombre;
    private JComboBox cBoxTipo;
    private JComboBox cBoxLocalidad;
    private JTextField txtFldPrecio;
    private JComboBox cBoxDificultad;
    private JButton btnRegistrar;
    private JPanel panelDatosAct;
    private JPanel panelCentrado;
    private JPanel panelContenido;
    private JPanel panelTextfields;
    private JTextArea txtAreaDesc;
    private JScrollPane envolturaDescArea;
    private JLabel lblTitulo;
    private JLabel lblNombre;
    private JLabel lblTipo;
    private JLabel lblLocalidad;
    private JLabel lblPrecio;
    private JLabel lblDificultad;
    private JLabel lblDescripcion;
    private static final ImageIcon logo = new ImageIcon("resources/imagenes/logo.png");
    ImageIcon imgCorporativa = new ImageIcon("resources/imagenes/asideSimple.png");


    public PantallaCrearAct() { //Constructor
        super("Crear actividad");
        init();
        cargarListeners();
        cargarTextFields();
    }

    private void init() {
        setSize(1480, 900);
        setContentPane(panelPrincipal);
        imgCorporativaDer.setIcon(imgCorporativa);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(logo.getImage());
    }

    private void cargarListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new PantallaActividades();
            }
        });
        btnRegistrar.addActionListener(registrar());
        Utils.cursorPointerBoton(btnRegistrar);
    }

    private ActionListener registrar() {
        return e -> {
            // TODO: Llamar un metodo que añada la actividad a la BD
            if (!checkTextFields()) {
                    JOptionPane.showMessageDialog(null,"No has rellenado todos los campos");
                }else{
                String nombre = txtFldNombre.getText();
                String tipo = (String) cBoxTipo.getSelectedItem();
                String localidad = (String) cBoxLocalidad.getSelectedItem();
                Double precio = Double.valueOf(txtFldPrecio.getText());
                String dificultad = (String) cBoxDificultad.getSelectedItem();
                String descripcion = txtAreaDesc.getText();

                if (DataManager.addActividad(nombre,tipo,localidad,precio,dificultad,descripcion)) {
                    JOptionPane.showMessageDialog(null, "Actividad registrado correctamente");
                }else{
                    JOptionPane.showMessageDialog(null, "ERROR. No se puedo agregar la actividad.");
                }

                }
            new PantallaActividades();
            dispose();
        };
    }

    private void cargarTextFields() {
        panelDatosAct.putClientProperty(FlatClientProperties.STYLE, "arc: 40");
        txtFldNombre.putClientProperty(FlatClientProperties.STYLE, "arc: 10");
        txtFldPrecio.putClientProperty(FlatClientProperties.STYLE, "arc: 10");
        envolturaDescArea.putClientProperty(FlatClientProperties.STYLE,"arc: 10");
        txtAreaDesc.setLineWrap(true);
    }

    private boolean checkTextFields() {
        if (txtFldNombre.getText().equalsIgnoreCase("")
                || txtAreaDesc.getText().equalsIgnoreCase("")
                || txtFldPrecio.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos", "Error en los datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }else return true;
    }
}
