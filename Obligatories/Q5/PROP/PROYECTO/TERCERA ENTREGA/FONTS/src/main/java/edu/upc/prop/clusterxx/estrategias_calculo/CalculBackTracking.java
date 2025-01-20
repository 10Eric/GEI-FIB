package edu.upc.prop.clusterxx.estrategias_calculo;

import edu.upc.prop.clusterxx.clases_dominio.Producte;
import edu.upc.prop.clusterxx.clases_dominio.ProducteColocat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.Serializable;

/**
 * Esta clase implementa la estrategia de cálculo utilizando el algoritmo de BackTracking.
 */
public class CalculBackTracking implements EstrategiaCalculo,Serializable{
    //mantenerCambiosSiempreEsFalso
    /**
     * Organiza los productos por similitud utilizando BackTracking.
     *
     * @param productes la lista de productos a organizar
     * @param altura la altura máxima permitida
     * @return la lista de productos organizados
     */
    public List<ProducteColocat> arrangeProductsBySimilarity(List<ProducteColocat> productes, int altura) {
        int medida;
        if (productes.size() % altura == 0) {
            medida = productes.size() / altura;
        } else {
            medida = productes.size() / altura + 1;
        }
        ProducteColocat[] arranged = new ProducteColocat[productes.size()];

        //Parte del codigo que no se usa
        int[] posicionesFijas = new int[productes.size()];


        // Generar una lista de productos no modificados
        ProducteColocat[] noModificados = new ProducteColocat[productes.size()];
        int idxNoModificados = 0;
        for (ProducteColocat producte : productes) {
            if (!producte.isManualmenteModificado()) {
                noModificados[idxNoModificados++] = producte;
            }
        }

        // Generar permutaciones de productos no modificados manualmente
        List<ProducteColocat[]> permutaciones = new ArrayList<>();
        generarPermutaciones(noModificados, 0, idxNoModificados, permutaciones);

        int maxSimilitud = -1;
        ProducteColocat[] mejorDisposicion = new ProducteColocat[arranged.length];

        for (ProducteColocat[] disposicion : permutaciones) {
            int pos = 0;
            ProducteColocat[] tempArranged = arranged.clone();

            // Colocar productos no fijos en posiciones vacías de `tempArranged`
            for (int i = 0; i < tempArranged.length; i++) {
                if (posicionesFijas[i] == 0) {
                    tempArranged[i] = disposicion[pos++];
                }
            }

            int similitudTotal = 0;
            for (int i = 0; i < tempArranged.length; i++) {
                Producte p1 = tempArranged[i].getProducte();
                Producte pLeft = tempArranged[(i - 1 + tempArranged.length) % tempArranged.length].getProducte();
                Producte pRight = tempArranged[(i + 1) % tempArranged.length].getProducte();
                similitudTotal += calculateSimilarity(p1, pLeft) + calculateSimilarity(p1, pRight);
            }

            // Si la disposición actual tiene mayor similitud, se guarda como la mejor
            if (similitudTotal > maxSimilitud) {
                maxSimilitud = similitudTotal;
                System.arraycopy(tempArranged, 0, mejorDisposicion, 0, arranged.length);
            }
        }

        // Ajustar posiciones y alturas en la disposición final
        for (int i = 0; i < mejorDisposicion.length; i++) {
            if (mejorDisposicion[i] != null) {
                mejorDisposicion[i].setPos(i + 1);
                mejorDisposicion[i].setAltura((i / medida) + 1);
            }
        }

        // Convertir a una lista final compacta
        return Arrays.asList(mejorDisposicion);
    }

    /**
     * Genera permutaciones de los productos.
     *
     * @param productos los productos a permutar
     * @param index el índice actual
     * @param size el tamaño de la lista de productos
     * @param permutaciones la lista donde se almacenan las permutaciones
     */
    public void generarPermutaciones(ProducteColocat[] productos, int index, int size, List<ProducteColocat[]> permutaciones) {
        if (index == size - 1) {
            permutaciones.add(productos.clone());
            return;
        }

        for (int i = index; i < size; i++) {
            ProducteColocat temp = productos[i];
            productos[i] = productos[index];
            productos[index] = temp;

            generarPermutaciones(productos, index + 1, size, permutaciones);

            // Revertir intercambio
            temp = productos[i];
            productos[i] = productos[index];
            productos[index] = temp;
        }
    }

    /**
     * Calcula la similitud entre dos productos.
     *
     * @param p1 el primer producto
     * @param p2 el segundo producto
     * @return la similitud entre los productos
     */
    public int calculateSimilarity(Producte p1, Producte p2) {
        if (p1 == null || p2 == null) return 0;
        return p1.getSimilitud(p2);
    }
}
