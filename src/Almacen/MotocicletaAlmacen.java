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
    // MÉTODOS DE LECTURA (READ) 
    // -----------------------------------------------------------
    
    /**
     * Recupera todas las motocicletas.
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
                m.setNombreModelo(rs.getString("nombreModelo"));
                m.setMarca(rs.getString("nombreMarca"));
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
                    m.setMarca(rs.getString("nombreMarca"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener motocicleta por ID: {0}", e.getMessage());
        }
        return m;
    }

    /**
     * Recupera motocicletas filtradas por una cadena de texto (matrícula, serie, marca o modelo).
     * @param filtro Cadena de texto a buscar.
     * @return Lista de motocicletas que coinciden con el filtro.
     */
    public List<Motocicleta> obtenerMotosPorFiltro(String filtro) {
        List<Motocicleta> lista = new ArrayList<>();
        
        // Sentencia SQL con el filtro, buscando en varios campos clave
        String sql = "SELECT m.idMoto, m.no_matricula, r.nombreMarca, o.nombreModelo, " +
                     "m.año, m.color, m.numSerie, m.idCliente, m.idModelo " +
                     "FROM motocicleta m " +
                     "JOIN modelo o ON m.idModelo = o.idModelo " +
                     "JOIN marca r ON o.idMarca = r.idMarca " +
                     "WHERE UPPER(m.no_matricula) LIKE ? OR " +
                     "      UPPER(m.numSerie) LIKE ? OR " +
                     "      UPPER(r.nombreMarca) LIKE ? OR " +
                     "      UPPER(o.nombreModelo) LIKE ?"; 

        String filtroLike = "%" + filtro.toUpperCase() + "%";
        
        try (Connection conn = MySQLConexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Seteamos los 4 parámetros del WHERE
            ps.setString(1, filtroLike);
            ps.setString(2, filtroLike);
            ps.setString(3, filtroLike);
            ps.setString(4, filtroLike);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Motocicleta m = new Motocicleta();
                    m.setIdMoto(rs.getInt("idMoto"));
                    m.setNoMatricula(rs.getString("no_matricula"));
                    m.setNombreModelo(rs.getString("nombreModelo"));
                    m.setMarca(rs.getString("nombreMarca"));
                    m.setAño(rs.getInt("año"));
                    m.setColor(rs.getString("color"));
                    m.setNumSerie(rs.getString("numSerie"));
                    m.setIdCliente(rs.getInt("idCliente"));
                    m.setIdModelo(rs.getInt("idModelo"));
                    lista.add(m);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener motocicletas por filtro: {0}", e.getMessage());
        }
        return lista;
    }
    
    // -----------------------------------------------------------
    // MÉTODOS DE ESCRITURA (CREATE, UPDATE, DELETE) - CORRECCIONES
    // -----------------------------------------------------------
    
    /**
     * Inserta una nueva motocicleta en la base de datos.
     * @param moto
     * @return 
     */
    public boolean insertarMoto(Motocicleta moto) {
        // Se asume que numSerie está incluido en el modelo Motocicleta
        String sql = "INSERT INTO motocicleta (idCliente, idModelo, año, color, numSerie, no_matricula) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = MySQLConexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, moto.getIdCliente());
            ps.setInt(2, moto.getIdModelo());
            ps.setInt(3, moto.getAño());
            ps.setString(4, moto.getColor());
            ps.setString(5, moto.getNumSerie()); // numSerie
            ps.setString(6, moto.getNoMatricula()); // no_matricula
            
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
        // CORREGIDO: Se incluyó el campo numSerie en la sentencia UPDATE
        String sql = "UPDATE motocicleta SET idCliente=?, idModelo=?, año=?, color=?, numSerie=?, no_matricula=? WHERE idMoto=?";
        
        try (Connection conn = MySQLConexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, moto.getIdCliente());
            ps.setInt(2, moto.getIdModelo());
            ps.setInt(3, moto.getAño());
            ps.setString(4, moto.getColor());
            ps.setString(5, moto.getNumSerie()); // CORREGIDO: numSerie
            ps.setString(6, moto.getNoMatricula()); // no_matricula
            ps.setInt(7, moto.getIdMoto()); // idMoto
            
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