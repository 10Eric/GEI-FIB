package edu.upc.prop.clusterxx;

import static org.junit.Assert.assertEquals;

import edu.upc.prop.clusterxx.clases_dominio.Producte;
import edu.upc.prop.clusterxx.controladores.ControladorProducte;
import org.junit.Before;
import org.junit.Test;

public class TestControladorProducte {
    private ControladorProducte controladorProducte;
    private Producte producte;

    @Before
    public void setUp() {
        producte = new Producte("Producto 1", "Marca 1", 10.0, 5);
        controladorProducte = new ControladorProducte(producte);
    }

    @Test
    public void testGetNom() {
        assertEquals("Producto 1", controladorProducte.getNom());
    }

    @Test
    public void testSetNom() {
        controladorProducte.setNom("Producto 2");
        assertEquals("Producto 2", controladorProducte.getNom());
    }

    @Test
    public void testGetMarca() {
        assertEquals("Marca 1", controladorProducte.getMarca());
    }

    @Test
    public void testSetMarca() {
        controladorProducte.setMarca("Marca 2");
        assertEquals("Marca 2", controladorProducte.getMarca());
    }

    @Test
    public void testGetPreu() {
        assertEquals(10.0, controladorProducte.getPreu(), 0.01);
    }

    @Test
    public void testSetPreu() {
        controladorProducte.setPreu(15.0);
        assertEquals(15.0, controladorProducte.getPreu(), 0.01);
    }

    @Test
    public void testGetQuantitat() {
        assertEquals(5, controladorProducte.getQuantitat());
    }

    @Test
    public void testSetQuantitat() {
        controladorProducte.setQuantitat(10);
        assertEquals(10, controladorProducte.getQuantitat());
    }

    @Test
    public void testGetSimilitud() {
        Producte otherProducte = new Producte("Producto 2", "Marca 2", 15.0, 3);
        producte.setSimilitud(otherProducte, 50);
        assertEquals(50, controladorProducte.getSimilitud(otherProducte));
    }

    @Test
    public void testSetSimilitud() {
        Producte otherProducte = new Producte("Producto 2", "Marca 2", 15.0, 3);
        controladorProducte.setSimilitud(otherProducte, 75);
        assertEquals(75, controladorProducte.getSimilitud(otherProducte));
    }
}
