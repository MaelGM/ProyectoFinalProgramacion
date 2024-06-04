import Objetos.Actividad;
import Objetos.Centro;
import Objetos.Material;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static List<Actividad> listActividades = new ArrayList<>();
    private static List<Centro> listCentros = new ArrayList<>();
    private static List<Material> listMaterial = new ArrayList<>();

    public static boolean getCentros(){
        if (DBManager.connect()) {
            try{
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

    public static List<Actividad> getListActividades(){
        return listActividades;
    }

    public static List<Centro> getListCentros(){
        return listCentros;
    }

    public static List<Material> getListMaterial(){
        return listMaterial;
    }
}
