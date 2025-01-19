package edu.upc.prop.clusterxx.controladores;
import edu.upc.prop.clusterxx.clases_dominio.Perfil;
import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;
import edu.upc.prop.clusterxx.controladores_persistencia.ControladorPersistenciaPerfil;

import java.util.List;
import java.io.*;
import java.util.Set;


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

    public void guardarPerfil() throws IOException {
        persistencia.guardarPerfil(perfil);
    }

    public void cargarPerfil(String username) throws IOException, ClassNotFoundException {
        perfil = persistencia.getPerfil(username);
    }

    public static void eliminarPerfil(String username) throws IOException {
        persistencia.deletePerfil(username);
    }

    public static Set<String> cargarTodosPerfiles() throws IOException, ClassNotFoundException {
        return persistencia.cargarTodosPerfiles();
    }

    public void limpiarDatos() throws IOException {
        persistencia.limpiarDatos();
    }

    public void buscarPerfil(String username) throws IOException, ClassNotFoundException {
        perfil =  persistencia.getPerfil(username);
    }

    public Perfil getPerfil() {return perfil;}
}
