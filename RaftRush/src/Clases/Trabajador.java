package Clases;

public class Trabajador {
    private String nif;
    private String contrasanya;
    private String nombre;
    private String apellido;
    private double salario;
    private int edad;
    private int idCentro;

    public Trabajador(String nif, String contrasanya, String nombre, String apellido, double salario, int edad, int idCentro) {
        this.nif = nif;
        this.contrasanya = contrasanya;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
        this.edad = edad;
        this.idCentro = idCentro;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getContrasanya() {
        return contrasanya;
    }

    public void setContrasanya(String contrasanya) {
        this.contrasanya = contrasanya;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
    }

    @Override
    public String toString() {
        return nif + ";" + contrasanya + ";" + nombre + ";" + apellido + ";" + salario + ";" + edad + ";" + idCentro;
    }
}
