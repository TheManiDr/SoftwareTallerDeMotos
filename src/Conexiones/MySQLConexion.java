package Conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MySQLConexion {
    
    // Configuración (AJUSTA ESTOS VALORES)
    private static final String URL = "jdbc:mysql://localhost:3306/motomami?serverTimezone=UTC"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "Gatoara09."; // Tu contraseña
    
    private static final Logger logger = Logger.getLogger(MySQLConexion.class.getName());
    

    /**
     * Establece la conexión a la base de datos y la retorna.
     * @return El objeto Connection o null si falla.
     */
    public static Connection getConnection() {
        try {
        // Siempre intentamos crear una conexión nueva
        Connection nuevaConexion = DriverManager.getConnection(URL, USER, PASSWORD);
        logger.log(Level.INFO, "Conexión creada exitosamente.");
        return nuevaConexion;
        
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error al crear la conexión con la base de datos.", e);
        JOptionPane.showMessageDialog(null, 
                "ERROR DE CONEXIÓN.\nDetalle: " + e.getMessage(), 
                "Error de Conexión", JOptionPane.ERROR_MESSAGE);
        return null;
    }
    }
    
    // Método para cerrar la conexión (opcional, pero buena práctica)
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Error al cerrar la conexión", e);
            }
        }
    }

    /**
     * CORREGIDO: Retorna la conexión. Usado por MarcaAlmacen, etc.
     * @return 
     */
    public static Connection getConexion() {
        // Llama al método estático que ya implementaste y funciona.
        return getConnection(); 
    }

    /**
     * CORREGIDO: Retorna la conexión. Usado en alguna parte de tu código.
     * @return 
     */
    public Connection conectar() {
        // Llama al método estático. Nota: Este método no es estático, pero llama al estático.
        return getConnection(); 
    }
}