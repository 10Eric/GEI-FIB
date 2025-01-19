package edu.upc.prop.clusterxx.controladores_persistencia;

import edu.upc.prop.clusterxx.controladores.ControladorDistribucio;

import java.io.*;

public class ControladorPersistenciaDistribucio {

    public void guardarDistribucio(ControladorDistribucio controladorDistribucio, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(controladorDistribucio);
        }
    }

    public ControladorDistribucio cargarDistribucio(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (ControladorDistribucio) ois.readObject();
        }
    }
}
