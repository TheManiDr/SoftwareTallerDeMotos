package Conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLConexion {
    
    // Configuración de la conexión (AJUSTA ESTOS VALORES)
    private static final String URL = "jdbc:mysql://localhost:3306/motomami?serverTimezone=UTC"; 
    private static final String USER = "root"; // Tu usuario de MySQL
    private static final String PASSWORD = "Gatoara09."; // ¡Cambia esto por tu contraseña!
    
    private static final Logger logger = Logger.getLogger(MySQLConexion.class.getName());

    /**
     * Establece y retorna la conexión a la base de datos 'motomami'.
     * @return Objeto Connection o null si falla.
     */
    public static Connection getConexion() {
        Connection con = null;
        try {
            // 1. Cargar el driver (Opcional en versiones modernas de JDBC)
            // Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 2. Crear la conexión
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.log(Level.INFO, "Conectado a motomami");
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al conectar con la base de datos.", e);
            
            // Mostrar un mensaje de error más amigable en caso de fallo
            System.err.println("\n--- ERROR DE CONEXIÓN A BASE DE DATOS ---");
            System.err.println("URL: " + URL);
            System.err.println("Usuario: " + USER);
            System.err.println("Mensaje: " + e.getMessage());
            System.err.println("-----------------------------------------\n");
        }
        return con;
    }
    
    // Método para cerrar la conexión (opcional, pero buena práctica)
    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Error al cerrar la conexión", e);
            }
        }
    }

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void conectar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}