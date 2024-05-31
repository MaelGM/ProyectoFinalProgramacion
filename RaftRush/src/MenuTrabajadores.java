import javax.swing.*;

public class MenuTrabajadores extends JFrame{

    private JPanel menuTrabajadoresPane;
    private JLabel cabeceraLbl;
    private JButton gestionarTrabajadoresButton;
    private JButton gestionarReservasButton;
    private JButton gestionarActividadButton;
    private JButton gestionarAlmacénButton;

    public MenuTrabajadores(){
        super("Menú Trabajadores");
        setContentPane(menuTrabajadoresPane);
        ImageIcon img = new ImageIcon("src/com/company/img/cabeceraConTitulo.png");
        cabeceraLbl.setIcon(img);
    }

    public static void cargarPanel(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new MenuTrabajadores();
                frame.setSize(1480, 770);
                frame.setResizable(false);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                ImageIcon favicon = new ImageIcon("src/com/company/img/TurquesaTransparente.png");
                frame.setIconImage(favicon.getImage());
            }
        });
    }
}
