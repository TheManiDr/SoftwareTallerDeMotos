package Conexiones; // cambia por tu paquete

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConexion {

    Connection con;

    public Connection conectar() {

        try {
            Properties p = new Properties();
            
            InputStream input = getClass().getClassLoader().getResourceAsStream("Utils/config.properties");
            
            if (input == null) {
                System.out.println("No se encontró el archivo config.properties");
                return null;
            }

            p.load(input);

            String host = p.getProperty("db_host");
            String port = p.getProperty("db_port");
            String name = p.getProperty("db_name");
            String user = p.getProperty("db_user");
            String pass = p.getProperty("db_pass");

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":" + port + "/" + name,
                user,
                pass
            );

            System.out.println("Conectado a " + name);
            return con; 

        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("ERROR en la conexión: " + e.getMessage());
            return null;
        }

        
    }
}

