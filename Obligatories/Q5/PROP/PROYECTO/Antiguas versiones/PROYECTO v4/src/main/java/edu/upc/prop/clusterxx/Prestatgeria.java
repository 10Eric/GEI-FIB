package edu.upc.prop.clusterxx;
import java.util.ArrayList;
import java.io.Serializable;

public class Prestatgeria implements Serializable {
    private String nom;
    private int altura;
    private ArrayList<Producte> productes;
    private Distribucio distribucion;

    // Constructor
    public Prestatgeria(String nom, Distribucio distribucion) {
        this.nom = nom;
        this.productes = new ArrayList<>();
        this.distribucion = distribucion;
    }

    public Prestatgeria(String nom, int altura) {
        this.nom = nom;
        this.altura = altura;
        this.productes = new ArrayList<>();
    }

    public Prestatgeria(String nom, int altura, Distribucio distribucion) {
        this.nom = nom;
        this.altura = altura;
        this.productes = new ArrayList<>();
        this.distribucion = distribucion;
    }

    public int getAltura(){
        return altura;
    }

    public void setAltura(int altura){
        this.altura = altura;
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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Producte getProductePorNom(String nom) {
        for (Producte producte : productes) {
            if (producte.getNom().equals(nom)) {
                return producte;
            }
        }
        return null;
    }

    public Distribucio getDistribucion() {
        return distribucion;
    }

    public void setDistribucion(Distribucio distribucion) {
        this.distribucion = distribucion;
    }
}
