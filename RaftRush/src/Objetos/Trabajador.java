package Objetos;

import Excepciones.ExceptionTrabajador;
import Excepciones.ExceptionUsuario;

/**
 * Clase Trabajador, representa un trabajador con un apellido, salario y centro asociado.
 */
public class Trabajador extends Usuario {
    /**
     * Campos de la clase.
     */
    private String apellido;
    private double salario;
    private Centro centro;

    /**
     * Constructor con todos los parámetros.
     *
     * @param nif El NIF del trabajador.
     * @param contrasenya La contraseña del trabajador.
     * @param nombre El nombre del trabajador.
     * @param apellido El apellido del trabajador.
     * @param salario El salario del trabajador.
     * @param edad La edad del trabajador.
     * @param centro El centro al que pertenece el trabajador.
     * @throws ExceptionUsuario Si alguno de los parámetros no cumple con los criterios esperados.
     */
    public Trabajador(String nif, String contrasenya, String nombre, String apellido, double salario, int edad, Centro centro) throws ExceptionUsuario {
        super(nif, nombre, edad, contrasenya);
        setApellido(apellido);
        setSalario(salario);
        setCentro(centro);
    }

    /**
     * Obtiene el NIF del trabajador.
     *
     * @return El NIF del trabajador.
     */
    public String getNif() {
        return super.getNif();
    }

    /**
     * Obtiene la contraseña del trabajador.
     *
     * @return La contraseña del trabajador.
     */
    public String getContrasenya() {
        return super.getContrasenya();
    }

    /**
     * Obtiene el nombre del trabajador.
     *
     * @return El nombre del trabajador.
     */
    public String getNombre() {
        return super.getNombre();
    }

    /**
     * Obtiene el apellido del trabajador.
     *
     * @return El apellido del trabajador.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Obtiene el salario del trabajador.
     *
     * @return El salario del trabajador.
     */
    public double getSalario() {
        return salario;
    }

    /**
     * Obtiene la edad del trabajador.
     *
     * @return La edad del trabajador.
     */
    public int getEdad() {
        return super.getEdad();
    }

    /**
     * Obtiene el centro al que pertenece el trabajador.
     *
     * @return El centro al que pertenece el trabajador.
     */
    public Centro getCentro() {
        return centro;
    }

    /**
     * Establece el apellido del trabajador.
     *
     * @param apellido El apellido del trabajador.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Establece el salario del trabajador.
     *
     * @param salario El salario del trabajador.
     * @throws ExceptionTrabajador Si el salario es menor o igual que 0.
     */
    public void setSalario(double salario) throws ExceptionTrabajador {
        if (salario > 0) this.salario = salario;
        else throw new ExceptionTrabajador("El salario no puede ser menor o igual que 0");
    }

    /**
     * Establece el centro al que pertenece el trabajador.
     *
     * @param centro El centro al que pertenece el trabajador.
     */
    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    /**
     * Verifica si el usuario es un trabajador.
     *
     * @return true si el usuario es un trabajador, false en caso contrario.
     */
    @Override
    public boolean isTrabajador() {
        return true;
    }

    /**
     * Devuelve una representación en cadena del trabajador.
     *
     * @return Una cadena que representa al trabajador.
     */
    @Override
    public String toString() {
        return "Trabajador{" +
                "apellido='" + apellido + '\'' +
                ", salario=" + salario +
                ", centro=" + centro +
                '}';
    }
}
