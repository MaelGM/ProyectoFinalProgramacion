import Excepciones.*;
import Objetos.*;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * En esta clase se define los metodos para la manipulacion de las listas obtenidas por el DBManager de la base de datos.
 * @see DBManager
 */
public class DataManager {
    /**
     * Campos de la clase.
     */
    private static List<Actividad> listActividades = new ArrayList<>();
    private static List<Centro> listCentros = new ArrayList<>();
    private static List<Material> listMaterial = new ArrayList<>();
    private static List<Proveedor> listProveedor = new ArrayList<>();
    private static List<Trabajador> listTrabajador = new ArrayList<>();
    private static List<Cliente> listClientes = new ArrayList<>();
    private static List<Tipo> listTipos = new ArrayList<>();
    private static List<Map<String, Object>> listReservas = new ArrayList<>();

    /**
     * Metodo para obtener los ususarios
     * @return true si ha podido obtenerlos, false si no.
     */
    public static boolean getUsuarios() {
        return getClientes() && getTrabajador();
    }

    /**
     * Metodo para rellenar la lista de centros
     * @return true si ha podido rellenar la lista, false si no
     */
    public static boolean getCentros(){
        if (DBManager.connect()) {
            try{
                listCentros.clear();
                ResultSet rs = DBManager.getCentro();//Select all centros
                while(rs.next()){
                    listCentros.add(new Centro(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getDouble(4)));
                }
            }catch (SQLException | ExceptionCentro e){
                DBManager.close();
                return false;
            }
            DBManager.close();
            return true;
        }
        return false;
    }

