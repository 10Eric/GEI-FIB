package edu.upc.prop.clusterxx;
import java.util.Scanner;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ControladorPrestatgeria implements Serializable {
    private Prestatgeria prestatgeria;

    public ControladorPrestatgeria(Prestatgeria prestatgeria) {
        this.prestatgeria = prestatgeria;
    }

    public Producte obtenirProductePos(int pos) {
        return prestatgeria.obtenirProductePos(pos);
    }

    public boolean afegirProducteColocat(ProducteColocat producteColocat) {
        return prestatgeria.afegirProducteColocat(producteColocat);
    }

    public void imprimirDistribucion() {
        prestatgeria.imprimirDistribucion();
    }

    public void verDistribucionProductos() {
        prestatgeria.verDistribucionProductos();
    }

    public List<ProducteColocat> arrangeProductsBySimilarity(List<ProducteColocat> productes, boolean mantenerCambios) {
        return prestatgeria.arrangeProductsBySimilarity(productes, mantenerCambios);
    }

    public int calculateSimilarity(Producte p1, Producte p2) {
        return prestatgeria.calculateSimilarity(p1, p2);
    }

    public int getAltura() {
        return prestatgeria.getAltura();
    }

    public void setAltura(int altura) {
        prestatgeria.setAltura(altura);
    }

    public ArrayList<ProducteColocat> getProductesColocats() {
        return prestatgeria.getProductesColocats();
    }

    public void setProductesColocats(ArrayList<ProducteColocat> productesColocats) {
        prestatgeria.setProductesColocats(productesColocats);
    }

    public boolean isCambiosManualesRealizados() {
        return prestatgeria.isCambiosManualesRealizados();
    }

    public void setCambiosManualesRealizados(boolean cambiosManualesRealizados) {
        prestatgeria.setCambiosManualesRealizados(cambiosManualesRealizados);
    }
}
