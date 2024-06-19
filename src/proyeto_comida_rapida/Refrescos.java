package proyeto_comida_rapida;

import java.util.Scanner;

/**
 * Clase que representa los refrescos disponibles en el menú de comida rápida.
 * Extiende de la clase Producto e implementa la interfaz ProductosInterfaz para definir comportamientos específicos.
 */
public class Refrescos extends Producto implements ProductosInterfaz {
    private int idRefresco;
    private String tipo;
    private double precio;
    private static int stock;
    private boolean esRefresco;

    private final static double precioRefresco = 2.50;
    private final static double precioAgua = 1.50;

    /**
     * Constructor por defecto de la clase Refrescos.
     * Inicializa los atributos de la clase.
     */
    public Refrescos() {
    }

    /**
     * Método para actualizar el precio del refresco según el tipo seleccionado.
     */
    private void actualizarPrecio() {
        if ("Refresco".equals(tipo)) {
            this.precio = precioRefresco;
        } else if ("Agua".equals(tipo)) {
            this.precio = precioAgua;
        }
    }

    /**
     * Implementación del método de la interfaz ProductosInterfaz para elegir el tipo de refresco.
     * Utiliza Scanner para recibir la entrada del usuario y seleccionar entre las opciones disponibles.
     */
    @Override
    public void elegirTipo() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Seleccione el tipo de refresco:");
            System.out.println("1. Refresco");
            System.out.println("2. Agua");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        tipo = "Refresco";
                        actualizarPrecio();
                        return;
                    case 2:
                        tipo = "Agua";
                        actualizarPrecio();
                        return;
                    default:
                        System.out.println("Por favor, seleccione 1 o 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intente nuevamente.");
            }
        } while (true);
    }

    /**
     * Método para actualizar el stock de refrescos en la base de datos.
     * Verifica la disponibilidad del tipo de refresco y ajusta el stock correspondiente.
     * @param tipo Tipo de refresco a actualizar en el stock.
     */
    public void actualizarStock(String tipo) {
        if (RefrescosDAO.verificarDisponibilidad(tipo)) {
            RefrescosDAO.ajustarStock(tipo, 1);
        } else {
            System.out.println("No hay suficiente stock de refresco tipo '" + tipo + "'.");
        }
    }

    /**
     * Método getter para obtener el precio del refresco.
     * @return Precio del refresco.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Método setter para establecer el precio del refresco.
     * @param precio Nuevo precio del refresco.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Método getter para obtener el stock de refrescos.
     * @return Stock de refrescos.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Método setter para establecer el stock de refrescos.
     * @param stock Nuevo stock de refrescos.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Método getter para obtener el tipo de refresco.
     * @return Tipo de refresco.
     */
    public String getTipo() {
        return tipo;
    }
}