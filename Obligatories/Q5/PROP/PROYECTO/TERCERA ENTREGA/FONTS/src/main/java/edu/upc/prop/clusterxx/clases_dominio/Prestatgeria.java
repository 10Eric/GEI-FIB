package edu.upc.prop.clusterxx.clases_dominio;

import java.io.Serializable;

/**
 * Esta clase representa una prestatgeria.
 */
public class Prestatgeria implements Serializable {
    private String nom;
    private int altura;
    private Distribucio distribucion;

    /**
     * Constructor que crea una prestatgeria con el nombre y la altura especificados.
     *
     * @param nom el nombre de la prestatgeria
     * @param altura la altura de la prestatgeria
     */
    public Prestatgeria(String nom, int altura) {
        this.nom = nom;
        this.altura = altura;
    }

    /**
     * Constructor que crea una prestatgeria con el nombre, la altura y la distribución especificados.
     *
     * @param nom el nombre de la prestatgeria
     * @param altura la altura de la prestatgeria
     * @param distribucion la distribución de la prestatgeria
     */
    public Prestatgeria(String nom, int altura, Distribucio distribucion) {
        this.nom = nom;
        this.altura = altura;
        this.distribucion = distribucion;
    }

    /**
     * Devuelve la altura de la prestatgeria.
     *
     * @return la altura de la prestatgeria
     */
    public int getAltura() {
        return altura;
    }

    /**
     * Establece la altura de la prestatgeria.
     *
     * @param altura la nueva altura de la prestatgeria
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

    /**
     * Devuelve el nombre de la prestatgeria.
     *
     * @return el nombre de la prestatgeria
     */
    public String getNom() {
        return nom;
    }

    /**
     * Establece el nombre de la prestatgeria.
     *
     * @param nom el nuevo nombre de la prestatgeria
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Devuelve la distribución de la prestatgeria.
     *
     * @return la distribución de la prestatgeria
     */
    public Distribucio getDistribucion() {
        return distribucion;
    }

    /**
     * Establece la distribución de la prestatgeria.
     *
     * @param distribucion la nueva distribución de la prestatgeria
     */
    public void setDistribucion(Distribucio distribucion) {
        this.distribucion = distribucion;
    }

    /**
     * Elimina la distribución de la prestatgeria.
     *
     * @param distribucio la distribución a eliminar
     */
    public void eliminaDistribucio(Distribucio distribucio) {
        this.distribucion = null;
    }
}