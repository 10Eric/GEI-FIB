package edu.upc.prop.clusterxx.clases_dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Esta clase representa un perfil de usuario.
 */
public class Perfil implements Serializable {
    private String usuari;
    private String contrasenya;
    private List<Prestatgeria> prestatgeria;

    /**
     * Constructor que crea un perfil con el usuario y la contraseña especificados.
     *
     * @param usuari el nombre de usuario
     * @param contrasenya la contraseña del usuario
     */
    public Perfil(String usuari, String contrasenya) {
        this.usuari = usuari;
        this.contrasenya = contrasenya;
        this.prestatgeria = new ArrayList<>();
    }

    /**
     * Devuelve el nombre de usuario.
     *
     * @return el nombre de usuario
     */
    public String getUsuari() {
        return usuari;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param usuari el nombre de usuario
     */
    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    /**
     * Devuelve la contraseña del usuario.
     *
     * @return la contraseña del usuario
     */
    public String getContrasenya() {
        return contrasenya;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasenya la contraseña del usuario
     */
    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    /**
     * Devuelve la lista de prestatgerias asociadas al perfil.
     *
     * @return la lista de prestatgerias
     */
    public List<Prestatgeria> getPrestatgeria() {
        return prestatgeria;
    }

    /**
     * Establece la lista de prestatgerias asociadas al perfil.
     *
     * @param prestatgeria la lista de prestatgerias
     */
    public void setPrestatgeria(List<Prestatgeria> prestatgeria) {
        this.prestatgeria = prestatgeria;
    }

    /**
     * Añade una prestatgeria a la lista de prestatgerias del perfil.
     *
     * @param prestatgeria la prestatgeria a añadir
     */
    public void añadirPrestatgeria(Prestatgeria prestatgeria) {
        this.prestatgeria.add(prestatgeria);
    }

    /**
     * Elimina una prestatgeria de la lista de prestatgerias del perfil.
     *
     * @param prestatgeria la prestatgeria a eliminar
     */
    public void eliminarPrestatgeria(Prestatgeria prestatgeria) {
        this.prestatgeria.remove(prestatgeria);
    }
}
