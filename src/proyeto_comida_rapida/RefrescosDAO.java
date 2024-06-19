package proyeto_comida_rapida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase DAO (Data Access Object) para gestionar los refrescos en la base de datos.
 */
public class RefrescosDAO {
    private static Connection conexion = Conexion.obtenerConexion();

    /**
     * Constructor por defecto de la clase RefrescosDAO.
     * Inicializa la conexión a la base de datos utilizando la clase de conexión Conexion.
     */
    public RefrescosDAO() {
        this.conexion = Conexion.obtenerConexion();
    }

    /**
     * Método para insertar un nuevo refresco en la base de datos.
     * @param refresco Objeto Refrescos que contiene la información del refresco a insertar.
     */
    public static void insertarRefresco(Refrescos refresco) {
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO Refresco (tipo, precio) VALUES (?, ?)";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, refresco.getTipo());
            stmt.setDouble(2, refresco.getPrecio());


        } catch (SQLException e) {
            System.out.println("Error al insertar refresco en la base de datos: " + e.getMessage());
        } finally {
            cerrarRecursos(stmt, null);
        }
    }

    /**
     * Método para verificar la disponibilidad de un tipo específico de refresco en el stock.
     * @param tipo Tipo de refresco a verificar.
     * @return true si hay stock disponible, false si no.
     */
    public static boolean verificarDisponibilidad(String tipo) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT stock FROM Refrescos WHERE tipo = ?";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, tipo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int stock = rs.getInt("stock");
                return stock > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error al verificar disponibilidad del refresco: " + e.getMessage());
        } finally {
            cerrarRecursos(stmt, rs);
        }

        return false;
    }

    /**
     * Método para ajustar el stock de un tipo específico de refresco en la base de datos.
     * @param tipo Tipo de refresco a ajustar en el stock.
     * @param cantidad Cantidad en la que se ajusta el stock (positiva para reducir, negativa para aumentar).
     */
    public static void ajustarStock(String tipo, int cantidad) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sqlSelect = "SELECT stock FROM Refresco WHERE tipo = ?";
            stmt = conexion.prepareStatement(sqlSelect);
            stmt.setString(1, tipo);
            rs = stmt.executeQuery();

            int stockActual = 0;
            if (rs.next()) {
                stockActual = rs.getInt("stock");
            }

            if (stockActual < cantidad) {
                System.out.println("No hay suficiente stock para ajustar.");
                return;
            }

            int nuevoStock = stockActual - cantidad;

            String sqlUpdate = "UPDATE Refresco SET stock = ? WHERE tipo = ?";
            stmt = conexion.prepareStatement(sqlUpdate);
            stmt.setInt(1, nuevoStock);
            stmt.setString(2, tipo);

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Stock de refresco actualizado correctamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al ajustar el stock del refresco: " + e.getMessage());
        } finally {
            cerrarRecursos(stmt, rs);
        }
    }

    /**
     * Método privado para cerrar los recursos de PreparedStatement y ResultSet.
     * @param stmt PreparedStatement a cerrar.
     * @param rs ResultSet a cerrar.
     */
    private static void cerrarRecursos(PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
}
