package edu.upc.prop.clusterxx.estrategias_calculo;

import edu.upc.prop.clusterxx.clases_dominio.ProducteColocat;

import java.util.List;
/**
 * Esta clase implementa una estrategia de cálculo simple que no modifica la lista de productos.
 */
public class EstrategiaOneToOne implements EstrategiaCalculo {
    /**
     * Organiza los productos por similitud sin realizar cambios.
     *
     * @param productes la lista de productos a organizar
     * @param altura la altura máxima permitida
     * @return la lista de productos sin cambios
     */
    @Override
    public List<ProducteColocat> arrangeProductsBySimilarity(List<ProducteColocat> productes, int altura){
        return productes;
    }

}
