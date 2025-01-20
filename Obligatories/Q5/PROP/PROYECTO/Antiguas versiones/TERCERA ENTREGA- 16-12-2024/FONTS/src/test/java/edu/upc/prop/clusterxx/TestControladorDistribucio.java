package edu.upc.prop.clusterxx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import edu.upc.prop.clusterxx.clases_dominio.Distribucio;
import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;
import edu.upc.prop.clusterxx.clases_dominio.Producte;
import edu.upc.prop.clusterxx.clases_dominio.ProducteColocat;
import edu.upc.prop.clusterxx.estrategias_calculo.CalculBackTracking;
import edu.upc.prop.clusterxx.estrategias_calculo.EstrategiaTSP;
import edu.upc.prop.clusterxx.controladores.ControladorDistribucio;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestControladorDistribucio {

    private ControladorDistribucio controladorDistribucio;
    private Prestatgeria prestatgeria;
    private Distribucio distribucio;

    @Before
    public void setUp() {
        prestatgeria = new Prestatgeria("Estante 1", 5);
        distribucio = new Distribucio("Distribucion Predeterminada");
        controladorDistribucio = new ControladorDistribucio();
        controladorDistribucio.setPrestatgeria(prestatgeria);
    }

    @Test
    public void testCrearInicial() {
        controladorDistribucio.crear_inicial(distribucio, prestatgeria);
        assertEquals(distribucio, controladorDistribucio.getDistribucion());
        assertEquals(prestatgeria, controladorDistribucio.get_Prestatgeria());
    }

    @Test
    public void testCalcularDistribucion() {
        controladorDistribucio.afegeix_producte("Producto 1", "Marca 1", 10.0, 5, 1, 5);
        controladorDistribucio.calcula_distribucio(1); // Assuming 1 is a valid strategy
        assertNotNull(controladorDistribucio.getMatrizDistribucion());
    }

    @Test
    public void testGetMatrizDistribucion() {
        controladorDistribucio.afegeix_producte("Producto 1", "Marca 1", 10.0, 5, 1, 5);
        String[][] matriz = controladorDistribucio.getMatrizDistribucion();
        assertNotNull(matriz);
        assertEquals("Producto 1", matriz[0][0]);
    }

    @Test
    public void testGetAlturaMedida() {
        controladorDistribucio.crear_inicial(distribucio, prestatgeria);
        ArrayList<String> alturaMedida = controladorDistribucio.get_altura_medida();
        assertEquals("5", alturaMedida.get(0));
    }

    @Test
    public void testAgregarProducte() {
        controladorDistribucio.afegeix_producte("Producto 1", "Marca 1", 10.0, 5, 1, 5);
        assertTrue(controladorDistribucio.existeix_producte("Producto 1"));
    }

    @Test
    public void testGetSimilitudsProducte() {
        controladorDistribucio.afegeix_producte("Producto 1", "Marca 1", 10.0, 5, 1, 5);
        controladorDistribucio.afegeix_producte("Producto 2", "Marca 2", 15.0, 3, 2, 5);
        ArrayList<String> similituds = controladorDistribucio.getSimilitudsProducte("Producto 1");
        assertNotNull(similituds);
        assertFalse(similituds.isEmpty());
    }

    @Test
    public void testGetProductes() {
        controladorDistribucio.afegeix_producte("Producto 1", "Marca 1", 10.0, 5, 1, 5);
        ArrayList<String> productes = controladorDistribucio.getProductes();
        assertNotNull(productes);
        assertEquals(1, productes.size());
    }

    @Test
    public void testIntercanviaProductes() {
        controladorDistribucio.afegeix_producte("Producto 1", "Marca 1", 10.0, 5, 1, 5);
        controladorDistribucio.afegeix_producte("Producto 2", "Marca 2", 15.0, 3, 2, 5);
        controladorDistribucio.intercanviaProductes("Producto 1", "Producto 2");
        assertEquals(2, controladorDistribucio.getProductesColocats().get(0).getPos());
        assertEquals(1, controladorDistribucio.getProductesColocats().get(1).getPos());
    }

    @Test
    public void testGetNumero_prestatges() {
        assertEquals(0, controladorDistribucio.getNumero_prestatges());
        controladorDistribucio.afegeix_producte("Producto 1", "Marca 1", 10.0, 5, 1, 5);
        assertEquals(1, controladorDistribucio.getNumero_prestatges());
    }

    @Test
    public void testSetSimilitud() {
        controladorDistribucio.afegeix_producte("Producto 1", "Marca 1", 10.0, 5, 1, 5);
        controladorDistribucio.afegeix_producte("Producto 2", "Marca 2", 15.0, 3, 2, 5);
        controladorDistribucio.set_similitud("Producto 1", "Producto 2", 10);
        assertEquals(10, controladorDistribucio.getProductesColocats().get(0).getProducte().getSimilitud(controladorDistribucio.getProductesColocats().get(1).getProducte()));
    }

    @Test
    public void testEliminarProducte() {
        controladorDistribucio.afegeix_producte("Producto 1", "Marca 1", 10.0, 5, 1, 5);
        controladorDistribucio.eliminaProducte("Producto 1");
        assertFalse(controladorDistribucio.existeix_producte("Producto 1"));
    }

    @Test
    public void testGetInfoProducte() {
        controladorDistribucio.afegeix_producte("Producto 1", "Marca 1", 10.0, 5, 1, 5);
        ArrayList<String> info = controladorDistribucio.get_info_producte("Producto 1");
        assertNotNull(info);
        assertEquals("Producto 1", info.get(0));
    }

    @Test
    public void testSetCantidadProducto() {
        controladorDistribucio.afegeix_producte("Producto 1", "Marca 1", 10.0, 5, 1, 5);
        controladorDistribucio.set_cantidad_producto("Producto 1", 10);
        assertEquals(10, controladorDistribucio.getProductesColocats().get(0).getProducte().getQuantitat());
    }

    @Test
    public void testSetPrecioProducto() {
        controladorDistribucio.afegeix_producte("Producto 1", "Marca 1", 10.0, 5, 1, 5);
        controladorDistribucio.set_precio_producto("Producto 1", 15.0);
        assertEquals(15.0, controladorDistribucio.getProductesColocats().get(0).getProducte().getPreu(), 0.01);
    }

    @Test
    public void testGetProductesColocats() {
        controladorDistribucio.afegeix_producte("Producto 1", "Marca 1", 10.0, 5, 1, 5);
        ArrayList<ProducteColocat> productesColocats = controladorDistribucio.getProductesColocats();
        assertNotNull(productesColocats);
        assertEquals(1, productesColocats.size());
    }

    @Test
    public void testSetProductesColocats() {
        ArrayList<ProducteColocat> productesColocats = new ArrayList<>();
        productesColocats.add(new ProducteColocat(1, 5, new Producte("Producto 1", "Marca 1", 10.0, 5)));
        controladorDistribucio.setProductesColocats(productesColocats);
        assertEquals(productesColocats, controladorDistribucio.getProductesColocats());
    }

    @Test
    public void testIsCambiosManualesRealizados() {
        controladorDistribucio.setCambiosManualesRealizados(true);
        assertTrue(controladorDistribucio.isCambiosManualesRealizados());
    }

    @Test
    public void testSetCambiosManualesRealizados() {
        controladorDistribucio.setCambiosManualesRealizados(true);
        assertTrue(controladorDistribucio.isCambiosManualesRealizados());
        controladorDistribucio.setCambiosManualesRealizados(false);
        assertFalse(controladorDistribucio.isCambiosManualesRealizados());
    }

    @Test
    public void testIntercambiarProductes() {
        controladorDistribucio.afegeix_producte("Producto 1", "Marca 1", 10.0, 5, 1, 5);
        controladorDistribucio.afegeix_producte("Producto 2", "Marca 2", 15.0, 3, 2, 5);
        controladorDistribucio.intercambiar_productes(0, 1);
        assertEquals(2, controladorDistribucio.getProductesColocats().get(0).getPos());
        assertEquals(1, controladorDistribucio.getProductesColocats().get(1).getPos());
    }

    @Test
    public void testGetPrestatgeria() {
        assertEquals(prestatgeria, controladorDistribucio.get_Prestatgeria());
    }

}
