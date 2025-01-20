package edu.upc.prop.clusterxx.controladores_persistencia;
import edu.upc.prop.clusterxx.controladores.ControladorPrestatgeria;


import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;

import java.io.*;
import java.util.List;
import java.util.Map;

public class ControladorPersistenciaPrestatgeria {

    public void guardarDatos(Map<String, ControladorPrestatgeria> prestatgeria, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(prestatgeria);
        }
    }

    public Map<String,ControladorPrestatgeria> cargarDatos(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {;
            return (Map<String, ControladorPrestatgeria>) ois.readObject();
        }
    }


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
