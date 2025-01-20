package edu.upc.prop.clusterxx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestPerfil {

    private Perfil perfil;
    private Prestatgeria Prestatgeria1;
    private Prestatgeria Prestatgeria2;

    @Before
    public void setUp() {
        perfil = new Perfil("usuari1", "contrasenya1");
        Prestatgeria1 = new Prestatgeria("Distribuidor 1", 5, new Distribucio("Distribuidor2"));
        Prestatgeria2 = new Prestatgeria("Distribuidor 2", 10 , new Distribucio("Distribuidor2"));
    }

    @Test
    public void testConstructor() {
        assertEquals("usuari1", perfil.getUsuari());
        assertEquals("contrasenya1", perfil.getContrasenya());
        assertTrue(perfil.getPrestatgeria().isEmpty());
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
    public void testAñadirPrestatgeria() {
        perfil.añadirPrestatgeria(Prestatgeria1);
        assertTrue(perfil.getPrestatgeria().contains(Prestatgeria1));
    }

    @Test
    public void testEliminarPrestatgeria() {
        perfil.añadirPrestatgeria(Prestatgeria1);
        perfil.eliminarPrestatgeria(Prestatgeria1);
        assertFalse(perfil.getPrestatgeria().contains(Prestatgeria1));
    }

    @Test
    public void testGetPrestatgerias() {
        perfil.añadirPrestatgeria(Prestatgeria1);
        perfil.añadirPrestatgeria(Prestatgeria2);
        assertTrue(perfil.getPrestatgeria().contains(Prestatgeria1));
        assertTrue(perfil.getPrestatgeria().contains(Prestatgeria2));
        assertEquals(2, perfil.getPrestatgeria().size());
    }
}