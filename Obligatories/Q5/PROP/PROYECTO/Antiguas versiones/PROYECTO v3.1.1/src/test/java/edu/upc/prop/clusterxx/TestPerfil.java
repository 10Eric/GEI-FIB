package edu.upc.prop.clusterxx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestPerfil {

    private Perfil perfil;
    private Distribucio distribucio1;
    private Distribucio distribucio2;

    @Before
    public void setUp() {
        perfil = new Perfil("usuari1", "contrasenya1");
        distribucio1 = new Distribucio("Distribuidor 1", new Prestatgeria(10, 5));
        distribucio2 = new Distribucio("Distribuidor 2", new Prestatgeria(20, 10));
    }

    @Test
    public void testConstructor() {
        assertEquals("usuari1", perfil.getUsuari());
        assertEquals("contrasenya1", perfil.getContrasenya());
        assertTrue(perfil.getDistribucions().isEmpty());
    }

    @Test
    public void testGetAndSetUsuari() {
        perfil.setUsuari("nouUsuari");
        assertEquals("nouUsuari", perfil.getUsuari());
    }

    @Test
    public void testGetAndSetContrasenya() {
        perfil.setContrasenya("novaContrasenya");
        assertEquals("novaContrasenya", perfil.getContrasenya());
    }

    @Test
    public void testAñadirDistribucion() {
        perfil.añadirDistribucion(distribucio1);
        assertTrue(perfil.getDistribucions().contains(distribucio1));
    }

    @Test
    public void testEliminarDistribucion() {
        perfil.añadirDistribucion(distribucio1);
        perfil.eliminarDistribucion(distribucio1);
        assertFalse(perfil.getDistribucions().contains(distribucio1));
    }

    @Test
    public void testGetDistribucions() {
        perfil.añadirDistribucion(distribucio1);
        perfil.añadirDistribucion(distribucio2);
        assertTrue(perfil.getDistribucions().contains(distribucio1));
        assertTrue(perfil.getDistribucions().contains(distribucio2));
        assertEquals(2, perfil.getDistribucions().size());
    }
}