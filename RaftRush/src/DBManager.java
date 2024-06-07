import Objetos.*;

import java.sql.*;


public class DBManager {
    private static Connection conn = null;
    private static final String DB_HOST = "127.0.0.1";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "raftrush";
    private static final String DB_URL = "jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_NAME;
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    private static final String DB_MSQ_CONN_OK = "CONEXIÓN CORRECTA";
    private static final String DB_MSQ_CONN_NO = "ERROR EN LA CONEXIÓN";

    public static boolean loadDriver(){
        try{
            System.out.println("CARGANDO DRIVER");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("OK!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean connect(){
        try {
            System.out.println("Conectando a la base de datos");
            conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
            System.out.println("OK!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void close() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada exitosamente.");
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static ResultSet getTipos() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM tipo");
    }
    public static ResultSet getClientes() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM cliente");
    }
    public static ResultSet getCentro() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM centro");
    }
    public static ResultSet getActividad() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM actividad");
    }
    public static ResultSet getTrabajador() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM trabajador");
    }
    public static ResultSet getMaterial() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM material");
    }
    public static ResultSet getProveedor() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM proveedor");
    }
    public static ResultSet getReservas() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM reservaclienteactividad");
    }

    public static ResultSet getCentro(int id) throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM centro WHERE centro.id = " + id);
    }

    public static ResultSet getTipo(int id) throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM tipo WHERE tipo.id = " + id);
    }

    public static ResultSet getCliente(String nif) throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM cliente WHERE cliente.nif = '" + nif + "'");
    }

    public static ResultSet getTrabajador(String nif) throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM trabajador WHERE trabajador.nif = '" + nif + "'");
    }

    public static int agregarCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO cliente (nif, contrasenya, telefono, nombre, edad) VALUES (?,?,?,?,?)";

        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1,cliente.getNif());
            pstmt.setString(2,cliente.getContrasenya());
            pstmt.setString(3,cliente.getTelefono());
            pstmt.setString(4,cliente.getNombre());
            pstmt.setInt(5,cliente.getEdad());

            return pstmt.executeUpdate();
        }
    }

    public static int editarCliente(Cliente cliente) throws SQLException {
        String query = "UPDATE cliente SET nif= ?, contrasenya= ?, telefono= ?, nombre= ?,edad= ? WHERE cliente.nif = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1,cliente.getNif());
            pstmt.setString(2,cliente.getContrasenya());
            pstmt.setString(3,cliente.getTelefono());
            pstmt.setInt(4,Integer.parseInt(cliente.getNombre()));
            pstmt.setInt(5,cliente.getEdad());

            return pstmt.executeUpdate();
        }
    }

    public static int editarUsu(Usuario usu, String nombre, String contrasenya, String nif) throws SQLException {
        String query = "";
        if (usu instanceof Trabajador) {
            query = "UPDATE trabajador SET nombre= ?, contrasenya= ? WHERE trabajador.nif = ?";
        }else if (usu instanceof Cliente) {
            query = "UPDATE cliente SET nombre= ?, contrasenya= ? WHERE cliente.nif = ?";
        }
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1,nombre);
            pstmt.setString(2,contrasenya);
            pstmt.setString(3,nif);

            return pstmt.executeUpdate();
        }
    }

    public static ResultSet getEntregas(Material material, Proveedor proveedor) throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM entregaproveedormaterial WHERE entregaproveedormaterial.idProv = "
                + proveedor.getId() + " AND entregaproveedormaterial.codMaterial = " + material.getCodigo());
    }

    public static ResultSet getHashPassword(String nif) throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM cliente where cliente.nif = '" + nif+"'");
    public static ResultSet getReservas(Cliente cliente, Actividad actividad) throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM reservaclienteactividad WHERE reservaclienteactividad.idActividad = "
                + actividad.getId() + " AND reservaclienteactividad.nifCli =  '" + cliente.getNif() + "'");
    }

    public static ResultSet getPrecioAct(int id) throws SQLException{
        return conn.createStatement().executeQuery("SELECT * FROM actividad WHERE actividad.id = " + id);
    }

    /*
        Esto no hace falta, ya que al ser incremental, se asigna el ID automaticamente

    //TODO Este codigo lo hago para sacar el ultimo id u codigo que se ha creado para luego insertarlo a la nueva instancia Author -->Hakeem
    //TODO no he hecho la de tipo porque no estaba muy seguro de ello Author --> Hakeem
    public static ResultSet sacarUltimoIDCentro() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM centro ORDER BY centro.id DESC LIMIT 1");
    }

    public static ResultSet sacarUltimoIDActividad() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM actividad ORDER BY actividad.id DESC LIMIT 1");
    }

    public static ResultSet sacarUltimoCodigoMaterial() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM material ORDER BY material.codigo DESC LIMIT 1");
    }

    public static ResultSet sacarUltimoIDProveedor() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM proveedor ORDER BY proveedor.id DESC LIMIT 1");
    }
    */
}
