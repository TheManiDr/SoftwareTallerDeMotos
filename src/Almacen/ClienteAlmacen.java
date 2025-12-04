package Almacen;

import Conexiones.MySQLConexion;
import Model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteAlmacen {

    // ---------------------------------------------------------
    // R (READ) - Cargar la lista de clientes
    // ---------------------------------------------------------
    public List<Cliente> obtenerClientes() {
        List<Cliente> lista = new ArrayList<>();

        // CORREGIDO: Seleccionamos t.telefono y agregamos LEFT JOIN con la tabla telefono
        String sql = "SELECT p.idPersona, p.apellido_p, p.apellido_m, p.nombre, t.telefono, " 
                + "d.idDireccion, d.calle, d.ciudad, d.estado, d.codigoPostal, " 
                + "co.correo, "
                + "c.tipoCliente, c.limiteCredito, c.estatus "
                + "FROM persona p "
                + "JOIN cliente c ON p.idPersona = c.idCliente "
                + "JOIN direccion d ON p.idDireccion = d.idDireccion "
                + "LEFT JOIN correo co ON p.idPersona = co.idPersona "
                + "LEFT JOIN telefono t ON p.idPersona = t.idPersona"; // <-- JOIN CORRECTO

        try (Connection conn = new MySQLConexion().conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente();

                // Datos Persona
                c.setIdPersona(rs.getInt("idPersona"));
                c.setNombre(rs.getString("nombre"));
                c.setApellidoP(rs.getString("apellido_p"));
                c.setApellidoM(rs.getString("apellido_m"));
                c.setTelefono(rs.getString("telefono")); // <-- LECTURA CORRECTA

                // Datos Dirección 
                c.setIdDireccion(rs.getInt("idDireccion"));
                c.setCalle(rs.getString("calle"));
                c.setCiudad(rs.getString("ciudad"));
                c.setEstado(rs.getString("estado"));
                c.setCodigoPostal(rs.getString("codigoPostal"));

                // Datos Correo
                c.setCorreo(rs.getString("correo")); 
                
                // Datos Cliente
                c.setTipoCliente(rs.getString("tipoCliente"));
                c.setLimiteCredito(rs.getDouble("limiteCredito")); 
                c.setEstatus(rs.getString("estatus"));

                lista.add(c);
            }

        } catch (SQLException ex) {
            System.err.println("Error cargando clientes: " + ex.getMessage());
        }

        return lista;
    }
    
// ---------------------------------------------------------
// R (READ) - Obtener cliente por ID
// ---------------------------------------------------------
    public Cliente obtenerClientePorId(int id) {
        Cliente c = null;
        
        // CORREGIDO: Seleccionamos t.telefono y agregamos LEFT JOIN con la tabla telefono
        String sql = "SELECT p.idPersona, p.apellido_p, p.apellido_m, p.nombre, t.telefono, " 
                + "d.idDireccion, d.calle, d.ciudad, d.estado, d.codigoPostal, " 
                + "co.correo, "
                + "c.tipoCliente, c.limiteCredito, c.estatus "
                + "FROM persona p "
                + "JOIN cliente c ON p.idPersona = c.idCliente "
                + "JOIN direccion d ON p.idDireccion = d.idDireccion "
                + "LEFT JOIN correo co ON p.idPersona = co.idPersona "
                + "LEFT JOIN telefono t ON p.idPersona = t.idPersona " // <-- JOIN CORRECTO
                + "WHERE p.idPersona = ?";

        try (Connection conn = new MySQLConexion().conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c = new Cliente();
                    c.setIdPersona(rs.getInt("idPersona"));
                    c.setIdDireccion(rs.getInt("idDireccion"));
                    c.setNombre(rs.getString("nombre"));
                    c.setApellidoP(rs.getString("apellido_p"));
                    c.setApellidoM(rs.getString("apellido_m"));
                    c.setTelefono(rs.getString("telefono")); // <-- LECTURA CORRECTA
                    c.setCorreo(rs.getString("correo"));
                    c.setCalle(rs.getString("calle"));
                    c.setCiudad(rs.getString("ciudad"));
                    c.setEstado(rs.getString("estado"));
                    c.setCodigoPostal(rs.getString("codigoPostal"));
                    c.setTipoCliente(rs.getString("tipoCliente"));
                    c.setLimiteCredito(rs.getDouble("limiteCredito"));
                    c.setEstatus(rs.getString("estatus"));
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error buscando cliente por ID: " + ex.getMessage());
        }
        return c;
    }

