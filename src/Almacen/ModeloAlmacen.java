package Almacen;

import Conexiones.MySQLConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO para la gestión de la tabla 'modelo'.
 */
public class ModeloAlmacen {

    private static final Logger logger = Logger.getLogger(ModeloAlmacen.class.getName());

    /**
     * Busca el idModelo por nombre de modelo y idMarca. Si no existe, lo inserta 
     * y retorna el nuevo ID.
     * * @param nombreModelo Nombre del modelo (ej: "Ninja 400").
     * @param idMarca ID de la marca a la que pertenece (ej: 4 para Kawasaki).
     * @return El idModelo existente o el nuevo idModelo generado, o -1 si falla.
     */
    public int obtenerIdModeloPorNombre(String nombreModelo, int idMarca) {
        int idModelo = -1;

        // 1. INTENTAR BUSCAR EL MODELO EXISTENTE
        String sqlSelect = "SELECT idModelo FROM modelo WHERE nombreModelo = ? AND idMarca = ?";

        try (Connection conn = MySQLConexion.getConexion();
             PreparedStatement psSelect = conn.prepareStatement(sqlSelect)) {

            psSelect.setString(1, nombreModelo);
            psSelect.setInt(2, idMarca);

            try (ResultSet rs = psSelect.executeQuery()) {
                if (rs.next()) {
                    idModelo = rs.getInt("idModelo");
                    return idModelo; // Modelo encontrado, retornar ID
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al buscar modelo existente: {0}", e.getMessage());
            return -1;
        }

        // 2. SI NO EXISTE, INSERTAR EL NUEVO MODELO
        String sqlInsert = "INSERT INTO modelo (idMarca, nombreModelo) VALUES (?, ?)";

        try (Connection conn = MySQLConexion.getConexion();
             PreparedStatement psInsert = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {

            psInsert.setInt(1, idMarca);
            psInsert.setString(2, nombreModelo);
            
            int filasAfectadas = psInsert.executeUpdate();

            if (filasAfectadas > 0) {
                // Obtener el ID generado automáticamente
                try (ResultSet rsKeys = psInsert.getGeneratedKeys()) {
                    if (rsKeys.next()) {
                        idModelo = rsKeys.getInt(1);
                        return idModelo; // Nuevo modelo insertado, retornar ID
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al insertar nuevo modelo: {0}", e.getMessage());
            return -1;
        }
        
        return idModelo;
    }
}