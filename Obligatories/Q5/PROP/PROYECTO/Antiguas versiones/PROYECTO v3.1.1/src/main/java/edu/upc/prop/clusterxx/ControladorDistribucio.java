package edu.upc.prop.clusterxx;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.Serializable;

public class ControladorDistribucio implements Serializable {
    private Distribucio distribucio;

    public ControladorDistribucio(Distribucio distribucio) {
        this.distribucio = distribucio;
    }

    public void agregarProducte(Producte producte) {
        distribucio.agregaProducte(producte);
    }

    public void eliminarProducte(Producte producte) {
        distribucio.eliminaProducte(producte);
    }

    public Producte getProductePorNom(String nom) {
        return distribucio.getProductePorNom(nom);
    }

    public String getNom() {
        return distribucio.getNom();
    }

    public void setNom(String nom) {
        distribucio.setNom(nom);
    }

    public Prestatgeria getPrestatge() {
        return distribucio.getPrestatge();
    }

    public void setPrestatge(Prestatgeria prestatgeria) {
        distribucio.setPrestatge(prestatgeria);
    }

    public List<Producte> getProductes() {
        return distribucio.getProductes();
    }
}
