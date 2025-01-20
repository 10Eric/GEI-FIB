package edu.upc.prop.clusterxx.controladores;
import edu.upc.prop.clusterxx.clases_dominio.Producte;
import edu.upc.prop.clusterxx.clases_dominio.ProducteColocat;

import java.io.Serializable;

/**
 * Esta clase controla las operaciones relacionadas con el producto colocado.
 */
public class ControladorProducteColocat implements Serializable {
    private ProducteColocat producteColocat;

    /**
     * Constructor que inicializa el controlador con un producto colocado.
     *
     * @param producteColocat el producto colocado a controlar
     */
    public ControladorProducteColocat(ProducteColocat producteColocat) {
        this.producteColocat = producteColocat;
    }

    /**
     * Devuelve la posición del producto colocado.
     *
     * @return la posición del producto colocado
     */
    public int getPos() {
        return producteColocat.getPos();
    }

    /**
     * Establece la posición del producto colocado.
     *
     * @param pos la nueva posición del producto colocado
     */
    public void setPos(int pos) {
        producteColocat.setPos(pos);
    }

    /**
     * Devuelve la altura del producto colocado.
     *
     * @return la altura del producto colocado
     */
    public int getAltura() {
        return producteColocat.getAltura();
    }

    /**
     * Establece la altura del producto colocado.
     *
     * @param altura la nueva altura del producto colocado
     */
    public void setAltura(int altura) {
        producteColocat.setAltura(altura);
    }

    /**
     * Devuelve si el producto ha sido modificado manualmente.
     *
     * @return true si el producto ha sido modificado manualmente, false en caso contrario
     */
    public boolean isManualmenteModificado() {
        return producteColocat.isManualmenteModificado();
    }

    /**
     * Establece si el producto ha sido modificado manualmente.
     *
     * @param manualmenteModificado true si el producto ha sido modificado manualmente, false en caso contrario
     */
    public void setManualmenteModificado(boolean manualmenteModificado) {
        producteColocat.setManualmenteModificado(manualmenteModificado);
    }

    /**
     * Devuelve el producto colocado.
     *
     * @return el producto colocado
     */
    public Producte getProducte() {
        return producteColocat.getProducte();
    }

    /**
     * Establece el producto colocado.
     *
     * @param producte el nuevo producto colocado
     */
    public void setProducte(Producte producte) {
        producteColocat.setProducte(producte);
    }
}
