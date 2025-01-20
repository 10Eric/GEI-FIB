package edu.upc.prop.clusterxx.controladores_persistencia;

import edu.upc.prop.clusterxx.controladores.ControladorDistribucio;

import java.io.*;

public class ControladorPersistenciaDistribucio {

   //S'utilitza nomes al driver
    public void guardarDistribucioDriver(ControladorDistribucio controladorDistribucio, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(controladorDistribucio);
        }
    }

    public ControladorDistribucio cargarDistribucioDriver(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (ControladorDistribucio) ois.readObject();
        }
    }
}
