package edu.upc.prop.clusterxx;
public class ProducteColocat {
    private int pos;    // Posición en la prestatgeria
    private int altura; // Altura del producto colocado
    private Producte producte;  // Relación uno a uno con Producte

    // Constructor
    public ProducteColocat(int pos, int altura, Producte producte) {
        this.pos = pos;
        this.altura = altura;
        this.producte = producte;
    }

    // Método para verificar si la altura es válida en relación con la prestatgeria
    public boolean esValidaAltura(int alturaPrestatgeria) {
        return this.altura <= alturaPrestatgeria;  // La altura debe ser menor o igual que la altura de la prestatgeria
    }

    // Getters y setters
    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public Producte getProducte() {
        return producte;
    }

    public void setProducte(Producte producte) {
        this.producte = producte;
    }
}
