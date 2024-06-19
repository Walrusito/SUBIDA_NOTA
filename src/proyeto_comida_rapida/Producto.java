package proyeto_comida_rapida;

/**
 * Clase abstracta que representa un producto genérico.
 */
public abstract class Producto implements ProductosInterfaz {
    protected String nombre;
    protected double precio;
    protected int stock;
    protected String tipo;

    public Producto() {
    }

    public Producto(String nombre, double precio, int stock, String tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.tipo = tipo;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public abstract void elegirTipo();
    public abstract void actualizarStock(String tipo);

}
