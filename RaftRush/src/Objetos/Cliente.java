package Objetos;

import Excepciones.ExceptionCliente;
import Excepciones.ExceptionUsuario;

public class Cliente extends Usuario{
    private String telefono;

    public Cliente(String nif, String contrasenya, String telefono, String nombre, int edad) throws ExceptionCliente, ExceptionUsuario {
        super(nif, nombre, edad, contrasenya);
        validaTelefono(telefono);
    }

    public String getContrasenya() {
        return super.getContrasenya();
    }
    public String getNombre() {
        return super.getNombre();
    }
    public int getEdad() {
        return super.getEdad();
    }

    public String getTelefono() {
        return telefono;
    }
    private void validaTelefono(String telefono) throws ExceptionCliente {
        if (telefono.length() <= 9) {
            setTelefono(telefono);
        }else{
            throw new ExceptionCliente("El telefono esta mal formateado");
        }
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean isTrabajador() {
        return false;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "telefono='" + telefono + '\'' +
                '}';
    }
}
