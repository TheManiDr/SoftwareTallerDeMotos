package Almacen;

import Conexiones.MySQLConexion;
import Model.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO para la gestión de la tabla 'marca'.
 */
public class MarcaAlmacen {
    
    private static final Logger logger = Logger.getLogger(MarcaAlmacen.class.getName());

    /**
     * Recupera todas las marcas de la base de datos.
     */
    public List<Marca> obtenerTodasLasMarcas() {
        List<Marca> lista = new ArrayList<>();
        String sql = "SELECT idMarca, nombreMarca FROM marca ORDER BY nombreMarca"; 
        
        try (Connection conn = MySQLConexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Marca m = new Marca();
                m.setIdMarca(rs.getInt("idMarca"));
                m.setNombreMarca(rs.getString("nombreMarca"));
                lista.add(m);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener marcas: {0}", e.getMessage());
        }
        return lista;
    }
    
    // Otros métodos CRUD para Marca (insertar, actualizar, eliminar) pueden ser añadidos aquí si son necesarios
}