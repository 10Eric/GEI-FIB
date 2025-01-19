package edu.upc.prop.clusterxx;
public class ControladorPrestatgeria {
    private Prestatgeria prestatgeria;

    // Constructor
    public ControladorPrestatgeria(Prestatgeria prestatgeria) {
        this.prestatgeria = prestatgeria;
    }

    // Método para agregar un producto colocado a la prestatgeria
    public boolean afegirProducteColocat(int pos, int altura, Producte producte) {
        ProducteColocat producteColocat = new ProducteColocat(pos, altura, producte);
        return prestatgeria.afegirProducteColocat(producteColocat);  // Utiliza el método de Prestatgeria para agregarlo
    }

    // Método para obtener un producto por su posición
    public Producte obtenirProductePos(int pos) {
        return prestatgeria.obtenirProductePos(pos);  // Utiliza el método de Prestatgeria
    }

    // Método para listar los productos colocados
    public void llistarProductesColocats() {
        for (ProducteColocat producteColocat : prestatgeria.getProductesColocats()) {
            System.out.println("Posición: " + producteColocat.getPos() + ", Producto: " + producteColocat.getProducte().getNom());
        }
    }
}
