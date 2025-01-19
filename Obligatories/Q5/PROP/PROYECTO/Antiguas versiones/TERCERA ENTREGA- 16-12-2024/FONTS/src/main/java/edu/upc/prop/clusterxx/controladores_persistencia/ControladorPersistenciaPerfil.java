package edu.upc.prop.clusterxx.controladores_persistencia;

import edu.upc.prop.clusterxx.clases_dominio.Perfil;
import edu.upc.prop.clusterxx.controladores.ControladorPerfil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ControladorPersistenciaPerfil {
    private static final String DATOS_DIR = "DATOS";

    public void guardarPerfil(Perfil perfil, String filePath) throws IOException {
        File dir = new File(DATOS_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(dir, filePath)))) {
            oos.writeObject(perfil);
        }
    }

    public Perfil cargar1Perfil(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Perfil) ois.readObject();
        }
    }

    public Map<String, Perfil> cargarPerfil(String filePath) throws IOException, ClassNotFoundException {
        Map<String, Perfil> perfiles = new HashMap<>();
        File dir = new File("DATOS");
        if (dir.exists()) {
            File[] files = dir.listFiles((d, name) -> name.startsWith(filePath + "_") && name.endsWith(".dat"));
            if (files != null) {
                for (File file : files) {
                    Perfil perfil = cargar1Perfil(file.getAbsolutePath());
                    perfiles.put(perfil.getUsuari(), perfil);
                }
            }
        }
        return perfiles;
    }

    public void limpiarDatos() throws IOException {
        File dir = new File(DATOS_DIR);
        if (dir.exists()) {
            File[] files = dir.listFiles((d, name) -> name.endsWith(".dat")); // Filtrar solo archivos .dat
            if (files != null) {
                for (File file : files) {
                    if (!file.delete()) {
                        throw new IOException("No se pudo eliminar el archivo: " + file.getAbsolutePath());
                    }
                }
            }
        }
    }



    //Estas las llama el Driver
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
