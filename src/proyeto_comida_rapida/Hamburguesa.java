package proyeto_comida_rapida;

import java.util.Scanner;

/**
 * Clase que representa una hamburguesa en la aplicación de comida rápida.
 * Extiende la clase Producto.
 */
public class Hamburguesa extends Producto {
    private double precio;
    private String tipo;
    private final static double precioSinQueso = 6.99;
    private final static double precioConQueso = 8.99;

    /**
     * Constructor por defecto de la clase Hamburguesa.
     */
    public Hamburguesa() {
    }

    /**
     * Método para elegir el tipo de hamburguesa (con queso o sin queso).
     * Actualiza el precio y el stock de acuerdo al tipo seleccionado.
     */
    public void elegirTipo() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Seleccione el tipo de hamburguesa:");
            System.out.println("1. Sin queso");
            System.out.println("2. Con queso");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcion) {
                    case 1:
                        this.tipo = "Sin queso";
                        this.precio = precioSinQueso;
                        actualizarStock(this.tipo);
                        return; 
                    case 2:
                        this.tipo = "Con queso";
                        this.precio = precioConQueso;
                        actualizarStock(this.tipo);
                        return; 
                    default:
                        System.out.println("Por favor, seleccione 1 o 2.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Intente nuevamente.");
                scanner.nextLine();
            }
        } while (true);
    }

    /**
     * Actualiza el stock de hamburguesas del tipo especificado.
     *
     * @param tipo El tipo de hamburguesa cuyo stock se debe actualizar.
     */
    public void actualizarStock(String tipo) {
        if (HamburguesaDAO.verificarDisponibilidad(tipo)) {
            HamburguesaDAO.ajustarStock(tipo, 1);
        } else {
            System.out.println("No hay suficiente stock de hamburguesas tipo '" + tipo + "'.");
        }
    }

    /**
     * Obtiene el tipo de hamburguesa.
     *
     * @return El tipo de hamburguesa.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtiene el precio de la hamburguesa.
     *
     * @return El precio de la hamburguesa.
     */
    public double getPrecio() {
        return precio;
    }

}
