package edu.upc.prop.clusterxx;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
public class TestDistribucio {

    private Distribucio distribucio;
    private Producte p1;
    private Producte p2;

    @Before
    public void setUp() {
        distribucio = new Distribucio("Distribuidor 1");
        p1 = new Producte("Marca1", "Producto A", 10.0f, "5", "001");
        p2 = new Producte("Marca2", "Producto B", 15.0f, "3", "002");
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
}
