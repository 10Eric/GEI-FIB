package edu.upc.prop.clusterxx;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
public class TestDistribucio {

    private Distribucio distribucio;
    private Producte p1;
    private Producte p2;
    private Prestatgeria prestatgeria;

    @Before
    public void setUp() {
        prestatgeria = new Prestatgeria(10, 5);
        distribucio = new Distribucio("Distribuidor 1", prestatgeria);
        p1 = new Producte("Producto A", "Marca1", 10.0, 5);
        p2 = new Producte("Producto B", "Marca2", 15.0, 3);
    }

    @Test
    public void testAgregarProducte() {
        distribucio.agregaProducte(p1);
        ArrayList<Producte> productes = distribucio.getProductes();
        assertTrue(productes.contains(p1));
    }

    @Test
    public void testEliminarProducte() {
        distribucio.agregaProducte(p1);
        distribucio.eliminaProducte(p1);
        ArrayList<Producte> productes = distribucio.getProductes();
        assertFalse(productes.contains(p1));
    }

    @Test
    public void testGetProductes() {
        distribucio.agregaProducte(p1);
        distribucio.agregaProducte(p2);
        ArrayList<Producte> productes = distribucio.getProductes();
        assertTrue(productes.contains(p1));
        assertTrue(productes.contains(p2));
        assertEquals(2, productes.size());
    }

    @Test
    public void testGetNom() {
        assertEquals("Distribuidor 1", distribucio.getNom());
    }

    @Test
    public void testSetNom() {
        distribucio.setNom("Nuevo Distribuidor");
        assertEquals("Nuevo Distribuidor", distribucio.getNom());
    }

    @Test
    public void testGetProductePorNom() {
        distribucio.agregaProducte(p1);
        distribucio.agregaProducte(p2);
        assertEquals(p1, distribucio.getProductePorNom("Producto A"));
        assertEquals(p2, distribucio.getProductePorNom("Producto B"));
        assertNull(distribucio.getProductePorNom("Producto C"));
    }

    @Test
    public void testGetPrestatge() {
        assertEquals(prestatgeria, distribucio.getPrestatge());
    }

    @Test
    public void testSetPrestatge() {
        Prestatgeria nuevoPrestatge = new Prestatgeria(20, 10);
        distribucio.setPrestatge(nuevoPrestatge);
        assertEquals(nuevoPrestatge, distribucio.getPrestatge());
    }
}
