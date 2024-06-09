package Objetos;

import Excepciones.ExceptionMaterial;

import java.util.Objects;

/**
 * Clase Material, representa un material con un código, nombre, precio, cantidad y centro asociado.
 */
public class Material {
    /**
     * Campos de la clase.
     */
    private int codigo;
    private String nombre;
    private double precio;
    private int cantidad;
    private Centro centro;

    /**
     * Constructor con todos los parámetros.
     *
     * @param codigo El código del material.
     * @param nombre El nombre del material.
     * @param precio El precio del material.
     * @param cantidad La cantidad disponible del material.
     * @param centro El centro al que pertenece el material.
     * @throws ExceptionMaterial Si alguno de los parámetros no cumple con los criterios esperados.
     */
    public Material(int codigo, String nombre, double precio, int cantidad, Centro centro) throws ExceptionMaterial {
        setCodigo(codigo);
        setNombre(nombre);
        setPrecio(precio);
        setCantidad(cantidad);
        setCentro(centro);
    }

    /**
     * Constructor sin el parámetro código.
     *
     * @param nombre El nombre del material.
     * @param precio El precio del material.
     * @param cantidad La cantidad disponible del material.
     * @param centro El centro al que pertenece el material.
     * @throws ExceptionMaterial Si alguno de los parámetros no cumple con los criterios esperados.
     */
    public Material(String nombre, double precio, int cantidad, Centro centro) throws ExceptionMaterial {
        this.codigo = 0;
        setNombre(nombre);
        setPrecio(precio);
        setCantidad(cantidad);
        setCentro(centro);
    }

    /**
     * Obtiene el código del material.
     *
     * @return El código del material.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Establece el código del material.
     *
     * @param codigo El código del material.
     * @throws ExceptionMaterial Si el código es menor que 0.
     */
    public void setCodigo(int codigo) throws ExceptionMaterial {
        if (codigo >= 0) this.codigo = codigo;
        else throw new ExceptionMaterial("El código del material debe ser mayor o igual que 0");
    }

    /**
     * Obtiene el nombre del material.
     *
     * @return El nombre del material.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del material.
     *
     * @param nombre El nombre del material.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el precio del material.
     *
     * @return El precio del material.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del material.
     *
     * @param precio El precio del material.
     * @throws ExceptionMaterial Si el precio es menor o igual que 0.
     */
    public void setPrecio(double precio) throws ExceptionMaterial {
        if (precio > 0) this.precio = precio;
        else throw new ExceptionMaterial("El precio de un material debe ser mayor que 0");
    }

    /**
     * Obtiene la cantidad disponible del material.
     *
     * @return La cantidad disponible del material.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad disponible del material.
     *
     * @param cantidad La cantidad disponible del material.
     * @throws ExceptionMaterial Si la cantidad es menor que 0.
     */
    public void setCantidad(int cantidad) throws ExceptionMaterial {
        if (cantidad >= 0) this.cantidad = cantidad;
        else throw new ExceptionMaterial("La cantidad de un material debe ser mayor o igual que 0");
    }

    /**
     * Obtiene el centro al que pertenece el material.
     *
     * @return El centro al que pertenece el material.
     */
    public Centro getCentro() {
        return centro;
    }

    /**
     * Establece el centro al que pertenece el material.
     *
     * @param centro El centro al que pertenece el material.
     */
    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    /**
     * Devuelve una representación en cadena del material.
     *
     * @return Una cadena que representa el material.
     */
    @Override
    public String toString() {
        return codigo + ";" + nombre + ";" + precio + ";" + cantidad + ";" + centro;
    }

    /**
     * Compara este material con el objeto especificado para determinar si son iguales.
     *
     * @param o El objeto a comparar.
     * @return true si los objetos son iguales; false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return Objects.equals(nombre, material.nombre) &&
                Objects.equals(centro, material.centro);
    }

    /**
     * Calcula el código hash para este material.
     *
     * @return El código hash del material.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre, centro);
    }
}
