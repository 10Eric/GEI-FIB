package edu.upc.prop.clusterxx.controladores;
import edu.upc.prop.clusterxx.clases_dominio.Perfil;
import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;
import edu.upc.prop.clusterxx.controladores_persistencia.ControladorPersistenciaPerfil;

import java.util.List;
import java.io.*;
import java.util.Map;
import java.util.HashMap;


public class ControladorPerfil implements Serializable {
    private Perfil perfil;
    private static final ControladorPersistenciaPerfil persistencia = new ControladorPersistenciaPerfil();

    public ControladorPerfil(){}

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

    public void setPrestatgerias(List<Prestatgeria> prestatgerias) {
        perfil.setPrestatgeria(prestatgerias);
    }

    //crea un perfil y lo enlaza al controlador
    public void creaPerfil(String username, String password){
        Perfil aux = new Perfil(username,password);
        this.perfil = aux;
    }

    public void añadirPrestatgeriaCtrl(ControladorPrestatgeria prest) {
        perfil.añadirPrestatgeria(prest.getPrestatgeria());
    }

    public void guardarPerfil(String filePath) throws IOException {
        persistencia.guardarPerfil(perfil, filePath);
    }

    public Map<String, ControladorPerfil> cargarPerfil(String filePath) throws IOException, ClassNotFoundException {
        Map<String, Perfil> perfilesCargados = persistencia.cargarPerfil(filePath);
        Map<String, ControladorPerfil> controladores = new HashMap<>();
        for (Map.Entry<String, Perfil> entry : perfilesCargados.entrySet()) {
            controladores.put(entry.getKey(), new ControladorPerfil(entry.getValue()));
        }
        return controladores;
    }

    public void limpiarDatos() throws IOException {
        persistencia.limpiarDatos();
    }
}
