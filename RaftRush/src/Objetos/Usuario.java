package Objetos;

import Excepciones.ExceptionUsuario;

import java.util.regex.Pattern;

/**
 * Clase abstracta Usuario, representa un usuario con NIF, nombre, edad y contraseña.
 */
public abstract class Usuario {
    /**
     * Campos de la clase.
     */
    private String nif;
    private String nombre;
    private int edad;
    private String contrasenya;

    /**
     * Letras utilizadas para la validación del NIF.
     */
    private static final char[] LETRAS = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

    /**
     * Constructor con todos los parámetros.
     *
     * @param nif El NIF del usuario.
     * @param nombre El nombre del usuario.
     * @param edad La edad del usuario.
     * @param contrasenya La contraseña del usuario.
     * @throws ExceptionUsuario Si alguno de los parámetros no cumple con los criterios esperados.
     */
    public Usuario(String nif, String nombre, int edad, String contrasenya) throws ExceptionUsuario {
        setNif(nif);
        setNombre(nombre);
        setEdad(edad);
        setContrasenya(contrasenya);
    }

    /**
     * Establece el NIF del usuario.
     *
     * @param nif El NIF del usuario.
     * @throws ExceptionUsuario Si el NIF no es válido.
     */
    private void setNif(String nif) throws ExceptionUsuario {
        if (validaNif(nif)) this.nif = nif;
        else throw new ExceptionUsuario("Nif incorrecto.");
    }

    /**
     * Valida el NIF.
     *
     * @param nif El NIF a validar.
     * @return true si el NIF es válido, false en caso contrario.
     */
    public static boolean validaNif(String nif) {
        if (nif == null || nif.length() != 9 || !nif.substring(0, 8).matches("\\d+")) return false;
        return nif.charAt(8) == LETRAS[Integer.parseInt(nif.substring(0, 8)) % 23];
    }

    /**
     * Obtiene el NIF del usuario.
     *
     * @return El NIF del usuario.
     */
    public String getNif() {
        return nif;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre El nombre del usuario.
     * @throws ExceptionUsuario Si el nombre es demasiado largo.
     */
    public void setNombre(String nombre) throws ExceptionUsuario {
        if (nombre.length() <= 100) this.nombre = nombre;
        else throw new ExceptionUsuario("Nombre demasiado largo");
    }

    /**
     * Obtiene la edad del usuario.
     *
     * @return La edad del usuario.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad del usuario.
     *
     * @param edad La edad del usuario.
     * @throws ExceptionUsuario Si la edad es negativa.
     */
    public void setEdad(int edad) throws ExceptionUsuario {
        if (edad > 0) this.edad = edad;
        else throw new ExceptionUsuario("No puede tener edad negativa");
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getContrasenya() {
        return contrasenya;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasenya La contraseña del usuario.
     * @throws ExceptionUsuario Si la contraseña no cumple con los criterios de validación.
     */
    public void setContrasenya(String contrasenya) throws ExceptionUsuario {
        if (isValid(contrasenya)) this.contrasenya = contrasenya;
    }

    /**
     * Valida la contraseña.
     *
     * @param contrasenya La contraseña a validar.
     * @return true si la contraseña es válida, false en caso contrario.
     * @throws ExceptionUsuario Si la contraseña no cumple con los criterios de validación.
     */
    private boolean isValid(String contrasenya) throws ExceptionUsuario {
        String mensaje = validaContrasenya(contrasenya);
        if (mensaje.equals("")) return true;
        else throw new ExceptionUsuario(mensaje);
    }

    /**
     * Verifica los criterios de la contraseña.
     *
     * @param contrasenya La contraseña a verificar.
     * @return Una cadena vacía si la contraseña es válida, un mensaje de error en caso contrario.
     */
    private String validaContrasenya(String contrasenya) {
        if (contrasenya.length() < 8) {
            return "La contraseña es demasiado corta (8 caracteres)";
        } else if (!Pattern.compile("[A-Za-z]").matcher(contrasenya).find()) {
            return "La contraseña debe tener al menos una letra";
        } else if (!Pattern.compile("[0-9]").matcher(contrasenya).find()) {
            return "La contraseña debe contener al menos un número";
        }
        return "";
    }

    /**
     * Método abstracto para verificar si el usuario es trabajador.
     *
     * @return true si el usuario es trabajador, false en caso contrario.
     */
    public abstract boolean isTrabajador();
}
