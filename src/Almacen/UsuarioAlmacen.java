package Almacen;


import java.sql.*;
import model.Usuario;

public class UsuarioAlmacen {

    public Usuario login(String usuario, String contra) {

        String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";

        try (Connection conn = Conexiones.MySQLConexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, contra);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setRol(rs.getString("rol"));
                return u;
            }

        } catch (Exception e) {
        }

        return null;
    }
}
