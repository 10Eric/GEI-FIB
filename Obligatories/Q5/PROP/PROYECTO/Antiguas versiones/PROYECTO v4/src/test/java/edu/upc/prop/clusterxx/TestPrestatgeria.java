package edu.upc.prop.clusterxx;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
public class TestPrestatgeria {

    private Distribucio distribucio;
    private Producte p1;
    private Producte p2;
    private Prestatgeria prestatgeria;

    @Before
    public void setUp() {
        distribucio = new Distribucio("distri");
        prestatgeria = new Prestatgeria("Distribuidor 1", 5, distribucio);
        p1 = new Producte("Producto A", "Marca1", 10.0, 5);
        p2 = new Producte("Producto B", "Marca2", 15.0, 3);
    }

    @Test
    public void testAgregarProducte() {
        prestatgeria.agregaProducte(p1);
        ArrayList<Producte> productes = prestatgeria.getProductes();
        assertTrue(productes.contains(p1));
    }

    @Test
    public void testEliminarProducte() {
        prestatgeria.agregaProducte(p1);
        prestatgeria.eliminaProducte(p1);
        ArrayList<Producte> productes = prestatgeria.getProductes();
        assertFalse(productes.contains(p1));
    }

    @Test
    public void testGetProductes() {
        prestatgeria.agregaProducte(p1);
        prestatgeria.agregaProducte(p2);
        ArrayList<Producte> productes = prestatgeria.getProductes();
        assertTrue(productes.contains(p1));
        assertTrue(productes.contains(p2));
        assertEquals(2, productes.size());
    }

    @Test
    public void testGetNom() {
        assertEquals("Distribuidor 1", prestatgeria.getNom());
    }

    @Test
    public void testSetNom() {
        prestatgeria.setNom("Nuevo Distribuidor");
        assertEquals("Nuevo Distribuidor", prestatgeria.getNom());
    }

    @Test
    public void testGetProductePorNom() {
        prestatgeria.agregaProducte(p1);
        prestatgeria.agregaProducte(p2);
        assertEquals(p1, prestatgeria.getProductePorNom("Producto A"));
        assertEquals(p2, prestatgeria.getProductePorNom("Producto B"));
        assertNull(prestatgeria.getProductePorNom("Producto C"));
    }

    @Test
    public void testGetPrestatge() {
        assertEquals(distribucio, prestatgeria.getDistribucion());
    }

    @Test
    public void testSetPrestatge() {
        Distribucio nuevoPrestatge = new Distribucio("distri2");
        prestatgeria.setDistribucion(nuevoPrestatge);
        assertEquals(nuevoPrestatge, prestatgeria.getDistribucion());
    }
}
