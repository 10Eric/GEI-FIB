package edu.upc.prop.clusterxx.clases_dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Perfil implements Serializable {
    private String usuari;
    private String contrasenya;
    private List<Prestatgeria> prestatgeria;

    // Constructor
    public Perfil(String usuari, String contrasenya) {
        this.usuari = usuari;
        this.contrasenya = contrasenya;
        this.prestatgeria = new ArrayList<>();
    }

    // Getters y setters
    public String getUsuari() {
        return usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public List<Prestatgeria> getPrestatgeria() {
        return prestatgeria;
    }

    public void setPrestatgeria(List<Prestatgeria> Prestatgerias) {
        this.prestatgeria =  Prestatgerias;
    }

    public void añadirPrestatgeria(Prestatgeria prestatgerias) {
        prestatgeria.add(prestatgerias);
    }

    public void eliminarPrestatgeria(Prestatgeria prestatgerias) {
        prestatgeria.remove(prestatgerias);
    }
}
