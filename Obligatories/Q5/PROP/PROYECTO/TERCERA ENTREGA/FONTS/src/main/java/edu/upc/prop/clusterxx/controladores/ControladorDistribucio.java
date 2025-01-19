package edu.upc.prop.clusterxx.controladores;

import edu.upc.prop.clusterxx.clases_dominio.Distribucio;
import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;
import edu.upc.prop.clusterxx.clases_dominio.Producte;
import edu.upc.prop.clusterxx.clases_dominio.ProducteColocat;
import edu.upc.prop.clusterxx.estrategias_calculo.CalculBackTracking;
import edu.upc.prop.clusterxx.estrategias_calculo.EstrategiaTSP;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Esta clase controla la distribución de productos en una prestatgeria.
 */
public class ControladorDistribucio implements Serializable {
    private Distribucio distribucion;
    private Prestatgeria prestatgeria;

    /**
     * Constructor por defecto que crea una nueva distribución.
     */
    public ControladorDistribucio() {
        distribucion = new Distribucio();
    }

    /**
     * Constructor que crea una nueva distribución con el nombre especificado.
     *
     * @param nombreDistro el nombre de la distribución
     */
    public ControladorDistribucio(String nombreDistro) {
        distribucion = new Distribucio(nombreDistro);
    }

    /**
     * Calcula la distribución utilizando la estrategia especificada.
     *
     * @param estrategia el número de la estrategia (1 para backtracking, 2 para TSP)
     */
    public void calcula_distribucio(int estrategia) {
        int id = distribucion.getIdentificador_estrategia();
        int alturas = get_Prestatgeria().getAltura();
        if (id == 1 && estrategia == 2) {
            distribucion.canvia_estrategia_calculo(new EstrategiaTSP(), 2);
        } else if (id == 2 && estrategia == 1) {
            distribucion.canvia_estrategia_calculo(new CalculBackTracking(), 1);
        }
        distribucion.calcula_distribucio(alturas);
    }

    /**
     * Devuelve la matriz de distribución de productos.
     *
     * @return la matriz de distribución de productos
     */
    public String[][] getMatrizDistribucion() {
        ArrayList<ProducteColocat> resultat = distribucion.getProductesColocats();
        ArrayList<String> altura_medida = this.get_altura_medida();
        int medida = Integer.parseInt(altura_medida.get(1));
        int altura = Integer.parseInt(altura_medida.get(0));
        String[][] matrix = null;
        if (!resultat.isEmpty()) {
            matrix = new String[altura][medida];
            for (ProducteColocat producteColocat : resultat) {
                int row = (producteColocat.getPos() - 1) / medida;
                int col = (producteColocat.getPos() - 1) % medida;
                matrix[row][col] = producteColocat.getProducte().getNom();
            }
        }
        return matrix;
    }

    /**
     * Devuelve la altura y medida de la prestatgeria.
     *
     * @return una lista con la altura y medida de la prestatgeria
     */
    public ArrayList<String> get_altura_medida() {
        int medida;
        if (distribucion.getProductesColocats().size() % prestatgeria.getAltura() == 0) {
            medida = distribucion.getProductesColocats().size() / prestatgeria.getAltura();
        } else {
            medida = distribucion.getProductesColocats().size() / prestatgeria.getAltura() + 1;
        }
        ArrayList<String> resultat = new ArrayList<>();
        resultat.add(Integer.toString(prestatgeria.getAltura()));
        resultat.add(Integer.toString(medida));
        return resultat;
    }

    /**
     * Crea una distribución inicial con el nombre y la altura especificados.
     *
     * @param nom el nombre de la distribución
     * @param altura la altura de la prestatgeria
     */
    public void crea_distribucio_inicial(String nom, int altura) {
        distribucion = new Distribucio(nom);
        prestatgeria = new Prestatgeria("Default", altura);
    }

    /**
     * Crea una distribución inicial con la distribución y prestatgeria especificadas.
     *
     * @param distribucion la distribución
     * @param prestatgeria la prestatgeria
     */
    public void crear_inicial(Distribucio distribucion, Prestatgeria prestatgeria) {
        this.distribucion = distribucion;
        this.prestatgeria = prestatgeria;
    }

