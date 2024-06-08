package Objetos;

import java.util.Objects;

public class Centro {
    private int id;
    private String nombre;
    private String localidad;
    private double presupuesto;

    public Centro(int id, String nombre, String localidad, double presupuesto) {
        this.id = id;
        this.nombre = nombre;
        this.localidad = localidad;
        this.presupuesto = presupuesto;
    }

    public Centro(String nombre, String localidad, double presupuesto) {
        this.nombre = nombre;
        this.localidad = localidad;
        this.presupuesto = presupuesto;
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

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    @Override
    public String toString() {
        return id + ";" + nombre + ";" + localidad + ";" + presupuesto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Centro centro = (Centro) o;
        return Objects.equals(localidad, centro.localidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localidad);
    }
}
