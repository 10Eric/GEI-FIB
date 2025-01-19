package edu.upc.prop.clusterxx.controladores_persistencia;

import edu.upc.prop.clusterxx.clases_dominio.Distribucio;

import java.io.*;

public class ControladorPersistenciaDistribucio {

    public void guardarDistribucio(Distribucio distribucio, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(distribucio);
        }
    }

    public Distribucio cargarDistribucio(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Distribucio) ois.readObject();
        }
    }
}
