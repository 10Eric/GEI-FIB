package edu.upc.prop.clusterxx.clases_dominio;

import edu.upc.prop.clusterxx.estrategias_calculo.CalculBackTracking;
import edu.upc.prop.clusterxx.estrategias_calculo.EstrategiaCalculo;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Distribucio implements Serializable {
    private String nombre;
    private int numero_prestatges;
    private boolean usar_cambios_fijos;
    private ArrayList<ProducteColocat> productesColocats;
    private EstrategiaCalculo estrategiaCalculo;
    private int identificador_estrategia;

    /**
     * Construye una nueva Distribucio con el nombre especificado.
     *
     * @param nom el nombre de la distribución
     */
    public Distribucio(String nom) {
        this.nombre = nom;
        this.numero_prestatges = 0;
        this.productesColocats = new ArrayList<>();
        this.usar_cambios_fijos = false;
        this.estrategiaCalculo = new CalculBackTracking();
        this.identificador_estrategia = 1;
    }

    /**
     * Construye una nueva Distribucio con el nombre y número de estantes especificados.
     *
     * @param nom el nombre de la distribución
     * @param numero_prestatge el número de estantes
     */
    public Distribucio(String nom, int numero_prestatge) {
        this.nombre = nom;
        this.numero_prestatges = numero_prestatge;
        this.productesColocats = new ArrayList<>();
        this.usar_cambios_fijos = false;
        this.estrategiaCalculo = new CalculBackTracking();
        this.identificador_estrategia = 1;
    }

    /**
     * Construye una nueva Distribucio con valores por defecto.
     */
    public Distribucio() {
        this.numero_prestatges = 0;
        this.productesColocats = new ArrayList<>();
        this.usar_cambios_fijos = false;
        this.estrategiaCalculo = new CalculBackTracking();
        this.identificador_estrategia = 1;
    }

    /**
     * Devuelve la estrategia de cálculo.
     *
     * @return la estrategia de cálculo
     */
    public EstrategiaCalculo getEstrategiaCalculo() {
        return estrategiaCalculo;
    }

    /**
     * Devuelve el identificador de la estrategia.
     *
     * @return el identificador de la estrategia
     */
    public int getIdentificador_estrategia() {
        return identificador_estrategia;
    }

    /**
     * Devuelve la lista de productos colocados.
     *
     * @return la lista de productos colocados
     */
    public ArrayList<ProducteColocat> getProductesColocats() {
        return productesColocats;
    }

    /**
     * Añade un producto colocado a la distribución.
     *
     * @param producteColocat el producto colocado a añadir
     */
    public void afegeix_producte_colocat(ProducteColocat producteColocat) {
        productesColocats.add(producteColocat);
        numero_prestatges++;
    }

    /**
     * Devuelve el producto en la posición especificada.
     *
     * @param pos la posición del producto
     * @return el producto en la posición especificada, o null si la posición es inválida
     */
    public Producte obtenirProductePos(int pos) {
        if (pos <= numero_prestatges && pos >= 0) {
            return productesColocats.get(pos - 1).getProducte();
        }
        return null;
    }

    /**
     * Devuelve el número de estantes.
     *
     * @return el número de estantes
     */
    public int getNumero_prestatges() {
        return numero_prestatges;
    }

    /**
     * Elimina el producto con el nombre especificado de la distribución.
     *
     * @param producte el nombre del producto a eliminar
     */
    public void elimina_producte(String producte) {
        int pos_a_borrar = -1;
        for (int i = 0; i < this.productesColocats.size(); i++) {
            if (this.productesColocats.get(i).getnom().equals(producte)) {
                pos_a_borrar = i;
            }
        }
        if (pos_a_borrar != -1) this.productesColocats.remove(pos_a_borrar);
    }

    /**
     * Intercambia las posiciones de dos productos en la distribución.
     *
     * @param seleccion1 la posición del primer producto
     * @param seleccion2 la posición del segundo producto
     */
    public void intercambiar_productes(int seleccion1, int seleccion2) {
        if (seleccion1 < 0 || seleccion1 >= productesColocats.size() || seleccion2 < 0 || seleccion2 >= productesColocats.size()) return;
        ProducteColocat p1 = productesColocats.get(seleccion1);
        ProducteColocat p2 = productesColocats.get(seleccion2);
        int tempPos = p1.getPos();
        int tempAltura = p1.getAltura();
        p1.setPos(p2.getPos());
        p1.setAltura(p2.getAltura());
        p2.setPos(tempPos);
        p2.setAltura(tempAltura);
    }

    /**
     * Añade un producto colocado a la distribución.
     *
     * @param producteColocat el producto colocado a añadir
     */
    public void afegeixproducte(ProducteColocat producteColocat) {
        productesColocats.add(producteColocat);
    }

    /**
     * Establece si se han realizado cambios manuales.
     *
     * @param cambios_fijos true si se han realizado cambios manuales, false en caso contrario
     */
    public void setCambiosManualesRealizados(boolean cambios_fijos) {
        this.usar_cambios_fijos = cambios_fijos;
    }

    /**
     * Establece la lista de productos colocados.
     *
     * @param productesColocats la lista de productos colocados
     */
    public void setProductesColocats(ArrayList<ProducteColocat> productesColocats) {
        this.productesColocats = productesColocats;
    }

    /**
     * Devuelve si se han realizado cambios manuales.
     *
     * @return true si se han realizado cambios manuales, false en caso contrario
     */
    public boolean isCambiosManualesRealizados() {
        return usar_cambios_fijos;
    }

    /**
     * Calcula y asocia la distribución.
     *
     * @param altura la altura a utilizar en el cálculo
     */
    public void calcula_distribucio(int altura) {
        List<ProducteColocat> resultat = estrategiaCalculo.arrangeProductsBySimilarity(productesColocats, altura);
        ArrayList<ProducteColocat> temp = new ArrayList<>(resultat);
        productesColocats = temp;
    }

    /**
     * Cambia la estrategia de cálculo.
     *
     * @param estrategia la nueva estrategia de cálculo
     * @param id_estrategia el identificador de la nueva estrategia
     */
    public void canvia_estrategia_calculo(EstrategiaCalculo estrategia, int id_estrategia) {
        this.estrategiaCalculo = estrategia;
        this.identificador_estrategia = id_estrategia;
    }

    /**
     * Devuelve la similitud entre dos productos.
     *
     * @param producte11 el nombre del primer producto
     * @param producte22 el nombre del segundo producto
     * @return la similitud entre los dos productos
     */
    public int getSimilitud(String producte11, String producte22) {
        ProducteColocat producte1 = null;
        Producte producte2 = null;
        for (int i = 0; i < this.productesColocats.size(); i++) {
            if (this.productesColocats.get(i).getnom().equals(producte11)) {
                producte1 = this.productesColocats.get(i);
            } else if (this.productesColocats.get(i).getnom().equals(producte22)) {
                producte2 = this.productesColocats.get(i).getProducte();
            }
        }
        return producte1.calculateSimilarity(producte2);
    }
}



