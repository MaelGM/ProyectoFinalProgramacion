package Clases;

public class Cliente {
    private String nif;
    private String contrasenya;
    private String telefono;
    private String nombre;
    private int edad;

    public Cliente(String nif, String contrasenya, String telefono, String nombre, int edad) {
        this.nif = nif;
        this.contrasenya = contrasenya;
        this.telefono = telefono;
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return nif + ";" + contrasenya + ";" + telefono + ";" + nombre + ";" + edad;
    }
}
