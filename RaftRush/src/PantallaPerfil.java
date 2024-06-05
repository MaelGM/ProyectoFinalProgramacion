import Excepciones.ExceptionUsuario;
import Objetos.Cliente;
import Objetos.Trabajador;
import Objetos.Usuario;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaPerfil extends JFrame{
    private JPanel panelGeneral;
    private JPanel panelDatos;
    private JTextField txtTelefono;
    private JTextField txtNif;
    private JTextField txtNombre;
    private JButton btnAct;
    private JButton btnLogOut;
    private JLabel lblMiPerfil;
    private JPanel panelPerfilDatos;
    private JLabel lblAvatar;
    private JLabel lblBG;
    private JLabel lblNombre;
    private JLabel lblTelefono;
    private JLabel lblNif;
    private JLabel lblIcon1;
    private JLabel lblIcon2;
    private JLabel lblIcon3;

    public PantallaPerfil(Usuario usu){
        init();
        setContentPane(panelGeneral);
        panelDatos.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
        background();
        cargarDatos(usu);
        cargarListeners(usu);
    }

    private void cargarDatos(Usuario usu) {
        if (usu instanceof Trabajador) {
            txtTelefono.setEditable(false);
        }
        txtNif.setText(usu.getNif());
    }

    public PantallaPerfil(){
        init();
        setContentPane(panelGeneral);
        panelDatos.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
        background();

        //cargarListeners();
    }



    private void cargarListeners(Usuario usu) {
        btnAct.addActionListener(e -> dispose());
        btnLogOut.addActionListener(e -> {
            for (Window window : Window.getWindows()) {
                window.dispose();
            }
            new PantallaInicial();
        });

        btnAct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarUsu(usu);
            }
        });
    }

    private void actualizarUsu(Usuario usu){
        if (usu instanceof Trabajador) {
            if (!txtNombre.getText().isEmpty()) {
                try {
                    usu.setNombre(txtNombre.getText());
                } catch (ExceptionUsuario e) {
                    e.printStackTrace();
                }
            }
        }else if (usu instanceof Cliente) {
            if (!txtNombre.getText().isEmpty() || !txtTelefono.getText().isEmpty()) {
                try {
                    usu.setNombre(txtNombre.getText());
                    ((Cliente) usu).setTelefono(txtTelefono.getText());


                } catch (ExceptionUsuario e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void init() {
        setSize(660,1033);
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);
        setIconImage(new ImageIcon("resources/imagenes/logo.png").getImage());
    }

    private void background(){
        ImageIcon background = new ImageIcon("resources/imagenes/montanyas.png");
        lblBG.setIcon(background);
        ImageIcon roundAct = new ImageIcon("resources/imagenes/avatar.png");
        lblAvatar.setIcon(roundAct);
        ImageIcon user = new ImageIcon("resources/imagenes/user.png");
        lblIcon1.setIcon(user);
        ImageIcon phone = new ImageIcon("resources/imagenes/phone.png");
        lblIcon2.setIcon(phone);
        ImageIcon key = new ImageIcon("resources/imagenes/password.png");
        lblIcon3.setIcon(key);
    }
}
