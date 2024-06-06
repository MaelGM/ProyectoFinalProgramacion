package Objetos;

import Excepciones.ExceptionCliente;
import Excepciones.ExceptionTrabajador;
import Excepciones.ExceptionUsuario;

public class Trabajador extends Usuario{
    private String apellido;
    private double salario;
    private int idCentro;

    public Trabajador(String nif, String contrasenya, String nombre, String apellido, double salario, int edad, int idCentro) throws ExceptionUsuario, ExceptionTrabajador {
        super(nif, nombre, edad, contrasenya);
        setApellido(apellido);
        setSalario(salario);
        setIdCentro(idCentro);
    }

    public String getNif() {
        return super.getNif();
    }
    public String getContrasenya() {
        return super.getContrasenya();
    }
    public String getNombre() {
        return super.getNombre();
    }
    public String getApellido() {
        return apellido;
    }
    public double getSalario() {
        return salario;
    }
    public int getEdad() {
        return super.getEdad();
    }
    public int getIdCentro() {
        return idCentro;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setSalario(double salario) throws ExceptionTrabajador {
        if (salario > 0) this.salario = salario;
        else throw new ExceptionTrabajador("La edad no puede ser menor de 0");
    }
    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
    }

    @Override
    public boolean isTrabajador() {
        return true;
    }

    @Override
    public String toString() {
        return "Trabajador{" +
                "apellido='" + apellido + '\'' +
                ", salario=" + salario +
                ", idCentro=" + idCentro +
                '}';
    }
}
