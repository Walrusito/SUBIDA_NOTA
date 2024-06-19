package proyeto_comida_rapida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidosDAO {
	private static Connection conexion = Conexion.obtenerConexion();

	public PedidosDAO() {
	}

	/**
     * Inserta un nuevo pedido en la base de datos.
     *
     * @param pedido El pedido a insertar.
     */
	public static void insertarPedido(Pedidos pedido) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sql = "INSERT INTO Pedidos (total) VALUES (?)";
			stmt = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setDouble(1, pedido.getTotal());

			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int idPedidoGenerado = rs.getInt(1);
				asignarProductosAPedido(idPedidoGenerado, pedido);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
     * Asigna productos a un pedido en la base de datos.
     *
     * @param idPedido El ID del pedido.
     * @param pedido   El pedido con los productos a asignar.
     */

	private static void asignarProductosAPedido(int idPedido, Pedidos pedido) {
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE Pedidos SET cantidadHamburguesa=?, cantidadPatatasFritas=?, cantidadRefresco=? "
					+ "WHERE idPedido=?";
			stmt = conexion.prepareStatement(sql);

			stmt.setInt(1, pedido.getCantidadHamburguesa());
			stmt.setInt(2, pedido.getCantidadPatatasFritas());
			stmt.setInt(3, pedido.getCantidadRefresco());
			stmt.setInt(4, idPedido);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

}
