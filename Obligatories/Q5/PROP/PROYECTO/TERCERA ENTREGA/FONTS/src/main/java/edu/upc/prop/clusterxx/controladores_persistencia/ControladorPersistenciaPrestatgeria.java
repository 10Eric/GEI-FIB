package edu.upc.prop.clusterxx.controladores_persistencia;


import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Esta clase controla la persistencia de las prestatgerias.
 */
public class ControladorPersistenciaPrestatgeria {
    private static final String DATOS_DIR = "PRESTATGERIES";

    /**
     * Guarda una prestatgeria en un archivo.
     *
     * @param prestatgeria la prestatgeria a guardar
     * @param nombreUsuario el nombre del usuario
     * @throws IOException si ocurre un error al guardar la prestatgeria
     */

    public void guardarPrestatgeria(Prestatgeria prestatgeria, String nombreUsuario) throws IOException {
        File dir = new File(new File("").getAbsolutePath(), DATOS_DIR);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("No se pudo crear la carpeta 'PRESTATGERIES'.");
        }

        String filePath = dir.getAbsolutePath() + "/TERCERA_ENTREGA_" + nombreUsuario + "_" + prestatgeria.getNom() + ".dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filePath)))) {
            oos.writeObject(prestatgeria);
        }
    }

    /**
     * Carga una prestatgeria desde un archivo.
     *
     * @param filePath la ruta del archivo a cargar
     * @return la prestatgeria cargada
     * @throws IOException si ocurre un error al cargar la prestatgeria
     * @throws ClassNotFoundException si la clase del objeto serializado no se encuentra
     */

    public Prestatgeria cargar1Prestatgeria(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Prestatgeria) ois.readObject();
        }
    }


    /**
     * Obtiene los nombres de los archivos de prestatgeria de un usuario.
     *
     * @param nomUsuari el nombre del usuario
     * @return un conjunto de nombres de archivos de prestatgeria
     */

    public Set<String> getArxiusPrestatgeria(String nomUsuari) {
        Set<String> nomsArxius = new HashSet<>();
        File dir = new File(new File("").getAbsolutePath(), DATOS_DIR);

        if (!dir.exists() && !dir.mkdirs()) {
            return nomsArxius;
        }

        File[] files = dir.listFiles((d, name) -> name.startsWith("TERCERA_ENTREGA_" + nomUsuari) && name.endsWith(".dat"));
        if (files != null) {
            for (File file : files) {
                String nomArxiu = file.getName();
                String textDespresUsuari = nomArxiu.substring(("TERCERA_ENTREGA_" + nomUsuari + "_").length(), nomArxiu.lastIndexOf(".dat"));
                nomsArxius.add(textDespresUsuari);
            }
        }
        return nomsArxius;
    }

    /**
     * Edita el nombre de una prestatgeria.
     *
     * @param nombreantiguo el nombre antiguo de la prestatgeria
     * @param nuevoNombre el nuevo nombre de la prestatgeria
     * @throws IOException si ocurre un error al editar el nombre
     * @throws ClassNotFoundException si la clase del objeto serializado no se encuentra
     */

    public void editar_nombre_prestatgeria(String nombreantiguo, String nuevoNombre) throws IOException, ClassNotFoundException {
        File dir = new File(new File("").getAbsolutePath(), DATOS_DIR);

        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("No se pudo crear la carpeta 'PRESTATGERIES'.");
        }

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


    /**
     * Limpia todos los datos de prestatgerias.
     *
     * @throws IOException si ocurre un error al limpiar los datos
     */

    public void limpiarDatos() throws IOException {
        File dir = new File(new File("").getAbsolutePath(), DATOS_DIR);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("No se pudo crear la carpeta 'PRESTATGERIES'.");
        }

        File[] files = dir.listFiles((d, name) -> name.endsWith(".dat"));
        if (files != null) {
            for (File file : files) {
                if (!file.delete()) {
                    throw new IOException("No se pudo eliminar el archivo: " + file.getAbsolutePath());
                }
            }
        }
    }

    /**
     * Busca una prestatgeria por nombre de usuario y nombre de prestatgeria.
     *
     * @param nombreUsuario el nombre del usuario
     * @param nombrePrestatgeria el nombre de la prestatgeria
     * @return la prestatgeria encontrada
     * @throws IOException si ocurre un error al buscar la prestatgeria
     * @throws ClassNotFoundException si la clase del objeto serializado no se encuentra
     */

    public Prestatgeria buscarPrestatgeria(String nombreUsuario, String nombrePrestatgeria) throws IOException, ClassNotFoundException {
        return getPrestatgeria(nombreUsuario, nombrePrestatgeria);
    }

    /**
     * Obtiene una prestatgeria por nombre de usuario y nombre de prestatgeria.
     *
     * @param nombreUsuario el nombre del usuario
     * @param nombrePrestatgeria el nombre de la prestatgeria
     * @return la prestatgeria encontrada
     * @throws IOException si ocurre un error al obtener la prestatgeria
     * @throws ClassNotFoundException si la clase del objeto serializado no se encuentra
     */

    public Prestatgeria getPrestatgeria(String nombreUsuario, String nombrePrestatgeria) throws IOException, ClassNotFoundException {
        File dir = new File(new File("").getAbsolutePath(), DATOS_DIR);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("No se pudo crear la carpeta 'PRESTATGERIES'.");
        }

        String filePath = dir.getAbsolutePath() + "/TERCERA_ENTREGA_" + nombreUsuario + "_" + nombrePrestatgeria + ".dat";
        File file = new File(filePath);
        if (file.exists()) {
            return cargar1Prestatgeria(filePath);
        }
        return null;
    }

    /**
     * Elimina una prestatgeria por nombre de usuario y nombre de prestatgeria.
     *
     * @param nombreUsuario el nombre del usuario
     * @param nombrePrestatgeria el nombre de la prestatgeria
     * @throws IOException si ocurre un error al eliminar la prestatgeria
     */

    public void deletePrestatgeria(String nombreUsuario, String nombrePrestatgeria) throws IOException {
        File dir = new File(new File("").getAbsolutePath(), DATOS_DIR);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("No se pudo crear la carpeta 'PRESTATGERIES'.");
        }

        String filePath = dir.getAbsolutePath() + "/TERCERA_ENTREGA_" + nombreUsuario + "_" + nombrePrestatgeria + ".dat";
        File file = new File(filePath);
        if (file.exists() && !file.delete()) {
            throw new IOException("No se pudo eliminar el archivo: " + filePath);
        }
    }

    /**
     * Elimina todas las prestatgerias de un usuario.
     *
     * @param nombreUsuario el nombre del usuario
     * @throws IOException si ocurre un error al eliminar las prestatgerias
     * @throws ClassNotFoundException si la clase del objeto serializado no se encuentra
     */

    public void deletePrestatgerias(String nombreUsuario) throws IOException, ClassNotFoundException {
        File dir = new File(new File("").getAbsolutePath(), DATOS_DIR);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("No se pudo crear la carpeta 'PRESTATGERIES'.");
        }

        File[] files = dir.listFiles((d, name) -> name.startsWith("TERCERA_ENTREGA_" + nombreUsuario) && name.endsWith(".dat"));
        if (files != null) {
            for (File file : files) {
                Prestatgeria prestatgeria = cargar1Prestatgeria(file.getPath());
                deletePrestatgeria(nombreUsuario, prestatgeria.getNom());
            }
        }
    }


//NO TOCAR COSAS DEL DRIVER!!

    /**
     * Guarda una lista de prestatgerias en un archivo (usado por el driver).
     *
     * @param prestatgeries la lista de prestatgerias a guardar
     * @param filePath la ruta del archivo a guardar
     * @throws IOException si ocurre un error al guardar las prestatgerias
     */

    public void guardarPrestatgeria_driver(List<Prestatgeria> prestatgeries, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(prestatgeries);
        }
    }

    /**
     * Carga una lista de prestatgerias desde un archivo (usado por el driver).
     *
     * @param filePath la ruta del archivo a cargar
     * @return la lista de prestatgerias cargadas
     * @throws IOException si ocurre un error al cargar las prestatgerias
     * @throws ClassNotFoundException si la clase del objeto serializado no se encuentra
     */

    public List<Prestatgeria> cargarPrestatgeria_driver(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Prestatgeria>) ois.readObject();
        }
    }
}


