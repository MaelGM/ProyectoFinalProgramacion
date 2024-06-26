import Objetos.*;

import java.sql.*;
import java.time.LocalDate;


/**
 * En esta clase define los metodos necesarios para la carga de la base de datos.
 */
public class DBManager {
    /**
     * Campos de la clase
     */
    private static Connection conn = null;
    private static final String DB_HOST = "127.0.0.1";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "raftrush";
    private static final String DB_URL = "jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_NAME;
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    private static final String DB_MSQ_CONN_OK = "CONEXIÓN CORRECTA";
    private static final String DB_MSQ_CONN_NO = "ERROR EN LA CONEXIÓN";

    /**
     * Metodo para la carga del driver
     * @return true si ha podido cargar, false si no.
     */
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

    /**
     * Metodo para conectar con la base de datos
     * @return true si ha podido conectar, false si no.
     */
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

    /**
     * Metodo para cerrar la conexion con la base de datos
     */
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

    /**
     * metodo para coger la lista de tipos de la base de datos
     * @return ResulSet con la consulta realizada
     * @throws SQLException
     */
    public static ResultSet getTipos() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM tipo");
    }
    /**
     * metodo para coger la lista de clientes de la base de datos
     * @return ResulSet con la consulta realizada
     * @throws SQLException
     */
    public static ResultSet getClientes() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM cliente");
    }
    /**
     * metodo para coger la lista de centros de la base de datos
     * @return ResulSet con la consulta realizada
     * @throws SQLException
     */
    public static ResultSet getCentro() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM centro");
    }
    /**
     * metodo para coger la lista de actividades de la base de datos
     * @return ResulSet con la consulta realizada
     * @throws SQLException
     */
    public static ResultSet getActividad() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM actividad");
    }
    /**
     * metodo para coger la lista de trabajadores de la base de datos
     * @return ResulSet con la consulta realizada
     * @throws SQLException
     */
    public static ResultSet getTrabajador() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM trabajador");
    }
    /**
     * metodo para coger la lista de materiales de la base de datos
     * @return ResulSet con la consulta realizada
     * @throws SQLException
     */
    public static ResultSet getMaterial() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM material");
    }
    /**
     * metodo para coger la lista de proveedores de la base de datos
     * @return ResulSet con la consulta realizada
     * @throws SQLException
     */
    public static ResultSet getProveedor() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM proveedor");
    }
    /**
     * metodo para coger el proveedor editado de la base de datos
     * @return ResulSet con la consulta realizada
     * @throws SQLException
     */
    public static ResultSet getProveedorEdit() throws SQLException {
        return conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM proveedor");
    }
    /**
     * metodo para coger la lista de reservas de la base de datos
     * @return ResulSet con la consulta realizada
     * @throws SQLException
     */
    public static ResultSet getReservas() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM reservaclienteactividad");
    }
    /**
     * metodo para coger la lista de entregas de la base de datos
     * @return ResulSet con la consulta realizada
     * @throws SQLException
     */
    public static ResultSet getEntregas() throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM entregaproveedormaterial");
    }
    /**
     * metodo para coger la lista de centros de la base de datos
     * @return ResulSet con la consulta realizada
     * @throws SQLException
     */
    public static ResultSet getCentro(int id) throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM centro WHERE centro.id = " + id);
    }

    /**
     * Metodo para editar un proveedor
     * @param p
     * @return int 0 si no cambia nada, 1 si cambia
     */

    public static int updateProveedor(Proveedor p) {
        try {
            String query = "UPDATE proveedor SET nombre= ?, telefono= ?, email= ? WHERE id = ?";
            try(PreparedStatement pstmt = conn.prepareStatement(query)){
                pstmt.setString(1, p.getNombre());
                pstmt.setString(2, p.getTelefono());
                pstmt.setString(3, p.getEmail());
                pstmt.setInt(4, p.getId());

                return pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }


    public static ResultSet getProveedorId(Proveedor proveedor) throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM proveedor WHERE proveedor.nombre = '" + proveedor.getNombre()
                + "' AND proveedor.telefono = '" + proveedor.getTelefono() + "' AND proveedor.email = '" + proveedor.getEmail() + "'");
    }

    /**
     * Metodo para agregar un cliente a la base de datos
     * @param cliente
     * @return int 0 si no se ha podido agregar, 1 si que ha podido.
     * @throws SQLException
     */
    
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

    /**
     * Metodo para agregar un centro a la base de datos
     * @param centro
     * @return int 1 si seha podido agregar, 0 si no.
     * @throws SQLException
     */
    public static int agregarCentro(Centro centro) throws SQLException {
        String query = "INSERT INTO centro (nombre, localidad, presupuesto) VALUES (?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, centro.getNombre());
            pstmt.setString(2, centro.getLocalidad());
            pstmt.setDouble(3, centro.getPresupuesto());

            return pstmt.executeUpdate();
        }
    }

    /**
     * Metodo para editar un centro en la base de datos
     * @param centro
     * @param codigo
     * @return int 1 si se ha podido editar, 0 si no.
     */
    public static int updateCentro(Centro centro, int codigo) {
        try {
            String query = "UPDATE centro SET nombre= ?, localidad= ?, presupuesto= ? WHERE id = ?";
            try(PreparedStatement pstmt = conn.prepareStatement(query)){
                pstmt.setString(1, centro.getNombre());
                pstmt.setString(2, centro.getLocalidad());
                pstmt.setDouble(3, centro.getPresupuesto());
                pstmt.setInt(4, codigo);

                return pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    /**
     * Metodo para agregar un trabajador a la base de datos
     * @param nif
     * @param nombre
     * @param apellido
     * @param edad
     * @param salario
     * @param idCentro
     * @param contrasenya
     * @return int 0 si no ha podido agragar el trabajador, 1 si que ha podido.
     * @throws SQLException
     */
    public static int agregarTrabajador(String nif, String nombre, String apellido, int edad, double salario, int idCentro, String contrasenya) throws SQLException{
        String query = "INSERT INTO trabajador (nif, contrasenya, nombre, apellido, salario, edad, idCentro) VALUES (?,?,?,?,?,?,?)";

        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1,nif);
            pstmt.setString(2,contrasenya);
            pstmt.setString(3,nombre);
            pstmt.setString(4,apellido);
            pstmt.setDouble(5,salario);
            pstmt.setInt(6,edad);
            pstmt.setInt(7,idCentro);

            return pstmt.executeUpdate();
        }
    }
    /**
     * Metodo para agregar una reserva a la base de datos
     * @param cliente
     * @param actividad
     * @return int 0 si no ha podido agragar la reserva, 1 si que ha podido agregala.
     * @throws SQLException
     */
    public static int agregarReserva(Usuario cliente, Actividad actividad) throws SQLException{
        String query = "INSERT INTO reservaclienteactividad (fechaDeReserva, nifCli, idActividad, idActividadCentro) VALUES (?,?,?,?)";

        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, String.valueOf(LocalDate.now()));
            pstmt.setString(2,cliente.getNif());
            pstmt.setInt(3,actividad.getId());
            pstmt.setInt(4,actividad.getCentro().getId());

            return pstmt.executeUpdate();
        }
    }

    /**
     * Este método se encarga de añadir una entrega a la base de datos.
     * @param fecha Fecha en la que se solicitó el material
     * @param proveedor Proveedor que realiza la entrega
     * @param material Material de la entrega
     * @return Devolvera un int, que si ese int es 0, significará que no ha funcionado.
     * @throws SQLException Saltará esta exception en caso de que no funcione la inserción
     */
    public static int addEntrega(LocalDate fecha, Proveedor proveedor, Material material) throws SQLException{
        String query = "INSERT INTO entregaproveedormaterial (fechaEntrega, idProv, codMaterial, idCentro) VALUES (?,?,?,?)";

        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setDate(1, Date.valueOf(fecha));
            pstmt.setInt(2, proveedor.getId());
            pstmt.setInt(3, material.getCodigo());
            pstmt.setInt(4, material.getCentro().getId());


            return pstmt.executeUpdate();
        }
    }

    /**
     * Metodo para agregar una actividad a la base de datos
     * @param nombre
     * @param tipo
     * @param idCentro
     * @param precio
     * @param idCentro
     * @param dificultad
     * @param descripcion
     * @return int 0 si no ha podido agragar la actividad, 1 si que ha podido.
     * @throws SQLException
     */
    public static int addActividad(String nombre, int tipo, int idCentro, Double precio,
                                     String dificultad, String descripcion) throws SQLException{
        String query = "INSERT INTO actividad (nombre, descripcion, dificultad, precio, idTipo, idCentro) VALUES (?,?,?,?,?,?)";

        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, nombre);
            pstmt.setString(2,descripcion);
            pstmt.setString(3,dificultad);
            pstmt.setDouble(4,precio);
            pstmt.setInt(5,tipo);
            pstmt.setInt(6,idCentro);


            return pstmt.executeUpdate();
        }
    }

    /**
     * Método encargado de añadir un material a la base de datos.
     * @param material Material que se tiene que añadir a la base de datos
     * @return Devolverá un int, que si ese int es 0, significará que no ha funcionado.
     * @throws SQLException Saltará esta exception en caso de que no funcione la inserción
     */
    public static int addMaterial(Material material) throws SQLException {
        String query = "INSERT INTO material (nombre, precio, cantidad, idCentro) VALUES (?,?,?,?)";

        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, material.getNombre());
            pstmt.setDouble(2, material.getPrecio());
            pstmt.setInt(3, material.getCantidad());
            pstmt.setInt(4, material.getCentro().getId());

            return pstmt.executeUpdate();
        }
    }

    /**
     * Método encargado de modificar la cantidad de un material.
     * @param material Material ya registrado en la base de datos
     * @param cantidad Número de la cantidad nueva a modificar
     * @return Devolverá un int, que si ese int es 0, significará que no ha funcionado.
     * @throws SQLException Saltará esta exception en caso de que no funcione la inserción
     */
    public static int editarCantidadMaterial(Material material, int cantidad) throws SQLException {
        String query = "UPDATE material SET cantidad= ? WHERE material.codigo = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1,cantidad);
            pstmt.setInt(2,material.getCodigo());

            return pstmt.executeUpdate();
        }
    }

    /**
     * Método encargado de modificar el precio de un material.
     * @param material Material ya registrado en la base de datos
     * @param precio Número del precio nuevo a modificar
     * @return Devolverá un int, que si ese int es 0, significará que no ha funcionado.
     * @throws SQLException Saltará esta exception en caso de que no funcione la inserción
     */
    public static int editarPrecioMaterial(Material material, double precio) throws SQLException {
        String query = "UPDATE material SET precio= ? WHERE material.codigo = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setDouble(1,precio);
            pstmt.setInt(2,material.getCodigo());

            return pstmt.executeUpdate();
        }
    }

    /**
     * Metodo para editar un cliente
     * @param cliente
     * @return return 0 si no ha podido, 1 si que ha podido.
     * @throws SQLException
     * @deprecated
     */
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

    /**
     * Metodo para editar los usuarios independientemente de si son trabajadores o clientes
     * @param usu
     * @param nombre
     * @param contrasenya
     * @param nif
     * @return int 1 si ha podido editar el usuario, 0 si no.
     * @throws SQLException
     */
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

    /**
     * Metodo para editar un trabajador a la base de datos
     * @param trabajador
     * @param idCentro
     * @return int 0 si no ha podido editar el trabajador, 1 si que ha podido editarlo.
     * @throws SQLException
     */
    public static int editarTrabajador(Trabajador trabajador, int idCentro) throws SQLException {
        String query = "UPDATE trabajador SET " +
                "nombre = ?, apellido = ?, salario = ?, edad = ?, idCentro = ?" +
                " WHERE nif = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, trabajador.getNombre());
            pstmt.setString(2, trabajador.getApellido());
            pstmt.setDouble(3, trabajador.getSalario());
            pstmt.setInt(4, trabajador.getEdad());
            pstmt.setInt(5, idCentro);
            pstmt.setString(6, trabajador.getNif());

            return pstmt.executeUpdate();
        }
    }

    /**
     * Metodo para eliminar un trabajador a la base de datos
     * @param nif
     * @return int 0 si no ha podido eliminar el trabajador, 1 si que ha podido agregala.
     * @throws SQLException
     */
    public static int borrarTrabajador(String nif) throws SQLException {
        return conn.createStatement().executeUpdate("DELETE FROM trabajador where trabajador.nif = '" + nif + "'");
    }
    /**
     * Metodo para eliminar una actividad a la base de datos
     * @param id
     * @return int 0 si no ha podido eliminar la actividad, 1 si que ha podido agregala.
     * @throws SQLException
     */
    public static int borrarActividad(int id) throws SQLException {
        borrarReservaByID(id);
        return conn.createStatement().executeUpdate("DELETE FROM actividad where actividad.id = " + id);
    }
    /**
     * Metodo para eliminar una reserva a la base de datos
     * @param nif
     * @return int 0 si no ha podido eliminar la reserva, 1 si que ha podido agregala.
     * @throws SQLException
     */
    public static int borrarReserva(String date, String nif, int idAct) throws SQLException {
        return conn.createStatement().executeUpdate("DELETE FROM reservaclienteActividad WHERE reservaclienteActividad.fechaDeReserva = '"
                + date + "' AND reservaclienteActividad.nifCli = '" + nif + "' AND reservaclienteActividad.idActividad = " + idAct);
    }
    /**
     * Metodo para eliminar una reserva dependiendo del id de actividad a la base de datos
     * @param idAct
     * @throws SQLException
     */
    public static void borrarReservaByID(int idAct) throws SQLException {
        conn.createStatement().executeUpdate("DELETE FROM reservaclienteActividad WHERE reservaclienteActividad.idActividad = " + idAct);
    }

    /**
     * Metodo para obtener la lista de entregas de la base de datos
     * @param material
     * @param proveedor
     * @return ResulSet de la consulta realizada.
     * @throws SQLException
     */
    public static ResultSet getEntregas(Material material, Proveedor proveedor) throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM entregaproveedormaterial WHERE entregaproveedormaterial.idProv = "
                + proveedor.getId() + " AND entregaproveedormaterial.codMaterial = " + material.getCodigo());
    }

    /**
     * Metodo para obtener la contraseña de un usuario
     * @param nif
     * @return ResulSet de la consulta realizada.
     * @throws SQLException
     */
    public static ResultSet getHashPasswordTrab(String nif) throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM trabajador where trabajador.nif = '" + nif + "'");
    }
    public static ResultSet getHashPasswordCli(String nif) throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM cliente where cliente.nif = '" + nif + "'");
    }

    /**
     * Metodo para obtener las reservas hechas por un cliente
     * @param cliente
     * @param actividad
     * @return ResulSet de la consulta realizada.
     * @throws SQLException
     */
    public static ResultSet getReservasCli(Cliente cliente, Actividad actividad) throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM reservaclienteactividad WHERE reservaclienteactividad.idActividad = "
                + actividad.getId() + " AND reservaclienteactividad.nifCli =  '" + cliente.getNif() + "'");
    }

    /**
     * Metodo para obtener el precio de una actividad
     * @param id
     * @return ResulSet de la consulta realizada.
     * @throws SQLException
     */
    public static ResultSet getPrecioAct(int id) throws SQLException{
        return conn.createStatement().executeQuery("SELECT * FROM actividad WHERE actividad.id = " + id);
    }

    /**
     * Metodo para editar un trabajador
     * @param trabajador
     * @param nif
     * @param idCentro
     * @return int 1 si ha podido editarlo, 0 si no
     * @throws SQLException
     * @deprecated 
     */
    public static int editarTrabajador(Trabajador trabajador, String nif, int idCentro) throws SQLException {
        String query = "UPDATE trabajador SET " +
                "nombre = ?, apellido = ?, salario = ?, edad = ?, idCentro = ?" +
                "WHERE nif = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, trabajador.getNombre());
            pstmt.setString(2, trabajador.getApellido());
            pstmt.setDouble(3, trabajador.getSalario());
            pstmt.setInt(4, trabajador.getEdad());
            pstmt.setInt(5, idCentro);
            pstmt.setString(6, nif);

            return pstmt.executeUpdate();
        }
    }
}
