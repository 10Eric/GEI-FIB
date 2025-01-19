package edu.upc.prop.clusterxx.controladores;
import edu.upc.prop.clusterxx.clases_dominio.Producte;

import java.io.Serializable;

public class ControladorProducte implements Serializable {
    private Producte producte;

    public ControladorProducte(Producte producte) {
        this.producte = producte;
    }

    public String getNom() {
        return producte.getNom();
    }

    public void setNom(String nom) {
        producte.setNom(nom);
    }

    public String getMarca() {
        return producte.getMarca();
    }

    public void setMarca(String marca) {
        producte.setMarca(marca);
    }

    public double getPreu() {
        return producte.getPreu();
    }

    public void setPreu(double preu) {
        producte.setPreu(preu);
    }

    public int getQuantitat() {
        return producte.getQuantitat();
    }

    public void setQuantitat(int quantitat) {
        producte.setQuantitat(quantitat);
    }

    public int getSimilitud(Producte other) {
        return producte.getSimilitud(other);
    }

    public void setSimilitud(Producte other, int similitud) {
        producte.setSimilitud(other, similitud);
    }
}
