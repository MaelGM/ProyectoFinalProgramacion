import Excepciones.ExceptionUsuario;
import Objetos.*;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DataManager {
    private static List<Actividad> listActividades = new ArrayList<>();
    private static List<Centro> listCentros = new ArrayList<>();
    private static List<Material> listMaterial = new ArrayList<>();
    private static List<Proveedor> listProveedor = new ArrayList<>();
    private static List<Trabajador> listTrabajador = new ArrayList<>();
    private static List<Cliente> listClientes = new ArrayList<>();
    private static List<Tipo> listTipos = new ArrayList<>();

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
                    listActividades.add(new Actividad(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4),rs.getDouble(5), getTipoById(rs.getInt(6)),
                            getCentroById(rs.getInt(7))));
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

    public static boolean getReservas(){
        if (DBManager.connect()) {
            try{
                ResultSet rs = DBManager.getReservas();//Select all actividades
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

    public static List<Actividad> getActividadesReservas(){
        if (DBManager.connect()) {
            List<Actividad> actividades = new ArrayList<>();
            try{
                ResultSet rs = DBManager.getReservas();//Select all actividades
                while(rs.next()){
                    actividades.add(getActividadById(rs.getInt(3)));
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

    public static List<Date> getFechasReservas(){
        if (DBManager.connect()) {
            List<Date> fechasReservas = new ArrayList<>();
            try{
                ResultSet rs = DBManager.getReservas();//Select all actividades
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

    public static String getTipo(int id){
        String result = "";
        if (DBManager.connect()) {
            try{
                ResultSet rs = DBManager.getTipo(id);

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
    }

    public static boolean addCliente(Cliente cliente){
        if (findUsuario(cliente.getNif()) != null) return false;
        if (DBManager.connect()){
            try {
                int res = DBManager.agregarCliente(cliente);
                DBManager.close();
                return res != 0;
            } catch (SQLException e) {
                DBManager.close();
                return false;
            }
        }else return false;
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

    public static boolean agregarTrabajador(Trabajador trabajador){
        int idCentro = trabajador.getCentro().getId();

        try {
            if (DBManager.connect() && (DBManager.agregarTrabajador(trabajador, idCentro) != 0)){
                listTrabajador.add(trabajador);
                DBManager.close();
                return true;
            }
        } catch (SQLException e) {
            DBManager.close();
            return false;
        }
        return false;
    }

    public static boolean editarTrabajador(Trabajador trabajador, String nif){
        int idCentro = trabajador.getCentro().getId();


        try {
            if (DBManager.connect() && (DBManager.editarTrabajador(trabajador, nif, idCentro) != 0)){
                for (Trabajador t: listTrabajador){
                    if (t.getNif().equals(nif)){
                        try{
                            t.setNombre(String.valueOf(trabajador.getNombre()));
                            t.setApellido(String.valueOf(trabajador.getApellido()));
                            t.setSalario(trabajador.getSalario());
                            t.setEdad(trabajador.getEdad());
                            t.setIdCentro(trabajador.getCentro());

                            DBManager.close();
                            return true;
                        } catch (ExceptionUsuario e){
                            JOptionPane.showMessageDialog(null, "Alguno de los campos es incorrecto",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            return false;
                        }

                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido actualizar el trabajador",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
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
    public static List<String> getLocalidadesCentro() {
        return listCentros.stream().map(Centro::getLocalidad).distinct().toList();
    }
    public static List<String> getTiposActividadesCentro() {
        return listTipos.stream().map(Tipo::getNombre).distinct().toList();
    }

    public static List<Map<String, Object>> getListEntregas(){
        List<Map<String, Object>> entregas = new ArrayList<>();

        if (DBManager.connect()) {
            for (Material material:listMaterial) {
                for (Proveedor proveedor:listProveedor) {
                    try {
                        ResultSet rs = DBManager.getEntregas(material,proveedor);
                        if (rs != null) {
                            while (rs.next()){
                                Map<String, Object> entrega = new HashMap<>();
                                entrega.put("column1", rs.getString(1));
                                entrega.put("column2", rs.getInt(2));
                                entrega.put("column3", rs.getInt(3));
                                entrega.put("column4", rs.getInt(4));
                                entregas.add(entrega);
                            }
                        }
                    } catch (SQLException e) {
                        DBManager.close();
                        return null;
                    }
                }
            }
        }
        DBManager.close();
        return entregas;
    }
}
