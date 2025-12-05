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
    private static final String PASSWORD = "Gatoara09."; // <--- ¡CAMBIA ESTA CONTRASEÑA!
    
    private static final Logger logger = Logger.getLogger(MySQLConexion.class.getName());
    
    // La conexión global (puede ser estática o manejada por una instancia)
    private static Connection con = null;

    /**
     * Establece la conexión a la base de datos y la retorna.
     * Si la conexión ya existe y está abierta, la reutiliza.
     * @return El objeto Connection.
     */
    public static Connection conectar() {
        if (con == null) {
            try {
                // Si la línea 61 de tu código es aquí, debe estar implementada.
                // Class.forName("com.mysql.cj.jdbc.Driver"); // Puede ser opcional
                con = DriverManager.getConnection(URL, USER, PASSWORD);
                logger.log(Level.INFO, "Conectado a motomami exitosamente.");
                
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error al conectar con la base de datos.", e);
                JOptionPane.showMessageDialog(null, 
                        "ERROR DE CONEXIÓN.\nVerifica el servidor o las credenciales.\nDetalle: " + e.getMessage(), 
                        "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                con = null;
            }
        }
        return con;
    }
    
    // Si tu código usa getConexion() también, asegúrate de que use conectar().
    public static Connection getConexion() {
        return conectar(); 
    }

    // Método para cerrar la conexión (buena práctica)
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Error al cerrar la conexión", e);
            }
        }
    }

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}