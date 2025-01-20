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

public class TestDistribucio {

    private Distribucio distribucio;
    private Producte producte;
    private ProducteColocat producteColocat;
    private ArrayList<ProducteColocat> productesColocats;

    @Before
    public void setUp() {
        distribucio = new Distribucio("Distribucio", 10);
        producte = new Producte("Producto A", "Marca1", 10.0, 5);
        producteColocat = new ProducteColocat(0, 4, producte);
        productesColocats = new ArrayList<>();
    }

    @Test
    public void testConstructor() {
        assertNotNull(distribucio);
        assertEquals(10, distribucio.getNumero_prestatges());
        assertTrue(distribucio.getProductesColocats().isEmpty());
        assertFalse(distribucio.isCambiosManualesRealizados());
    }

    @Test
    public void testObtenirProductePos() {
        distribucio.afegeix_producte_colocat(producteColocat);
        assertEquals(producte, distribucio.obtenirProductePos(1));
        assertNull(distribucio.obtenirProductePos(2));
    }

    @Test
    public void testAfegirProducteColocat() {
        distribucio.afegeix_producte_colocat(producteColocat);
        assertEquals(1, distribucio.getNumero_prestatges());
    }

    @Test
    public void testGetAndSetProductesColocats() {
        distribucio.setProductesColocats(productesColocats);
        assertEquals(productesColocats, distribucio.getProductesColocats());
    }

    @Test
    public void testIsAndSetCambiosManualesRealizados() {
        distribucio.setCambiosManualesRealizados(true);
        assertTrue(distribucio.isCambiosManualesRealizados());
        distribucio.setCambiosManualesRealizados(false);
        assertFalse(distribucio.isCambiosManualesRealizados());
    }

    @Test
    public void testEliminaProducte() {
        distribucio.afegeix_producte_colocat(producteColocat);
        distribucio.elimina_producte(producte.getNom());
        assertFalse(distribucio.getProductesColocats().contains(producteColocat));
    }

    @Test
    public void testIntercambiarProductes() {
        ProducteColocat producteColocat2 = new ProducteColocat(1, 5, new Producte("Producto B", "Marca2", 15.0, 3));
        distribucio.afegeix_producte_colocat(producteColocat);
        distribucio.afegeix_producte_colocat(producteColocat2);
        distribucio.intercambiar_productes(1, 2); // Use 1-based indices
        assertEquals(2, producteColocat.getPos());
        assertEquals(1, producteColocat2.getPos());
    }

    @Test
    public void testCanviaEstrategiaCalculo() {
        EstrategiaCalculo novaEstrategia = new EstrategiaTSP();
        distribucio.canvia_estrategia_calculo(novaEstrategia);
        assertEquals(novaEstrategia, distribucio.getEstrategiaCalculo());
    }
}
