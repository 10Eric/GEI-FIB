package edu.upc.prop.clusterxx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import edu.upc.prop.clusterxx.clases_dominio.Perfil;
import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;
import edu.upc.prop.clusterxx.controladores.ControladorPerfil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestControladorPerfil {
    private ControladorPerfil controladorPerfil;
    private Perfil perfil;

    @Before
    public void setUp() {
        perfil = new Perfil("Usuario 1","pimienta");
        controladorPerfil = new ControladorPerfil(perfil);
    }

    @Test
    public void testGetUsuari() {
        assertEquals("Usuario 1", controladorPerfil.getUsuari());
    }

    @Test
    public void testSetUsuari() {
        controladorPerfil.setUsuari("Usuario 2");
        assertEquals("Usuario 2", controladorPerfil.getUsuari());
    }

    @Test
    public void testGetContrasenya() {
        perfil.setContrasenya("password");
        assertEquals("password", controladorPerfil.getContrasenya());
    }

    @Test
    public void testSetContrasenya() {
        controladorPerfil.setContrasenya("newpassword");
        assertEquals("newpassword", controladorPerfil.getContrasenya());
    }

    @Test
    public void testAñadirPrestatgeria() {
        Prestatgeria prestatgeria = new Prestatgeria("Estante 1", 5);
        controladorPerfil.añadirPrestatgeria(prestatgeria);
        assertEquals(1, controladorPerfil.getPrestatgerias().size());
    }

    @Test
    public void testEliminarPrestatgeria() {
        Prestatgeria prestatgeria = new Prestatgeria("Estante 1", 5);
        controladorPerfil.añadirPrestatgeria(prestatgeria);
        controladorPerfil.eliminarPrestatgeria(prestatgeria);
        assertEquals(0, controladorPerfil.getPrestatgerias().size());
    }

    @Test
    public void testGetPrestatgerias() {
        Prestatgeria prestatgeria = new Prestatgeria("Estante 1", 5);
        controladorPerfil.añadirPrestatgeria(prestatgeria);
        List<Prestatgeria> prestatgeries = controladorPerfil.getPrestatgerias();
        assertNotNull(prestatgeries);
        assertEquals(1, prestatgeries.size());
    }

    @Test
    public void testSetPrestatgerias() {
        List<Prestatgeria> prestatgeries = new ArrayList<>();
        prestatgeries.add(new Prestatgeria("Estante 1", 5));
        controladorPerfil.setPrestatgerias(prestatgeries);
        assertEquals(prestatgeries, controladorPerfil.getPrestatgerias());
    }
}
