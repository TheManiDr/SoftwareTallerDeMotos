package Model;

/**
 * Clase de modelo para la tabla 'modelo'.
 */
public class Modelo {
    private int idModelo;
    private int idMarca;
    private String nombreModelo;

    // Constructor vacío
    public Modelo() {
    }

    // Constructor completo
    public Modelo(int idModelo, int idMarca, String nombreModelo) {
        this.idModelo = idModelo;
        this.idMarca = idMarca;
        this.nombreModelo = nombreModelo;
    }

    // Getters y Setters
    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }
}