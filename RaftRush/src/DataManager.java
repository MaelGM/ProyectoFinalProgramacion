import Excepciones.ExceptionUsuario;
import Objetos.*;

import javax.sound.sampled.Port;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static List<Actividad> listActividades = new ArrayList<>();
    private static List<Centro> listCentros = new ArrayList<>();
    private static List<Material> listMaterial = new ArrayList<>();
    private static List<Proveedor> listProveedor = new ArrayList<>();
    private static List<Trabajador> listTrabajador = new ArrayList<>();
    private static List<Cliente> listClientes = new ArrayList<>();


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
                            rs.getString(4),rs.getDouble(5),rs.getInt(6),
                            rs.getInt(7)));
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
                            rs.getInt(4),rs.getInt(5)));
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

    public static boolean getTrabajador() {
        if (DBManager.connect()) {
            try{
                listTrabajador.clear();
                ResultSet rs = DBManager.getTrabajador();//Select all trabajadores
                while(rs.next()){
                    listTrabajador.add(new Trabajador(rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getDouble(5), rs.getInt(6), rs.getInt(7)));
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

    public static boolean checkPassword(Usuario user, String password){
        return user.getContrasenya().equals(password);
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



}
