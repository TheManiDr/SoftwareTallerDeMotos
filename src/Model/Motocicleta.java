package Model;

/**
 * Clase de modelo para la tabla 'motocicleta'.
 */
public class Motocicleta {
    
    // Campos de la tabla 'motocicleta'
    private int idMoto;
    private int idCliente;
    private int idModelo;
    private int año;
    private String color;
    private String numSerie; // Número de Identificación Vehicular (VIN)
    private String noMatricula; // NUEVO CAMPO: Placa o Matrícula

    // Campos adicionales para mostrar en la tabla (si se requieren)
    private String marca;
    private String nombreModelo;

    // Constructor vacío
    public Motocicleta() {
    }

    // Constructor completo
    public Motocicleta(int idMoto, int idCliente, int idModelo, int año, String color, String numSerie, String noMatricula, String marca, String nombreModelo) {
        this.idMoto = idMoto;
        this.idCliente = idCliente;
        this.idModelo = idModelo;
        this.año = año;
        this.color = color;
        this.numSerie = numSerie;
        this.noMatricula = noMatricula;
        this.marca = marca;
        this.nombreModelo = nombreModelo;
    }

    // Getters y Setters
    
    public int getIdMoto() {
        return idMoto;
    }

    public void setIdMoto(int idMoto) {
        this.idMoto = idMoto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }
    
    // Getters y Setters para la nueva Matrícula
    public String getNoMatricula() {
        return noMatricula;
    }

    public void setNoMatricula(String noMatricula) {
        this.noMatricula = noMatricula;
    }

    // Getters y Setters para campos de apoyo (marca/modelo)
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }
}