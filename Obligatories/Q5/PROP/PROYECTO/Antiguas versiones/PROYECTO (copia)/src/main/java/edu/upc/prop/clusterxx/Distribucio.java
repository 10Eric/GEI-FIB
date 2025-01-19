package edu.upc.prop.clusterxx;
import java.util.ArrayList;
import java.io.Serializable;

public class Distribucio implements Serializable {
    private String nom;
    private ArrayList<Producte> productes;
    private Prestatgeria prestatge;

    // Constructor
    public Distribucio(String nom, Prestatgeria prestatge) {
        this.nom = nom;
        this.productes = new ArrayList<>();
        this.prestatge = prestatge;
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

    public Producte getProductePorNom(String nom) {
        for (Producte producte : productes) {
            if (producte.getNom().equals(nom)) {
                return producte;
            }
        }
        return null;
    }

    public Prestatgeria getPrestatge() {
        return prestatge;
    }

    public void setPrestatge(Prestatgeria prestatge) {
        this.prestatge = prestatge;
    }
}
