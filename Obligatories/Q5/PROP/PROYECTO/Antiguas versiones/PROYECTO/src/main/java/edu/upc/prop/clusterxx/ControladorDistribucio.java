package edu.upc.prop.clusterxx;
import java.util.ArrayList;
import java.util.List;


public class ControladorDistribucio {
    private Distribucio distribucio;

    // Constructor
    public ControladorDistribucio(Distribucio distribucio) {
        this.distribucio = distribucio;
    }

    // Método para agregar un producto a la distribución
    public void agregarProducte(Producte producte) {
        distribucio.agregaProducte(producte);
    }

    // Método para eliminar un producto de la distribución
    public void eliminarProducte(String id) {
        Producte productoAEliminar = null;
        for (Producte producte : distribucio.getProductes()) {
            if (producte.getId().equals(id)) {
                productoAEliminar = producte;
                break;
            }
        }
        if (productoAEliminar != null) {
            distribucio.eliminaProducte(productoAEliminar);
            System.out.println("Producto eliminado correctamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    // Método para listar todos los productos de la distribución
    public void llistarProductes() {
        List<Producte> productes = distribucio.getProductes();
        if (productes.isEmpty()) {
            System.out.println("No hay productos en la distribución.");
        } else {
            for (Producte producte : productes) {
                System.out.println(producte);
            }
        }
    }

    public ArrayList<Producte> getProductes() {
        return distribucio.getProductes();
    }
}
