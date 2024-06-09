package Objetos;

import Excepciones.ExceptionCentro;

import java.util.Objects;

/**
 * Clase objeto Centro, define un centro con un presupuesto y una localidad.
 */
public class Centro {
    /**
     * Campos de la clase.
     */
    private int id;
    private String nombre;
    private String localidad;
    private double presupuesto;

    /**
     * Constructor con todos los parámetros.
     *
     * @param id El identificador único del centro.
     * @param nombre El nombre del centro.
     * @param localidad La localidad donde se encuentra el centro.
     * @param presupuesto El presupuesto asignado al centro.
     * @throws ExceptionCentro Si alguno de los parámetros no cumple con los criterios esperados.
     */
    public Centro(int id, String nombre, String localidad, double presupuesto) throws ExceptionCentro {
        setId(id);
        setNombre(nombre);
        setLocalidad(localidad);
        setPresupuesto(presupuesto);
    }

    /**
     * Constructor sin el parámetro id.
     *
     * @param nombre El nombre del centro.
     * @param localidad La localidad donde se encuentra el centro.
     * @param presupuesto El presupuesto asignado al centro.
     * @throws ExceptionCentro Si alguno de los parámetros no cumple con los criterios esperados.
     */
    public Centro(String nombre, String localidad, double presupuesto) throws ExceptionCentro {
        setNombre(nombre);
        setLocalidad(localidad);
        setPresupuesto(presupuesto);
    }

    /**
     * Obtiene el identificador del centro.
     *
     * @return El identificador del centro.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del centro.
     *
     * @param id El identificador del centro.
     * @throws ExceptionCentro Si el id es menor que 0.
     */
    public void setId(int id) throws ExceptionCentro {
        if (id >= 0) this.id = id;
        else throw new ExceptionCentro("El id del centro debe ser mayor o igual que 0");
    }

    /**
     * Obtiene el nombre del centro.
     *
     * @return El nombre del centro.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del centro.
     *
     * @param nombre El nombre del centro.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la localidad del centro.
     *
     * @return La localidad del centro.
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Establece la localidad del centro.
     *
     * @param localidad La localidad del centro.
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * Obtiene el presupuesto del centro.
     *
     * @return El presupuesto del centro.
     */
    public double getPresupuesto() {
        return presupuesto;
    }

    /**
     * Establece el presupuesto del centro.
     *
     * @param presupuesto El presupuesto del centro.
     * @throws ExceptionCentro Si el presupuesto es negativo.
     */
    public void setPresupuesto(double presupuesto) throws ExceptionCentro {
        if (presupuesto >= 0) this.presupuesto = presupuesto;
        else throw new ExceptionCentro("El presupuesto de un centro no puede ser negativo");
    }

    /**
     * Devuelve una representación en cadena del centro.
     *
     * @return Una cadena que representa el centro.
     */
    @Override
    public String toString() {
        return id + ";" + nombre + ";" + localidad + ";" + presupuesto;
    }

    /**
     * Compara este centro con el objeto especificado para determinar si son iguales.
     *
     * @param o El objeto a comparar.
     * @return true si los objetos son iguales; false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Centro centro = (Centro) o;
        return Objects.equals(localidad, centro.localidad);
    }

    /**
     * Calcula el código hash para este centro.
     *
     * @return El código hash del centro.
     */
    @Override
    public int hashCode() {
        return Objects.hash(localidad);
    }
}
