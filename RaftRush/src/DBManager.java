import Clases.Cliente;

import javax.swing.*;
import javax.xml.transform.Result;
import java.sql.*;
import javax.sql.DataSource;


public class DBManager {
    private static Connection conn = null;
    private static final String DB_HOST = "127.0.0.1";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "";
    private static final String DB_URL = "jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_NAME;
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    private static final String DB_MSQ_CONN_OK = "CONEXIÓN CORRECTA";
    private static final String DB_MSQ_CONN_NO = "ERROR EN LA CONEXIÓN";

    public static boolean loadDriver(){
        try{
            System.out.println("CARGANDO DRIVER");
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("OK!");
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }catch (Exception e) {
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

    public static int agregarCliente(String nif, String contrasenya, String telefono, String nombre, int edad) throws SQLException {
        String query = "INSERT INTO cliente (nif, contrasenya, telefono, nombre, edad) VALUES (?,?,?,?,?)";

        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1,nif);
            pstmt.setString(2,contrasenya);
            pstmt.setString(3,telefono);
            pstmt.setString(4,nombre);
            pstmt.setInt(5,edad);

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
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
    }


    ///////////////////////
    //TODO Este codigo lo hago para sacar el ultimo id u codigo que se ha creado para luego insertarlo a la nueva instancia
    //TODO no he hecho la de tipo porque no estaba muy seguro de ello
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
    ///////////////////////
}
