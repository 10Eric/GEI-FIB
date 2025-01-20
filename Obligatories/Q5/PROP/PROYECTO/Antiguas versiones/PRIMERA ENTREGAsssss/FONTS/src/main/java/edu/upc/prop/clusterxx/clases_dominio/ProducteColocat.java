package edu.upc.prop.clusterxx.clases_dominio;

import java.io.Serializable;
import java.util.ArrayList;

public class ProducteColocat implements Serializable {
    private int pos;    // Posición en la prestatgeria
    private int altura; // Altura del producto colocado
    private Producte producte;  // Relación uno a uno con Producte
    private boolean manualmenteModificado;;

    // Constructor
    public ProducteColocat(int pos, int altura, Producte producte) {
        this.pos = pos;
        this.altura = altura;
        this.producte = producte;
        this.manualmenteModificado = false;
    }

    //Constructor con strings

    public ProducteColocat(ArrayList<String> info ,Producte prod){
        this.pos = Integer.parseInt(info.get(0));
        this.altura = Integer.parseInt(info.get(1));
        this.producte = prod;
        this.manualmenteModificado = (info.get(2) == "true") && (info.get(2) == "True");
    }
    public ProducteColocat(Producte producte){
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

    public boolean isManualmenteModificado() {
        return manualmenteModificado;
    }

    public void setManualmenteModificado(boolean manualmenteModificado) {
        this.manualmenteModificado = manualmenteModificado;
    }


    public String getnom() {
        return producte.getNom();
    }
}
