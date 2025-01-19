package edu.upc.prop.clusterxx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.lang.reflect.Method;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestPrestatgeria {

    private Prestatgeria prestatgeria;
    private Producte producte;
    private ProducteColocat producteColocat;
    private ArrayList<ProducteColocat> productesColocats;

    @Before
    public void setUp() {
        prestatgeria = new Prestatgeria(10, 5);
        producte = new Producte("Producto A", "Marca1", 10.0, 5);
        producteColocat = new ProducteColocat(1, 4, producte);
        productesColocats = new ArrayList<>();
    }

    @Test
    public void testConstructor() {
        assertNotNull(prestatgeria);
        assertEquals(10, prestatgeria.getNumeroDePrestages());
        assertEquals(5, prestatgeria.getAltura());
        assertTrue(prestatgeria.getProductesColocats().isEmpty());
        assertFalse(prestatgeria.isCambiosManualesRealizados());
    }

    @Test
    public void testObtenirProductePos() {
        prestatgeria.afegirProducteColocat(producteColocat);
        assertEquals(producte, prestatgeria.obtenirProductePos(1));
        assertNull(prestatgeria.obtenirProductePos(2));
    }

    @Test
    public void testAfegirProducteColocat() {
        assertTrue(prestatgeria.afegirProducteColocat(producteColocat));
        assertFalse(prestatgeria.afegirProducteColocat(new ProducteColocat(2, 6, producte))); // Invalid height
    }


    @Test
    public void testVerDistribucionProductos() {
        prestatgeria.afegirProducteColocat(producteColocat);
        prestatgeria.verDistribucionProductos();
        // This test will print to the console, manual verification needed
    }

    @Test
    public void testArrangeProductsBySimilarity() throws Exception {
        prestatgeria.afegirProducteColocat(producteColocat);
        Method method = Prestatgeria.class.getDeclaredMethod("arrangeProductsBySimilarity", List.class, boolean.class);
        method.setAccessible(true);
        List<ProducteColocat> arranged = (List<ProducteColocat>) method.invoke(prestatgeria, prestatgeria.getProductesColocats(), false);
        assertEquals(1, arranged.size());
        assertEquals(producteColocat, arranged.get(0));
    }


    @Test
    public void testCalculateSimilarity() throws Exception {
        Producte producte2 = new Producte("Producto B", "Marca2", 15.0, 3);
        producte.setSimilitud(producte2, 50);
        Method method = Prestatgeria.class.getDeclaredMethod("calculateSimilarity", Producte.class, Producte.class);
        method.setAccessible(true);
        int similarity = (int) method.invoke(prestatgeria, producte, producte2);
        assertEquals(50, similarity);
    }

    @Test
    public void testGetAndSetNumeroDePrestages() {
        prestatgeria.setNumeroDePrestages(15);
        assertEquals(15, prestatgeria.getNumeroDePrestages());
    }

    @Test
    public void testGetAndSetAltura() {
        prestatgeria.setAltura(7);
        assertEquals(7, prestatgeria.getAltura());
    }

    @Test
    public void testGetAndSetProductesColocats() {
        prestatgeria.setProductesColocats(productesColocats);
        assertEquals(productesColocats, prestatgeria.getProductesColocats());
    }

    @Test
    public void testIsAndSetCambiosManualesRealizados() {
        prestatgeria.setCambiosManualesRealizados(true);
        assertTrue(prestatgeria.isCambiosManualesRealizados());
        prestatgeria.setCambiosManualesRealizados(false);
        assertFalse(prestatgeria.isCambiosManualesRealizados());
    }
}
