package Clases;

import Excepciones.ExceptionCliente;

public class Cliente {
    private String nif;
    private String contrasenya;
    private String telefono;
    private String nombre;
    private int edad;

    public Cliente(String nif, String contrasenya, String telefono, String nombre, int edad) throws ExceptionCliente {
        if (validaNif(nif)) {
            setNif(nif);
        }
        validaContrasenya(contrasenya);
        validaTelefono(telefono);
        validaNombre(nombre);
        validaEdad(edad);
    }

    ///////////////////////////
    public String getNif() {
        return nif;
    }

    private boolean validaNif(String nif) throws ExceptionCliente {
        if (nif.length() == 9) {
            return true;
        }else{
            throw new ExceptionCliente("El nif esta mal formateado");
        }
    }

    public void setNif(String nif) {
        this.nif = nif;
    }
    ///////////////////////////

    public String getContrasenya() {
        return contrasenya;
    }

    private void validaContrasenya(String contrasenya) throws ExceptionCliente {
        if (contrasenya.length() <= 25) {
            setContrasenya(contrasenya);
        }else{
            throw new ExceptionCliente("La contraseña debe ser menos de 25 caracteres");
        }
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }
    ///////////////////////////
    public String getTelefono() {
        return telefono;
    }

    private void validaTelefono(String telefono) throws ExceptionCliente {
        if (telefono.length() <= 20) {
            setTelefono(telefono);
        }else{
            throw new ExceptionCliente("El telefono esta mal formateado");
        }
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    ///////////////////////////
    public String getNombre() {
        return nombre;
    }

    private void validaNombre(String nombre) throws ExceptionCliente {
        if (nombre.length() <= 100) {
            setNombre(nombre);
        }else{
            throw new ExceptionCliente("Pero que nombre tienes loco. Cambia eso");
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    ///////////////////////////
    public int getEdad() {
        return edad;
    }

    private void validaEdad(int edad) throws ExceptionCliente {
        if (edad > 0) {
            setEdad(edad);
        }else{
            throw new ExceptionCliente("La edad no puede ser menor de 0");
        }
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    ///////////////////////////
    @Override
    public String toString() {
        return nif + ";" + contrasenya + ";" + telefono + ";" + nombre + ";" + edad;
    }
}
