package edu.upc.prop.clusterxx;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class New_Controlador_Prestatgeria {
    private New_Prestatgeria prestatgeria;



    //Constructora default que crearemos al iniciar la ejecución.
    public New_Controlador_Prestatgeria() {
        prestatgeria = new New_Prestatgeria();
    }

    public List<ProducteColocat> arrangeProductsBySimilarity(List<ProducteColocat> productes, int altura, boolean mantenerCambios) {
        return prestatgeria.getEstrategiaCalculo().arrangeProductsBySimilarity(productes,altura, mantenerCambios);
    }



    public void calcula_distribucio(boolean mantenerCambios){
        //la parte de imprimir la controla el driver, pedira imprimir al principio y al final del calculo,
        //esto solo debe calcular


    }

    //nos viene en info nombre, marca, precio(double), cantidad,posicion, altura, modificado
    public boolean afegeix_producte(String nombre, String marca, double precio, int cantidad, int pos, int altura){
        Producte prod = new Producte(nombre,marca,precio,cantidad);
        ProducteColocat producteColocat = new ProducteColocat(pos,altura,prod);
        prestatgeria.afegeix_producte_colocat(producteColocat);
        return true;
    }

    public ArrayList<String> getSimilitudsProducte(String nomProducte) {
        ArrayList<String> similitudsList = new ArrayList<>();

        // Buscar el producto por nombre en la prestatgeria
        Producte targetProduct = null;
        for (ProducteColocat producteColocat : prestatgeria.getProductesColocats()) {
            if (producteColocat.getProducte().getNom().equals(nomProducte)) {
                targetProduct = producteColocat.getProducte();
                break;
            }
        }

        // Si no se encuentra el producto, retornamos una lista vacía o indicamos que no existe
        if (targetProduct == null) {
            similitudsList.add("El producto no existe en la prestatgeria.");
            return similitudsList;
        }

        // Recorrer todos los productos colocados para obtener las similitudes
        for (ProducteColocat producteColocat : prestatgeria.getProductesColocats()) {
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


    //Este metodo debe devolver una lista con los nombres de todos los productos en la prestatgeria.
    public ArrayList<String> getProductes() {
        ArrayList<String> productes = new ArrayList<>();
        for (ProducteColocat producteColocat : prestatgeria.getProductesColocats()) {
            productes.add(producteColocat.getProducte().getNom());
        }
        return productes;
    }

    public void set_similitud(String nomProducte1, String nomProducte2, int similitudValue) {
        Producte producte1 = null;
        Producte producte2 = null;

        for (ProducteColocat producteColocat : prestatgeria.getProductesColocats()) {
            Producte producte = producteColocat.getProducte();
            if (producte.getNom().equals(nomProducte1)) producte1 = producte;
            if (producte.getNom().equals(nomProducte2)) producte2 = producte;
        }

        if (producte1 != null && producte2 != null) {
            producte1.setSimilitud(producte2, similitudValue);
        }
    }

    public String getNombreDeproductes() {
        return Integer.toString(prestatgeria.getAltura());
    }

    public Integer getAltura() {
        return prestatgeria.getAltura();
    }

    public void setAltura(int altura) {
        prestatgeria.setAltura(altura);
    }

    //Este metodo verifica si un producto existe en la prestatgeria.
    public boolean existeix_producte(String nombre) {
        for (ProducteColocat producteColocat : prestatgeria.getProductesColocats()) {
            if (producteColocat.getProducte().getNom().equals(nombre)) return true;
        }
        return false;
    }

    //Este metodo elimina un producto de la prestatgeria.
    public void eliminaProducte(String nomProducte) {
        prestatgeria.getProductesColocats().removeIf(producteColocat ->
                producteColocat.getProducte().getNom().equals(nomProducte)
        );
    }

    public ArrayList<String> get_info_producte(String producte) {
        for (ProducteColocat producteColocat : prestatgeria.getProductesColocats()) {
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
        for (ProducteColocat producteColocat : prestatgeria.getProductesColocats()) {
            if (producteColocat.getProducte().getNom().equals(nombre)) {
                producteColocat.getProducte().setQuantitat(nuevaCantidad);
                break;
            }
        }
    }

    public void set_precio_producto(String nombre, double nuevoPrecio) {
        for (ProducteColocat producteColocat : prestatgeria.getProductesColocats()) {
            if (producteColocat.getProducte().getNom().equals(nombre)) {
                producteColocat.getProducte().setPreu(nuevoPrecio);
                break;
            }
        }
    }

    public void intercambiar_productes(int nombre1, int nombre2) {
        prestatgeria.intercambiar_productes(nombre1, nombre2);
    }

    public ArrayList<ProducteColocat> getProductesColocats() {
        return prestatgeria.getProductesColocats();
    }

    public void setProductesColocats(ArrayList<ProducteColocat> productesColocats) {
        prestatgeria.setProductesColocats(productesColocats);
    }

    public boolean isCambiosManualesRealizados() {
        return prestatgeria.isCambiosManualesRealizados();
    }

    public void setCambiosManualesRealizados(boolean cambiosManualesRealizados) {
        prestatgeria.setCambiosManualesRealizados(cambiosManualesRealizados);
    }

    /*public String[][] getDistribucio() {
    }*/
}
