package proyeto_comida_rapida;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para gestionar la conexión con la base de datos.
 */
public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/COMIDA_RAPIDA";
    private static final String USER = "root";
    private static final String PASSWORD = "elnegosiososio";

    private static Connection conexion = null;

    /**
     * Obtiene la conexión con la base de datos.
     * 
     * @return La conexión con la base de datos.
     */
    public static Connection obtenerConexion() {
        if (conexion == null) {
            try {
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            }
        }
        return conexion;
    }

    /**
     * Cierra la conexión con la base de datos si está abierta.
     */
    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
