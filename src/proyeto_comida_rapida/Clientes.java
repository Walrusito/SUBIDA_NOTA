package proyeto_comida_rapida;

/**
 * Clase que representa a los clientes de la aplicación de comida rápida.
 */
public class Clientes {
    private int idCliente;
    private String nombre;
    private String email;
    private String password;

    /**
     * Constructor de la clase Clientes.
     *
     * @param nombre   El nombre del cliente.
     * @param email    El email del cliente.
     * @param password La contraseña del cliente.
     */
    public Clientes(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    /**
     * Obtiene el ID del cliente.
     *
     * @return El ID del cliente.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Establece el ID del cliente.
     *
     * @param idCliente El ID del cliente.
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     *
     * @param nombre El nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el email del cliente.
     *
     * @return El email del cliente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del cliente.
     *
     * @param email El email del cliente.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del cliente.
     *
     * @return La contraseña del cliente.
     */
    public String getPassword() {
        return password;
    }
}