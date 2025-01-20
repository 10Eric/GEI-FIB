package edu.upc.prop.clusterxx.clases_dominio;

import edu.upc.prop.clusterxx.controladores.ControladorPrestatgeria;

import java.util.ArrayList;
import java.io.Serializable;

public class Prestatgeria implements Serializable {
    private String nom;
    private int altura;
    private Distribucio distribucion;

    // Constructor
    public Prestatgeria(String nom, int altura) {
        this.nom = nom;
        this.altura = altura;
    }

    public Prestatgeria(String nom, int altura, Distribucio distribucion) {
        this.nom = nom;
        this.altura = altura;
        this.distribucion = distribucion;
    }

    public int getAltura(){
        return altura;
    }

    public void setAltura(int altura){
        this.altura = altura;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Distribucio getDistribucion() {
        return distribucion;
    }

    public void setDistribucion(Distribucio distribucion) {
        this.distribucion = distribucion;
    }

    public void eliminaDistribucio(Distribucio distribucio) {
        distribucion= null;}
}