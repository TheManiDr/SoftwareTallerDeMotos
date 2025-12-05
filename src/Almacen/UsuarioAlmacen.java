package Almacen;

import java.sql.*;
import model.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioAlmacen {
    
    private static final Logger logger = Logger.getLogger(UsuarioAlmacen.class.getName());

    public Usuario login(String usuario, String contra) {

        // ⭐ CORRECCIÓN CLAVE: Usamos TRIM() en las columnas password y rol para evitar problemas de espacios en blanco.
        String sql = "SELECT id, username, TRIM(rol) AS rol FROM usuarios WHERE username = ? AND TRIM(password) = ?";

        Usuario u = null;

        try (Connection conn = Conexiones.MySQLConexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, contra);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setRol(rs.getString("rol"));
                }
            }

        } catch (SQLException e) {
            // Muestra el error real si la consulta falla (ej. nombre de columna incorrecto)
            logger.log(Level.SEVERE, "Error en la verificación de LOGIN (SQL): {0}", e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error general en login: {0}", e.getMessage());
        }

        return u;
    }
}