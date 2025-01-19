package edu.upc.prop.clusterxx;
import java.util.ArrayList;
import java.io.Serializable;

public class Prestatgeria implements Serializable {
    private int numeroDePrestages;
    private int altura;
    private ArrayList<ProducteColocat> productesColocats;  // Relación uno a muchos con ProducteColocat

    // Constructor
    public Prestatgeria(int numeroDePrestages, int altura) {
        this.numeroDePrestages = numeroDePrestages;
        this.altura = altura;
        this.productesColocats = new ArrayList<>();
    }

    // Método para obtener un producto por su posición
    public Producte obtenirProductePos(int pos) {
        for (ProducteColocat producteColocat : productesColocats) {
            if (producteColocat.getPos() == pos) {
                return producteColocat.getProducte();
            }
        }
        return null; // Retorna null si no se encuentra un producto en esa posición
    }

    // Método para agregar un producto colocado, con validación de altura
    public boolean afegirProducteColocat(ProducteColocat producteColocat) {
        if (producteColocat.esValidaAltura(this.altura)) {
            productesColocats.add(producteColocat);
            return true;
        }
        return false;  // No se agrega si la altura es inválida
    }

    // Getters y setters
    public int getNumeroDePrestages() {
        return numeroDePrestages;
    }

    public void setNumeroDePrestages(int numeroDePrestages) {
        this.numeroDePrestages = numeroDePrestages;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public ArrayList<ProducteColocat> getProductesColocats() {
        return productesColocats;
    }

    public void setProductesColocats(ArrayList<ProducteColocat> productesColocats) {
        this.productesColocats = productesColocats;
    }
}
