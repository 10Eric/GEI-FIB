package edu.upc.prop.clusterxx.clases_dominio;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

/**
 * Esta clase representa un producto.
 */
public class Producte implements Serializable {
    private String nom;
    private String marca;
    private double preu;
    private int quantitat;
    private Map<Producte, Integer> similituds;

    /**
     * Constructor que crea un producto con el nombre, la marca, el precio y la cantidad especificados.
     *
     * @param nom el nombre del producto
     * @param marca la marca del producto
     * @param preu el precio del producto
     * @param quantitat la cantidad del producto
     */
    public Producte(String nom, String marca, double preu, int quantitat) {
        this.nom = nom;
        this.marca = marca;
        this.preu = preu;
        this.quantitat = quantitat;
        this.similituds = new HashMap<>();
    }

    /**
     * Constructor que crea un producto a partir de una lista de cadenas.
     *
     * @param a la lista de cadenas que contiene los atributos del producto
     */
    public Producte(ArrayList<String> a) {
        this.nom = a.get(0);
        this.marca = a.get(1);
        this.preu = Double.parseDouble(a.get(2));
        this.quantitat = Integer.parseInt(a.get(3));
    }

    /**
     * Constructor que crea un producto con el nombre especificado.
     *
     * @param nom el nombre del producto
     */
    public Producte(String nom) {
        this.nom = nom;
        this.similituds = new HashMap<>();
    }

    /**
     * Devuelve el nombre del producto.
     *
     * @return el nombre del producto
     */
    public String getNom() {
        return nom;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nom el nuevo nombre del producto
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Devuelve la marca del producto.
     *
     * @return la marca del producto
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Establece la marca del producto.
     *
     * @param marca la nueva marca del producto
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Devuelve el precio del producto.
     *
     * @return el precio del producto
     */
    public double getPreu() {
        return preu;
    }

    /**
     * Establece el precio del producto.
     *
     * @param preu el nuevo precio del producto
     */
    public void setPreu(double preu) {
        this.preu = preu;
    }

    /**
     * Devuelve la cantidad del producto.
     *
     * @return la cantidad del producto
     */
    public int getQuantitat() {
        return quantitat;
    }

    /**
     * Establece la cantidad del producto.
     *
     * @param quantitat la nueva cantidad del producto
     */
    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    /**
     * Devuelve la similitud con otro producto.
     *
     * @param other el otro producto
     * @return la similitud con el otro producto
     */
    public int getSimilitud(Producte other) {
        return similituds.getOrDefault(other, 0);
    }

    /**
     * Establece la similitud con otro producto.
     *
     * @param other el otro producto
     * @param similitud la similitud con el otro producto
     */
    public void setSimilitud(Producte other, int similitud) {
        if (similitud < 0 || similitud > 100) {
            throw new IllegalArgumentException("La similitud debe estar entre 0 y 100.");
        }
        similituds.put(other, similitud);
        other.similituds.put(this, similitud); // Asegura la similitud bidireccional
    }

    @Override
    public String toString() {
        return marca + " " + nom + " " + preu + " " + quantitat;
    }
}
