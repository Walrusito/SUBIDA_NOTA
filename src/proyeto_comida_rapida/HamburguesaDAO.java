package proyeto_comida_rapida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) para realizar operaciones CRUD sobre la tabla Hamburguesa en la base de datos.
 */
public class HamburguesaDAO {
    private static Connection conexion = Conexion.obtenerConexion();

    /**
     * Constructor privado para evitar la creación de instancias de esta clase.
     */
    private HamburguesaDAO() {
    }

    /**
     * Inserta una hamburguesa en la base de datos.
     *
     * @param hamburguesa La hamburguesa que se desea insertar.
     */
    public static void insertarHamburguesa(Hamburguesa hamburguesa) {
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO Hamburguesa (tipo, precio) VALUES (?, ?)";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, hamburguesa.getTipo());
            stmt.setDouble(2, hamburguesa.getPrecio());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar hamburguesa en la base de datos: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    /**
     * Verifica la disponibilidad de una hamburguesa basada en su tipo.
     *
     * @param tipo El tipo de hamburguesa a verificar.
     * @return true si hay stock disponible, false en caso contrario.
     */
    public static boolean verificarDisponibilidad(String tipo) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT stock FROM Hamburguesa WHERE tipo = ?";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, tipo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int stock = rs.getInt("stock");
                return stock > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar disponibilidad de la hamburguesa: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return false;
    }

    /**
     * Ajusta el stock de hamburguesas basado en el tipo y la cantidad especificados.
     *
     * @param tipo     El tipo de hamburguesa cuyo stock se desea ajustar.
     * @param cantidad La cantidad a ajustar del stock.
     */
    public static void ajustarStock(String tipo, int cantidad) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sqlSelect = "SELECT stock FROM Hamburguesa WHERE tipo = ?";
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

            String sqlUpdate = "UPDATE Hamburguesa SET stock = ? WHERE tipo = ?";
            stmt = conexion.prepareStatement(sqlUpdate);
            stmt.setInt(1, nuevoStock);
            stmt.setString(2, tipo);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al ajustar el stock de la hamburguesa: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
}
