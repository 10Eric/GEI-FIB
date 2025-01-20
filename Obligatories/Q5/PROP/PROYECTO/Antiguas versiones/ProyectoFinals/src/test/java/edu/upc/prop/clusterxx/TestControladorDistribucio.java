package edu.upc.prop.clusterxx;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.List;

public class TestControladorDistribucio {

  private ControladorDistribucio ctrlDistribucio;
  private Producte p1;
  private Producte p2;

  @Before
  public void setUp() {
    Distribucio distribucio = new Distribucio("Distribuidor 1");
    ctrlDistribucio = new ControladorDistribucio(distribucio);
    p1 = new Producte("Marca1", "Producto A", 10.0f, "5", "001");
    p2 = new Producte("Marca2", "Producto B", 15.0f, "3", "002");
  }

  @Test
  public void testAgregarProducte() {
    ctrlDistribucio.agregarProducte(p1);
    assertTrue(ctrlDistribucio.getProductes().contains(p1));
  }

  @Test
  public void testEliminarProducte() {
    ctrlDistribucio.agregarProducte(p1);
    ctrlDistribucio.eliminarProducte("001");
    assertTrue(!ctrlDistribucio.getProductes().contains(p1));
  }

  @Test
  public void testLlistarProductes() {
    // Capturar la salida del método llistarProductes
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    // Caso cuando no hay productos
    ctrlDistribucio.llistarProductes();
    String expectedOutputEmpty = "No hay productos en la distribución.";
    assertEquals(expectedOutputEmpty, outContent.toString().trim());

    // Limpiar el contenido capturado
    outContent.reset();

    // Agregar productos y probar de nuevo
    ctrlDistribucio.agregarProducte(p1);
    ctrlDistribucio.agregarProducte(p2);
    ctrlDistribucio.llistarProductes();

    // Formatear manualmente la salida esperada
    String expectedOutput = p1.getMarca() + " " + p1.getNom() + " " + p1.getPreu() + " " + p1.getQuantitat() + " " + p1.getId() + "\n" +
            p2.getMarca() + " " + p2.getNom() + " " + p2.getPreu() + " " + p2.getQuantitat() + " " + p2.getId();
    assertEquals(expectedOutput, outContent.toString().trim());

    // Restaurar la salida estándar
    System.setOut(System.out);
  }

  @Test
  public void testGetProductes() {
    // Add products to the distribution
    ctrlDistribucio.agregarProducte(p1);
    ctrlDistribucio.agregarProducte(p2);

    // Get the list of products
    List<Producte> productes = ctrlDistribucio.getProductes();

    // Verify the list contains the added products
    assertTrue(productes.contains(p1));
    assertTrue(productes.contains(p2));
    assertEquals(2, productes.size());
  }
}