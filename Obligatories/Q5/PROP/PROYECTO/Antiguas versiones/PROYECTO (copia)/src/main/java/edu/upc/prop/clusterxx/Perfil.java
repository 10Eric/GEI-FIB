package edu.upc.prop.clusterxx;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Perfil implements Serializable {
    private String usuari;
    private String contrasenya;
    private List<Distribucio> distribucions;

    // Constructor
    public Perfil(String usuari, String contrasenya) {
        this.usuari = usuari;
        this.contrasenya = contrasenya;
        this.distribucions = new ArrayList<>();
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

    public List<Distribucio> getDistribucions() {
        return distribucions;
    }

    public void añadirDistribucion(Distribucio distribucio) {
        distribucions.add(distribucio);
    }

    public void eliminarDistribucion(Distribucio distribucio) {
        distribucions.remove(distribucio);
    }
}
