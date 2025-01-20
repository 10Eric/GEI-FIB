package edu.upc.prop.clusterxx;

import java.util.List;

public interface EstrategiaCalculo {

    public List<ProducteColocat> arrangeProductsBySimilarity(List<ProducteColocat> productes, int altura, boolean mantenerCambios);

    }
