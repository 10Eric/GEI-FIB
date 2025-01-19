package edu.upc.prop.clusterxx.estrategias_calculo;

import edu.upc.prop.clusterxx.clases_dominio.ProducteColocat;

import java.util.List;

/**
 * Esta interfaz define el método para organizar productos por similitud.
 */
public interface EstrategiaCalculo {

    /**
     * Organiza los productos por similitud.
     *
     * @param productes la lista de productos a organizar
     * @param altura la altura máxima permitida
     * @return la lista de productos organizados
     */
    public List<ProducteColocat> arrangeProductsBySimilarity(List<ProducteColocat> productes, int altura);

    }
