package Objetos;

import Excepciones.ExceptionCliente;
import Excepciones.ExceptionUsuario;

/**
 * Clase Cliente que extiende la clase Usuario, representa a un cliente con un teléfono.
 */
public class Cliente extends Usuario {
    /**
     * Campos de la clase.
     */
    private String telefono;

    /**
     * Constructor con todos los parámetros.
     *
     * @param nif El NIF del cliente.
     * @param contrasenya La contraseña del cliente.
     * @param telefono El teléfono del cliente.
     * @param nombre El nombre del cliente.
     * @param edad La edad del cliente.
     * @throws ExceptionUsuario Si alguno de los parámetros de la clase padre no cumple con los criterios esperados.
     * @throws ExceptionCliente Si el teléfono no está bien formateado.
     */
    public Cliente(String nif, String contrasenya, String telefono, String nombre, int edad) throws ExceptionUsuario, ExceptionCliente {
        super(nif, nombre, edad, contrasenya);
        validaTelefono(telefono);
    }

    /**
     * Obtiene la contraseña del cliente.
     *
     * @return La contraseña del cliente.
     */
    public String getContrasenya() {
        return super.getContrasenya();
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return super.getNombre();
    }

    /**
     * Obtiene la edad del cliente.
     *
     * @return La edad del cliente.
     */
    public int getEdad() {
        return super.getEdad();
    }

    /**
     * Obtiene el teléfono del cliente.
     *
     * @return El teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Valida el formato del teléfono y lo establece.
     *
     * @param telefono El teléfono del cliente.
     * @throws ExceptionCliente Si el teléfono no está bien formateado.
     */
    private void validaTelefono(String telefono) throws ExceptionCliente {
        if (telefono.length() <= 9) {
            setTelefono(telefono);
        } else {
            throw new ExceptionCliente("El teléfono está mal formateado");
        }
    }

    /**
     * Establece el teléfono del cliente.
     *
     * @param telefono El teléfono del cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Indica si el usuario es un trabajador.
     *
     * @return false siempre, ya que este objeto es un cliente.
     */
    @Override
    public boolean isTrabajador() {
        return false;
    }

    /**
     * Devuelve una representación en cadena del cliente.
     *
     * @return Una cadena que representa al cliente.
     */
    @Override
    public String toString() {
        return "Cliente{" +
                "telefono='" + telefono + '\'' +
                '}';
    }
}
