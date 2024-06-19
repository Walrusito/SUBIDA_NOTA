package proyeto_comida_rapida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase de acceso a datos para los clientes de la aplicación de comida rápida.
 */
public class ClientesDAO {

    private Connection conexion;

    /**
     * Constructor de la clase ClientesDAO.
     * Inicializa la conexión con la base de datos.
     */
    public ClientesDAO() {
        this.conexion = Conexion.obtenerConexion();
    }

    /**
     * Busca un cliente en la base de datos por su email y contraseña.
     *
     * @param emailParam El email del cliente.
     * @param password   La contraseña del cliente.
     * @return El cliente encontrado o null si no se encuentra.
     */
    public Clientes buscarPorUsuarioYContraseña(String emailParam, String password) {
        Clientes cliente = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM Clientes WHERE email = ? AND password = ?";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, emailParam);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            if (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                cliente = new Clientes(nombre, email, password);
                cliente.setIdCliente(idCliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar cliente en la base de datos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return cliente;
    }

    /**
     * Inserta un nuevo cliente en la base de datos.
     *
     * @param cliente El cliente a insertar.
     */
    public void insertarCliente(Clientes cliente) {
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO Clientes (nombre, email, password) VALUES (?, ?, ?)";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getPassword());

            // Ejecutar la inserción
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al insertar cliente en la base de datos: " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el statement: " + e.getMessage());
                }
            }
        }
    }
}
