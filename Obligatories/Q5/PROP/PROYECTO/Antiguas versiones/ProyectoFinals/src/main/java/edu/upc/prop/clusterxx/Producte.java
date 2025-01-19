package edu.upc.prop.clusterxx;
import java.util.ArrayList;

public class Producte {
    private String marca;
    private String nom;
    private float preu;
    private String quantitat;
    private String id;
    private ArrayList<Similitud> similituds;  // Relación uno a muchos con Similitud

    // Constructor
    public Producte(String marca, String nom, float preu, String quantitat, String id) {
        this.marca = marca;
        this.nom = nom;
        this.preu = preu;
        this.quantitat = quantitat;
        this.id = id;
        this.similituds = new ArrayList<>();
    }

    // Métodos para agregar y obtener similitudes
    public void afegirSimilitud(Producte producte, int valor) {
        Similitud novaSimilitud = new Similitud(producte, valor);
        similituds.add(novaSimilitud);
    }

    public int obtenirSimilituds(Producte producte) {
        for (Similitud similitud : similituds) {
            if (similitud.getProducte().equals(producte)) {
                return similitud.getValor();
            }
        }
        return 0; // Si no hay similitud
    }

    // Getters y setters
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPreu() {
        return preu;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }

    public String getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(String quantitat) {
        this.quantitat = quantitat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Similitud> getSimilituds() {
        return similituds;
    }

    @Override
    public String toString() {
        return marca + " " + nom + " " + preu + " " + quantitat + " " + id;
    }
}
