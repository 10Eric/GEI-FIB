package edu.upc.prop.clusterxx;

import edu.upc.prop.clusterxx.clases_dominio.Producte;
import edu.upc.prop.clusterxx.clases_dominio.ProducteColocat;
import edu.upc.prop.clusterxx.estrategias_calculo.EstrategiaTSP;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestEstrategiaTSP {
    EstrategiaTSP estrategiaTSP = new EstrategiaTSP();
    List<ProducteColocat> productes = new ArrayList<>();

    @Before
    public void setUp() {
        //TEST 9 NODOS QUE NOS DAN: Minimo 246
        // Crear los productos
        Producte p1 = new Producte("Leche");
        Producte p2 = new Producte("Pan");
        Producte p3 = new Producte("Agua");
        Producte p4 = new Producte("Aceite");
        Producte p5 = new Producte("Arroz");
        Producte p6 = new Producte("Huevos");
        Producte p7 = new Producte("Fruta");
        Producte p8 = new Producte("Verdura");
        Producte p9 = new Producte("Carne");

        // Establecer similitudes entre los productos según la matriz
        p1.setSimilitud(p2, 29);
        p1.setSimilitud(p3, 82);
        p1.setSimilitud(p4, 46);
        p1.setSimilitud(p5, 68);
        p1.setSimilitud(p6, 52);
        p1.setSimilitud(p7, 72);
        p1.setSimilitud(p8, 42);
        p1.setSimilitud(p9, 51);

        p2.setSimilitud(p1, 29);
        p2.setSimilitud(p3, 55);
        p2.setSimilitud(p4, 46);
        p2.setSimilitud(p5, 42);
        p2.setSimilitud(p6, 43);
        p2.setSimilitud(p7, 43);
        p2.setSimilitud(p8, 23);
        p2.setSimilitud(p9, 23);

        p3.setSimilitud(p1, 82);
        p3.setSimilitud(p2, 55);
        p3.setSimilitud(p4, 68);
        p3.setSimilitud(p5, 46);
        p3.setSimilitud(p6, 55);
        p3.setSimilitud(p7, 23);
        p3.setSimilitud(p8, 43);
        p3.setSimilitud(p9, 41);

        p4.setSimilitud(p1, 46);
        p4.setSimilitud(p2, 46);
        p4.setSimilitud(p3, 68);
        p4.setSimilitud(p5, 82);
        p4.setSimilitud(p6, 15);
        p4.setSimilitud(p7, 72);
        p4.setSimilitud(p8, 31);
        p4.setSimilitud(p9, 62);

        p5.setSimilitud(p1, 68);
        p5.setSimilitud(p2, 42);
        p5.setSimilitud(p3, 46);
        p5.setSimilitud(p4, 82);
        p5.setSimilitud(p6, 74);
        p5.setSimilitud(p7, 23);
        p5.setSimilitud(p8, 52);
        p5.setSimilitud(p9, 21);

        p6.setSimilitud(p1, 52);
        p6.setSimilitud(p2, 43);
        p6.setSimilitud(p3, 55);
        p6.setSimilitud(p4, 15);
        p6.setSimilitud(p5, 74);
        p6.setSimilitud(p7, 61);
        p6.setSimilitud(p8, 23);
        p6.setSimilitud(p9, 55);

        p7.setSimilitud(p1, 72);
        p7.setSimilitud(p2, 43);
        p7.setSimilitud(p3, 23);
        p7.setSimilitud(p4, 72);
        p7.setSimilitud(p5, 23);
        p7.setSimilitud(p6, 61);
        p7.setSimilitud(p8, 42);
        p7.setSimilitud(p9, 23);

        p8.setSimilitud(p1, 42);
        p8.setSimilitud(p2, 23);
        p8.setSimilitud(p3, 43);
        p8.setSimilitud(p4, 31);
        p8.setSimilitud(p5, 52);
        p8.setSimilitud(p6, 23);
        p8.setSimilitud(p7, 42);
        p8.setSimilitud(p9, 33);

        p9.setSimilitud(p1, 51);
        p9.setSimilitud(p2, 23);
        p9.setSimilitud(p3, 41);
        p9.setSimilitud(p4, 62);
        p9.setSimilitud(p5, 21);
        p9.setSimilitud(p6, 55);
        p9.setSimilitud(p7, 23);
        p9.setSimilitud(p8, 33);

        // Crear productos colocados
        ProducteColocat pc1 = new ProducteColocat(p1);
        ProducteColocat pc2 = new ProducteColocat(p2);
        ProducteColocat pc3 = new ProducteColocat(p3);
        ProducteColocat pc4 = new ProducteColocat(p4);
        ProducteColocat pc5 = new ProducteColocat(p5);
        ProducteColocat pc6 = new ProducteColocat(p6);
        ProducteColocat pc7 = new ProducteColocat(p7);
        ProducteColocat pc8 = new ProducteColocat(p8);
        ProducteColocat pc9 = new ProducteColocat(p9);

        // Añadirlos a la lista
        productes.add(pc1);
        productes.add(pc2);
        productes.add(pc3);
        productes.add(pc4);
        productes.add(pc5);
        productes.add(pc6);
        productes.add(pc7);
        productes.add(pc8);
        productes.add(pc9);
    }

    //HAY QUE HACER TESTS PARAMETRICOS!!
    @Test public void testCalculo(){
        List<ProducteColocat> resultat = estrategiaTSP.arrangeProductsBySimilarity(productes,2);
        //sumem les similituds que haurien de donar
        int similitud_total = 0;
//atun,aceite,pan,cafe,leche,agua,te,azucar,chocolate,huevos
        for (int i = 0; i < resultat.size()-1; i++){
           similitud_total += resultat.get(i).getProducte().getSimilitud(resultat.get(i+1).getProducte());

        }
        similitud_total += resultat.getLast().getProducte().getSimilitud(resultat.getFirst().getProducte());
        //ahora mismo el EstrategiaTSP da una similitud de 131 :(, probamos con el Backtracking para ver las diferencias
        assertEquals(similitud_total,496);


    }



}
