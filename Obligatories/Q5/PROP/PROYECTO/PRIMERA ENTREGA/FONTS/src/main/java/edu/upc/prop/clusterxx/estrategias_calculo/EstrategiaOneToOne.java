package edu.upc.prop.clusterxx.estrategias_calculo;

import edu.upc.prop.clusterxx.clases_dominio.ProducteColocat;

import java.util.List;

public class EstrategiaOneToOne implements EstrategiaCalculo {
    @Override
    public List<ProducteColocat> arrangeProductsBySimilarity(List<ProducteColocat> productes, int altura){
        return productes;
    }

}
