package proyeto_comida_rapida;

import java.util.ArrayList;

/**
 * Clase que representa un pedido realizado por un cliente.
 */
public class Pedidos {
    private int idCliente;
    private double total;
    private int cantidadPatatasFritas;
    private int cantidadHamburguesa;
    private int cantidadRefresco;
    private ArrayList<Producto> productos;

    /**
     * Constructor de la clase Pedidos.
     *
     * @param idCliente            El ID del cliente que realiza el pedido.
     * @param total                El total del pedido.
     * @param productos            La lista de productos del pedido.
     * @param cantidadHamburguesa  La cantidad de hamburguesas en el pedido.
     * @param cantidadPatatasFritas La cantidad de patatas fritas en el pedido.
     * @param cantidadRefresco     La cantidad de refrescos en el pedido.
     */
    public Pedidos(int idCliente, double total, ArrayList<Producto> productos, int cantidadHamburguesa,
                   int cantidadPatatasFritas, int cantidadRefresco) {
        this.idCliente = idCliente;
        this.total = total;
        this.productos = productos;
        this.cantidadHamburguesa = cantidadHamburguesa;
        this.cantidadPatatasFritas = cantidadPatatasFritas;
        this.cantidadRefresco = cantidadRefresco;
    }

    /**
     * Obtiene la cantidad de patatas fritas en el pedido.
     *
     * @return La cantidad de patatas fritas.
     */
    public int getCantidadPatatasFritas() {
        return cantidadPatatasFritas;
    }

    /**
     * Establece la cantidad de patatas fritas en el pedido.
     *
     * @param cantidadPatatasFritas La cantidad de patatas fritas.
     */
    public void setCantidadPatatasFritas(int cantidadPatatasFritas) {
        this.cantidadPatatasFritas = cantidadPatatasFritas;
    }

    /**
     * Obtiene la cantidad de hamburguesas en el pedido.
     *
     * @return La cantidad de hamburguesas.
     */
    public int getCantidadHamburguesa() {
        return cantidadHamburguesa;
    }

    /**
     * Establece la cantidad de hamburguesas en el pedido.
     *
     * @param cantidadHamburguesa La cantidad de hamburguesas.
     */
    public void setCantidadHamburguesa(int cantidadHamburguesa) {
        this.cantidadHamburguesa = cantidadHamburguesa;
    }

    /**
     * Obtiene la cantidad de refrescos en el pedido.
     *
     * @return La cantidad de refrescos.
     */
    public int getCantidadRefresco() {
        return cantidadRefresco;
    }

    /**
     * Establece la cantidad de refrescos en el pedido.
     *
     * @param cantidadRefresco La cantidad de refrescos.
     */
    public void setCantidadRefresco(int cantidadRefresco) {
        this.cantidadRefresco = cantidadRefresco;
    }

    /**
     * Obtiene el total del pedido.
     *
     * @return El total del pedido.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Obtiene la lista de productos en el pedido.
     *
     * @return La lista de productos.
     */
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    /**
     * Devuelve una representación en forma de cadena del pedido.
     *
     * @return Una cadena que describe el pedido.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido:\n");
        sb.append("ID del Cliente: ").append(idCliente).append("\n");
        sb.append("Productos:\n");

        int countHamburguesa = 0, countPatatas = 0, countRefresco = 0;

        for (Producto producto : productos) {
            sb.append("- ").append(producto.getTipo()).append(": $").append(producto.getPrecio()).append("\n");

            if (producto instanceof Hamburguesa) {
                countHamburguesa++;
            } else if (producto instanceof Patatas) {
                countPatatas++;
            } else if (producto instanceof Refrescos) {
                countRefresco++;
            }
        }

        sb.append("\nCantidad de Hamburguesas: ").append(countHamburguesa).append("\n");
        sb.append("Cantidad de Patatas Fritas: ").append(countPatatas).append("\n");
        sb.append("Cantidad de Refrescos: ").append(countRefresco).append("\n");

        String formattedTotal = String.format("%.2f", total);
        sb.append("\nTotal: $").append(formattedTotal).append("\n");

        return sb.toString();
    }
}