// ---------------------------------------------------------
// C (CREATE) - Insertar CLIENTE COMPLETO (Transaccional)
// ---------------------------------------------------------
    public boolean insertarCliente(Cliente c) {
        Connection conn = null;

        try {
            conn = new MySQLConexion().conectar();
            conn.setAutoCommit(false); // Iniciar Transacción

            int idDireccion = 0;
            int idPersona = 0;
            
            // 1) Insertar dirección
            String sqlDireccion = "INSERT INTO direccion (calle, ciudad, estado, codigoPostal) VALUES (?,?,?,?)";
            try (PreparedStatement ps1 = conn.prepareStatement(sqlDireccion, Statement.RETURN_GENERATED_KEYS)) {
                ps1.setString(1, c.getCalle());
                ps1.setString(2, c.getCiudad());
                ps1.setString(3, c.getEstado());
                ps1.setString(4, c.getCodigoPostal());

                ps1.executeUpdate();
                try (ResultSet rs1 = ps1.getGeneratedKeys()) {
                    if (rs1.next()) {
                        idDireccion = rs1.getInt(1);
                        c.setIdDireccion(idDireccion);
                    }
                }
            }

            // 2) Insertar persona 
            // CORREGIDO: Quitamos 'telefono' de la tabla persona
            String sqlPersona = "INSERT INTO persona (nombre, apellido_p, apellido_m, idDireccion) VALUES (?,?,?,?)";
            try (PreparedStatement ps2 = conn.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS)) {
                ps2.setString(1, c.getNombre());
                ps2.setString(2, c.getApellidoP());
                ps2.setString(3, c.getApellidoM());
                ps2.setInt(4, idDireccion); 
                // ps2.setString(5, c.getTelefono()); // <-- Eliminado
                
                ps2.executeUpdate();
                try (ResultSet rs2 = ps2.getGeneratedKeys()) {
                    if (rs2.next()) {
                        idPersona = rs2.getInt(1);
                        c.setIdPersona(idPersona); 
                    }
                }
            }

            // 3) Insertar teléfono (¡NUEVO PASO!)
            if (c.getTelefono() != null && !c.getTelefono().trim().isEmpty()) {
                String sqlTelefono = "INSERT INTO telefono (idPersona, telefono) VALUES (?,?)";
                try (PreparedStatement ps3a = conn.prepareStatement(sqlTelefono)) {
                    ps3a.setInt(1, idPersona);
                    ps3a.setString(2, c.getTelefono());
                    ps3a.executeUpdate();
                }
            }

            // 4) Insertar correo
            String sqlCorreo = "INSERT INTO correo (idPersona, correo) VALUES (?,?)";
            try (PreparedStatement ps3 = conn.prepareStatement(sqlCorreo)) {
                ps3.setInt(1, idPersona);
                ps3.setString(2, c.getCorreo());
                ps3.executeUpdate();
            }

            // 5) Insertar cliente
            String sqlCliente = "INSERT INTO cliente (idCliente, tipoCliente, limiteCredito, estatus) "
                    + "VALUES (?, ?, ?, 'Activo')";
            try (PreparedStatement ps4 = conn.prepareStatement(sqlCliente)) {
                ps4.setInt(1, idPersona);
                ps4.setString(2, c.getTipoCliente());
                ps4.setDouble(3, c.getLimiteCredito());
                ps4.executeUpdate();
            }

            conn.commit(); // Confirmar Transacción
            return true;

        } catch (SQLException ex) {
            System.err.println("ERROR insertando cliente: " + ex.getMessage());

            try { if (conn != null) conn.rollback(); } catch (SQLException e) { 
                 System.err.println("Error en rollback: " + e.getMessage());
            }
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
        return false;
    }
    
