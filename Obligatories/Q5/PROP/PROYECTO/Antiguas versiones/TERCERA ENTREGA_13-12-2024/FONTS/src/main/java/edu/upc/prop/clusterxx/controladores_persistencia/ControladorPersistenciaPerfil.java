package edu.upc.prop.clusterxx.controladores_persistencia;

import edu.upc.prop.clusterxx.controladores.ControladorPerfil;

import java.io.*;
import java.util.Map;

public class ControladorPersistenciaPerfil {

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
