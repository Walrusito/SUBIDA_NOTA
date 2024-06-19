package proyeto_comida_rapida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que gestiona las operaciones de acceso a datos para las patatas.
 */
public class PatatasDAO {
    private static Connection conexion = Conexion.obtenerConexion();

    /**
     * Constructor de la clase PatatasDAO.
     */
    public PatatasDAO() {
    }

    /**
     * Inserta un registro de patatas en la base de datos.
     *
     * @param patatas El objeto Patatas a insertar.
     */
    public static void insertarPatatas(Patatas patatas) {
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO Patatas (tipo, precio) VALUES (?, ?)";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, patatas.getTipo());
            stmt.setDouble(2, patatas.getPrecio());

        } catch (SQLException e) {
            System.out.println("Error al insertar patatas en la base de datos: " + e.getMessage());
        } finally {
            cerrarRecursos(stmt, null);
        }
    }

    /**
     * Verifica la disponibilidad de un tipo de patatas en la base de datos.
     *
     * @param tipo El tipo de patatas a verificar.
     * @return true si hay disponibilidad, false en caso contrario.
     */
    public static boolean verificarDisponibilidad(String tipo) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT stock FROM Patatas WHERE tipo = ?";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, tipo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int stock = rs.getInt("stock");
                return stock > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error al verificar disponibilidad de las patatas: " + e.getMessage());
        } finally {
            cerrarRecursos(stmt, rs);
        }

        return false;
    }

    /**
     * Ajusta el stock de un tipo de patatas en la base de datos.
     *
     * @param tipo     El tipo de patatas cuyo stock se va a ajustar.
     * @param cantidad La cantidad de ajuste (reducción).
     * @return true si el ajuste fue exitoso, false en caso contrario.
     */
    public static boolean ajustarStock(String tipo, int cantidad) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sqlSelect = "SELECT stock FROM Patatas WHERE tipo = ?";
            stmt = conexion.prepareStatement(sqlSelect);
            stmt.setString(1, tipo);
            rs = stmt.executeQuery();

            int stockActual = 0;
            if (rs.next()) {
                stockActual = rs.getInt("stock");
            }

            if (stockActual < cantidad) {
                System.out.println("No hay suficiente stock para ajustar.");
                return false;
            }

            int nuevoStock = stockActual - cantidad;

            String sqlUpdate = "UPDATE Patatas SET stock = ? WHERE tipo = ?";
            stmt = conexion.prepareStatement(sqlUpdate);
            stmt.setInt(1, nuevoStock);
            stmt.setString(2, tipo);

            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al ajustar el stock de las patatas: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(stmt, rs);
        }
    }

    /**
     * Cierra los recursos de base de datos utilizados.
     *
     * @param stmt El PreparedStatement a cerrar.
     * @param rs   El ResultSet a cerrar.
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
