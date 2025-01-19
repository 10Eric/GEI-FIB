package edu.upc.prop.clusterxx;

import static org.junit.Assert.assertEquals;

import edu.upc.prop.clusterxx.clases_dominio.Distribucio;
import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;
import edu.upc.prop.clusterxx.controladores.ControladorPrestatgeria;
import org.junit.Before;
import org.junit.Test;

public class TestControladorPrestatgeria {
    private ControladorPrestatgeria controladorPrestatgeria;
    private Prestatgeria prestatgeria;
    private Distribucio distribucio;

    @Before
    public void setUp() {
        distribucio = new Distribucio("Distribucion 1");
        prestatgeria = new Prestatgeria("Estante 1", 5);
        prestatgeria.setDistribucion(distribucio);
        controladorPrestatgeria = new ControladorPrestatgeria(prestatgeria);
    }

    @Test
    public void testGetNom() {
        assertEquals("Estante 1", controladorPrestatgeria.getNom());
    }

    @Test
    public void testSetNom() {
        controladorPrestatgeria.setNom("Estante 2");
        assertEquals("Estante 2", controladorPrestatgeria.getNom());
    }

    @Test
    public void testGetNombreDeproductes() {
        assertEquals("5", controladorPrestatgeria.getNombreDeproductes());
    }

    @Test
    public void testGetAltura() {
        assertEquals(Integer.valueOf(5), controladorPrestatgeria.getAltura());
    }

    @Test
    public void testSetAltura() {
        controladorPrestatgeria.setAltura(10);
        assertEquals(Integer.valueOf(10), controladorPrestatgeria.getAltura());
    }

    @Test
    public void testGetDistribucion() {
        assertEquals(distribucio, controladorPrestatgeria.getDistribucion());
    }

    @Test
    public void testSetDistribucion() {
        Distribucio newDistribucio = new Distribucio("Distribucion 2");
        controladorPrestatgeria.setDistribucion(newDistribucio);
        assertEquals(newDistribucio, controladorPrestatgeria.getDistribucion());
    }
}
