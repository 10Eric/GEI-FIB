package edu.upc.prop.clusterxx.controladores_persistencia;
import edu.upc.prop.clusterxx.clases_dominio.Perfil;
import edu.upc.prop.clusterxx.controladores.ControladorPrestatgeria;


import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ControladorPersistenciaPrestatgeria {
    private static final String DATOS_DIR = "PRESTATGERIES";

    public void guardarPrestatgeria(Prestatgeria prestatgeria, String nombreUsuario) throws IOException {
        String filePath = "TERCERA_ENTREGA_" + nombreUsuario + "_" + prestatgeria.getNom() + ".dat";
        File dir = new File(DATOS_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(dir, filePath)))) {
            oos.writeObject(prestatgeria);
        }
    }

    public Prestatgeria cargar1Prestatgeria(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Prestatgeria) ois.readObject();
        }
    }


    public Set<String> getArxiusPrestatgeria(String nomUsuari) {
        Set<String> nomsArxius = new HashSet<>();
        File dir = new File(DATOS_DIR);

        if (dir.exists() && dir.isDirectory()) {
            // Filtre per trobar arxius que comencin amb "TERCERA_ENTREGA_" + nomUsuari
            File[] files = dir.listFiles((d, name) -> name.startsWith("TERCERA_ENTREGA_" + nomUsuari) && name.endsWith(".dat"));

            if (files != null) {
                for (File file : files) {
                    String nomArxiu = file.getName();
                    // Treiem la part de davant: "TERCERA_ENTREGA_nomUsuari_"
                    String textDespresUsuari = nomArxiu.substring(("TERCERA_ENTREGA_" + nomUsuari + "_").length(), nomArxiu.lastIndexOf(".dat"));
                    nomsArxius.add(textDespresUsuari); // Afegim només la part després del nomUsuari
                }
            }
        }
        return nomsArxius;
    }

    public void editar_nombre_prestatgeria(String nombreantiguo, String nuevoNombre) throws IOException, ClassNotFoundException {
        File dir = new File(DATOS_DIR);
        if (dir.exists() && dir.isDirectory()) {
            // Filtre per trobar arxius que comencin amb "TERCERA_ENTREGA_" + nomUsuari
            File[] files = dir.listFiles((d, name) -> name.startsWith("TERCERA_ENTREGA_" + nombreantiguo) && name.endsWith(".dat"));

            if (files != null) {
                for (File file : files) {
                    String fileName = file.getName();
                    if (fileName.contains(nombreantiguo)) {
                        Prestatgeria prestatgeria = cargar1Prestatgeria(file.getPath());
                        deletePrestatgeria(nombreantiguo, prestatgeria.getNom());
                        guardarPrestatgeria(prestatgeria, nuevoNombre);
                    }

                }
            }
        }
    }


    public void limpiarDatos() throws IOException {
        File dir = new File(DATOS_DIR);
        if (dir.exists()) {
            File[] files = dir.listFiles((d, name) -> name.endsWith(".dat"));
            if (files != null) {
                for (File file : files) {
                    if (!file.delete()) {
                        throw new IOException("No se pudo eliminar el archivo: " + file.getAbsolutePath());
                    }
                }
            }
        }
    }

    public Prestatgeria buscarPrestatgeria(String nombreUsuario, String nombrePrestatgeria) throws IOException, ClassNotFoundException {
        return getPrestatgeria(nombreUsuario, nombrePrestatgeria);
    }

    public Prestatgeria getPrestatgeria(String nombreUsuario, String nombrePrestatgeria) throws IOException, ClassNotFoundException {
        String filePath = DATOS_DIR + "/TERCERA_ENTREGA_" + nombreUsuario + "_" + nombrePrestatgeria + ".dat";
        File file = new File(filePath);
        if (file.exists()) {
            return cargar1Prestatgeria(filePath);
        }
        return null;
    }

    public void deletePrestatgeria(String nombreUsuario, String nombrePrestatgeria) throws IOException {
        String filePath = DATOS_DIR + "/TERCERA_ENTREGA_" + nombreUsuario + "_" + nombrePrestatgeria + ".dat";
        File file = new File(filePath);
        if (file.exists()) {
            if (!file.delete()) {
                throw new IOException("No se pudo eliminar el archivo: " + filePath);
            }
        }
    }

    public void deletePrestatgerias(String nombreUsuario) throws IOException, ClassNotFoundException {
        File dir = new File(DATOS_DIR);
        if (dir.exists() && dir.isDirectory()) {
            // Filtre per trobar arxius que comencin amb "TERCERA_ENTREGA_" + nomUsuari
            File[] files = dir.listFiles((d, name) -> name.startsWith("TERCERA_ENTREGA_" + nombreUsuario) && name.endsWith(".dat"));

            if (files != null) {
                for (File file : files) {
                    Prestatgeria prestatgeria = cargar1Prestatgeria(file.getPath());
                    deletePrestatgeria(nombreUsuario, prestatgeria.getNom());
                }

            }
        }
    }


//NO TOCAR COSAS DEL DRIVER!!
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


