package edu.upc.prop.clusterxx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestProducteColocat {

    private ProducteColocat producteColocat;
    private Producte producte;
    private Prestatgeria prestatgeria;

    @Before
    public void setUp() {
        producte = new Producte("Producto A", "Marca1", 10.0, 5);
        prestatgeria = new Prestatgeria(10, 5);
        producteColocat = new ProducteColocat(1, 100, producte);
    }

    @Test
    public void testConstructor() {
        assertNotNull(producteColocat);
        assertEquals(1, producteColocat.getPos());
        assertEquals(100, producteColocat.getAltura());
        assertEquals(producte, producteColocat.getProducte());
        assertFalse(producteColocat.isManualmenteModificado());
    }

    @Test
    public void testEsValidaAltura() {
        assertTrue(producteColocat.esValidaAltura(150)); // Should be true, as 100 <= 150
        assertFalse(producteColocat.esValidaAltura(80)); // Should be false, as 100 >= 80
    }

    @Test
    public void testGetAndSetPos() {
        producteColocat.setPos(2);
        assertEquals(2, producteColocat.getPos());
    }

    @Test
    public void testGetAndSetAltura() {
        producteColocat.setAltura(120);
        assertEquals(120, producteColocat.getAltura());
    }

    @Test
    public void testGetAndSetProducte() {
        Producte nuevoProducte = new Producte("Producto B", "Marca2", 15.0, 3);
        producteColocat.setProducte(nuevoProducte);
        assertEquals(nuevoProducte, producteColocat.getProducte());
    }

    @Test
    public void testIsAndSetManualmenteModificado() {
        producteColocat.setManualmenteModificado(true);
        assertTrue(producteColocat.isManualmenteModificado());
    }
}