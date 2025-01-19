package edu.upc.prop.clusterxx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class TestProducte {

    private Producte producte;
    private Producte producte2;
    private Producte producte3;

    @Before
    public void setUp() {
        producte = new Producte("Producto A", "Marca1", 10.0, 5);
        producte2 = new Producte("Producto B", "Marca2", 15.0, 3);
        producte3 = new Producte("Producto C", "Marca3", 20.0, 2);
    }

    @Test
    public void testConstructor() {
        assertNotNull(producte);
        assertEquals("Producto A", producte.getNom());
        assertEquals("Marca1", producte.getMarca());
        assertEquals(10.0, producte.getPreu(), 0.01);
        assertEquals(5, producte.getQuantitat());
    }

    @Test
    public void testGetAndSetNom() {
        producte.setNom("Nuevo Nombre");
        assertEquals("Nuevo Nombre", producte.getNom());
    }

    @Test
    public void testGetAndSetMarca() {
        producte.setMarca("Nueva Marca");
        assertEquals("Nueva Marca", producte.getMarca());
    }

    @Test
    public void testGetAndSetPreu() {
        producte.setPreu(12.0);
        assertEquals(12.0, producte.getPreu(), 0.01);
    }

    @Test
    public void testGetAndSetQuantitat() {
        producte.setQuantitat(10);
        assertEquals(10, producte.getQuantitat());
    }

    @Test
    public void testGetSimilitud() {
        producte.setSimilitud(producte2, 50);
        assertEquals(50, producte.getSimilitud(producte2));
    }

    @Test
    public void testSetSimilitud() {
        producte.setSimilitud(producte2, 70);
        assertEquals(70, producte.getSimilitud(producte2));
        assertEquals(70, producte2.getSimilitud(producte));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetSimilitudInvalid() {
        producte.setSimilitud(producte2, 110);
    }
}

