package Model;

public class Cliente {

    // IDs de las tablas relacionadas (necesarios para UPDATE/DELETE)
    private int idPersona; 
    private int idDireccion; 

    // Campos de la tabla PERSONA
    private String nombre;
    private String apellidoP;
    private String apellidoM;

    // Campo de la tabla CORREO
    private String correo;
    
    // Campo de la tabla PERSONA/TELEFONO
    private String telefono; // <-- ¡NUEVO CAMPO!

    // Campos de la tabla DIRECCION
    private String calle;    
    private String ciudad;
    private String estado;   
    private String codigoPostal; 

    // Campos de la tabla CLIENTE
    private String tipoCliente;
    private double limiteCredito;
    private String estatus; 

    // Constructor vacío
    public Cliente() {
    }

    // Constructor completo (MODIFICADO para incluir telefono)
    public Cliente(int idPersona, int idDireccion, String nombre, String apellidoP, String apellidoM, String correo,
                   String telefono, // <-- AGREGADO
                   String calle, String ciudad, String estado, String codigoPostal, 
                   String tipoCliente, double limiteCredito, String estatus) {
        this.idPersona = idPersona;
        this.idDireccion = idDireccion;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.correo = correo;
        this.telefono = telefono; // <-- INICIALIZADO
        this.calle = calle;
        this.ciudad = ciudad;
        this.estado = estado;
        this.codigoPostal = codigoPostal;
        this.tipoCliente = tipoCliente;
        this.limiteCredito = limiteCredito;
        this.estatus = estatus;
    }

    // =============== GETTERS ===============

    public int getIdPersona() { return idPersona; }
    public int getIdDireccion() { return idDireccion; }
    public String getNombre() { return nombre; }
    public String getApellidoP() { return apellidoP; }
    public String getApellidoM() { return apellidoM; }
    public String getCorreo() { return correo; }
    public String getTelefono() { return telefono; } // <-- NUEVO GETTER
    public String getCalle() { return calle; } 
    public String getCiudad() { return ciudad; }
    public String getEstado() { return estado; } 
    public String getCodigoPostal() { return codigoPostal; }
    public String getTipoCliente() { return tipoCliente; }
    public double getLimiteCredito() { return limiteCredito; }
    public String getEstatus() { return estatus; }

    // =============== SETTERS ===============

    public void setIdPersona(int idPersona) { this.idPersona = idPersona; }
    public void setIdDireccion(int idDireccion) { this.idDireccion = idDireccion; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellidoP(String apellidoP) { this.apellidoP = apellidoP; }
    public void setApellidoM(String apellidoM) { this.apellidoM = apellidoM; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setTelefono(String telefono) { this.telefono = telefono; } // <-- NUEVO SETTER
    public void setCalle(String calle) { this.calle = calle; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public void setEstado(String estado) { this.estado = estado; } 
    public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }
    public void setTipoCliente(String tipoCliente) { this.tipoCliente = tipoCliente; }
    public void setLimiteCredito(double limiteCredito) { this.limiteCredito = limiteCredito; }
    public void setEstatus(String estatus) { this.estatus = estatus; }
}