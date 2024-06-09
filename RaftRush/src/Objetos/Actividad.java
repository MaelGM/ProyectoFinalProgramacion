package Objetos;

import Excepciones.ExceptionActividad;


public class Actividad {
    private int id;
    private String nombre;
    private String descripcion;
    private String dificultad;
    private double precio;
    private Tipo tipo;
    private Centro centro;

    public Actividad(int id, String nombre, String descripcion, String dificultad, double precio, Tipo tipo, Centro centro) throws ExceptionActividad {
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        setDificultad(dificultad);
        setPrecio(precio);
        setTipo(tipo);
        setCentro(centro);
    }

    public Actividad(String nombre, String descripcion, String dificultad, double precio, Tipo tipo, Centro centro) throws ExceptionActividad {
        setNombre(nombre);
        setDescripcion(descripcion);
        setDificultad(dificultad);
        setPrecio(precio);
        setTipo(tipo);
        setCentro(centro);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) throws ExceptionActividad {
        if (id >= 0) this.id = id;
        else throw new ExceptionActividad("El id de la actividad debe ser mayor o igual que 0");
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
    public void setPrecio(double precio) throws ExceptionActividad {
        if (precio > 0) this.precio = precio;
        else throw new ExceptionActividad("El precio debe ser mayor que 0");
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
