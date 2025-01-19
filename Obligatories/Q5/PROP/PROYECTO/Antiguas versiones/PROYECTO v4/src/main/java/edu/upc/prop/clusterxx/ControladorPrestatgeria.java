package edu.upc.prop.clusterxx;
import java.util.List;
import java.io.Serializable;

public class ControladorPrestatgeria implements Serializable {
    private Prestatgeria prestatgeria;

    public ControladorPrestatgeria(Prestatgeria prestatgeria) {
        this.prestatgeria = prestatgeria;
    }

    public void agregarProducte(Producte producte) {
        prestatgeria.agregaProducte(producte);
    }

    public void eliminarProducte(Producte producte) {
        prestatgeria.eliminaProducte(producte);
    }

    public Producte getProductePorNom(String nom) {
        return prestatgeria.getProductePorNom(nom);
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

    public List<Producte> getProductes() {
        return prestatgeria.getProductes();
    }
}
