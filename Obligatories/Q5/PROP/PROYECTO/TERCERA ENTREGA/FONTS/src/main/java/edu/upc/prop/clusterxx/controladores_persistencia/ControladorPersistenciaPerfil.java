package edu.upc.prop.clusterxx.controladores_persistencia;

import edu.upc.prop.clusterxx.clases_dominio.Perfil;
import edu.upc.prop.clusterxx.controladores.ControladorPerfil;

import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

/**
 * Esta clase controla la persistencia de los perfiles.
 */
public class ControladorPersistenciaPerfil {
    private static final String DATOS_DIR = "PERFILES";

    /**
     * Guarda un perfil en un archivo.
     *
     * @param perfil el perfil a guardar
     * @throws IOException si ocurre un error al guardar el perfil
     */

    public void guardarPerfil(Perfil perfil) throws IOException {
        String filePath = "TERCERA_ENTREGA_" + perfil.getUsuari() + ".dat";
        // Ruta base del proyecto
        String baseDir = new File("").getAbsolutePath(); // Directorio actual

        // Ruta donde debería estar la carpeta "PERFILES"
        File perfilesDir = new File(baseDir, DATOS_DIR);

        // Si la carpeta no existe, se crea
        if (!perfilesDir.exists()) {
            if (!perfilesDir.mkdirs()) {
                throw new IOException("No se pudo crear la carpeta 'PERFILES' en: " + perfilesDir.getAbsolutePath());
            }
        }
        // Crear y guardar el archivo en la carpeta adecuada
        Perfil guardar = new Perfil(perfil.getUsuari(), perfil.getContrasenya());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(perfilesDir, filePath)))) {
            oos.writeObject(guardar);
        }
    }

    /**
     * Carga un perfil desde un archivo.
     *
     * @param filePath la ruta del archivo a cargar
     * @return el perfil cargado
     * @throws IOException si ocurre un error al cargar el perfil
     * @throws ClassNotFoundException si la clase del objeto serializado no se encuentra
     */

    public Perfil cargar1Perfil(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Perfil) ois.readObject();
        }
    }

    /**
     * Carga todos los perfiles.
     *
     * @return un conjunto de todos los nombres de perfiles
     * @throws IOException si ocurre un error al cargar los perfiles
     * @throws ClassNotFoundException si la clase del objeto serializado no se encuentra
     */

    public Set<String> cargarTodosPerfiles() throws IOException, ClassNotFoundException {
        Set<String> nomsArxius = new HashSet<>();
        File dir = new File(new File("").getAbsolutePath(), DATOS_DIR);

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new IOException("No se pudo crear la carpeta 'PERFILES'.");
            }
        }

        if (dir.isDirectory()) {
            File[] files = dir.listFiles((d, name) -> name.startsWith("TERCERA_ENTREGA_") && name.endsWith(".dat"));
            if (files != null) {
                for (File file : files) {
                    String nomArxiu = file.getName();
                    String textDespresUsuari = nomArxiu.substring(("TERCERA_ENTREGA" + "_").length(), nomArxiu.lastIndexOf(".dat"));
                    nomsArxius.add(textDespresUsuari);
                }
            }
        }
        return nomsArxius;
    }



    /**
     * Limpia todos los datos de perfiles.
     *
     * @throws IOException si ocurre un error al limpiar los datos
     */

    public void limpiarDatos() throws IOException {
        File dir = new File(new File("").getAbsolutePath(), DATOS_DIR);

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new IOException("No se pudo crear la carpeta 'PERFILES'.");
            }
        }

        if (dir.isDirectory()) {
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

    /**
     * Obtiene un perfil por nombre de usuario.
     *
     * @param username el nombre de usuario del perfil
     * @return el perfil
     * @throws IOException si ocurre un error al obtener el perfil
     * @throws ClassNotFoundException si la clase del objeto serializado no se encuentra
     */

    public Perfil getPerfil(String username) throws IOException, ClassNotFoundException {
        File dir = new File(new File("").getAbsolutePath(), DATOS_DIR);

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new IOException("No se pudo crear la carpeta 'PERFILES'.");
            }
        }

        String filePath = dir.getAbsolutePath() + "/TERCERA_ENTREGA_" + username + ".dat";
        File file = new File(filePath);
        if (file.exists()) {
            return cargar1Perfil(filePath);
        }
        return null;
    }


    /**
     * Elimina un perfil por nombre de usuario.
     *
     * @param username el nombre de usuario del perfil
     * @throws IOException si ocurre un error al eliminar el perfil
     */

    public void deletePerfil(String username) throws IOException {
        File dir = new File(new File("").getAbsolutePath(), DATOS_DIR);

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new IOException("No se pudo crear la carpeta 'PERFILES'.");
            }
        }

        String filePath = dir.getAbsolutePath() + "/TERCERA_ENTREGA_" + username + ".dat";
        File file = new File(filePath);
        if (file.exists()) {
            if (!file.delete()) {
                throw new IOException("No se pudo eliminar el archivo: " + filePath);
            }
        }
    }



    //ESTAS LAS LLAMA EL DRIVER, NO FUNCIONAN, NO TOCAR

    /**
     * Guarda múltiples perfiles en un archivo.
     *
     * @param perfiles los perfiles a guardar
     * @param filePath la ruta del archivo a guardar
     * @throws IOException si ocurre un error al guardar los perfiles
     */
    public void guardarPerfiles(Map<String, ControladorPerfil> perfiles, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(perfiles);
        }
    }

    /**
     * Carga múltiples perfiles desde un archivo.
     *
     * @param filePath la ruta del archivo a cargar
     * @return los perfiles cargados
     * @throws IOException si ocurre un error al cargar los perfiles
     * @throws ClassNotFoundException si la clase del objeto serializado no se encuentra
     */
    public Map<String, ControladorPerfil> cargarPerfiles(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Map<String, ControladorPerfil>) ois.readObject();
        }
    }


}
