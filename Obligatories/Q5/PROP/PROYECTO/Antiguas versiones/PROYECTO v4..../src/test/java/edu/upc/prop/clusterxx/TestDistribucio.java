package edu.upc.prop.clusterxx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


import edu.upc.prop.clusterxx.clases_dominio.Distribucio;
import edu.upc.prop.clusterxx.clases_dominio.Producte;
import edu.upc.prop.clusterxx.clases_dominio.ProducteColocat;
import edu.upc.prop.clusterxx.estrategias_calculo.EstrategiaCalculo;
import edu.upc.prop.clusterxx.estrategias_calculo.EstrategiaOneToOne;
import edu.upc.prop.clusterxx.estrategias_calculo.EstrategiaTSP;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestDistribucio {

    private Distribucio distribucio;
    private Producte producte;
    private ProducteColocat producteColocat;
    private ArrayList<ProducteColocat> productesColocats;

    @Before
    public void setUp() {
        distribucio = new Distribucio("Distribucion");
        producte = new Producte("Producto A", "Marca1", 10.0, 5);
        producteColocat = new ProducteColocat(0, 1, producte);
        productesColocats = new ArrayList<>();
        productesColocats.add(producteColocat);
    }

    @Test
    public void testConstructor() {
        assertNotNull(distribucio);
        assertEquals(0, distribucio.getNumero_prestatges());
        assertTrue(distribucio.getProductesColocats().isEmpty());
        assertFalse(distribucio.isCambiosManualesRealizados());
    }

    @Test
    public void testGetNumero_prestatges() {
        assertEquals(0, distribucio.getNumero_prestatges());
        distribucio.afegeix_producte_colocat(producteColocat);
        assertEquals(1, distribucio.getNumero_prestatges());
    }

    @Test
    public void testObtenirProductePos() {
        distribucio.afegeix_producte_colocat(producteColocat);
        assertEquals(producte, distribucio.obtenirProductePos(1));
        assertNull(distribucio.obtenirProductePos(2));
        assertNull(distribucio.obtenirProductePos(-1));
        assertNull(distribucio.obtenirProductePos(100));
    }

    @Test
    public void testAfegirProducteColocat() {
        distribucio.afegeix_producte_colocat(producteColocat);
        assertEquals(1, distribucio.getProductesColocats().size());
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
        distribucio.elimina_producte("Producto Inexistente");
        assertFalse(distribucio.getProductesColocats().contains(producteColocat));
    }

    @Test
    public void testIntercambiarProductes() {
        ProducteColocat producteColocat2 = new ProducteColocat(1, 1, new Producte("Producto B", "Marca2", 15.0, 3));
        distribucio.afegeix_producte_colocat(producteColocat);
        distribucio.afegeix_producte_colocat(producteColocat2);
        distribucio.intercambiar_productes(0, 1);
        assertEquals(1, producteColocat.getPos());
        assertEquals(0, producteColocat2.getPos());
        distribucio.intercambiar_productes(-1, 1);
        distribucio.intercambiar_productes(1, 100);
        assertEquals(1, producteColocat.getPos());
        assertEquals(0, producteColocat2.getPos());
    }

    @Test
    public void testGetIdentificador_estrategia() {
        assertEquals(1, distribucio.getIdentificador_estrategia());
        distribucio.canvia_estrategia_calculo(new EstrategiaTSP(), 2);
        assertEquals(2, distribucio.getIdentificador_estrategia());
    }

    @Test
    public void testCanviaEstrategiaCalculo() {
        EstrategiaCalculo novaEstrategia = new EstrategiaTSP();
        distribucio.canvia_estrategia_calculo(novaEstrategia, 2);
        assertEquals(novaEstrategia, distribucio.getEstrategiaCalculo());
    }

    @Test
    public void testCalculaDistribucio() {
        distribucio.canvia_estrategia_calculo(new EstrategiaOneToOne(), 3);
        ArrayList<ProducteColocat> productes_abans_estrategia = distribucio.getProductesColocats();
        distribucio.calcula_distribucio(2); // la estrategia one to one deberia dejar todo igual si funciona correctamente
        ArrayList<ProducteColocat> productes_despres_estrategia = distribucio.getProductesColocats();
        assertEquals(productes_abans_estrategia, productes_despres_estrategia);
    }
}