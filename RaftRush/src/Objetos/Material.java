package Objetos;

import Excepciones.ExceptionMaterial;

import java.util.Objects;

public class Material {
    private int codigo;
    private String nombre;
    private double precio;
    private int cantidad;
    private Centro centro;

    public Material(int codigo, String nombre, double precio, int cantidad, Centro centro) throws ExceptionMaterial {
        setCodigo(codigo);
        setNombre(nombre);
        setPrecio(precio);
        setCantidad(cantidad);
        setCentro(centro);
    }

    public Material(String nombre, double precio, int cantidad, Centro centro) throws ExceptionMaterial {
        this.codigo = 0;
        setNombre(nombre);
        setPrecio(precio);
        setCantidad(cantidad);
        setCentro(centro);
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) throws ExceptionMaterial {
        if (codigo >= 0) this.codigo = codigo;
        else throw new ExceptionMaterial("El codigo del material debe ser mayor o igual que 0");
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) throws ExceptionMaterial {
        if (precio > 0) this.precio = precio;
        else throw new ExceptionMaterial("El precio de un material no puede ser negativo");
    }

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) throws ExceptionMaterial {
        if (cantidad >= 0) this.cantidad = cantidad;
        else throw new ExceptionMaterial("La cantidad de un material");
    }

    public Centro getCentro() {
        return centro;
    }
    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    @Override
    public String toString() {
        return codigo + ";" + nombre + ";" + precio + ";" + cantidad + ";" + centro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return Objects.equals(nombre, material.nombre) &&
                Objects.equals(centro, material.centro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, centro);
    }
}
