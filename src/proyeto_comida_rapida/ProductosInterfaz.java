package proyeto_comida_rapida;

/**
 * Interfaz que define el comportamiento básico que deben implementar los productos.
 * La hice para probar las interfaces ya que no las había probado.
 */
public interface ProductosInterfaz {

    /**
     * Método abstracto para elegir el tipo específico de un producto.
     * Implementado por las clases que representan productos con opciones de tipo.
     */
    void elegirTipo();
}