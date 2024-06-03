package Clases;

public class Material {
    private int codigo;
    private String nombre;
    private double precio;
    private int cantidad;
    private int idCentro;

    public Material(int codigo, String nombre, double precio, int cantidad, int idCentro) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.idCentro = idCentro;
    }

    public Material(String nombre, double precio, int cantidad, int idCentro) {
        this.codigo = 0;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.idCentro = idCentro;
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

    public int getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
    }

    @Override
    public String toString() {
        return codigo + ";" + nombre + ";" + precio + ";" + cantidad + ";" + idCentro;
    }
}
