package edu.upc.prop.clusterxx.controladores;
import edu.upc.prop.clusterxx.clases_dominio.Producte;

import java.io.Serializable;

/**
 * Esta clase controla las operaciones relacionadas con el producto.
 */
public class ControladorProducte implements Serializable {
    private Producte producte;

    /**
     * Constructor que inicializa el controlador con un producto.
     *
     * @param producte el producto a controlar
     */
    public ControladorProducte(Producte producte) {
        this.producte = producte;
    }

    /**
     * Devuelve el nombre del producto.
     *
     * @return el nombre del producto
     */
    public String getNom() {
        return producte.getNom();
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nom el nuevo nombre del producto
     */
    public void setNom(String nom) {
        producte.setNom(nom);
    }

    /**
     * Devuelve la marca del producto.
     *
     * @return la marca del producto
     */
    public String getMarca() {
        return producte.getMarca();
    }

    /**
     * Establece la marca del producto.
     *
     * @param marca la nueva marca del producto
     */
    public void setMarca(String marca) {
        producte.setMarca(marca);
    }

    /**
     * Devuelve el precio del producto.
     *
     * @return el precio del producto
     */
    public double getPreu() {
        return producte.getPreu();
    }

    /**
     * Establece el precio del producto.
     *
     * @param preu el nuevo precio del producto
     */
    public void setPreu(double preu) {
        producte.setPreu(preu);
    }

    /**
     * Devuelve la cantidad del producto.
     *
     * @return la cantidad del producto
     */
    public int getQuantitat() {
        return producte.getQuantitat();
    }

    /**
     * Establece la cantidad del producto.
     *
     * @param quantitat la nueva cantidad del producto
     */
    public void setQuantitat(int quantitat) {
        producte.setQuantitat(quantitat);
    }

    /**
     * Devuelve la similitud con otro producto.
     *
     * @param other el otro producto
     * @return la similitud con el otro producto
     */
    public int getSimilitud(Producte other) {
        return producte.getSimilitud(other);
    }

    /**
     * Establece la similitud con otro producto.
     *
     * @param other el otro producto
     * @param similitud la similitud con el otro producto
     */
    public void setSimilitud(Producte other, int similitud) {
        producte.setSimilitud(other, similitud);
    }
}
