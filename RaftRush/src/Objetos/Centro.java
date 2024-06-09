package Objetos;

import Excepciones.ExceptionActividad;
import Excepciones.ExceptionCentro;

import java.util.Objects;

public class Centro {
    private int id;
    private String nombre;
    private String localidad;
    private double presupuesto;

    public Centro(int id, String nombre, String localidad, double presupuesto) throws ExceptionCentro {
        setId(id);
        setNombre(nombre);
        setLocalidad(localidad);
        setPresupuesto(presupuesto);
    }

    public Centro(String nombre, String localidad, double presupuesto) throws ExceptionCentro {
        setNombre(nombre);
        setLocalidad(localidad);
        setPresupuesto(presupuesto);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) throws ExceptionCentro {
        if (id >= 0) this.id = id;
        else throw new ExceptionCentro("El id del centro debe ser mayor o igual que 0");
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
    public void setPresupuesto(double presupuesto) throws ExceptionCentro {
        if (presupuesto >= 0) this.presupuesto = presupuesto;
        else throw new ExceptionCentro("El presupuesto de un centro no puede ser negativo");
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
