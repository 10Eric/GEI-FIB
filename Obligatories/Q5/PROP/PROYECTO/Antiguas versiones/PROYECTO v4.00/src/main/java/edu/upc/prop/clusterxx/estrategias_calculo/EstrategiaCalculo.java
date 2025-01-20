package edu.upc.prop.clusterxx.estrategias_calculo;

import edu.upc.prop.clusterxx.clases_dominio.ProducteColocat;

import java.util.List;

public interface EstrategiaCalculo {

    public List<ProducteColocat> arrangeProductsBySimilarity(List<ProducteColocat> productes, int altura, boolean mantenerCambios);

    }
