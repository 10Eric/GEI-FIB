package edu.upc.prop.clusterxx.controladores;
import edu.upc.prop.clusterxx.clases_dominio.Producte;
import edu.upc.prop.clusterxx.clases_dominio.ProducteColocat;

import java.io.Serializable;

public class ControladorProducteColocat implements Serializable {
    private ProducteColocat producteColocat;

    public ControladorProducteColocat(ProducteColocat producteColocat) {
        this.producteColocat = producteColocat;
    }

    public int getPos() {
        return producteColocat.getPos();
    }

    public void setPos(int pos) {
        producteColocat.setPos(pos);
    }

    public int getAltura() {
        return producteColocat.getAltura();
    }

    public void setAltura(int altura) {
        producteColocat.setAltura(altura);
    }

    public boolean isManualmenteModificado() {
        return producteColocat.isManualmenteModificado();
    }

    public void setManualmenteModificado(boolean manualmenteModificado) {
        producteColocat.setManualmenteModificado(manualmenteModificado);
    }

    public Producte getProducte() {
        return producteColocat.getProducte();
    }

    public void setProducte(Producte producte) {
        producteColocat.setProducte(producte);
    }

}
