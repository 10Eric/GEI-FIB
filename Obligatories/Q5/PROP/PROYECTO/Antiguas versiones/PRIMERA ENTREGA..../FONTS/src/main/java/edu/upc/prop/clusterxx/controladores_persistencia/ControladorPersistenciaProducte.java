package edu.upc.prop.clusterxx.controladores_persistencia;

import edu.upc.prop.clusterxx.clases_dominio.Producte;

import java.io.*;

public class ControladorPersistenciaProducte {

    public void guardarProducte(Producte producte, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(producte);
        }
    }

    public Producte cargarProducte(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Producte) ois.readObject();
        }
    }
}