    /**
     * Crea una distribución inicial con el controlador de distribución y prestatgeria especificados.
     *
     * @param distribucion el controlador de distribución
     * @param prestatgeria el controlador de prestatgeria
     */
    public void crear_inicialCtrl(ControladorDistribucio distribucion, ControladorPrestatgeria prestatgeria) {
        this.distribucion = distribucion.getDistribucion();
        this.prestatgeria = prestatgeria.getPrestatgeria();
    }

    /**
     * Crea una distribución inicial con el controlador de prestatgeria especificado.
     *
     * @param prestatgeria el controlador de prestatgeria
     */
    public void crear_inicialCtrlPrestatgeria(ControladorPrestatgeria prestatgeria) {
        this.distribucion = prestatgeria.getDistribucion();
        this.prestatgeria = prestatgeria.getPrestatgeria();
    }

    /**
     * Establece el controlador de prestatgeria.
     *
     * @param prestatgeria el controlador de prestatgeria
     */
    public void setPrestatgeriaCtrl(ControladorPrestatgeria prestatgeria) {
        setPrestatgeria(prestatgeria.getPrestatgeria());
    }

    /**
     * Añade un producto a la distribución.
     *
     * @param nombre el nombre del producto
     * @param marca la marca del producto
     * @param precio el precio del producto
     * @param cantidad la cantidad del producto
     * @param pos la posición del producto
     * @param altura la altura del producto
     * @return true si el producto se añadió correctamente
     */
    public boolean afegeix_producte(String nombre, String marca, double precio, int cantidad, int pos, int altura) {
        Producte prod = new Producte(nombre, marca, precio, cantidad);
        ProducteColocat producteColocat = new ProducteColocat(pos, altura, prod);
        distribucion.afegeix_producte_colocat(producteColocat);
        return true;
    }

    /**
     * Devuelve una lista con las similitudes de un producto.
     *
     * @param nomProducte el nombre del producto
     * @return una lista con las similitudes del producto
     */
    public ArrayList<String> getSimilitudsProducte(String nomProducte) {
        ArrayList<String> similitudsList = new ArrayList<>();
        Producte targetProduct = null;
        for (ProducteColocat producteColocat : distribucion.getProductesColocats()) {
            if (producteColocat.getProducte().getNom().equals(nomProducte)) {
                targetProduct = producteColocat.getProducte();
                break;
            }
        }
        if (targetProduct == null) {
            similitudsList.add("El producto no existe en la distribucion.");
            return similitudsList;
        }
        for (ProducteColocat producteColocat : distribucion.getProductesColocats()) {
            Producte currentProduct = producteColocat.getProducte();
            if (!currentProduct.equals(targetProduct)) {
                int similitud = targetProduct.getSimilitud(currentProduct);
                similitudsList.add(currentProduct.getNom());
                similitudsList.add(Integer.toString(similitud));
            }
        }
        return similitudsList;
    }

    /**
     * Devuelve una lista con los nombres de todos los productos en la distribución.
     *
     * @return una lista con los nombres de todos los productos
     */
    public ArrayList<String> getProductes() {
        ArrayList<String> productes = new ArrayList<>();
        for (ProducteColocat producteColocat : distribucion.getProductesColocats()) {
            productes.add(producteColocat.getProducte().getNom());
        }
        return productes;
    }

    /**
     * Intercambia las posiciones de dos productos en la distribución.
     *
     * @param producte1 el nombre del primer producto
     * @param producte2 el nombre del segundo producto
     */
    public void intercanviaProductes(String producte1, String producte2) {
        ProducteColocat primerProducte = null;
        ProducteColocat segonProducte = null;
        for (ProducteColocat producteColocat : distribucion.getProductesColocats()) {
            if (producteColocat.getProducte().getNom().equals(producte1)) {
                primerProducte = producteColocat;
            } else if (producteColocat.getProducte().getNom().equals(producte2)) {
                segonProducte = producteColocat;
            }
            if (primerProducte != null && segonProducte != null) {
                break;
            }
        }
        int tempPos = primerProducte.getPos();
        primerProducte.setPos(segonProducte.getPos());
        segonProducte.setPos(tempPos);
    }

    /**
     * Devuelve el número de estantes en la distribución.
     *
     * @return el número de estantes
     */
    public int getNumero_prestatges() {
        return distribucion.getNumero_prestatges();
    }

    /**
     * Establece la similitud entre dos productos.
     *
     * @param nomProducte1 el nombre del primer producto
     * @param nomProducte2 el nombre del segundo producto
     * @param similitudValue el valor de la similitud
     */
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