// ---------------------------------------------------------
// U (UPDATE) - Actualizar CLIENTE COMPLETO (Transaccional)
// ---------------------------------------------------------
    public boolean actualizarCliente(Cliente c) {
        Connection conn = null;

        try {
            conn = new MySQLConexion().conectar();
            conn.setAutoCommit(false); 

            // 1) Actualizar dirección
            String sqlDireccion = "UPDATE direccion SET calle=?, ciudad=?, estado=?, codigoPostal=? WHERE idDireccion=?";
            try (PreparedStatement ps1 = conn.prepareStatement(sqlDireccion)) {
                ps1.setString(1, c.getCalle());
                ps1.setString(2, c.getCiudad());
                ps1.setString(3, c.getEstado());
                ps1.setString(4, c.getCodigoPostal());
                ps1.setInt(5, c.getIdDireccion());
                ps1.executeUpdate();
            }

            // 2) Actualizar persona 
            // CORREGIDO: Quitamos 'telefono' de la tabla persona
            String sqlPersona = "UPDATE persona SET nombre=?, apellido_p=?, apellido_m=? WHERE idPersona=?";
            try (PreparedStatement ps2 = conn.prepareStatement(sqlPersona)) {
                ps2.setString(1, c.getNombre());
                ps2.setString(2, c.getApellidoP());
                ps2.setString(3, c.getApellidoM());
                // ps2.setString(4, c.getTelefono()); // <-- Eliminado
                ps2.setInt(4, c.getIdPersona()); 
                ps2.executeUpdate();
            }

            // 3) Actualizar o Insertar teléfono (¡NUEVO PASO!)
            String sqlTelefonoUpdate = "UPDATE telefono SET telefono=? WHERE idPersona=?";
            try (PreparedStatement ps3a = conn.prepareStatement(sqlTelefonoUpdate)) {
                ps3a.setString(1, c.getTelefono());
                ps3a.setInt(2, c.getIdPersona());
                int rowsAffected = ps3a.executeUpdate();
                
                // Si el UPDATE no afectó ninguna fila, significa que no existía registro, entonces lo insertamos.
                if (rowsAffected == 0 && c.getTelefono() != null && !c.getTelefono().trim().isEmpty()) {
                    String sqlInsertTelefono = "INSERT INTO telefono (idPersona, telefono) VALUES (?,?)";
                    try (PreparedStatement psInsert = conn.prepareStatement(sqlInsertTelefono)) {
                        psInsert.setInt(1, c.getIdPersona());
                        psInsert.setString(2, c.getTelefono());
                        psInsert.executeUpdate();
                    }
                }
            }


            // 4) Actualizar correo (Se asume que la lógica debe manejar un UPDATE)
            String sqlCorreo = "UPDATE correo SET correo=? WHERE idPersona=?";
            try (PreparedStatement ps3 = conn.prepareStatement(sqlCorreo)) {
                ps3.setString(1, c.getCorreo());
                ps3.setInt(2, c.getIdPersona());
                ps3.executeUpdate();
            }


            // 5) Actualizar cliente
            String sqlCliente = "UPDATE cliente SET tipoCliente=?, limiteCredito=? WHERE idCliente=?";
            try (PreparedStatement ps4 = conn.prepareStatement(sqlCliente)) {
                ps4.setString(1, c.getTipoCliente());
                ps4.setDouble(2, c.getLimiteCredito());
                ps4.setInt(3, c.getIdPersona());
                ps4.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException ex) {
            System.err.println("ERROR actualizando cliente: " + ex.getMessage());
            try { if (conn != null) conn.rollback(); } catch (SQLException e) {
                System.err.println("Error en rollback: " + e.getMessage());
            }
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
        return false;
    }
    
// ---------------------------------------------------------
// D (DELETE) - Eliminar cliente (baja lógica)
// ---------------------------------------------------------
    public boolean eliminarCliente(int idPersona) {
        String sql = "UPDATE cliente SET estatus = 'Inactivo' WHERE idCliente = ?";
        
        try (Connection conn = new MySQLConexion().conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPersona);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException ex) {
            System.err.println("Error eliminando cliente: " + ex.getMessage());
            return false;
        }
    }
}