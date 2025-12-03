package Almacen;

import Conexiones.MySQLConexion;
import Model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteAlmacen {

    // ---------------------------------------------------------
    // Cargar la vista de clientes
    // ---------------------------------------------------------
    public List<Cliente> obtenerClientes() {
        List<Cliente> lista = new ArrayList<>();

        String sql = "SELECT idPersona, nombre, apellido_p, apellido_m, ciudad, tipoCliente, limiteCredito "
                   + "FROM vista_clientes_completa";

        try (Connection conn = new MySQLConexion().conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Model.Cliente c = new Model.Cliente();

                c.setIdPersona(rs.getInt("idPersona"));
                c.setNombre(rs.getString("nombre"));
                c.setApellidoP(rs.getString("apellido_p"));
                c.setApellidoM(rs.getString("apellido_m"));
                c.setCiudad(rs.getString("ciudad"));
                c.setTipoCliente(rs.getString("tipoCliente"));
                c.setLimiteCredito((int) rs.getDouble("limiteCredito"));

                lista.add(c);
            }

        } catch (SQLException ex) {
            System.out.println("Error cargando clientes: " + ex.getMessage());
        }

        return lista;
    }

    // ---------------------------------------------------------
    // Insertar CLIENTE COMPLETO
    // ---------------------------------------------------------
    public boolean insertarCliente(Cliente c) {
        Connection conn = null;

        try {
            conn = new MySQLConexion().conectar();
            conn.setAutoCommit(false);

            // ----------------------------------------------
            // 1) Insertar dirección
            // ----------------------------------------------
            String sqlDireccion = "INSERT INTO direccion (calle, colonia, ciudad, estado) VALUES (?,?,?,?)";
            PreparedStatement ps1 = conn.prepareStatement(sqlDireccion, Statement.RETURN_GENERATED_KEYS);

            ps1.setString(1, c.getCalle());
            ps1.setString(2, c.getColonia());
            ps1.setString(3, (String) c.getCiudad());
            ps1.setString(4, c.getEstado());

            ps1.executeUpdate();
            ResultSet rs1 = ps1.getGeneratedKeys();
            rs1.next();
            int idDireccion = rs1.getInt(1);

            // ----------------------------------------------
            // 2) Insertar persona
            // ----------------------------------------------
            String sqlPersona = "INSERT INTO persona (nombre, apellido_p, apellido_m, idDireccion) VALUES (?,?,?,?)";
            PreparedStatement ps2 = conn.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS);

            ps2.setString(1, (String) c.getNombre());
            ps2.setString(2, (String) c.getApellidoP());
            ps2.setString(3, (String) c.getApellidoM());
            ps2.setInt(4, idDireccion);

            ps2.executeUpdate();
            ResultSet rs2 = ps2.getGeneratedKeys();
            rs2.next();
            int idPersona = rs2.getInt(1);

            // ----------------------------------------------
            // 3) Insertar correo
            // ----------------------------------------------
            String sqlCorreo = "INSERT INTO correo (idPersona, correo) VALUES (?,?)";
            PreparedStatement ps3 = conn.prepareStatement(sqlCorreo);

            ps3.setInt(1, idPersona);
            ps3.setString(2, c.getCorreo());
            ps3.executeUpdate();

            // ----------------------------------------------
            // 4) Insertar cliente
            // ----------------------------------------------
            String sqlCliente = "INSERT INTO cliente (idCliente, tipoCliente, limiteCredito, estatus) "
                    + "VALUES (?, ?, ?, 'Activo')";
            PreparedStatement ps4 = conn.prepareStatement(sqlCliente);
            ps4.setInt(1, idPersona);
            ps4.setString(2, (String) c.getTipoCliente());
            ps4.setDouble(3, (double) c.getLimiteCredito());
            ps4.executeUpdate();

            conn.commit();
            return true;

        } catch (SQLException ex) {
            System.out.println("ERROR insertando cliente: " + ex.getMessage());

            try { if (conn != null) conn.rollback(); } catch (SQLException e) {}
        }
        return false;
    }

    // ---------------------------------------------------------
    // Eliminar cliente (baja lógica)
    // ---------------------------------------------------------
    public boolean eliminarCliente(int idPersona) {
        String sql = "UPDATE cliente SET estatus = 'Inactivo' WHERE idCliente = ?";

        try (Connection conn = new MySQLConexion().conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPersona);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error eliminando cliente: " + ex.getMessage());
        }
        return false;
    }
}
