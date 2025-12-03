package Model;

public class Cliente {

    private int idPersona;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String correo;
    private String calle;
    private String colonia;
    private String ciudad;
    private String estado;
    private String tipoCliente;
    private double limiteCredito;

    // Constructor vacío
    public Cliente() {
    }

    // Constructor con todos los campos (opcional)
    public Cliente(int idPersona, String nombre, String apellidoP, String apellidoM, String correo,
                   String calle, String colonia, String ciudad, String estado,
                   String tipoCliente, double limiteCredito) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.correo = correo;
        this.calle = calle;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.estado = estado;
        this.tipoCliente = tipoCliente;
        this.limiteCredito = limiteCredito;
    }

    // =============== GETTERS ===============

    public int getIdPersona() {
        return idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCalle() {
        return calle;
    }

    public String getColonia() {
        return colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    // =============== SETTERS ===============

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }
}
