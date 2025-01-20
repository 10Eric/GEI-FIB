package edu.upc.prop.clusterxx;

import java.util.ArrayList;
import java.util.List;

public class ControladorDistribucio {
    private Distribucio distribucion;



    //Constructora default que crearemos al iniciar la ejecución.
    public ControladorDistribucio() {
        distribucion = new Distribucio();
    }

    public List<ProducteColocat> arrangeProductsBySimilarity(List<ProducteColocat> productes, int altura, boolean mantenerCambios) {
        return distribucion.getEstrategiaCalculo().arrangeProductsBySimilarity(productes,altura, mantenerCambios);
    }



    public void calcula_distribucio(boolean mantenerCambios){
        //la parte de imprimir la controla el driver, pedira imprimir al principio y al final del calculo,
        //esto solo debe calcular


    }

    //nos viene en info nombre, marca, precio(double), cantidad,posicion, altura, modificado
    public boolean afegeix_producte(String nombre, String marca, double precio, int cantidad, int pos, int altura){
        Producte prod = new Producte(nombre,marca,precio,cantidad);
        ProducteColocat producteColocat = new ProducteColocat(pos,altura,prod);
        distribucion.afegeix_producte_colocat(producteColocat);
        return true;
    }

    public ArrayList<String> getSimilitudsProducte(String nomProducte) {
        ArrayList<String> similitudsList = new ArrayList<>();

        // Buscar el producto por nombre en la distribucion
        Producte targetProduct = null;
        for (ProducteColocat producteColocat : distribucion.getProductesColocats()) {
            if (producteColocat.getProducte().getNom().equals(nomProducte)) {
                targetProduct = producteColocat.getProducte();
                break;
            }
        }

        // Si no se encuentra el producto, retornamos una lista vacía o indicamos que no existe
        if (targetProduct == null) {
            similitudsList.add("El producto no existe en la distribucion.");
            return similitudsList;
        }

        // Recorrer todos los productos colocados para obtener las similitudes
        for (ProducteColocat producteColocat : distribucion.getProductesColocats()) {
            Producte currentProduct = producteColocat.getProducte();

            // Evitar comparar el producto consigo mismo
            if (!currentProduct.equals(targetProduct)) {
                int similitud = targetProduct.getSimilitud(currentProduct);
                similitudsList.add(currentProduct.getNom());        // Posición par: nombre del producto
                similitudsList.add(Integer.toString(similitud));    // Posición impar: similitud como string
            }
        }

        return similitudsList;
    }


    //Este metodo debe devolver una lista con los nombres de todos los productos en la distribucion.
    public ArrayList<String> getProductes() {
        ArrayList<String> productes = new ArrayList<>();
        for (ProducteColocat producteColocat : distribucion.getProductesColocats()) {
            productes.add(producteColocat.getProducte().getNom());
        }
        return productes;
    }

    public void set_similitud(String nomProducte1, String nomProducte2, int similitudValue) {
        Producte producte1 = null;
        Producte producte2 = null;

        for (ProducteColocat producteColocat : distribucion.getProductesColocats()) {
            Producte producte = producteColocat.getProducte();
            if (producte.getNom().equals(nomProducte1)) producte1 = producte;
            if (producte.getNom().equals(nomProducte2)) producte2 = producte;
        }

        if (producte1 != null && producte2 != null) {
            producte1.setSimilitud(producte2, similitudValue);
        }
    }



    //Este metodo verifica si un producto existe en la distribucion.
    public boolean existeix_producte(String nombre) {
        for (ProducteColocat producteColocat : distribucion.getProductesColocats()) {
            if (producteColocat.getProducte().getNom().equals(nombre)) return true;
        }
        return false;
    }

    //Este metodo elimina un producto de la distribucion.
    public void eliminaProducte(String nomProducte) {
        distribucion.getProductesColocats().removeIf(producteColocat ->
                producteColocat.getProducte().getNom().equals(nomProducte)
        );
    }

    public ArrayList<String> get_info_producte(String producte) {
        for (ProducteColocat producteColocat : distribucion.getProductesColocats()) {
            if (producteColocat.getProducte().getNom().equals(producte)) {
                ArrayList<String> info = new ArrayList<>();
                info.add(producteColocat.getProducte().getNom());
                info.add(producteColocat.getProducte().getMarca());
                info.add(Double.toString(producteColocat.getProducte().getPreu()));
                info.add(Integer.toString(producteColocat.getProducte().getQuantitat()));
                info.add(Integer.toString(producteColocat.getPos()));
                info.add(Integer.toString(producteColocat.getAltura()));
                info.add(Boolean.toString(producteColocat.isManualmenteModificado()));
                return info;
            }
        }
        return null;
    }

    public void set_cantidad_producto(String nombre, int nuevaCantidad) {
        for (ProducteColocat producteColocat : distribucion.getProductesColocats()) {
            if (producteColocat.getProducte().getNom().equals(nombre)) {
                producteColocat.getProducte().setQuantitat(nuevaCantidad);
                break;
            }
        }
    }

    public void set_precio_producto(String nombre, double nuevoPrecio) {
        for (ProducteColocat producteColocat : distribucion.getProductesColocats()) {
            if (producteColocat.getProducte().getNom().equals(nombre)) {
                producteColocat.getProducte().setPreu(nuevoPrecio);
                break;
            }
        }
    }

    public void intercambiar_productes(int nombre1, int nombre2) {
        distribucion.intercambiar_productes(nombre1, nombre2);
    }

    public ArrayList<ProducteColocat> getProductesColocats() {
        return distribucion.getProductesColocats();
    }

    public void setProductesColocats(ArrayList<ProducteColocat> productesColocats) {
        distribucion.setProductesColocats(productesColocats);
    }

    public boolean isCambiosManualesRealizados() {
        return distribucion.isCambiosManualesRealizados();
    }

    public void setCambiosManualesRealizados(boolean cambiosManualesRealizados) {
        distribucion.setCambiosManualesRealizados(cambiosManualesRealizados);
    }
}
