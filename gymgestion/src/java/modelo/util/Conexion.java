package modelo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** 
 * @author Kennit David Ruz Romero 
 * Fecha: 4 de Marzo de 2015
 */
public class Conexion {

    private static Connection conexion = null;

    private static void conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/evaluacion_logica_negocio", "root", "");
        } catch (SQLException e) {
            System.out.println("Error de MySQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    private Conexion() {

    }

    public static Connection getInstance() {
        if (conexion == null) {
            conectar();
        }
        return conexion;
    }
}
