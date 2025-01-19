package edu.upc.prop.clusterxx.controladores;
import edu.upc.prop.clusterxx.clases_dominio.Perfil;
import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;
import edu.upc.prop.clusterxx.controladores_persistencia.ControladorPersistenciaPerfil;

import java.util.List;
import java.io.*;
import java.util.Set;


/**
 * Esta clase controla las operaciones relacionadas con el perfil de usuario.
 */
public class ControladorPerfil implements Serializable {
    private Perfil perfil;
    private static final ControladorPersistenciaPerfil persistencia = new ControladorPersistenciaPerfil();

    /**
     * Constructor por defecto.
     */
    public ControladorPerfil() {}

    /**
     * Constructor que inicializa el controlador con un perfil.
     *
     * @param perfil el perfil a controlar
     */
    public ControladorPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    /**
     * Devuelve el nombre de usuario del perfil.
     *
     * @return el nombre de usuario
     */
    public String getUsuari() {
        return perfil.getUsuari();
    }

    /**
     * Establece el nombre de usuario del perfil.
     *
     * @param usuari el nuevo nombre de usuario
     */
    public void setUsuari(String usuari) {
        perfil.setUsuari(usuari);
    }

    /**
     * Devuelve la contraseña del perfil.
     *
     * @return la contraseña
     */
    public String getContrasenya() {
        return perfil.getContrasenya();
    }

    /**
     * Establece la contraseña del perfil.
     *
     * @param contrasenya la nueva contraseña
     */
    public void setContrasenya(String contrasenya) {
        perfil.setContrasenya(contrasenya);
    }

    /**
     * Añade una prestatgeria al perfil.
     *
     * @param prestatgeria la prestatgeria a añadir
     */
    public void añadirPrestatgeria(Prestatgeria prestatgeria) {
        perfil.añadirPrestatgeria(prestatgeria);
    }

    /**
     * Elimina una prestatgeria del perfil.
     *
     * @param prestatgerias la prestatgeria a eliminar
     */
    public void eliminarPrestatgeria(Prestatgeria prestatgerias) {
        perfil.eliminarPrestatgeria(prestatgerias);
    }

    /**
     * Devuelve la lista de prestatgerias del perfil.
     *
     * @return la lista de prestatgerias
     */
    public List<Prestatgeria> getPrestatgerias() {
        return perfil.getPrestatgeria();
    }

    /**
     * Establece la lista de prestatgerias del perfil.
     *
     * @param prestatgerias la nueva lista de prestatgerias
     */
    public void setPrestatgerias(List<Prestatgeria> prestatgerias) {
        perfil.setPrestatgeria(prestatgerias);
    }

    /**
     * Crea un perfil con el nombre de usuario y la contraseña especificados.
     *
     * @param username el nombre de usuario
     * @param password la contraseña
     */
    public void creaPerfil(String username, String password) {
        Perfil aux = new Perfil(username, password);
        this.perfil = aux;
    }

    /**
     * Añade una prestatgeria al perfil utilizando un controlador de prestatgeria.
     *
     * @param prest el controlador de prestatgeria
     */
    public void añadirPrestatgeriaCtrl(ControladorPrestatgeria prest) {
        perfil.añadirPrestatgeria(prest.getPrestatgeria());
    }

    /**
     * Guarda el perfil actual.
     *
     * @throws IOException si ocurre un error al guardar el perfil
     */
    public void guardarPerfil() throws IOException {
        persistencia.guardarPerfil(perfil);
    }

    /**
     * Carga un perfil a partir del nombre de usuario.
     *
     * @param username el nombre de usuario
     * @throws IOException si ocurre un error al cargar el perfil
     * @throws ClassNotFoundException si la clase del perfil no se encuentra
     */
    public void cargarPerfil(String username) throws IOException, ClassNotFoundException {
        perfil = persistencia.getPerfil(username);
    }

    /**
     * Elimina un perfil a partir del nombre de usuario.
     *
     * @param username el nombre de usuario
     * @throws IOException si ocurre un error al eliminar el perfil
     */
    public static void eliminarPerfil(String username) throws IOException {
        persistencia.deletePerfil(username);
    }

    /**
     * Carga todos los perfiles.
     *
     * @return un conjunto con los nombres de usuario de todos los perfiles
     * @throws IOException si ocurre un error al cargar los perfiles
     * @throws ClassNotFoundException si la clase de los perfiles no se encuentra
     */
    public static Set<String> cargarTodosPerfiles() throws IOException, ClassNotFoundException {
        return persistencia.cargarTodosPerfiles();
    }

    /**
     * Limpia todos los datos de los perfiles.
     *
     * @throws IOException si ocurre un error al limpiar los datos
     */
    public void limpiarDatos() throws IOException {
        persistencia.limpiarDatos();
    }

    /**
     * Busca un perfil a partir del nombre de usuario.
     *
     * @param username el nombre de usuario
     * @throws IOException si ocurre un error al buscar el perfil
     * @throws ClassNotFoundException si la clase del perfil no se encuentra
     */
    public void buscarPerfil(String username) throws IOException, ClassNotFoundException {
        perfil = persistencia.getPerfil(username);
    }

    /**
     * Devuelve el perfil actual.
     *
     * @return el perfil actual
     */
    public Perfil getPerfil() {
        return perfil;
    }
}
