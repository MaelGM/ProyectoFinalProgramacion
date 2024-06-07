package Objetos;

public class Actividad {
    private int id;
    private String nombre;
    private String descripcion;
    private String dificultad;
    private double precio;
    private Tipo tipo;
    private Centro centro;

    public Actividad(int id, String nombre, String descripcion, String dificultad, double precio, Tipo tipo, Centro centro) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.precio = precio;
        this.tipo = tipo;
        this.centro = centro;
    }

    public Actividad(String nombre, String descripcion, String dificultad, double precio, Tipo tipo, Centro centro) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.precio = precio;
        this.tipo = tipo;
        this.centro = centro;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDificultad() {
        return dificultad;
    }
    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Centro getCentro() {
        return centro;
    }
    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    @Override
    public String toString() {
        return id + ";" + nombre + ";" + descripcion + ";" + dificultad + ";" + precio + ";" + tipo + ";" + centro;
    }
}
