package Objetos;

/**
 * Clase Tipo, representa un tipo con un id y un nombre.
 */
public class Tipo {
    /**
     * Campos de la clase.
     */
    private int id;
    private String nombre;

    /**
     * Constructor con todos los parámetros.
     *
     * @param id El identificador único del tipo.
     * @param nombre El nombre del tipo.
     */
    public Tipo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Obtiene el id del tipo.
     *
     * @return El id del tipo.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre del tipo.
     *
     * @return El nombre del tipo.
     */
    public String getNombre() {
        return nombre;
    }
}
