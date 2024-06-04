package Objetos;

import Excepciones.ExceptionUsuario;

import java.util.regex.Pattern;

public abstract class Usuario {
    private String nif;
    private String nombre;
    private int edad;
    private String contrasenya;

    private static final char[] LETRAS = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

    public Usuario(String nif, String nombre, int edad, String contrasenya) throws ExceptionUsuario {
        setNif(nif);
        setNombre(nombre);
        setEdad(edad);
        setContrasenya(contrasenya);
    }

    private void setNif(String nif) throws ExceptionUsuario {
        this.nif = nif;
        if (validaNif(nif)) this.nif = nif;
        else throw new ExceptionUsuario("Nif incorrecto.");
    }
    public static boolean validaNif(String nif){
        if (nif == null || nif.length() != 9 || !nif.substring(0,8).matches("\\d+")) return false;

        return nif.charAt(8) == LETRAS[Integer.parseInt(nif.substring(0,8)) % 23];
    }
    public String getNif() {
        return nif;
    }

    public String getNombre() {
        return nombre;
    }
    private void setNombre(String nombre) throws ExceptionUsuario {
        if (nombre.length() <= 100) this.nombre = nombre;
        else throw new ExceptionUsuario("Nombre demasiado largo");
    }

    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) throws ExceptionUsuario {
        if (edad > 0) this.edad = edad;
        else throw new ExceptionUsuario("No puede tener edad negativa");
    }

    public String getContrasenya() {
        return contrasenya;
    }
    public void setContrasenya(String contrasenya) throws ExceptionUsuario {
        this.contrasenya = contrasenya;
        if (isValid(contrasenya)) this.contrasenya = contrasenya;
    }
    private boolean isValid(String contrasenya) throws ExceptionUsuario {
        String mensaje = validaContrasenya(contrasenya);
        if (mensaje.equals("")) return true;
        else throw new ExceptionUsuario(mensaje);
    }
    private  String validaContrasenya(String contrasenya) {
        if (contrasenya.length() < 8) {
            return "La contraseña es demasiado corta (8 caracteres)";
        }else if (!Pattern.compile("[A-Za-z]").matcher(contrasenya).find()){
            return "La contraseña debe tener al menos una letra";
        }else if (!Pattern.compile("[0-9]").matcher(contrasenya).find()){
            return "La contraseña debe contener al menos un numero";
        }
        return "";
    }


    public abstract boolean isTrabajador();
}
