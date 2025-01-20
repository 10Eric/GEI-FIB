package edu.upc.prop.clusterxx.clases_dominio;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Esta clase representa un producto colocado en una prestatgeria.
 */
public class ProducteColocat implements Serializable {
    private int pos;    // Posición en la prestatgeria
    private int altura; // Altura del producto colocado
    private Producte producte;  // Relación uno a uno con Producte
    private boolean manualmenteModificado;

    /**
     * Constructor que crea un producto colocado con la posición, altura y producto especificados.
     *
     * @param pos la posición del producto en la prestatgeria
     * @param altura la altura del producto colocado
     * @param producte el producto colocado
     */
    public ProducteColocat(int pos, int altura, Producte producte) {
        this.pos = pos;
        this.altura = altura;
        this.producte = producte;
        this.manualmenteModificado = false;
    }

    /**
     * Constructor que crea un producto colocado a partir de una lista de cadenas y un producto.
     *
     * @param info la lista de cadenas que contiene la información del producto colocado
     * @param prod el producto colocado
     */
    public ProducteColocat(ArrayList<String> info, Producte prod) {
        this.pos = Integer.parseInt(info.get(0));
        this.altura = Integer.parseInt(info.get(1));
        this.producte = prod;
        this.manualmenteModificado = info.get(2).equalsIgnoreCase("true");
    }

    /**
     * Constructor que crea un producto colocado con el producto especificado.
     *
     * @param producte el producto colocado
     */
    public ProducteColocat(Producte producte) {
        this.producte = producte;
    }

    /**
     * Verifica si la altura es válida en relación con la prestatgeria.
     *
     * @param alturaPrestatgeria la altura de la prestatgeria
     * @return true si la altura es válida, false en caso contrario
     */
    public boolean esValidaAltura(int alturaPrestatgeria) {
        return this.altura <= alturaPrestatgeria;  // La altura debe ser menor o igual que la altura de la prestatgeria
    }

    // Getters y setters

    /**
     * Devuelve la posición del producto en la prestatgeria.
     *
     * @return la posición del producto
     */
    public int getPos() {
        return pos;
    }

    /**
     * Establece la posición del producto en la prestatgeria.
     *
     * @param pos la nueva posición del producto
     */
    public void setPos(int pos) {
        this.pos = pos;
    }

    /**
     * Devuelve la altura del producto colocado.
     *
     * @return la altura del producto
     */
    public int getAltura() {
        return altura;
    }

    /**
     * Establece la altura del producto colocado.
     *
     * @param altura la nueva altura del producto
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

    /**
     * Devuelve el producto colocado.
     *
     * @return el producto colocado
     */
    public Producte getProducte() {
        return producte;
    }

    /**
     * Establece el producto colocado.
     *
     * @param producte el nuevo producto colocado
     */
    public void setProducte(Producte producte) {
        this.producte = producte;
    }

    /**
     * Devuelve si el producto ha sido modificado manualmente.
     *
     * @return true si el producto ha sido modificado manualmente, false en caso contrario
     */
    public boolean isManualmenteModificado() {
        return manualmenteModificado;
    }

    /**
     * Establece si el producto ha sido modificado manualmente.
     *
     * @param manualmenteModificado true si el producto ha sido modificado manualmente, false en caso contrario
     */
    public void setManualmenteModificado(boolean manualmenteModificado) {
        this.manualmenteModificado = manualmenteModificado;
    }

    /**
     * Devuelve el nombre del producto.
     *
     * @return el nombre del producto
     */
    public String getnom() {
        return producte.getNom();
    }

    /**
     * Calcula la similitud entre el producto colocado y otro producto.
     *
     * @param p2 el otro producto
     * @return la similitud entre los dos productos
     */
    public int calculateSimilarity(Producte p2) {
        Producte p1 = this.producte;
        if (p1 == null || p2 == null) return 0;
        return p1.getSimilitud(p2);
    }
}
