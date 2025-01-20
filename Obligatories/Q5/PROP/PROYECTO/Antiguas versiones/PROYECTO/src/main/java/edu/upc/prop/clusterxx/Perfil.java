package edu.upc.prop.clusterxx;
public class Perfil {
    private String usuari;
    private String contrasenya;
    private Distribucio distribucio;  // Relación uno a uno

    // Constructor
    public Perfil(String usuari, String contrasenya, Distribucio distribucio) {
        this.usuari = usuari;
        this.contrasenya = contrasenya;
        this.distribucio = distribucio;
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

    public Distribucio getDistribucio() {
        return distribucio;
    }

    public void setDistribucio(Distribucio distribucio) {
        this.distribucio = distribucio;
    }
}
