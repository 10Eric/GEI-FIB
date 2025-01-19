package edu.upc.prop.clusterxx;
public class Similitud {
    private Producte producte;  // Relación con Producte
    private int valor;

    // Constructor
    public Similitud(Producte producte, int valor) {
        this.producte = producte;
        this.valor = valor;
    }

    // Getters y setters
    public Producte getProducte() {
        return producte;
    }

    public void setProducte(Producte producte) {
        this.producte = producte;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
