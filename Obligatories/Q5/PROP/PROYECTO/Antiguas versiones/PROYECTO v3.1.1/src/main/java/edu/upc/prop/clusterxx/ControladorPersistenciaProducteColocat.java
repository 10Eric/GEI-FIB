package edu.upc.prop.clusterxx;

import java.io.*;

public class ControladorPersistenciaProducteColocat {

    public void guardarProducteColocat(ProducteColocat producteColocat, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(producteColocat);
        }
    }

    public ProducteColocat cargarProducteColocat(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (ProducteColocat) ois.readObject();
        }
    }
}
