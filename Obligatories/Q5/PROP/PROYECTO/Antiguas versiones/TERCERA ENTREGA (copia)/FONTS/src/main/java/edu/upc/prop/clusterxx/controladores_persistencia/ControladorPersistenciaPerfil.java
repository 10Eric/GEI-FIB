package edu.upc.prop.clusterxx.controladores_persistencia;

import edu.upc.prop.clusterxx.clases_dominio.Perfil;
import edu.upc.prop.clusterxx.controladores.ControladorPerfil;

import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class ControladorPersistenciaPerfil {
    private static final String DATOS_DIR = "PERFILES";

    public void guardarPerfil(Perfil perfil) throws IOException {
        String filePath = "TERCERA_ENTREGA_" + perfil.getUsuari() + ".dat";
        File dir = new File(DATOS_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Perfil guardar = new Perfil(perfil.getUsuari(), perfil.getContrasenya());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(dir, filePath)))) {
            oos.writeObject(guardar);
        }
    }

    public Perfil cargar1Perfil(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Perfil) ois.readObject();
        }
    }

    public Set<String> cargarTodosPerfiles() throws IOException, ClassNotFoundException {
        Set<String> nomsArxius = new HashSet<>();
        File dir = new File(DATOS_DIR);

        if (dir.exists() && dir.isDirectory()) {
            // Filtre per trobar arxius que comencin amb "TERCERA_ENTREGA_" + nomUsuari
            File[] files = dir.listFiles((d, name) -> name.startsWith("TERCERA_ENTREGA_") && name.endsWith(".dat"));

            if (files != null) {
                for (File file : files) {
                    String nomArxiu = file.getName();
                    // Treiem la part de davant: "TERCERA_ENTREGA_nomUsuari_"
                    String textDespresUsuari = nomArxiu.substring(("TERCERA_ENTREGA" + "_").length(), nomArxiu.lastIndexOf(".dat"));
                    nomsArxius.add(textDespresUsuari); // Afegim només la part després del nomUsuari
                }
            }
        }
        return nomsArxius;
    }




    public void limpiarDatos() throws IOException {
        File dir = new File(DATOS_DIR);
        if (dir.exists()) {
            File[] files = dir.listFiles((d, name) -> name.endsWith(".dat"));
            if (files != null) {
                for (File file : files) {
                    if (!file.delete()) {
                        throw new IOException("No se pudo eliminar el archivo: " + file.getAbsolutePath());
                    }
                }
            }
        }
    }

    public Perfil getPerfil(String username) throws IOException, ClassNotFoundException {
        String filePath = DATOS_DIR + "/TERCERA_ENTREGA_" + username + ".dat";
        File file = new File(filePath);
        if (file.exists()) {
            return cargar1Perfil(filePath);
        }
        return null;
    }



    public void deletePerfil(String username) throws IOException {
        String filePath = DATOS_DIR + "/TERCERA_ENTREGA_" + username + ".dat";
        File file = new File(filePath);
        if (file.exists()) {
            if (!file.delete()) {
                throw new IOException("No se pudo eliminar el archivo: " + filePath);
            }
        }
    }



    //ESTAS LAS LLAMA EL DRIVER, NO FUNCIONAN, NO TOCAR
    public void guardarPerfiles(Map<String, ControladorPerfil> perfiles, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(perfiles);
        }
    }
    public Map<String, ControladorPerfil> cargarPerfiles(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Map<String, ControladorPerfil>) ois.readObject();
        }
    }


}
