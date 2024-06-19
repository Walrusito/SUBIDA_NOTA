package proyeto_comida_rapida;

import java.util.Scanner;

/**
 * Clase que representa un producto de patatas fritas.
 */
public class Patatas extends Producto {
    private double precio;
    private static int stock;

    private static final double precioNormal = 3.50;
    private static final double precioDeluxe = 5.00;
    private String tipo;

    /**
     * Constructor por defecto de la clase Patatas.
     */
    public Patatas() {
    }

    /**
     * Método para que el usuario elija el tipo de patatas.
     * Asigna el tipo y el precio correspondiente a la instancia y actualiza el stock.
     */
    @Override
    public void elegirTipo() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Seleccione el tipo de patatas:");
            System.out.println("1. Normal");
            System.out.println("2. Deluxe");

            try {
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        tipo = "Normal";
                        this.precio = precioNormal;
                        actualizarStock(tipo);
                        return;
                    case 2:
                        tipo = "Deluxe";
                        this.precio = precioDeluxe;
                        actualizarStock(tipo);
                        return;
                    default:
                        System.out.println("Por favor, seleccione 1 o 2.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Intente nuevamente.");
                scanner.nextLine(); 
            }
        } while (true);
    }

    /**
     * Método para actualizar el stock de las patatas.
     * Verifica la disponibilidad y ajusta el stock en la base de datos.
     *
     * @param tipo El tipo de patatas cuyo stock se va a actualizar.
     */
    public void actualizarStock(String tipo) {
        if (PatatasDAO.verificarDisponibilidad(tipo)) { 
            PatatasDAO.ajustarStock(tipo, 1);
        } else {
            System.out.println("No hay suficiente stock de patatas tipo '" + tipo + "'.");
        }
    }

    /**
     * Obtiene el precio de las patatas.
     *
     * @return El precio de las patatas.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio de las patatas.
     *
     * @param precio El nuevo precio de las patatas.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el stock de las patatas.
     *
     * @return El stock de las patatas.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Obtiene el tipo de patatas.
     *
     * @return El tipo de patatas.
     */
    public String getTipo() {
        return tipo;
    }




}
