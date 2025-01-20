package edu.upc.prop.clusterxx;

import java.util.List;

public class EstrategiaOneToOne implements EstrategiaCalculo{
    @Override
    public List<ProducteColocat> arrangeProductsBySimilarity(List<ProducteColocat> productes, int altura, boolean mantenerCambios){
        return productes;
    }

}
