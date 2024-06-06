import Excepciones.ExceptionUsuario;
import Objetos.Cliente;
import Objetos.Trabajador;
import Objetos.Usuario;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutionException;

public class PantallaPerfil extends JFrame{
    private JPanel panelGeneral;
    private JPanel panelDatos;
    private JTextField txtContra;
    private JTextField txtNif;
    private JTextField txtNombre;
    private JButton btnAct;
    private JButton btnLogOut;
    private JLabel lblMiPerfil;
    private JPanel panelPerfilDatos;
    private JLabel lblAvatar;
    private JLabel lblBG;
    private JLabel lblNombre;
    private JLabel lblContra;
    private JLabel lblNif;
    private JLabel lblIcon1;
    private JLabel lblIcon2;
    private JLabel lblIcon3;

    public PantallaPerfil(Usuario usu){
        init();
        setContentPane(panelGeneral);
        panelDatos.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
        background();
        txtNif.setText(usu.getNif());
        cargarListeners(usu);
    }

    private void cargarListeners(Usuario usu) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Window window : Window.getWindows()) {
                    window.dispose();
                }
                if (usu instanceof Trabajador) {
                    new PantallaMenu(usu);
                }else if (usu instanceof Cliente){
                    new  PantallaActClientes(usu);
                }
            }
        });

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
                dispose();
            }
        });
    }

    private void actualizarUsu(Usuario usu){
        String nombre = txtNombre.getText();
        String contrasenya = txtContra.getText();

        if (!txtNombre.getText().isEmpty() || !txtContra.getText().isEmpty()) {
            if (txtNombre.getText().isEmpty()) {
                nombre = usu.getNombre();
            }
            if (txtContra.getText().isEmpty()) {
                contrasenya = usu.getContrasenya();
            }

            Usuario tempUsu = usu;
            try{
                tempUsu.setNombre(nombre);
                tempUsu.setContrasenya(contrasenya);
                if (DataManager.editarUsuario(usu, nombre, contrasenya, txtNif.getText()) > 0) {
                    //usu.setNombre(nombre);
                    //usu.setContrasenya(contrasenya);
                    JOptionPane.showMessageDialog(null, "Perfil De " + usu.getNombre() +" actualizado correctamente", "Actualizar Perfil", JOptionPane.INFORMATION_MESSAGE);

                    if (usu instanceof Trabajador) {
                        new PantallaMenu(usu);
                    }else if (usu instanceof Cliente){
                        new  PantallaActClientes(usu);
                    }
                }
            }catch (ExceptionUsuario e) {
                JOptionPane.showMessageDialog(null, "Los datos de tu perfil no se puede actualizar debido de un error", "Actualizar Perfil", JOptionPane.ERROR_MESSAGE);
                if (usu instanceof Trabajador) {
                    new PantallaMenu(usu);
                }else if (usu instanceof Cliente){
                    new  PantallaActClientes(usu);
                }
                e.printStackTrace();
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
