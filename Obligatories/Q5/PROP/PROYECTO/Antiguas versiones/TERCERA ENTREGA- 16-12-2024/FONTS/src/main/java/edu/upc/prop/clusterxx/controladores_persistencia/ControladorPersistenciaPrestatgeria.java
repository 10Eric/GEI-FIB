package edu.upc.prop.clusterxx.controladores_persistencia;
import edu.upc.prop.clusterxx.controladores.ControladorPrestatgeria;


import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;

import java.io.*;
import java.util.List;
import java.util.Map;

public class ControladorPersistenciaPrestatgeria {

    public void guardarPrestatgeria_driver(List<Prestatgeria> prestatgeries, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(prestatgeries);
        }
    }

    public List<Prestatgeria> cargarPrestatgeria_driver(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Prestatgeria>) ois.readObject();
        }
    }
}