    /**
     * Verifica si un producto existe en la distribución.
     *
     * @param nombre el nombre del producto
     * @return true si el producto existe, false en caso contrario
     */
    public boolean existeix_producte(String nombre) {
        for (ProducteColocat producteColocat : distribucion.getProductesColocats()) {
            if (producteColocat.getProducte().getNom().equals(nombre)) return true;
        }
        return false;
    }

    /**
     * Elimina un producto de la distribución.
     *
     * @param nomProducte el nombre del producto a eliminar
     */
    public void eliminaProducte(String nomProducte) {
        distribucion.getProductesColocats().removeIf(producteColocat ->
                producteColocat.getProducte().getNom().equals(nomProducte)
        );
        int pos_ant = 0;
        for (ProducteColocat producteColocat : distribucion.getProductesColocats()) {
            if (producteColocat.getPos() == pos_ant + 2) {
                producteColocat.setPos(producteColocat.getPos() - 1);
            }
            pos_ant = producteColocat.getPos();
        }
    }

    /**
     * Devuelve la información de un producto.
     *
     * @param producte el nombre del producto
     * @return una lista con la información del producto
     */
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

    /**
     * Establece la cantidad de un producto.
     *
     * @param nombre el nombre del producto
     * @param nuevaCantidad la nueva cantidad del producto
     */
    public void set_cantidad_producto(String nombre, int nuevaCantidad) {
        for (ProducteColocat producteColocat : distribucion.getProductesColocats()) {
            if (producteColocat.getProducte().getNom().equals(nombre)) {
                producteColocat.getProducte().setQuantitat(nuevaCantidad);
                break;
            }
        }
    }

    /**
     * Establece el precio de un producto.
     *
     * @param nombre el nombre del producto
     * @param nuevoPrecio el nuevo precio del producto
     */
    public void set_precio_producto(String nombre, double nuevoPrecio) {
        for (ProducteColocat producteColocat : distribucion.getProductesColocats()) {
            if (producteColocat.getProducte().getNom().equals(nombre)) {
                producteColocat.getProducte().setPreu(nuevoPrecio);
                break;
            }
        }
    }

    /**
     * Devuelve la lista de productos colocados.
     *
     * @return la lista de productos colocados
     */
    public ArrayList<ProducteColocat> getProductesColocats() {
        return distribucion.getProductesColocats();
    }

    /**
     * Establece la lista de productos colocados.
     *
     * @param productesColocats la nueva lista de productos colocados
     */
    public void setProductesColocats(ArrayList<ProducteColocat> productesColocats) {
        distribucion.setProductesColocats(productesColocats);
    }

    /**
     * Devuelve si se han realizado cambios manuales.
     *
     * @return true si se han realizado cambios manuales, false en caso contrario
     */
    public boolean isCambiosManualesRealizados() {
        return distribucion.isCambiosManualesRealizados();
    }

    /**
     * Establece si se han realizado cambios manuales.
     *
     * @param cambiosManualesRealizados true si se han realizado cambios manuales, false en caso contrario
     */
    public void setCambiosManualesRealizados(boolean cambiosManualesRealizados) {
        distribucion.setCambiosManualesRealizados(cambiosManualesRealizados);
    }

    /**
     * Intercambia las posiciones de dos productos en la distribución.
     *
     * @param nombre1 la posición del primer producto
     * @param nombre2 la posición del segundo producto
     */
    public void intercambiar_productes(int nombre1, int nombre2) {
        distribucion.intercambiar_productes(nombre1, nombre2);
    }

    /**
     * Devuelve la prestatgeria.
     *
     * @return la prestatgeria
     */
    public Prestatgeria get_Prestatgeria() {
        return prestatgeria;
    }

    /**
     * Establece la prestatgeria.
     *
     * @param prestatgeria la nueva prestatgeria
     */
    public void setPrestatgeria(Prestatgeria prestatgeria) {
        this.prestatgeria = prestatgeria;
    }

    /**
     * Devuelve la distribución.
     *
     * @return la distribución
     */
    public Distribucio getDistribucion() {
        return distribucion;
    }

    /**
     * Devuelve la similitud entre dos productos.
     *
     * @param producte1 el nombre del primer producto
     * @param producte2 el nombre del segundo producto
     * @return la similitud entre los dos productos
     */
    public int getSimilitud(String producte1, String producte2) {
        return distribucion.getSimilitud(producte1, producte2);
    }
}
