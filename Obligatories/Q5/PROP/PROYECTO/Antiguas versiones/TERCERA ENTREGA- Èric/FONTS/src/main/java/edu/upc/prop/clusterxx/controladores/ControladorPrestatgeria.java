package edu.upc.prop.clusterxx.controladores;
import edu.upc.prop.clusterxx.clases_dominio.Distribucio;
import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;
import edu.upc.prop.clusterxx.clases_dominio.Producte;


import java.util.List;
import java.io.Serializable;

public class ControladorPrestatgeria implements Serializable {
    private Prestatgeria prestatgeria;

    public ControladorPrestatgeria(Prestatgeria prestatgeria) {
        this.prestatgeria = prestatgeria;
    }

    public String getNom() {
        return prestatgeria.getNom();
    }

    public void setNom(String nom) {
        prestatgeria.setNom(nom);
    }

    public String getNombreDeproductes() {
        return Integer.toString(prestatgeria.getAltura());
    }

    public Integer getAltura() {
        return prestatgeria.getAltura();
    }

    public void setAltura(int altura) {
        prestatgeria.setAltura(altura);
    }

    public Distribucio getDistribucion() {
        return prestatgeria.getDistribucion();
    }

    public void setDistribucion(Distribucio distribucion) {
        prestatgeria.setDistribucion(distribucion);
    }

    public Prestatgeria getPrestatgeria() {
        return prestatgeria;
    }


}
