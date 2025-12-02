/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author TheManiDr
 */
public class ClasePrueba {
     public static void main(String[] args) {
        Conexiones.MySQLConexion cn = new Conexiones.MySQLConexion();
        cn.conectar();
    }
}
