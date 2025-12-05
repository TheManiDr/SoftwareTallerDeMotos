package Almacen;

import Conexiones.MySQLConexion; 
import Model.Motocicleta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MotocicletaAlmacen {

    private static final Logger logger = Logger.getLogger(MotocicletaAlmacen.class.getName()); 

    // -----------------------------------------------------------
    // MÉTODOS DE LECTURA (READ) ACTUALIZADOS
    // -----------------------------------------------------------
    
    /**
     * Recupera todas las motocicletas.
     * Realiza un JOIN con 'modelo' y 'marca' para obtener los nombres descriptivos.
     * @return 
     */
    public List<Motocicleta> obtenerTodasLasMotos() {
        List<Motocicleta> lista = new ArrayList<>();
        // Consulta SQL actualizada para unir motocicleta, modelo (O) y marca (R)
        String sql = "SELECT m.idMoto, m.no_matricula, r.nombreMarca, o.nombreModelo, " +
                     "m.año, m.color, m.numSerie, m.idCliente, m.idModelo " +
                     "FROM motocicleta m " +
                     "JOIN modelo o ON m.idModelo = o.idModelo " +
                     "JOIN marca r ON o.idMarca = r.idMarca"; 
        
        try (Connection conn = MySQLConexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Motocicleta m = new Motocicleta();
                m.setIdMoto(rs.getInt("idMoto"));
                m.setNoMatricula(rs.getString("no_matricula"));
                // Cargando los datos del JOIN
                m.setNombreModelo(rs.getString("nombreModelo"));
                m.setMarca(rs.getString("nombreMarca")); // <-- Viene de la tabla 'marca'
                // Datos de la tabla motocicleta
                m.setAño(rs.getInt("año"));
                m.setColor(rs.getString("color"));
                m.setNumSerie(rs.getString("numSerie"));
                m.setIdCliente(rs.getInt("idCliente"));
                m.setIdModelo(rs.getInt("idModelo"));
                lista.add(m);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener motocicletas: {0}", e.getMessage());
        }
        return lista;
    }

    /**
     * Recupera una motocicleta por su ID.
     * Realiza un JOIN con 'modelo' y 'marca' para obtener los nombres descriptivos.
     * @param id
     * @return 
     */
    public Motocicleta obtenerMotoPorId(int id) {
        Motocicleta m = null;
        String sql = "SELECT m.*, o.nombreModelo, r.nombreMarca FROM motocicleta m " +
                     "JOIN modelo o ON m.idModelo = o.idModelo " +
                     "JOIN marca r ON o.idMarca = r.idMarca " +
                     "WHERE m.idMoto = ?";
        
        try (Connection conn = MySQLConexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    m = new Motocicleta();
                    m.setIdMoto(rs.getInt("idMoto"));
                    m.setIdCliente(rs.getInt("idCliente"));
                    m.setIdModelo(rs.getInt("idModelo"));
                    m.setAño(rs.getInt("año"));
                    m.setColor(rs.getString("color"));
                    m.setNumSerie(rs.getString("numSerie"));
                    m.setNoMatricula(rs.getString("no_matricula"));
                    // Cargando los datos del JOIN
                    m.setNombreModelo(rs.getString("nombreModelo"));
                    m.setMarca(rs.getString("nombreMarca")); // <-- Viene de la tabla 'marca'
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener motocicleta por ID: {0}", e.getMessage());
        }
        return m;
    }

    // -----------------------------------------------------------
    // MÉTODOS DE ESCRITURA (CREATE, UPDATE, DELETE) - NO CAMBIAN
    // -----------------------------------------------------------
    
    /**
     * Inserta una nueva motocicleta en la base de datos.
     * @param moto
     * @return 
     */
    public boolean insertarMoto(Motocicleta moto) {
        String sql = "INSERT INTO motocicleta (idCliente, idModelo, año, color, numSerie, no_matricula) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = MySQLConexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, moto.getIdCliente());
            ps.setInt(2, moto.getIdModelo());
            ps.setInt(3, moto.getAño());
            ps.setString(4, moto.getColor());
            ps.setString(5, moto.getNumSerie());
            ps.setString(6, moto.getNoMatricula()); 
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al insertar motocicleta: {0}", e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza una motocicleta existente.
     * @param moto
     * @return 
     */
    public boolean actualizarMoto(Motocicleta moto) {
        // La actualización no necesita JOIN, solo el idMoto
        String sql = "UPDATE motocicleta SET idCliente=?, idModelo=?, año=?, color=?, no_matricula=? WHERE idMoto=?";
        
        try (Connection conn = MySQLConexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, moto.getIdCliente());
            ps.setInt(2, moto.getIdModelo());
            ps.setInt(3, moto.getAño());
            ps.setString(4, moto.getColor());
            ps.setString(5, moto.getNoMatricula()); 
            ps.setInt(6, moto.getIdMoto());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar motocicleta: {0}", e.getMessage());
            return false;
        }
    }

    /**
     * Elimina una motocicleta por su ID.
     * @param idMoto
     * @return 
     */
    public boolean eliminarMoto(int idMoto) {
        String sql = "DELETE FROM motocicleta WHERE idMoto = ?";
        
        try (Connection conn = MySQLConexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idMoto);
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al eliminar motocicleta: {0}. (Verifique restricciones de clave foránea)", e.getMessage());
            return false;
        }
    }
}