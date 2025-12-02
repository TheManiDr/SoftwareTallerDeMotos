package Conexiones; // cambia por tu paquete

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConexion {

    Connection con;

    public Connection conectar() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/motomami",
                    "root",
                    "" // tu contraseña si tienes
            );

            System.out.println("Conexión exitosa");

        } catch (Exception e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }

        return con;
    }
}

