package edu.upc.prop.clusterxx;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

public class Producte implements Serializable {
    private String nom;
    private String marca;
    private double preu;
    private int quantitat;
    private Map<Producte, Integer> similituds;

    // Constructor
    public Producte(String nom, String marca, double preu, int quantitat) {
        this.nom = nom;
        this.marca = marca;
        this.preu = preu;
        this.quantitat = quantitat;
        this.similituds = new HashMap<>();
    }

    // Getters and setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    // Method to get similarity
    public int getSimilitud(Producte other) {
        return similituds.getOrDefault(other, 0);
    }

    // Method to set similarity
    public void setSimilitud(Producte other, int similitud) {
        if (similitud < 0 || similitud > 100) {
            throw new IllegalArgumentException("La similitud debe estar entre 0 y 100.");
        }
        similituds.put(other, similitud);
        other.similituds.put(this, similitud); // Ensure bidirectional similarity
    }

    @Override
    public String toString() {
        return marca + " " + nom + " " + preu + " " + quantitat;
    }
}
