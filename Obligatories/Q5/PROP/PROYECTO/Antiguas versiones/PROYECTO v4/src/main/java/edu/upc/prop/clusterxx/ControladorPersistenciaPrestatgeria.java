package edu.upc.prop.clusterxx;

import java.io.*;

public class ControladorPersistenciaPrestatgeria {

    public void guardarPrestatgeria(Prestatgeria prestatgeria, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(prestatgeria);
        }
    }

    public Prestatgeria cargarPrestatgeria(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Prestatgeria) ois.readObject();
        }
    }
}
