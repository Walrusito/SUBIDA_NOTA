package Test;

import org.junit.jupiter.api.Test;
import proyeto_comida_rapida.Refrescos;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RefrescosTest {

    double precioRefresco = 2.50;
    double precioAgua = 1.50;
    double precioAguaMal = 1.00;

    // Prueba para verificar que el método actualizarPrecio() de Refrescos ajusta correctamente el precio para un refresco.
    @Test
    public void testActualizarPrecio_Refresco() {
        Refrescos refresco = new Refrescos();
        refresco.setTipo("Refresco");
        refresco.actualizarPrecio();

        double precioEsperado = precioRefresco;

        assertEquals(precioEsperado, refresco.getPrecio(), 0.01);
    }

    // Prueba para verificar que el método actualizarPrecio() de Refrescos ajusta correctamente el precio para agua.
    @Test
    public void testActualizarPrecio_Agua() {
        Refrescos agua = new Refrescos();
        agua.setTipo("Agua");
        agua.actualizarPrecio();

        double precioEsperado = precioAgua;

        assertEquals(precioEsperado, agua.getPrecio(), 0.01);
    }

    // Prueba para verificar que el método actualizarPrecio() de Refrescos da error con un precio que no es válido.
    @Test
    public void testActualizarPrecio_AguaError() {
        Refrescos agua = new Refrescos();
        agua.setTipo("Agua");
        agua.actualizarPrecio();

        assertEquals(precioAguaMal, agua.getPrecio(), 0.01);
    }
}