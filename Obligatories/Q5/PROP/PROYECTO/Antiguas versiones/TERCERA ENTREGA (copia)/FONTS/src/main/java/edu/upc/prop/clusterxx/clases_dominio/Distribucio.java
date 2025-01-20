package edu.upc.prop.clusterxx.clases_dominio;

import edu.upc.prop.clusterxx.estrategias_calculo.CalculBackTracking;
import edu.upc.prop.clusterxx.estrategias_calculo.EstrategiaCalculo;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Distribucio implements Serializable {
    //Atributos de la clase Distribucio
    private String nombre;
    private int numero_prestatges;
    private boolean usar_cambios_fijos;
    //sera false cuando queremos ordenar todos por igual,
    // sino se quedaran fijos los productosColocats que tienen el bool fijo en true.

    //Relaciones de la clase Distribucio
    private ArrayList<ProducteColocat> productesColocats;
    private EstrategiaCalculo estrategiaCalculo;
    private int identificador_estrategia;


    //Constructora
    public Distribucio(String nom){
        this.nombre = nom;
        this.numero_prestatges = 0;
        this.productesColocats =  new ArrayList<>();
        this.usar_cambios_fijos = false;
        this.estrategiaCalculo = new CalculBackTracking();
        this.identificador_estrategia = 1; //pertenece al backtracking
    }

    public Distribucio(String nom, int numero_prestatge){
        this.nombre = nom;
        //ALERTA PARA QUE ESTA ESTO
        this.numero_prestatges = numero_prestatge;
        this.productesColocats =  new ArrayList<>();
        this.usar_cambios_fijos = false;
        this.estrategiaCalculo = new CalculBackTracking();
        this.identificador_estrategia = 1; //pertenece al identificador del backtracking, el 2 al TSP y el 3 al one to one
    }

    //Constructora por defecto
    public Distribucio(){
        this.numero_prestatges = 0;
        this.productesColocats =  new ArrayList<>();
        this.usar_cambios_fijos = false;
        this.estrategiaCalculo = new CalculBackTracking();
        this.identificador_estrategia = 1; //pertenece al identificador del backtracking

    }

    public EstrategiaCalculo getEstrategiaCalculo() {
        return estrategiaCalculo;
    }

    public int getIdentificador_estrategia(){
        return identificador_estrategia;
    }

    //devuelve el array de productes colocats que tiene asociado, si se ordenó con anterioridad,
    //este array ya esta ordeando y listo para imprimir en el controlador
    public ArrayList<ProducteColocat> getProductesColocats() {
        return productesColocats;
    }

    //Añade productoColocado con la posición introducida

    public void afegeix_producte_colocat(ProducteColocat producteColocat){
        productesColocats.add(producteColocat);
        numero_prestatges++;
    }

    //Devuelve el producto de la posicion = pos esto SOLO FUNCIONA SI ESTA ORDENADA LA LISTA
    public Producte obtenirProductePos(int pos) {
        //De forma alternativa se puede hacer una busqueda por_todo el array en el caso que no sea el mismo array
        //el que ordenamos que el que guardamos los productos colocats
        if (pos <= numero_prestatges && pos >= 0){
            return productesColocats.get(pos-1).getProducte();
        }
        return null;
    }


    public int getNumero_prestatges(){
        return numero_prestatges;
    }

    //aqui ya podria ser un objeto de entrada no un string
    public void elimina_producte(String producte){
        int pos_a_borrar = -1;
        for (int i = 0 ; i < this.productesColocats.size(); i++){
            if (this.productesColocats.get(i).getnom().equals(producte)){
                pos_a_borrar = i;
            }
        }
        if (pos_a_borrar != -1) this.productesColocats.remove(pos_a_borrar);
    }

    public void intercambiar_productes(int seleccion1, int seleccion2){
        if(seleccion1 < 0 || seleccion1 >= productesColocats.size() || seleccion2 < 0 || seleccion2 >= productesColocats.size()) return;
        ProducteColocat p1 = productesColocats.get(seleccion1);
        ProducteColocat p2 = productesColocats.get(seleccion2);
        int tempPos = p1.getPos();
        int tempAltura = p1.getAltura();
        p1.setPos(p2.getPos());
        p1.setAltura(p2.getAltura());
        p2.setPos(tempPos);
        p2.setAltura(tempAltura);
    }

    //en la capa de dominio debemos crear el objeto ProducteColocat y pasarselo para enlazarlo (navegabilidad)
    public void afegeixproducte(ProducteColocat producteColocat){
        productesColocats.add(producteColocat);
    }

    public void setCambiosManualesRealizados(boolean cambios_fijos) {
        this.usar_cambios_fijos = cambios_fijos;
    }

    public void setProductesColocats(ArrayList<ProducteColocat> productesColocats) {
        this.productesColocats = productesColocats;
    }

    public boolean isCambiosManualesRealizados() {
        return usar_cambios_fijos;
    }


//SI ESTA ORDENADA YA SERIA EL GRAFO Y NOS SERVIRIA PARA IMPRIMIR LA DISTRO, sino solo devuelve los productos desordeandos

    //calcula y asocia la distribucion
    public void calcula_distribucio(int altura){
        List<ProducteColocat> resultat = estrategiaCalculo.arrangeProductsBySimilarity(productesColocats,altura);
        ArrayList<ProducteColocat> temp = new ArrayList<>(resultat);
        productesColocats = temp;
    }

    //para canviar el algoritmo que se usa le enviamos desde el controlador de prestatgeria una clase de tipo
//- para calcular backtracking del tipo new CalculBackTracking();, del TSP enviamos new EstrategiaTSP(); y si es para el test de junit de integracion del sistema
//usamos la estrategia one to one que coloca el producto en la posicion en la que viene (no funciona solo para test) new EstrategiaOneToOne()
    public void canvia_estrategia_calculo(EstrategiaCalculo estrategia, int id_estrategia){
        this.estrategiaCalculo = estrategia;
        this.identificador_estrategia = id_estrategia;
    }

    public int getSimilitud(String producte11, String producte22) {
        ProducteColocat producte1 = null;
        Producte producte2 = null;
        for (int i = 0 ; i < this.productesColocats.size(); i++) {
            if (this.productesColocats.get(i).getnom().equals(producte11)) {
                producte1 = this.productesColocats.get(i);
            } else if (this.productesColocats.get(i).getnom().equals(producte22)) {
                producte2 = this.productesColocats.get(i).getProducte();
            }
        }
        return producte1.calculateSimilarity(producte2);

    }

}