    /**
     * Metodo para rellenar la lista de actividades
     * @return true si ha podido rellenar la lista, false si no
     */
    public static boolean getActividades(){
        if (DBManager.connect()) {
            try{
                listActividades.clear();
                ResultSet rs = DBManager.getActividad();//Select all actividades
                while(rs.next()){
                    int tipoId = rs.getInt(6);
                    Tipo tipo = filterTipoById(tipoId);
                    int centroId = rs.getInt((7));
                    Centro centro = filterCentroById(centroId);
                    listActividades.add(new Actividad(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4),rs.getDouble(5), tipo,
                            centro));
                }
            }catch (SQLException | ExceptionActividad e){
                DBManager.close();
                return false;
            }
            DBManager.close();
            return true;
        }
        return false;
    }

    /**
     * Metodo para filtrar un centro por su id
     * @param centroId
     * @return Centro centro obtenido por el id
     */
    private static Centro filterCentroById(int centroId) {
        for (int i = 0; i < listCentros.size(); i++) {
            if (listCentros.get(i).getId() == centroId) {
                return listCentros.get(i);
            }
        }
        return null;
    }

    /**
     * Metodo para filtrar el tipo por su id
     * @param id
     * @return Tipo tipo obtenido por el id
     */
    private static Tipo filterTipoById(int id) {
        for (int i = 0; i < listTipos.size(); i++) {
            if (listTipos.get(i).getId() == id) {
                return listTipos.get(i);
            }
        }
        return null;
    }

    public static int filterActividadIDByName(String actividad) {
        for (int i = 0; i < listActividades.size(); i++) {
            if (listActividades.get(i).getNombre().equalsIgnoreCase(actividad)) {
                return listActividades.get(i).getId();
            }
        }
        return -1;
    }
    
    /**
     * Metodo para rellenar la lista de materiales
     * @return true si ha podido rellenar la lista, false si no
     */

    public static boolean getMaterial(){
        if (DBManager.connect()) {
            try{
                listMaterial.clear();
                ResultSet rs = DBManager.getMaterial();//Select all actividades
                while(rs.next()){
                    listMaterial.add(new Material(rs.getInt(1), rs.getString(2), rs.getDouble(3),
                            rs.getInt(4), getCentroById(rs.getInt(5))));
                }
            }catch (SQLException | ExceptionMaterial e){
                DBManager.close();
                return false;
            }
            DBManager.close();
            return true;
        }
        return false;
    }
    /**
     * Metodo para rellenar la lista de proveedores
     * @return true si ha podido rellenar la lista, false si no
     */
    public static boolean getProveedor(){
        if (DBManager.connect()) {
            try{
                listProveedor.clear();
                ResultSet rs = DBManager.getProveedor();//Select all actividades
                while(rs.next()){
                    listProveedor.add(new Proveedor(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4)));
                }
            }catch (SQLException | ExceptionProveedor e){
                DBManager.close();
                return false;
            }
            DBManager.close();
            return true;
        }
        return false;
    }
    /**
     * Metodo para comprobar la lista de entregas
     * @return true si la lista contiene entregas, false si no
     */
    public static boolean getEntregas(){
        if (DBManager.connect()) {
            try{
                ResultSet rs = DBManager.getEntregas();//Select all actividades
                while(rs.next()){
                    // Lo recorre para comprobar que puede cargar la lista y devolver true
                }
            }catch (SQLException e){
                DBManager.close();
                return false;
            }
            DBManager.close();
            return true;
        }
        return false;
    }
    /**
     * Metodo para rellenar una lista de proveedores por entregas
     * @return List <Proveedor> proveedores
     */
    public static List<Proveedor> getProveedoresEntregas(){
        if (DBManager.connect()) {
            List<Proveedor> proveedores = new ArrayList<>();
            try{
                ResultSet rs = DBManager.getEntregas();//Select all entregas
                while(rs.next()){
                    proveedores.add(getProveedorId(rs.getInt(2)));
                }
            }catch (SQLException e){
                DBManager.close();
                return null;
            }
            DBManager.close();
            return proveedores;
        }
        return null;
    }
    /**
     * Metodo para rellenar una lista de materiales por entregas
     * @return List <Material> materiales
     */
    public static List<Material> getMaterialEntregas(){
        if (DBManager.connect()) {
            List<Material> materiales = new ArrayList<>();
            try{
                ResultSet rs = DBManager.getEntregas();//Select all actividades
                while(rs.next()){
                    materiales.add(getMaterialById(rs.getInt(3)));
                }
            }catch (SQLException e){
                DBManager.close();
                return null;
            }
            DBManager.close();
            return materiales;
        }
        return null;
    }

    /**
     * Metodo para obtener las fechas de las entregas
     * @return List <Date> fechasReservas
     */
    public static List<Date> getFechasEntregas(){
        if (DBManager.connect()) {
            List<Date> fechasReservas = new ArrayList<>();
            try{
                ResultSet rs = DBManager.getEntregas();//Select all actividades
                while(rs.next()){
                    fechasReservas.add(rs.getDate(1));
                }
            }catch (SQLException e){
                DBManager.close();
                return null;
            }
            DBManager.close();
            return fechasReservas;
        }
        return null;
    }

    /**
     * Metodo para obtener todas las reservas
     * @return true si la lista no esta vacia, false si si.
     */
    public static boolean getReservasGeneral(){
        if (DBManager.connect()) {
            try{
                ResultSet rs = DBManager.getReservas();//Select all reservas
                while(rs.next()){
                    // Lo recorre para comprobar que puede cargar la lista y devolver true
                }
            }catch (SQLException e){
                DBManager.close();
                return false;
            }
            DBManager.close();
            return true;
        }
        return false;
    }

    /**
     * Metodo para obtener los clientes por sus reservas
     * @return List <Cliente> clientes
     */
    public static List<Cliente> getClientesReservas(){
        if (DBManager.connect()) {
            List<Cliente> clientes = new ArrayList<>();
            try{
                ResultSet rs = DBManager.getReservas();//Select all actividades
                while(rs.next()){
                    clientes.add(getClienteByNif(rs.getString(2)));
                }
            }catch (SQLException e){
                DBManager.close();
                return null;
            }
            DBManager.close();
            return clientes;
        }
        return null;
    }

    /**
     * Metodo para obtener las actividades por las reservas hechas de estas
     * @param user
     * @return List <Actividad> actividades
     */
    public static List<Actividad> getActividadesReservas(Usuario user){
        if (DBManager.connect()) {
            List<Actividad> actividades = new ArrayList<>();
            try{
                ResultSet rs = DBManager.getReservas();//Select all actividades
                while(rs.next()){
                    if (user == null) actividades.add(getActividadById(rs.getInt(3)));
                    else if (rs.getString(2).equalsIgnoreCase(user.getNif())) actividades.add(getActividadById(rs.getInt(3)));
                }
            }catch (SQLException e){
                DBManager.close();
                return null;
            }
            DBManager.close();
            return actividades;
        }
        return null;
    }
    /**
     * Metodo para obtener las fechas de las reservas
     * @param user
     * @return List <Date> fechasReservas
     */
    public static List<Date> getFechasReservas(Usuario user){
        if (DBManager.connect()) {
            List<Date> fechasReservas = new ArrayList<>();
            try{
                ResultSet rs = DBManager.getReservas();//Select all actividades
                while(rs.next()){
                    if (user == null) fechasReservas.add(rs.getDate(1));
                    else if (rs.getString(2).equalsIgnoreCase(user.getNif())) fechasReservas.add(rs.getDate(1));
                }
            }catch (SQLException e){
                DBManager.close();
                return null;
            }
            DBManager.close();
            return fechasReservas;
        }
        return null;
    }

    /**
     * Metodo para rellenar la lista de trabajadores
     * @return true si ha podido rellenarla, false si no.
     */
    public static boolean getTrabajador() {
        if (DBManager.connect()) {
            try{
                listTrabajador.clear();
                ResultSet rs = DBManager.getTrabajador();//Select all trabajadores
                while(rs.next()){
                    listTrabajador.add(new Trabajador(rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getDouble(5), rs.getInt(6), getCentroById(rs.getInt(7))));
                }
            }catch (SQLException | ExceptionUsuario e){
                DBManager.close();
                return false;
            }
            DBManager.close();
            return true;
        }
        return false;
    }
    /**
     * Metodo para rellenar la lista de Clientes
     * @return true si ha podido rellenarla, false si no.
     */
    public static boolean getClientes() {
        if (DBManager.connect()) {
            try{
                listClientes.clear();
                ResultSet rs = DBManager.getClientes();//Select all clientes
                while(rs.next()){
                    listClientes.add(new Cliente(rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getInt(5)));
                }
            }catch (SQLException | ExceptionUsuario e){
                e.printStackTrace();
                DBManager.close();
                return false;
            }
            DBManager.close();
            return true;
        }
        return false;
    }
    /**
     * Metodo para rellenar la lista de tipos
     * @return true si ha podido rellenarla, false si no.
     */
    public static boolean getTipos() {
        if (DBManager.connect()) {
            try {
               listTipos.clear();
               ResultSet rs = DBManager.getTipos();
               while (rs.next()) {
                   listTipos.add(new Tipo(rs.getInt(1), rs.getString(2)));
               }
            } catch (SQLException e) {
                e.printStackTrace();
                DBManager.close();
                return false;
            }
            DBManager.close();
            return true;
        }
        return false;
    }

    /**
     * Metodo para obtener la localidad por el id del centro
     * @param id
     * @return String result
     */
    public static String getLocalidad(int id){
        String result = "";
        if (DBManager.connect()) {
            try{
                ResultSet rs = DBManager.getCentro(id);

                if (rs != null && rs.next()) {
                    result = rs.getString("localidad");
                }
            }catch (SQLException e){
                DBManager.close();
                return "";
            }
            DBManager.close();
            return result;
        }
        return result;
    }

    /**
     * Metodo para obtener el nombre del centro por su id
     * @param id
     * @return String result
     */
    public static String getNomCentro(int id){
        String result = "";
        if (DBManager.connect()) {
            try{
                ResultSet rs = DBManager.getCentro(id);

                if (rs != null && rs.next()) {
                    result = rs.getString("nombre");
                }
            }catch (SQLException e){
                DBManager.close();
                return "";
            }
            DBManager.close();
            return result;
        }
        return result;
    }
    /**
     * Metodo para obtener el cliente por su nif
     * @param nif
     * @return Cliente c, null si no hay cliente con ese nif
     */
    public static Cliente getCliente(String nif){
        for (Cliente c: listClientes) {
            if (c.getNif().equalsIgnoreCase(nif)) return c;
        }
        return null;
    }

    /**
     * Metodo para obtener el tipo por su nombre
     * @param nombre
     * @return Tipo tipo, null si no existe
     */
    public static Tipo getTipoByName(String nombre){
        for (Tipo tipo: listTipos) {
            if (tipo.getNombre().equalsIgnoreCase(nombre)) return tipo;
        }
        return null;
    }

    /**
     * Metodo para obtener el tipo por su id
     * @param id
     * @return Tipo tipo, null si no existe
     */
    public static Tipo getTipoById(int id){
        for (Tipo tipo: listTipos) {
            if (tipo.getId() == id) return tipo;
        }
        return null;
    }
    /**
     * Metodo para obtener el material por su id
     * @param id
     * @return Material material, null si no existe
     */
    public static Material getMaterialById(int id){
        for (Material material: listMaterial) {
            if (material.getCodigo() == id) return material;
        }
        return null;
    }

    /**
     * Metodo para obtener el centro por su localidad
     * @param localidad
     * @return Centro centro, null si no existe
     */
    public static Centro getCentroByLocalidad(String localidad){
        for (Centro centro: listCentros) {
            if (centro.getLocalidad().equalsIgnoreCase(localidad)) return centro;
        }
        return null;
    }
    /**
     * Metodo para obtener el centro por su nombre
     * @param nombre
     * @return Centro centro, null si no existe
     */
    public static Centro getCentroByName(String nombre){
        for (Centro centro: listCentros) {
            if (centro.getNombre().equalsIgnoreCase(nombre)) return centro;
        }
        return null;
    }

    /**
     * Metodo para obtener el centro por su id
     * @param id
     * @return Centro centro, null si no existe
     */
    public static Centro getCentroById(int id){
        for (Centro centro: listCentros) {
            if (centro.getId() == id) return centro;
        }
        return null;
    }

    /**
     * Metodo para obtener el id del centro por su nombre
     * @param centro
     * @return int id
     */
    public static int getIdCentroByName(String centro) {
        for (Centro centro1:listCentros) {
            if (centro1.getNombre().equalsIgnoreCase(centro)) return centro1.getId();
        }
        return 0;
    }

    /**
     * Metodo para obtener la actividad por su id
     * @param id
     * @return Actividad actividad, null si no existe
     */
    private static Actividad getActividadById(int id){
        for (Actividad actividad: listActividades) {
            if (actividad.getId() == id) return actividad;
        }
        return null;
    }

    /**
     * Metodo para obtener el cliente por su nif
     * @param nif
     * @return Cliente cliente, null si no existe
     */
    private static Cliente getClienteByNif(String nif){
        for (Cliente cliente: listClientes) {
            if (cliente.getNif().equalsIgnoreCase(nif)) return cliente;
        }
        return null;
    }

    /**
     * Metodo para obtener un proveedor por su nombre
     * @param nombre
     * @return Proveedor proveedor, null si no existe
     */
    public static Proveedor getProveedorByName(String nombre){
        for (Proveedor proveedor: listProveedor) {
            if (proveedor.getNombre().equalsIgnoreCase(nombre)) return proveedor;
        }
        return null;
    }

    /**
     * Metodo para obtener el proveedor por su id
     * @param id
     * @return Proveedor proveedor, null si no existe
     */
    public static Proveedor getProveedorId(int id){
        for (Proveedor proveedor: listProveedor) {
            if (proveedor.getId() == id) return proveedor;
        }
        return null;
    }

    /**
     * Metodo para obtener las dificultades de las actividades
     * @return List <String> dificultades
     */
    public static List<String> getDificultades(){
        ArrayList<String> dificultades = new ArrayList<>();
        for (Actividad a: listActividades) {
            if (!dificultades.contains(a.getDificultad())) dificultades.add(a.getDificultad());
        }
        return dificultades;
    }

    /*
        public static boolean getTipos() {
        if (DBManager.connect()) {
            try{
                listTipos.clear();
                ResultSet rs = DBManager.getTipos();//Select all tipos de actividades
                while(rs.next()){
                    listTipos.add(new Tipo(rs.getInt(1), rs.getString(2)));
                }
            }catch (SQLException e){
                e.printStackTrace();
                DBManager.close();
                return false;
            }
            DBManager.close();
            return true;
        }
        return false;
    } */

    /**
     * Metodo para agregar un trabajador a la lista
     * @param nif
     * @param nombre
     * @param apellido
     * @param edad
     * @param salario
     * @param idCentro
     * @param contrasenya
     * @return true si ha podido agregarse, false si no
     */
    public static boolean agregarTrabajador(String nif, String nombre, String apellido, int edad, double salario, int idCentro, String contrasenya) {
        Centro centro = findCentro(idCentro);
        if (DBManager.connect()){
            try {
                int res = DBManager.agregarTrabajador(nif, nombre, apellido, edad, salario, idCentro, contrasenya);
                listTrabajador.add(new Trabajador(nif, contrasenya, nombre, apellido, salario, edad, centro));
                DBManager.close();
                return res != 0;
            } catch (SQLException e) {
                DBManager.close();
                return false;
            } catch (ExceptionUsuario e) {
                throw new RuntimeException(e);
            }
        }else return false;
    }


    public static boolean editarTrabajador(Trabajador trabajador){
        int idCentro = trabajador.getCentro().getId();

        try {
            if (DBManager.connect() && (DBManager.editarTrabajador(trabajador, idCentro) != 0)){
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido actualizar el trabajador",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static int borrarTrabajador(String nif){
        int result = 0;
        if (DBManager.connect()) {
            try {
                result = DBManager.borrarTrabajador(nif);

                if (result > 0) {
                    listTrabajador.remove(PantallaGestionarTrabajadores.posicion);
                    DBManager.close();
                    return result;
                }
            } catch (SQLException e) {
                DBManager.close();
                return 0;
            }
        }
        return 0;
    }
    /**
     * Metodo para agregar un cliente a la lista
     * @param cliente
     * @return true si ha podido agregarse, false si no
     */
    public static boolean addCliente(Cliente cliente){
        if (findUsuario(cliente.getNif()) != null) return false;
        if (DBManager.connect()){
            try {
                int res = DBManager.agregarCliente(cliente);
                listClientes.add(cliente);
                //TODO Actualizar caché en inserts, deletes y updates de todas las clases
                DBManager.close();
                return res != 0;
            } catch (SQLException e) {
                DBManager.close();
                return false;
            }
        }else return false;
    }

    /**
     * Metodo para agregar un centro a la lista
     * @param centro
     * @return true si ha podido agregarse, false si no
     */
    public static boolean addCentro(Centro centro) {
        if (findCentro(centro.getId()) != null) return false;
        if (DBManager.connect()) {
            try {
                int res = DBManager.agregarCentro(centro);
                listCentros.add(centro);
                DBManager.close();
                return res != 0;
            } catch (SQLException e) {
                DBManager.close();
                return false;
            }
        } else return false;
    }
    /**
     * Metodo para editar un centro.
     * @param centro
     * @return true si se ha podido editar, false si no.
     */
    public static boolean editCentro(Centro centro){
        if (DBManager.connect()){
            int codigo = getIdCentroByName(centro.getNombre());
            if (codigo == -1) return false;
            int rs = DBManager.updateCentro(centro, codigo);
            if (rs == 0) return false;
            DBManager.close();
            return true;
        }return false;
    }

    /**
     * Metodo para editar un proveedor
     * @param proveedor
     * @return true si se ha podido editar, false si no.
     */
    public static boolean editProveedor(Proveedor proveedor){
        if (DBManager.connect()){
            try {
                ResultSet resultSet = DBManager.getProveedorId(proveedor);
                if (resultSet != null && resultSet.next()) {
                    proveedor.setId(resultSet.getInt("id"));
                }

                int rs = DBManager.updateProveedor(proveedor);
                if (rs == 0) return false;
                DBManager.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }return false;
    }

    /**
     * Metodo para agregar un proveedor
     * @param proveedor
     * @return true si se ha podido agregar, false si no
     */
    public static boolean addProveedor(Proveedor proveedor){
        if (listProveedor.contains(proveedor)) return false;
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getProveedorEdit();
                if (rs == null) return false;
                rs.moveToInsertRow();
                rs.updateString(2, proveedor.getNombre());
                rs.updateString(3, proveedor.getTelefono());
                rs.updateString(4, proveedor.getEmail());
                rs.insertRow();

                rs.close();
                listProveedor.add(proveedor);

                DBManager.close();
                return true;
            } catch (SQLException e) {
                DBManager.close();
                return false;
            }
        }else return false;
    }

    /**
     * Metodo para obtener el id de un proveedor
     * @param proveedor
     * @return int id, -1 si no existe
     */
    /*private static int getIdFromProveedor(Proveedor proveedor){
        for (Proveedor p: listProveedor) {
            if (p.getNombre().equals(proveedor.getNombre()) || p.getEmail().equals(proveedor.getEmail()) || p.getTelefono().equals(proveedor.getTelefono()))
                return p.getId();

        }
        return -1;
    }*/

    public static boolean addActividad(String nombre, String tipo, String localidad, Double precio,
                                       String dificultad, String descripcion){
        if (DBManager.connect()) {
            Tipo t = getTipoByName(tipo);
            Centro centro = getCentroByLocalidad(localidad);
            int rs = 0;
            try {
                rs = DBManager.addActividad(nombre,t.getId(),centro.getId(),precio,dificultad,descripcion);

                if (rs > 0) {
                    listActividades.add(new Actividad(nombre, descripcion,dificultad,precio, t,centro));
                    DBManager.close();
                    return true;
                }
            } catch (SQLException e) {
                DBManager.close();
                return false;
            }

        }
        DBManager.close();
        return false;
    }

    public static int borrarActividad(int id){
        int result = 0;
        if (DBManager.connect()) {
            try {
                result = DBManager.borrarActividad(id);

                if (result > 0) {
                    listActividades.remove(PantallaActividades.posicion);
                    DBManager.close();
                    return result;
                }
            } catch (SQLException e) {
                DBManager.close();
                return 0;
            }
        }
        return 0;
    }

    /**
     * Metodo para agregar una reserva
     * @param cliente
     * @param actividad
     * @return true si se ha podido agregar, false si no
     */
    public static boolean agregarReserva(Usuario cliente, Actividad actividad) {
        if (DBManager.connect()){
            try {
                int res = DBManager.agregarReserva(cliente, actividad);

                if (res > 0) {
                    DBManager.close();
                    return true;
                }
            } catch (SQLException e) {
                DBManager.close();
                return false;
            }
        }
        DBManager.close();
        return false;
    }

    /**
     * Metodo para editar un usuario
     * @param usu
     * @param nombre
     * @param contrasenya
     * @param nif
     * @return int rs si se ha podido editar, 0 si no
     */
    public static int editarUsuario(Usuario usu, String nombre, String contrasenya, String nif){
        if (DBManager.connect()) {
            try{
                int rs = DBManager.editarUsu(usu, nombre, contrasenya, nif);

                if (rs > 0) {
                    DBManager.close();
                    return rs;
                }
            }catch (SQLException e){
                DBManager.close();
                return 0;
            }
        }
        return 0;
    }

    /**
     * Metodo para encontrar un usuario por su nif
     * @param nif
     * @return Usuario t si es trabajador, c si es cliente, null si no se ha podido encontrar
     */
    public static Usuario findUsuario(String nif){
        for (Trabajador t: listTrabajador) {
            if (t.getNif().equals(nif)) return t;
        }
        for (Cliente c : listClientes) {
            if (c.getNif().equals(nif)) return c;
        }
        return null;
    }

    /**
     * Metodo para encontrar un centro por su id
     * @param id
     * @return Centro centro, null si no se ha encontrado
     */
    public static Centro findCentro(int id){
        for (Centro centro:listCentros) {
            if (centro.getId() == id) return centro;
        }
        return null;
    }

    /**
     * Metodo para obtener la contraseña por su nif
     * @param nif
     * @return String result
     */
    public static String getHashPassword(String nif){
        String result = "";
        if (DBManager.connect()) {
            try{
                ResultSet rs = DBManager.getHashPassword(nif);

                if (rs != null && rs.next()) {
                    result = rs.getString("contrasenya");
                }
            }catch (SQLException e){
                DBManager.close();
                return "";
            }
            DBManager.close();
            return result;
        }
        return result;
    }


    /**
     * Metodo para obtener la lista de actividades
     * @return List <Actividad> actividades
     */
    public static List<Actividad> getListActividades(){
        return listActividades;
    }
    /**
     * Metodo para obtener la lista de centros
     * @return List <Centro> centros
     */
    public static List<Centro> getListCentros(){
        return listCentros;
    }
    /**
     * Metodo para obtener la lista de materiales
     * @return List <Material> materiales
     */
    public static List<Material> getListMaterial(){
        return listMaterial;
    }
    /**
     * Metodo para obtener la lista de proveedores
     * @return List <Proveedor> proveedores
     */
    public static List<Proveedor> getListProveedor(){return listProveedor;}
    /**
     * Metodo para obtener la lista de trabajadores
     * @return List <Trabajador> trabajadores
     */
    public static List<Trabajador> getListTrabajador(){return listTrabajador;}
    public static List<Tipo> getListTipos(){
        return listTipos;
    }
    /**
     * Metodo para obtener la lista de clientes
     * @return List <Cliente> clientes
     */
    public static List<Cliente> getListClientes(){return listClientes;}
    /**
     * Metodo para obtener la lista de localidades de los centros
     * @return List <String>
     */
    public static List<String> getLocalidadesCentro() {
        return listCentros.stream().map(Centro::getLocalidad).distinct().toList();
    }
    /**
     * Metodo para obtener la lista de tipos de actividad por centro
     * @return List <String>
     */
    public static List<String> getTiposActividadesCentro() {
        return listTipos.stream().map(Tipo::getNombre).distinct().toList();
    }
    /**
     * Metodo para obtener la lista de reservas
     * @return List List<Map<String, Object>> listReservas
     */
    public static List<Map<String, Object>> getListReservas() {
        listReservas.sort(Comparator.comparing(r -> ((Date) r.get("columna1"))));
        return listReservas;
    }
    /**
     * Metodo para hacer una lista de reservas
     * @return true si ha podido hacerla, false si no
     */
    public static boolean getReservas(){

        if (DBManager.connect()) {
            for (Actividad actividad : listActividades) {
                for (Cliente cliente:listClientes) {
                    try {
                        ResultSet rs = DBManager.getReservasCli(cliente, actividad);
                        if (rs != null) {
                            while (rs.next()) {
                                Map<String, Object> reserva = new HashMap<>();
                                reserva.put("columna1", rs.getDate(1));
                                reserva.put("columna2", rs.getString(2));
                                reserva.put("columna3", rs.getInt(3));
                                reserva.put("columna4", rs.getInt(4));
                                listReservas.add(reserva);
                            }
                        }else return false;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        DBManager.close();
                        return false;
                    }
                }
            }
        }
        DBManager.close();
        return true;
    }


    public static int borrarReserva(String date, String nif, int idAct){
        int result = 0;
        if (DBManager.connect()) {
            try {
                result = DBManager.borrarReserva(date, nif, idAct);

                if (result > 0) {
                    listReservas.remove(PantallaReservas.posicion);
                    DBManager.close();
                    return result;
                }
            } catch (SQLException e) {
                DBManager.close();
                return 0;
            }
        }
        return 0;
    }
    /**
     * Metodo para obtener el precio de una actividad por su id
     * @param id
     * @return double resultado
     */
    public static double getPrecioAct(int id) {
        double resultado = 0;
        if (DBManager.connect()) {
            try {
                ResultSet rs = DBManager.getPrecioAct(id);
                if (rs != null && rs.next()) {
                    resultado = rs.getDouble("precio");
                    DBManager.close();
                    return resultado;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                DBManager.close();
                return 0;
            }
        }
        DBManager.close();
        return 0;
    }
}
