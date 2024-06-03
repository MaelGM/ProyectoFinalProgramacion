package Clases;

import Excepciones.ExceptionCliente;

public class Trabajador {
    private String nif;
    private String contrasenya;
    private String nombre;
    private String apellido;
    private double salario;
    private int edad;
    private int idCentro;

    public Trabajador(String nif, String contrasenya, String nombre, String apellido, double salario, int edad, int idCentro) {
        this.nif = nif;
        this.contrasenya = contrasenya;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
        this.edad = edad;
        this.idCentro = idCentro;
    }

    ///////////////////////////////
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
    ///////////////////////////////
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

    public void setContrasenya(String contrasanya) {
        this.contrasenya = contrasanya;
    }
    ///////////////////////////////
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
    ///////////////////////////////
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    ///////////////////////////////
    public double getSalario() {
        return salario;
    }

    private void validaSalario(double salario) throws ExceptionCliente {
        if (salario > 0) {
            setSalario(salario);
        }else{
            throw new ExceptionCliente("La edad no puede ser menor de 0");
        }
    }


    public void setSalario(double salario) {
        this.salario = salario;
    }
    ///////////////////////////////
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
    ///////////////////////////////
    public int getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
    }
    ///////////////////////////////
    @Override
    public String toString() {
        return nif + ";" + contrasenya + ";" + nombre + ";" + apellido + ";" + salario + ";" + edad + ";" + idCentro;
    }
}
