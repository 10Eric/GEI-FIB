package edu.upc.prop.clusterxx;

import java.io.*;

public class ControladorPersistenciaPerfil {

    public void guardarPerfil(Perfil perfil, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(perfil);
        }
    }

    public Perfil cargarPerfil(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Perfil) ois.readObject();
        }
    }
}
