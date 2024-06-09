package Objetos;

import Excepciones.ExceptionProveedor;

import java.util.regex.Pattern;

public class Proveedor {
    private int id;
    private String nombre;
    private String telefono;
    private String email;

    public Proveedor(int id, String nombre, String telefono, String email) throws ExceptionProveedor {
        setId(id);
        setNombre(nombre);
        setTelefono(telefono);
        setEmail(email);
    }

    public Proveedor(String nombre, String telefono, String email) throws ExceptionProveedor {
        setNombre(nombre);
        setTelefono(telefono);
        setEmail(email);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) throws ExceptionProveedor {
        if (id >= 0) this.id = id;
        else throw new ExceptionProveedor("El id no puede ser negativo");
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) throws ExceptionProveedor {
        if (Pattern.compile("\\d{3}-\\d{3}-\\d{4}").matcher(telefono).matches()) this.telefono = telefono;
        else throw new ExceptionProveedor("El telefono no sigue el siguiente formato: xxx-xxx-xxxx");
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) throws ExceptionProveedor {
        if (email.contains("@")) this.email = email;
        else throw new ExceptionProveedor("El email no cumple el formato");
    }

    @Override
    public String toString() {
        return id + ";" + nombre + ";" + telefono + ";" + email;
    }
}
