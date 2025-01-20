package edu.upc.prop.clusterxx;
import java.util.Scanner;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ControladorPerfil {
    private Perfil perfil;

    public ControladorPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getUsuari() {
        return perfil.getUsuari();
    }

    public void setUsuari(String usuari) {
        perfil.setUsuari(usuari);
    }

    public String getContrasenya() {
        return perfil.getContrasenya();
    }

    public void setContrasenya(String contrasenya) {
        perfil.setContrasenya(contrasenya);
    }

    public void añadirPrestatgeria(Prestatgeria prestatgeria) {
        perfil.añadirPrestatgeria(prestatgeria);
    }

    public void eliminarPrestatgeria(Prestatgeria prestatgerias) {
        perfil.eliminarPrestatgeria(prestatgerias);
    }

    public List<Prestatgeria> getPrestatgerias() {
        return perfil.getPrestatgeria();
    }
}
