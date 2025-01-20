package edu.upc.prop.clusterxx;
import java.util.ArrayList;

public class Distribucio {
    private String nom;
    private ArrayList<Producte> productes;

    // Constructor
    public Distribucio(String nom) {
        this.nom = nom;
        this.productes = new ArrayList<>();
    }

    // Método para agregar un producto
    public void agregaProducte(Producte producte) {
        productes.add(producte);
    }

    // Método para eliminar un producto
    public void eliminaProducte(Producte producte) {
        productes.remove(producte);
    }

    // Método para obtener la lista de productos
    public ArrayList<Producte> getProductes() {
        return productes;
    }

    public String getNom() {
        return nom;
    }
}
