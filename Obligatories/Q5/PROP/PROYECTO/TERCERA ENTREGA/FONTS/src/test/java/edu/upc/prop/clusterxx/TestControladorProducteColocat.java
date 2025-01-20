package edu.upc.prop.clusterxx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import edu.upc.prop.clusterxx.clases_dominio.Producte;
import edu.upc.prop.clusterxx.clases_dominio.ProducteColocat;
import edu.upc.prop.clusterxx.controladores.ControladorProducteColocat;
import org.junit.Before;
import org.junit.Test;

public class TestControladorProducteColocat {
    private ControladorProducteColocat controladorProducteColocat;
    private ProducteColocat producteColocat;
    private Producte producte;

    @Before
    public void setUp() {
        producte = new Producte("Producto 1", "Marca 1", 10.0, 5);
        producteColocat = new ProducteColocat(1, 5, producte);
        controladorProducteColocat = new ControladorProducteColocat(producteColocat);
    }

    @Test
    public void testGetPos() {
        assertEquals(1, controladorProducteColocat.getPos());
    }

    @Test
    public void testSetPos() {
        controladorProducteColocat.setPos(2);
        assertEquals(2, controladorProducteColocat.getPos());
    }

    @Test
    public void testGetAltura() {
        assertEquals(5, controladorProducteColocat.getAltura());
    }

    @Test
    public void testSetAltura() {
        controladorProducteColocat.setAltura(10);
        assertEquals(10, controladorProducteColocat.getAltura());
    }

    @Test
    public void testIsManualmenteModificado() {
        assertFalse(controladorProducteColocat.isManualmenteModificado());
    }

    @Test
    public void testSetManualmenteModificado() {
        controladorProducteColocat.setManualmenteModificado(true);
        assertTrue(controladorProducteColocat.isManualmenteModificado());
    }

    @Test
    public void testGetProducte() {
        assertEquals(producte, controladorProducteColocat.getProducte());
    }

    @Test
    public void testSetProducte() {
        Producte newProducte = new Producte("Producto 2", "Marca 2", 20.0, 10);
        controladorProducteColocat.setProducte(newProducte);
        assertEquals(newProducte, controladorProducteColocat.getProducte());
    }

}
