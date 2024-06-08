import Excepciones.ExceptionUsuario;
import Objetos.*;

import javax.xml.stream.events.DTD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DataManager {
    private static List<Actividad> listActividades = new ArrayList<>();
    private static List<Centro> listCentros = new ArrayList<>();
    private static List<Material> listMaterial = new ArrayList<>();
    private static List<Proveedor> listProveedor = new ArrayList<>();
    private static List<Trabajador> listTrabajador = new ArrayList<>();
    private static List<Cliente> listClientes = new ArrayList<>();
    private static List<Tipo> listTipos = new ArrayList<>();
    private static List<Map<String, Object>> listReservas = new ArrayList<>();

    public static boolean getUsuarios() {
        return getClientes() && getTrabajador();
    }

    public static boolean getCentros(){
        if (DBManager.connect()) {
            try{
                listCentros.clear();
                ResultSet rs = DBManager.getCentro();//Select all centros
                while(rs.next()){
                    listCentros.add(new Centro(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getDouble(4)));
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
            }catch (SQLException e){
                DBManager.close();
                return false;
            }
            DBManager.close();
            return true;
        }
        return false;
    }

    private static Centro filterCentroById(int centroId) {
        for (int i = 0; i < listCentros.size(); i++) {
            if (listCentros.get(i).getId() == centroId) {
                return listCentros.get(i);
            }
        }
        return null;
    }

    private static Tipo filterTipoById(int id) {
        for (int i = 0; i < listTipos.size(); i++) {
            if (listTipos.get(i).getId() == id) {
                return listTipos.get(i);
            }
        }
        return null;
    }

    public static boolean getMaterial(){
        if (DBManager.connect()) {
            try{
                listMaterial.clear();
                ResultSet rs = DBManager.getMaterial();//Select all actividades
                while(rs.next()){
                    listMaterial.add(new Material(rs.getInt(1), rs.getString(2), rs.getDouble(3),
                            rs.getInt(4), getCentroById(rs.getInt(5))));
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

    public static boolean getProveedor(){
        if (DBManager.connect()) {
            try{
                listProveedor.clear();
                ResultSet rs = DBManager.getProveedor();//Select all actividades
                while(rs.next()){
                    listProveedor.add(new Proveedor(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4)));
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

    public static Cliente getCliente(String nif){
        for (Cliente c: listClientes) {
            if (c.getNif().equalsIgnoreCase(nif)) return c;
        }
        return null;
    }

    public static Tipo getTipoByName(String nombre){
        for (Tipo tipo: listTipos) {
            if (tipo.getNombre().equalsIgnoreCase(nombre)) return tipo;
        }
        return null;
    }
    public static Tipo getTipoById(int id){
        for (Tipo tipo: listTipos) {
            if (tipo.getId() == id) return tipo;
        }
        return null;
    }

    public static Material getMaterialById(int id){
        for (Material material: listMaterial) {
            if (material.getCodigo() == id) return material;
        }
        return null;
    }

    public static Centro getCentroByLocalidad(String localidad){
        for (Centro centro: listCentros) {
            if (centro.getLocalidad().equalsIgnoreCase(localidad)) return centro;
        }
        return null;
    }
    public static Centro getCentroByName(String nombre){
        for (Centro centro: listCentros) {
            if (centro.getNombre().equalsIgnoreCase(nombre)) return centro;
        }
        return null;
    }
    public static Centro getCentroById(int id){
        for (Centro centro: listCentros) {
            if (centro.getId() == id) return centro;
        }
        return null;
    }

    public static int getIdCentroByName(String centro) {
        for (Centro centro1:listCentros) {
            if (centro1.getNombre().equalsIgnoreCase(centro)) return centro1.getId();
        }
        return 0;
    }

    private static Actividad getActividadById(int id){
        for (Actividad actividad: listActividades) {
            if (actividad.getId() == id) return actividad;
        }
        return null;
    }

    private static Cliente getClienteByNif(String nif){
        for (Cliente cliente: listClientes) {
            if (cliente.getNif().equalsIgnoreCase(nif)) return cliente;
        }
        return null;
    }

    public static Proveedor getProveedorByName(String nombre){
        for (Proveedor proveedor: listProveedor) {
            if (proveedor.getNombre().equalsIgnoreCase(nombre)) return proveedor;
        }
        return null;
    }
    public static Proveedor getProveedorId(int id){
        for (Proveedor proveedor: listProveedor) {
            if (proveedor.getId() == id) return proveedor;
        }
        return null;
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

    public static boolean agregarTrabajador(String nif, String nombre, String apellido, int edad, double salario, int idCentro, String contrasenya) {
        Centro centro = findCentro(idCentro);
        if (DBManager.connect()){
            try {
                int res = DBManager.agregarTrabajador(nif, nombre, apellido, edad, salario, idCentro, contrasenya);
                listTrabajador.add(new Trabajador(nif, contrasenya, nombre, apellido, salario, edad, centro));
                //TODO Actualizar caché en inserts, deletes y updates de todas las clases
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

    public static Usuario findUsuario(String nif){
        for (Trabajador t: listTrabajador) {
            if (t.getNif().equals(nif)) return t;
        }
        for (Cliente c : listClientes) {
            if (c.getNif().equals(nif)) return c;
        }
        return null;
    }

    public static Centro findCentro(int id){
        for (Centro centro:listCentros) {
            if (centro.getId() == id) return centro;
        }
        return null;
    }

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

    public static List<Actividad> getListActividades(){
        return listActividades;
    }
    public static List<Centro> getListCentros(){
        return listCentros;
    }
    public static List<Material> getListMaterial(){
        return listMaterial;
    }
    public static List<Proveedor> getListProveedor(){return listProveedor;}
    public static List<Trabajador> getListTrabajador(){return listTrabajador;}
    public static List<Tipo> getListTipos(){
        return listTipos;
    }
    public static List<Cliente> getListClientes(){return listClientes;}
    public static List<String> getLocalidadesCentro() {
        return listCentros.stream().map(Centro::getLocalidad).distinct().toList();
    }
    public static List<String> getTiposActividadesCentro() {
        return listTipos.stream().map(Tipo::getNombre).distinct().toList();
    }

    public static List<Map<String, Object>> getListReservas() {
        listReservas.sort(Comparator.comparing(r -> ((Date) r.get("columna1"))));
        return listReservas;
    }
    /**
     * Metodo para hacer una lista de reservas
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
