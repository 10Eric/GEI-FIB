package edu.upc.prop.clusterxx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


import edu.upc.prop.clusterxx.clases_dominio.Distribucio;
import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;
import edu.upc.prop.clusterxx.clases_dominio.Producte;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestPrestatgeria {

    private Prestatgeria prestatgeria;
    private Distribucio distribucio;

    @Before
    public void setUp() {
        distribucio = new Distribucio("Distribucio");
        prestatgeria = new Prestatgeria("Distribuidor 1", 5, distribucio);
    }

    @Test
    public void testConstructor() {
        assertNotNull(prestatgeria);
        assertEquals("Distribuidor 1", prestatgeria.getNom());
        assertEquals(5, prestatgeria.getAltura());

        assertEquals(distribucio, prestatgeria.getDistribucion());
    }

    @Test
    public void testGetAndSetNom() {
        prestatgeria.setNom("Nuevo Distribuidor");
        assertEquals("Nuevo Distribuidor", prestatgeria.getNom());
    }

    @Test
    public void testGetAndSetAltura() {
        prestatgeria.setAltura(10);
        assertEquals(10, prestatgeria.getAltura());
    }

    @Test
    public void testGetAndSetDistribucion() {
        Distribucio nuevaDistribucion = new Distribucio("Nueva Distribucion");
        prestatgeria.setDistribucion(nuevaDistribucion);
        assertEquals(nuevaDistribucion, prestatgeria.getDistribucion());
    }

    @Test
    public void testEliminaDistribucio() {
        prestatgeria.eliminaDistribucio(distribucio);
        assertNull(prestatgeria.getDistribucion());
    }
}
