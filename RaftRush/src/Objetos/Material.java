package Objetos;

public class Material {
    private int codigo;
    private String nombre;
    private double precio;
    private int cantidad;
    private Centro centro;

    public Material(int codigo, String nombre, double precio, int cantidad, Centro centro) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.centro = centro;
    }

    public Material(String nombre, double precio, int cantidad, Centro centro) {
        this.codigo = 0;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.centro = centro;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
}
