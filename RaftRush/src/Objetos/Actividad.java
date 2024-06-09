package Objetos;

import Excepciones.ExceptionActividad;

/**
 * Clase objeto Actividad, define una propia actividad.
 */
public class Actividad {
    /**
     * Campos de la clase
     */
    private int id;
    private String nombre;
    private String descripcion;
    private String dificultad;
    private double precio;
    private Tipo tipo;
    private Centro centro;

    /**
     * Constructor por parámetros por defecto.
     *
     * @param id El identificador único de la actividad.
     * @param nombre El nombre de la actividad.
     * @param descripcion La descripción de la actividad.
     * @param dificultad La dificultad de la actividad.
     * @param precio El precio de la actividad.
     * @param tipo El tipo de la actividad.
     * @param centro El centro al que pertenece la actividad.
     * @throws ExceptionActividad Si alguno de los parámetros no cumple con los criterios esperados.
     */
    public Actividad(int id, String nombre, String descripcion, String dificultad, double precio, Tipo tipo, Centro centro) throws ExceptionActividad {
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        setDificultad(dificultad);
        setPrecio(precio);
        setTipo(tipo);
        setCentro(centro);
    }

    /**
     * Constructor por parámetros sin el id.
     *
     * @param nombre El nombre de la actividad.
     * @param descripcion La descripción de la actividad.
     * @param dificultad La dificultad de la actividad.
     * @param precio El precio de la actividad.
     * @param tipo El tipo de la actividad.
     * @param centro El centro al que pertenece la actividad.
     * @throws ExceptionActividad Si alguno de los parámetros no cumple con los criterios esperados.
     */
    public Actividad(String nombre, String descripcion, String dificultad, double precio, Tipo tipo, Centro centro) throws ExceptionActividad {
        setNombre(nombre);
        setDescripcion(descripcion);
        setDificultad(dificultad);
        setPrecio(precio);
        setTipo(tipo);
        setCentro(centro);
    }

    /**
     * Obtiene el identificador de la actividad.
     *
     * @return El identificador de la actividad.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador de la actividad.
     *
     * @param id El identificador de la actividad.
     * @throws ExceptionActividad Si el id es menor que 0.
     */
    public void setId(int id) throws ExceptionActividad {
        if (id >= 0) this.id = id;
        else throw new ExceptionActividad("El id de la actividad debe ser mayor o igual que 0");
    }

    /**
     * Obtiene el nombre de la actividad.
     *
     * @return El nombre de la actividad.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la actividad.
     *
     * @param nombre El nombre de la actividad.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción de la actividad.
     *
     * @return La descripción de la actividad.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la actividad.
     *
     * @param descripcion La descripción de la actividad.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la dificultad de la actividad.
     *
     * @return La dificultad de la actividad.
     */
    public String getDificultad() {
        return dificultad;
    }

    /**
     * Establece la dificultad de la actividad.
     *
     * @param dificultad La dificultad de la actividad.
     */
    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    /**
     * Obtiene el precio de la actividad.
     *
     * @return El precio de la actividad.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio de la actividad.
     *
     * @param precio El precio de la actividad.
     * @throws ExceptionActividad Si el precio es menor o igual que 0.
     */
    public void setPrecio(double precio) throws ExceptionActividad {
        if (precio > 0) this.precio = precio;
        else throw new ExceptionActividad("El precio debe ser mayor que 0");
    }

    /**
     * Obtiene el tipo de la actividad.
     *
     * @return El tipo de la actividad.
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de la actividad.
     *
     * @param tipo El tipo de la actividad.
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el centro al que pertenece la actividad.
     *
     * @return El centro al que pertenece la actividad.
     */
    public Centro getCentro() {
        return centro;
    }

    /**
     * Establece el centro al que pertenece la actividad.
     *
     * @param centro El centro al que pertenece la actividad.
     */
    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    /**
     * Devuelve una representación en cadena de la actividad.
     *
     * @return Una cadena que representa la actividad.
     */
    @Override
    public String toString() {
        return id + ";" + nombre + ";" + descripcion + ";" + dificultad + ";" + precio + ";" + tipo + ";" + centro;
    }
}
