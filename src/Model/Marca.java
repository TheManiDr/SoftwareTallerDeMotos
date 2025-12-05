package Model;

/**
 * Clase de modelo para la tabla 'marca'.
 */
public class Marca {
    private int idMarca;
    private String nombreMarca;

    // Constructor vacío
    public Marca() {
    }

    // Constructor completo
    public Marca(int idMarca, String nombreMarca) {
        this.idMarca = idMarca;
        this.nombreMarca = nombreMarca;
    }

    // Getters y Setters
    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }
    
    // Método toString útil para depuración o ComboBox si fuera necesario
    @Override
    public String toString() {
        return nombreMarca;
    }
}