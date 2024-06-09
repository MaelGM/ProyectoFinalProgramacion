package Objetos;

import Excepciones.ExceptionProveedor;

import java.util.regex.Pattern;

/**
 * Clase Proveedor, representa un proveedor con id, nombre, teléfono y email.
 */
public class Proveedor {
    /**
     * Campos de la clase.
     */
    private int id;
    private String nombre;
    private String telefono;
    private String email;

    /**
     * Constructor con todos los parámetros.
     *
     * @param id El identificador único del proveedor.
     * @param nombre El nombre del proveedor.
     * @param telefono El teléfono del proveedor.
     * @param email El email del proveedor.
     * @throws ExceptionProveedor Si alguno de los parámetros no cumple con los criterios esperados.
     */
    public Proveedor(int id, String nombre, String telefono, String email) throws ExceptionProveedor {
        setId(id);
        setNombre(nombre);
        setTelefono(telefono);
        setEmail(email);
    }

    /**
     * Constructor sin el parámetro id.
     *
     * @param nombre El nombre del proveedor.
     * @param telefono El teléfono del proveedor.
     * @param email El email del proveedor.
     * @throws ExceptionProveedor Si alguno de los parámetros no cumple con los criterios esperados.
     */
    public Proveedor(String nombre, String telefono, String email) throws ExceptionProveedor {
        setNombre(nombre);
        setTelefono(telefono);
        setEmail(email);
    }

    /**
     * Obtiene el id del proveedor.
     *
     * @return El id del proveedor.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id del proveedor.
     *
     * @param id El id del proveedor.
     * @throws ExceptionProveedor Si el id es negativo.
     */
    public void setId(int id) throws ExceptionProveedor {
        if (id >= 0) this.id = id;
        else throw new ExceptionProveedor("El id no puede ser negativo");
    }

    /**
     * Obtiene el nombre del proveedor.
     *
     * @return El nombre del proveedor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del proveedor.
     *
     * @param nombre El nombre del proveedor.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el teléfono del proveedor.
     *
     * @return El teléfono del proveedor.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del proveedor.
     *
     * @param telefono El teléfono del proveedor.
     * @throws ExceptionProveedor Si el teléfono no sigue el formato xxx-xxx-xxxx.
     */
    public void setTelefono(String telefono) throws ExceptionProveedor {
        if (Pattern.compile("\\d{3}-\\d{3}-\\d{4}").matcher(telefono).matches()) this.telefono = telefono;
        else throw new ExceptionProveedor("El teléfono no sigue el siguiente formato: xxx-xxx-xxxx");
    }

    /**
     * Obtiene el email del proveedor.
     *
     * @return El email del proveedor.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del proveedor.
     *
     * @param email El email del proveedor.
     * @throws ExceptionProveedor Si el email no contiene un @.
     */
    public void setEmail(String email) throws ExceptionProveedor {
        if (email.contains("@")) this.email = email;
        else throw new ExceptionProveedor("El email no cumple el formato");
    }

    /**
     * Devuelve una representación en cadena del proveedor.
     *
     * @return Una cadena que representa al proveedor.
     */
    @Override
    public String toString() {
        return id + ";" + nombre + ";" + telefono + ";" + email;
    }
}
